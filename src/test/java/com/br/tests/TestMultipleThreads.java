package com.br.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;
import org.testng.annotations.Test;
 
public class TestMultipleThreads {
 
  @Test(invocationCount = 10, threadPoolSize = 2)
  public void loadTest() {
 
	System.out.printf("%n[START] Thread Id : %s is started!", 
                                  Thread.currentThread().getId());
 
	WebDriver driver = new FirefoxDriver();
	ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver expectationDriver) {
			JavascriptExecutor js = (JavascriptExecutor) expectationDriver;
			return js.executeScript("return document.readyState").equals("complete");
		}
	};
	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			.withTimeout(60000, TimeUnit.MILLISECONDS)
			.pollingEvery(100, TimeUnit.MILLISECONDS);
	try {
		driver.get("https://test.bravais.com");
		wait.until(expectation);
	} catch (Throwable error) {
		Reporter.log("Timeout waiting page to Load because of: "
				+ error.getMessage());
	}
	WebElement username = driver.findElement(By.id("username"));
	WebElement password = driver.findElement(By.name("password"));
	username.clear();
	password.clear();
	username.sendKeys("yevhen.leshchynskyy");
	password.sendKeys("BrAvAiS_TeSt_7");
	WebElement button = driver.findElement(By.xpath("//button[@class='btn btn-success']"));
	button.click();
	try {
		wait.until(expectation);
	} catch (Throwable error) {
		Reporter.log("Timeout waiting page to Load because of: "
				+ error.getMessage());
	}
 
 
	System.out.printf("%n[END] Thread Id : %s", 
                                  Thread.currentThread().getId());
 
	driver.quit();
 
  }
}
