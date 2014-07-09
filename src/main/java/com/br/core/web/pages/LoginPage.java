package com.br.core.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.br.core.web.WebPage;
import com.br.core.web.elements.Button;
import com.br.core.web.elements.TextInput;
import com.br.data.objects.User;
import com.br.utils.ConfigProperties;

public class LoginPage extends WebPage<LoginPage> {

	private static final String PAGE_URL = ConfigProperties.getSystemProperties("lcms.login");

	public LoginPage(WebDriver driver) {
		super(driver);
		loadAndWaitUntilAvailable();
	}

	@Override
	public LoginPage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		 return getUsernameInput().isAvailable() &&
		 getPasswordInput().isAvailable() &&
		 getLoginButton().isAvailable();
	}

	public HomePage loginWithRightCredentialsAs(User user) {
		fillFormAndClick(user);
		return new HomePage(driver).waitUntilAvailable();
	}

	private TextInput getUsernameInput() {
		return new TextInput(driver, By.id("j_username"));
	}

	private TextInput getPasswordInput() {
		return new TextInput(driver, By.id("j_password"));
	}

	private Button getLoginButton() {
		return new Button(driver,
				By.xpath("//input[@type='image' and contains(@id, 'loginForm')]"));
	}

	private void fillFormAndClick(User user) {
		getUsernameInput().inputText(user.getUsername());
		getPasswordInput().inputText(user.getPassword());
		getLoginButton().click();
	}

}
