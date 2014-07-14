package com.br.core.web.pages.doc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.br.core.web.Component;
import com.br.core.web.WebPage;
import com.br.core.web.elements.Button;
import com.br.core.web.elements.CustomElement;
import com.br.utils.TestStepReporter;

public class PageItem5 extends WebPage<PageItem5> {

	private static final String DOCUMENT_URL = XPE_DOCUMENT_URL;
	private static final String PAGE_URL = BASE_URL + DOCUMENT_URL + "#Item1.3.3.1";

	public PageItem5(WebDriver driver, String description) {
		super(driver, description);
	}

	@Override
	public PageItem5 load() {
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
			   getEmbeddedMovie().waitUntilAvailable().isAvailable();
	}
	
	/*
	@Override
	public DocPage waitUntilAvailable() {
		return null;
	}
	*/
	
	private CustomElement getEmbeddedMovie() {
		return new CustomElement(driver, By.xpath("//embed[contains(@src,'/media-service/Standard/Video/Solar_System/The_Inner_Planet_Mercury.mov')]"), "Embedded video");
	}
	
	private Button getNextButton() {
		return new Button(driver, By.xpath("//button[@id='FooterNavigationNext']"), "Next button");
	}
	
	private Button getPreviousButton() {
		return new Button(driver, By.xpath("//button[@id='FooterNavigationPrevious']"), "Previous button");
	}
	
	public PageItem5 navigateToNextPage() {
		//TODO
		getNextButton().click();
		return new PageItem5(driver, "Page #6");
	}

	public PageItem4 navigateToPreviousPage() {
		getPreviousButton().click();
		return new PageItem4(driver, "Page #4"); 
	}

}
