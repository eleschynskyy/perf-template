package com.br.core.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.br.core.web.WebPage;
import com.br.core.web.elements.CustomElement;
import com.br.utils.ConfigProperties;

public class DocPage extends WebPage<DocPage> {

	private static final String DOCUMENT_URL = ConfigProperties.getSystemProperties("xpe.doc.url");
	private static final String PAGE_URL = BASE_URL + DOCUMENT_URL;

	public DocPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public DocPage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		return getEmbeddedMovie().isAvailable();
	}

	private CustomElement getEmbeddedMovie() {
		return new CustomElement(driver, By.xpath("//embed[contains(@src,'https://qa.xyleme.com:10580/media-service/Standard/Video/Solar_System/what_is_a_planet_360.mov')]"));
	}

}
