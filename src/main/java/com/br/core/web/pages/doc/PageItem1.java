package com.br.core.web.pages.doc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.br.core.web.WebPage;
import com.br.core.web.elements.Button;
import com.br.core.web.elements.CustomElement;
import com.br.utils.TestStepReporter;

public class PageItem1 extends WebPage<PageItem1> {

	private static final String DOCUMENT_URL = XPE_DOCUMENT_URL;
	private static final String PAGE_URL = BASE_URL + DOCUMENT_URL + "#Item1.1";

	public PageItem1(WebDriver driver, String description) {
		super(driver, description);
	}

	@Override
	public PageItem1 load() {
		long start = System.currentTimeMillis();
		driver.get(PAGE_URL);
		long end = System.currentTimeMillis();
		TestStepReporter.reportln("PAGE1 LOADED: " + "'" + description + "': " + (end - start) + "ms");
		return this;
	}

	@Override
	public boolean isAvailable() {
		return getNextButton().isAvailable() &&
			   getPreviousButton().isAvailable() &&
			   getEmbeddedMovie().waitUntilAvailable().isAvailable();
	}
	
	/*
	@Override
	public DocPage waitUntilAvailable() {
		return null;
	}
	*/
	
	public PageItem2 navigateToNextPage() {
		long start = System.currentTimeMillis();
		getNextButton().click();
		long end = System.currentTimeMillis();
		TestStepReporter.reportln("navigateToNextPage(): " + "'" + description + "': " + (end - start) + "ms");
		return new PageItem2(driver, "Page #2");
	}

	private CustomElement getEmbeddedMovie() {
		return new CustomElement(driver, By.xpath("//embed[contains(@src,'https://qa.xyleme.com:10580/media-service/Standard/Video/Solar_System/what_is_a_planet_360.mov')]"), "Embedded video");
	}
	
	private Button getNextButton() {
		return new Button(driver, By.xpath("//button[@id='FooterNavigationNext']"), "Next button");
	}
	
	private Button getPreviousButton() {
		return new Button(driver, By.xpath("//button[@id='FooterNavigationPrevious']"), "Previous button");
	}

}
