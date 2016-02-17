package com.br.core.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.br.core.web.WebPage;
import com.br.core.web.elements.Button;
import com.br.core.web.elements.CustomElement;

public class PreviewCoursePage extends WebPage<PreviewCoursePage> {

	private static final String PAGE_URL = BASE_XPE_HOST + "/" + LCMS_INSTANCE
			+ "/preview/" + FILE_GUID + "/" + OUTPUT_PROFILE + "/env/"
			+ OUTPUT_ENV + "/page/index.html";

	public PreviewCoursePage(WebDriver driver, String description) {
		super(driver, description);
		waitUntilAvailable();
		// TestStepReporter.reportln("LOGIN PAGE LOADED: " +
		// System.currentTimeMillis());
	}

	@Override
	public PreviewCoursePage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		// System.out.println("After previewing course active window is: " +
		// driver.getWindowHandle());
		return getNextButton().waitUntilAvailable().isAvailable()
				&& getPreviousButton().waitUntilAvailable().isAvailable()
				&& getCustomElement().waitUntilAvailable().isAvailable();
	}

	private CustomElement getCustomElement() {
		return new CustomElement(
				driver,
				By.xpath("//img[contains(@src,'/media-service/Standard/Photo/Solar_System/OSS_732x400.jpg')]"),
				"OSS_732x400.jpg");
	}

	private Button getNextButton() {
		return new Button(driver,
				By.xpath("//button[@id='FooterNavigationNext']"), "Next button");
	}

	private Button getPreviousButton() {
		return new Button(driver,
				By.xpath("//button[@id='FooterNavigationPrevious']"),
				"Previous button");
	}

}
