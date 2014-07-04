package com.br.core.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.br.core.web.WebPage;
import com.br.core.web.elements.Text;
import com.br.data.objects.User;

public class RootDocumentsPage extends WebPage<RootDocumentsPage> {
	
	private static final String PAGE_URL = BASE_URL + "/documents";

	public RootDocumentsPage(WebDriver driver) {
		super(driver);
		waitUntilAvailable();
	}

	@Override
	public RootDocumentsPage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		return getDocumentsTitle().isAvailable();
	}
	
	private Text getDocumentsTitle() {
		return new Text(driver, By.xpath("//h1[contains(text(),'Documents') and ancestor::div[@class='row list-header']]"));
	}

	private void fillFormAndClick(User user) {
	}

	public void selectAllDocumentsWithTitle(String linkText) {
		//getDocumentWith
	}
	
}
