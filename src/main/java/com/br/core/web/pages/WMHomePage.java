package com.br.core.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.br.core.web.WebPage;
import com.br.core.web.elements.CustomElement;

public class WMHomePage extends WebPage<WMHomePage> {

	private static final String PAGE_URL = BASE_XPE_HOST + "/" + LCMS_INSTANCE + "/admin" ;

	public WMHomePage(WebDriver driver, String description) {
		super(driver, description);
		waitUntilAvailable();
//		TestStepReporter.reportln("LOGIN PAGE LOADED: " + System.currentTimeMillis());
	}

	@Override
	public WMHomePage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		 return getTestFolder().isAvailable();
	}

	private CustomElement getTestFolder() {
		String testFolderXpath = "//a[@href='documents/files/" + TEST_FOLDER + "']";
		return new CustomElement(driver, By.xpath(testFolderXpath), "Test folder");
	}

	public WMFileLevelPage navigateToTestFolder() {
		getTestFolder().click();
		return new WMFileLevelPage(driver, "File Level");
	}

}
