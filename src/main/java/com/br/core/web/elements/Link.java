package com.br.core.web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.br.core.web.WebComponent;

public class Link extends WebComponent<Link> {

	public Link(WebDriver driver, By findByMethod, String description) {
		super(driver, findByMethod, description);
	}
	
}
