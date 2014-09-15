package com.br.core.web.pages.doc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.br.core.web.WebPage;
import com.br.core.web.elements.Button;
import com.br.core.web.elements.Text;

public class PageItem3 extends WebPage<PageItem3> {

	private static final String DOCUMENT_URL = XPE_DOCUMENT_URL;
	private static final String PAGE_URL = BASE_XPE_HOST + DOCUMENT_URL + "#Item1.3.1";

	public PageItem3(WebDriver driver, String description) {
		super(driver, description);
	}

	@Override
	public PageItem3 load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		return getNextButton().isAvailable() &&
			   getPreviousButton().isAvailable() &&
			   getExpectedElement().waitUntilAvailable().isAvailable();
	}
	
	private Text getExpectedElement() {
		return new Text(driver, By.xpath("//div[contains(text(),'Global') and @class='TableTitle']"), "Text element");
	}

	private Button getNextButton() {
		return new Button(driver, By.xpath("//button[@id='FooterNavigationNext']"), "Next button");
	}
	
	private Button getPreviousButton() {
		return new Button(driver, By.xpath("//button[@id='FooterNavigationPrevious']"), "Previous button");
	}

	public PageItem4 navigateToNextPage() {
		getNextButton().click();
		return new PageItem4(driver, "Page #4");
	}
	
	public PageItem2 navigateToPreviousPage() {
		getPreviousButton().click();
		return new PageItem2(driver, "Page #2"); 
	}

}
