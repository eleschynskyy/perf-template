package com.br.core.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.br.core.web.WebPage;
import com.br.core.web.elements.Button;
import com.br.core.web.elements.TextInput;
import com.br.data.objects.User;
import com.br.utils.TestStepReporter;

public class LoginPage extends WebPage<LoginPage> {

	private static final String PAGE_URL = LCMS_LOGIN_PAGE_URL;
	private static final String HOME_PAGE = "Home Page";

	public LoginPage(WebDriver driver, String description) {
		super(driver, description);
		loadAndWaitUntilAvailable();
//		TestStepReporter.reportln("LOGIN PAGE LOADED: " + System.currentTimeMillis());
	}

	@Override
	public LoginPage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		 return getUsernameInput().waitUntilAvailable().isAvailable() &&
		 getPasswordInput().waitUntilAvailable().isAvailable() &&
		 getLoginButton().waitUntilAvailable().isAvailable();
	}

	public HomePage loginWithRightCredentialsAs(User user) {
		fillFormAndClick(user);
		return new HomePage(driver, HOME_PAGE).waitUntilAvailable();
	}

	private TextInput getUsernameInput() {
		return new TextInput(driver, By.id("j_username"), "Username input");
	}

	private TextInput getPasswordInput() {
		return new TextInput(driver, By.id("j_password"), "Password input");
	}

	private Button getLoginButton() {
		return new Button(driver,
				By.xpath("//input[@type='image' and contains(@id, 'loginForm')]"), "Login button");
	}

	private void fillFormAndClick(User user) {
//		TestStepReporter.reportln("START POPULATING CREDENTIALS: " + System.currentTimeMillis());
		getUsernameInput().inputText(user.getUsername());
//		TestStepReporter.reportln("USERNAME POPULATED: " + System.currentTimeMillis());
		getPasswordInput().inputText(user.getPassword());
//		TestStepReporter.reportln("PASSWORD POPULATED: " + System.currentTimeMillis());
		getLoginButton().click();
//		TestStepReporter.reportln("LOGIN BUTTON CLICKED: " + System.currentTimeMillis());
	}

}
