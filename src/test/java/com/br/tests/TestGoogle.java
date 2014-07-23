package com.br.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

public class TestGoogle {
	
	@Test
	public void userWithRightCredentialsCouldLogin() {
		WebDriver driver = new InternetExplorerDriver(); 
		driver.get("http://google.com");
	}
	
}
