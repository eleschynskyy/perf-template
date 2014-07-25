package com.br.tests;

import org.testng.annotations.Test;

import com.br.core.BaseTest;
import com.br.core.CsvDataProvider;
import com.br.core.DriverMaster;
import com.br.core.web.pages.HomePage;
import com.br.core.web.pages.LoginPage;
import com.br.data.objects.User;

public class LoginTest extends BaseTest {
	
	@Test(dataProvider = "provideUser", dataProviderClass = CsvDataProvider.class, enabled = true, invocationCount = 1)
	public void userWithRightCredentialsCouldLogin(User user) {
		HomePage homePage = new LoginPage(DriverMaster.getDriverInstance())
			.loginWithRightCredentialsAs(user);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
