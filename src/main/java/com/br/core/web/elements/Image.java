package com.br.core.web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.br.core.web.WebComponent;

public class Image extends WebComponent<Image> {

	public Image(WebDriver driver, By findByMethod) {
		super(driver, findByMethod); 
	}
	
	public boolean isFullyVisible() {
		WebElement image = driver.findElement(findByMethod);
		String js = "return (typeof arguments[0].naturalWidth!=\"undefined\" && arguments[0].naturalWidth > 0)";
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (Boolean)jsExecutor.executeScript(js, image);
	}
	
}
