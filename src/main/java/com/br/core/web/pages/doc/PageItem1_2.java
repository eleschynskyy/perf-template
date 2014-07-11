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
import com.br.utils.ConfigProperties;

public class PageItem1_2 extends WebPage<PageItem1_2> {

	private static final String DOCUMENT_URL = XPE_DOCUMENT_URL;
	private static final String PAGE_URL = BASE_URL + DOCUMENT_URL + "#Item1.2";
	private static final String BASE_PLANET_XPATH = "//img[contains(@src,'https://qa.xyleme.com:10580/media-service/Standard/Photo/Solar_System/";
	private static final String NEPTUNE_XPATH = BASE_PLANET_XPATH + "neptunea" + ".png')]";
	private static final String URANUS_XPATH = BASE_PLANET_XPATH + "uranus" + ".png')]";
	private static final String SATURN_XPATH = BASE_PLANET_XPATH + "saturn_rings" + ".png')]";
	private static final String JUPITER_XPATH = BASE_PLANET_XPATH + "jupiter" + ".png')]";
	private static final String MARS_XPATH = BASE_PLANET_XPATH + "marswithclouds" + ".png')]";
	private static final String EARTH_XPATH = BASE_PLANET_XPATH + "bluemarblewest" + ".png')]";
	private static final String VENUS_XPATH = BASE_PLANET_XPATH + "venusint" + ".png')]";
	private static final String MERCURY_XPATH = BASE_PLANET_XPATH + "mercury_am" + ".png')]";

	public PageItem1_2(WebDriver driver) {
		super(driver);
	}

	@Override
	public PageItem1_2 load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		return getNextButton().isAvailable() &&
			   getPreviousButton().isAvailable() &&
			   isImagesLoaded();
	}
	
	private boolean isImagesLoaded() {
		Image neptuneImage = getImage(By.xpath(NEPTUNE_XPATH));
		return neptuneImage.isAvailable() &&
				neptuneImage.isFullyVisible() &&
				getImage(By.xpath(URANUS_XPATH)).isAvailable() &&
				getImage(By.xpath(SATURN_XPATH)).isAvailable() &&
				getImage(By.xpath(JUPITER_XPATH)).isAvailable() &&
				getImage(By.xpath(MARS_XPATH)).isAvailable() &&
				getImage(By.xpath(EARTH_XPATH)).isAvailable() &&
				getImage(By.xpath(VENUS_XPATH)).isAvailable() &&
				getImage(By.xpath(MERCURY_XPATH)).isAvailable();
	}
	
	private Image getImage(By findByMethod) {
		return new Image(driver, findByMethod);
	}

	private Button getNextButton() {
		return new Button(driver, By.xpath("//button[@id='FooterNavigationNext']"));
	}
	
	private Button getPreviousButton() {
		return new Button(driver, By.xpath("//button[@id='FooterNavigationPrevious']"));
	}

	public PageItem1_3 navigateToNextPage() {
		getNextButton().click();
		return new PageItem1_3(driver);
	}

}
