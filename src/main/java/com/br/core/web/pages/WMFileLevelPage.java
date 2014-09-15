package com.br.core.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.br.core.web.WebPage;
import com.br.core.web.elements.CustomElement;

public class WMFileLevelPage extends WebPage<WMFileLevelPage> {
	
	private static final String PAGE_URL = BASE_XPE_HOST + "/" + LCMS_INSTANCE + "/admin/documents/files/" + TEST_FOLDER;

	public WMFileLevelPage(WebDriver driver, String description) {
		super(driver, description);
		waitUntilAvailable();
//		TestStepReporter.reportln("LOGIN PAGE LOADED: " + System.currentTimeMillis());
	}

	@Override
	public WMFileLevelPage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		 return getCourseElement().isAvailable();
	}
	
	public WMPreviewFormPage clickOnCourseToPreview() {
		getCourseElement().click();
		return new WMPreviewFormPage(driver, "Preview form");
	}
	
	private CustomElement getCourseElement() {
		String testCourseXpath = "//a[@href='documents/files/" + TEST_FOLDER + "?guid=" + FILE_GUID + "']";
		return new CustomElement(driver, By.xpath(testCourseXpath), "Test course");
	}

}
