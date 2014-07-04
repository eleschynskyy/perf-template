package com.br.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 * The listener interface for receiving webDriver events. The class that is
 * interested in processing a webDriver event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addWebDriverListener<code> method. When
 * the webDriver event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see WebDriverEvent
 */
public class WebDriverListener implements WebDriverEventListener {

	public void beforeNavigateTo(String url, WebDriver driver) {

	}

	public void afterNavigateTo(String url, WebDriver driver) {
//		TestStepReporter.report("Navigated to the url:", url);
	}

	public void beforeNavigateBack(WebDriver driver) {

	}

	public void afterNavigateBack(WebDriver driver) {

	}

	public void beforeNavigateForward(WebDriver driver) {

	}

	public void afterNavigateForward(WebDriver driver) {

	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {

	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {

	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
//		TestStepReporter.report(
//				"Click on webelement with locator",
//				getElementDescriptorXPATH(driver, element)
//						+ " was performed; html-tag: "
//						+ getElementDescriptorName(driver, element));
	}

	public void afterClickOn(WebElement element, WebDriver driver) {

	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) {

	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
//		TestStepReporter.report(
//				"Change was performed on element with locator:",
//				getElementDescriptorXPATH(driver, element)
//						+ "; Element html tag: "
//						+ getElementDescriptorName(driver, element));
	}

	public void beforeScript(String script, WebDriver driver) {

	}

	public void afterScript(String script, WebDriver driver) {
//		TestStepReporter.reportln("Execution of script performed ", script);
	}

	public void onException(Throwable throwable, WebDriver driver) {
		TestStepReporter.reportln("WebDriver Exception throwed:",
				throwable.getMessage());
	}

	/**
	 * Gets the element descriptor xpath.
	 * 
	 * @param driver
	 *            the driver
	 * @param element
	 *            the element
	 * @return the element descriptor xpath
	 */
	public String getElementDescriptorXPATH(WebDriver driver, WebElement element) {
		return (String) ((JavascriptExecutor) driver)
				.executeScript(
						"gPt=function(c){if(c.id!=='')"
								+ "{return'id(\"'+c.id+'\")'}"
								+ "if(c===document.body){return c.tagName}"
								+ "var a=0;var e=c.parentNode.childNodes;"
								+ "for(var b=0;b<e.length;b++){"
								+ "var d=e[b];if(d===c){"
								+ "return gPt(c.parentNode)+'/'+c.tagName+"
								+ "'['+(a+1)+']'}if(d.nodeType===1&&d.tagName===c.tagName)"
								+ "{a++}}};return gPt(arguments[0]).toLowerCase();",
						element);
	}

	/**
	 * Gets the element descriptor name.
	 * 
	 * @param driver
	 *            the driver
	 * @param element
	 *            the element
	 * @return the element descriptor name and element text
	 */
	public String getElementDescriptorName(WebDriver driver, WebElement element) {
		return element.getTagName() + ": " + element.getText();
	}

}
