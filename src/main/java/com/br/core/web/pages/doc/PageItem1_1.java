package com.br.core.web.pages.doc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.br.core.web.WebPage;
import com.br.core.web.elements.Button;
import com.br.core.web.elements.CustomElement;
import com.br.utils.ConfigProperties;

public class PageItem1_1 extends WebPage<PageItem1_1> {

	private static final String DOCUMENT_URL = XPE_DOCUMENT_URL;
	private static final String PAGE_URL = BASE_URL + DOCUMENT_URL + "#Item1.1";

	public PageItem1_1(WebDriver driver) {
		super(driver);
	}

	@Override
	public PageItem1_1 load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		return getNextButton().isAvailable() &&
			   getPreviousButton().isAvailable() &&
			   getEmbeddedMovie().isAvailable();
	}
	
	/*
	@Override
	public DocPage waitUntilAvailable() {
		return null;
	}
	*/
	
	public PageItem1_2 navigateToNextPage() {
		getNextButton().click();
		return new PageItem1_2(driver);
	}

	private CustomElement getEmbeddedMovie() {
		return new CustomElement(driver, By.xpath("//embed[contains(@src,'https://qa.xyleme.com:10580/media-service/Standard/Video/Solar_System/what_is_a_planet_360.mov')]"));
	}
	
	private Button getNextButton() {
		return new Button(driver, By.xpath("//button[@id='FooterNavigationNext']"));
	}
	
	private Button getPreviousButton() {
		return new Button(driver, By.xpath("//button[@id='FooterNavigationPrevious']"));
	}

}
