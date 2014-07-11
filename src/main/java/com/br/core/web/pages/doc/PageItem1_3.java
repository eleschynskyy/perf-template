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

public class PageItem1_3 extends WebPage<PageItem1_3> {

	private static final String DOCUMENT_URL = XPE_DOCUMENT_URL;
	private static final String PAGE_URL = BASE_URL + DOCUMENT_URL + "#Item1.3";

	public PageItem1_3(WebDriver driver) {
		super(driver);
	}

	@Override
	public PageItem1_3 load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		return getNextButton().isAvailable() &&
			   getPreviousButton().isAvailable() &&
			   getExpectedElement().isAvailable();
	}
	
	private Text getExpectedElement() {
		return new Text(driver, By.xpath("//div[contains(text(),'Global') and @class='TableTitle']"));
	}

	private Button getNextButton() {
		return new Button(driver, By.xpath("//button[@id='FooterNavigationNext']"));
	}
	
	private Button getPreviousButton() {
		return new Button(driver, By.xpath("//button[@id='FooterNavigationPrevious']"));
	}

	public PageItem1_3 navigateToNextPage() {
		getNextButton().click();
		//TODO
		return new PageItem1_3(driver);
	}

}
