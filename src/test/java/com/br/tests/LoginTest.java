package com.br.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.br.core.BaseTest;
import com.br.core.CsvDataProvider;
import com.br.core.DriverMaster;
import com.br.core.web.pages.HomePage;
import com.br.core.web.pages.LoginPage;
import com.br.data.objects.User;

public class LoginTest extends BaseTest {
	
	@Test(dataProvider = "provideRandomUserFromList", dataProviderClass = CsvDataProvider.class, enabled = true, invocationCount = 2, threadPoolSize = 2)
	public void userWithRightCredentialsCouldLogin(User user) {
		HomePage homePage = new LoginPage(DriverMaster.getDriverInstance())
			.loginWithRightCredentialsAs(user);
//		assertThat("Page Error Message should be as expected",
//				loginPage.getPageErrorMessage(),equalTo(msg.getMessage()));
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
	@Test(dataProvider = "provideUser", dataProviderClass = CsvDataProvider.class, enabled = false, invocationCount = 1)
	public void userWithRightCredentialsCouldLogin1(User user) {
		HomePage homePage = new LoginPage(DriverMaster.getDriverInstance())
			.loginWithRightCredentialsAs(user);
//		assertThat("Page Error Message should be as expected",
//				loginPage.getPageErrorMessage(),equalTo(msg.getMessage()));
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
}
