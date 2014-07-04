package com.br.core.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.br.core.web.WebPage;
import com.br.core.web.elements.Button;
import com.br.core.web.elements.TextInput;
import com.br.data.objects.User;

public class LoginPage extends WebPage<LoginPage> {

	private static final String PAGE_URL = BASE_URL;

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
		return new HomePage(driver, user).waitUntilAvailable();
	}

	private TextInput getUsernameInput() {
		return new TextInput(driver, By.id("username"));
	}

	private TextInput getPasswordInput() {
		return new TextInput(driver, By.name("password"));
	}

	private Button getLoginButton() {
		return new Button(driver,
				By.xpath("//button[@class='btn btn-success']"));
	}

	private void fillFormAndClick(User user) {
		getUsernameInput().inputText(user.getUsername());
		getPasswordInput().inputText(user.getPassword());
		getLoginButton().click();
	}

}
