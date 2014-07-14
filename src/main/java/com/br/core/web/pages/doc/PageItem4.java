package com.br.core.web.pages.doc;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.br.core.web.Component;
import com.br.core.web.WebPage;
import com.br.core.web.elements.Button;
import com.br.core.web.elements.CustomElement;
import com.br.core.web.elements.Image;
import com.br.core.web.elements.Text;
import com.br.utils.ConfigProperties;

public class PageItem4 extends WebPage<PageItem4> {

	private static final String DOCUMENT_URL = XPE_DOCUMENT_URL;
	private static final String PAGE_URL = BASE_URL + DOCUMENT_URL + "#Item1.3.2";

	public PageItem4(WebDriver driver, String description) {
		super(driver, description);
	}

	@Override
	public PageItem4 load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		return getNextButton().isAvailable() &&
			   getPreviousButton().isAvailable() &&
			   getExpectedElement().waitUntilAvailable().isAvailable() &&
			   getExpectedElement().isFullyVisible();
	}
	
	private Image getExpectedElement() {
		return new Image(driver, By.xpath("//img[@src='" + BASE_HOST + "/media-service/Standard/Photo/Solar_System/terr_sizes.png']"), "terr_sizes.png");
	}

	private Button getNextButton() {
		return new Button(driver, By.xpath("//button[@id='FooterNavigationNext']"), "Next button");
	}
	
	private Button getPreviousButton() {
		return new Button(driver, By.xpath("//button[@id='FooterNavigationPrevious']"), "Previous button");
	}

	public PageItem5 navigateToNextPage() {
		getNextButton().click();
		return new PageItem5(driver, "5th page");
	}
	
	public PageItem3 navigateToPreviousPage() {
		getPreviousButton().click();
		return new PageItem3(driver, "Page #3"); 
	}

}
