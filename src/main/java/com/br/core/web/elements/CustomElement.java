package com.br.core.web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.br.core.web.WebComponent;

public class CustomElement extends WebComponent<CustomElement> {

	public CustomElement(WebDriver driver, By findByMethod, String description) {
		super(driver, findByMethod, description);
	}
	
	public WebElement getAsWebElementType(){
		return getWebElement();
	}
	
}
