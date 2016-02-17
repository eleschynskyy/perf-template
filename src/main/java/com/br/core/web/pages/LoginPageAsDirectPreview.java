package com.br.core.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.br.core.web.WebPage;
import com.br.core.web.elements.Button;
import com.br.core.web.elements.TextInput;
import com.br.data.objects.User;

public class LoginPageAsDirectPreview extends WebPage<LoginPageAsDirectPreview> {

	private static final String PAGE_URL = BASE_XPE_HOST + "/" + LCMS_INSTANCE
			+ "/preview/" + FILE_GUID + "/" + OUTPUT_PROFILE + "/env/"
			+ OUTPUT_ENV + "/page/index.html";

	public LoginPageAsDirectPreview(WebDriver driver, String description) {
		super(driver, description);
		loadAndWaitUntilAvailable();
		// TestStepReporter.reportln("LOGIN PAGE LOADED: " +
		// System.currentTimeMillis());
	}

	@Override
	public LoginPageAsDirectPreview load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		return getUsernameInput().waitUntilAvailable().isAvailable()
				&& getPasswordInput().waitUntilAvailable().isAvailable()
				&& getLoginButton().waitUntilAvailable().isAvailable();
	}

	public PreviewCoursePage loginAs(User user) {
		fillFormAndClick(user);
		return new PreviewCoursePage(driver, "Preview course");
	}

	private TextInput getUsernameInput() {
		return new TextInput(driver, By.id("username"), "Username input");
	}

	private TextInput getPasswordInput() {
		return new TextInput(driver, By.id("password"), "Password input");
	}

	private Button getLoginButton() {
		return new Button(
				driver,
				By.xpath("//input[@type='image' and contains(@src, 'images/btnSubmitOff.png')]"),
				"Login button");
	}

	private void fillFormAndClick(User user) {
		// TestStepReporter.reportln("START POPULATING CREDENTIALS: " +
		// System.currentTimeMillis());
		getUsernameInput().inputText(user.getUsername());
		// TestStepReporter.reportln("USERNAME POPULATED: " +
		// System.currentTimeMillis());
		getPasswordInput().inputText(user.getPassword());
		// TestStepReporter.reportln("PASSWORD POPULATED: " +
		// System.currentTimeMillis());
		getLoginButton().click();
		// TestStepReporter.reportln("LOGIN BUTTON CLICKED: " +
		// System.currentTimeMillis());
	}

}
