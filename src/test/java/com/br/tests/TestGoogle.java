package com.br.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import com.br.core.BaseTest;
import com.br.core.DriverMaster;
import com.br.core.web.pages.HomePage;
import com.br.core.web.pages.LoginPage;

public class TestGoogle {
	
	@Test
	public void userWithRightCredentialsCouldLogin() {
		WebDriver driver = new InternetExplorerDriver(); 
		driver.get("http://google.com");
//		LoginPage loginPage = new LoginPage(DriverMaster.getDriverInstance());
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
}
