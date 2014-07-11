package com.br.core.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.br.core.web.WebPage;
import com.br.core.web.elements.CustomElement;

public class HomePage extends WebPage<HomePage> {

	private static final String PAGE_URL = LCMS_HOME_PAGE_URL;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public HomePage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		return getLogoutLogo().isAvailable();
	}

	private CustomElement getLogoutLogo() {
		return new CustomElement(driver, By.xpath("//img[contains(@src,'btnLogoutOff.png')]"));
	}

}
