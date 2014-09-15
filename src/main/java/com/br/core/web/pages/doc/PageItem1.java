package com.br.core.web.pages.doc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.br.core.web.WebPage;
import com.br.core.web.elements.Button;
import com.br.core.web.elements.CustomElement;
import com.br.utils.TestStepReporter;

public class PageItem1 extends WebPage<PageItem1> {

	private static final String DOCUMENT_URL = XPE_DOCUMENT_URL;
	private static final String PAGE_URL = BASE_XPE_HOST + DOCUMENT_URL + "#Item1.1";

	public PageItem1(WebDriver driver, String description) {
		super(driver, description);
	}

	@Override
	public PageItem1 load() {
		long start = System.currentTimeMillis();
		driver.get(PAGE_URL);
		long end = System.currentTimeMillis();
//		TestStepReporter.reportln("PAGE1 LOADED: " + "'" + description + "': " + (end - start) + "ms");
		return this;
	}

	@Override
	public boolean isAvailable() {
		return getNextButton().isAvailable() &&
			   getPreviousButton().isAvailable() &&
			   getCustomElement().waitUntilAvailable().isAvailable();
	}
	
	public PageItem2 navigateToNextPage() {
		long start = System.currentTimeMillis();
		getNextButton().click();
		long end = System.currentTimeMillis();
//		TestStepReporter.reportln("navigateToNextPage(): " + "'" + description + "': " + (end - start) + "ms");
		return new PageItem2(driver, "Page #2");
	}

	private CustomElement getCustomElement() {
		return new CustomElement(driver, By.xpath("//img[contains(@src,'/media-service/Standard/Photo/Solar_System/OSS_732x400.jpg')]"), "OSS_732x400.jpg");
	}
	
	private Button getNextButton() {
		return new Button(driver, By.xpath("//button[@id='FooterNavigationNext']"), "Next button");
	}
	
	private Button getPreviousButton() {
		return new Button(driver, By.xpath("//button[@id='FooterNavigationPrevious']"), "Previous button");
	}

}
