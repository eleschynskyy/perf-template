package com.br.core.web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.br.core.web.WebComponent;

public class CustomElement extends WebComponent<CustomElement> {

	public CustomElement(WebDriver driver, By findByMethod) {
		super(driver, findByMethod);
	}
	
}
