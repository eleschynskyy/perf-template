package com.br.tests;

import java.text.DecimalFormat;

import org.testng.annotations.Test;

import com.br.core.BaseTest;
import com.br.core.CsvDataProvider;
import com.br.core.DriverMaster;
import com.br.core.web.pages.HomePage;
import com.br.core.web.pages.LoginPage;
import com.br.data.objects.User;
import com.br.utils.TestStepReporter;

public class LoginTest extends BaseTest {
	
	@Test(dataProvider = "provideUser", dataProviderClass = CsvDataProvider.class, enabled = true, invocationCount = 1)
	public void userWithRightCredentialsCouldLogin(User user) {
		long startTime = System.currentTimeMillis();
		HomePage homePage = new LoginPage(DriverMaster.getDriverInstance())
			.loginWithRightCredentialsAs(user);
		long endTime = System.currentTimeMillis();
		reportTime("Logging in took: ", (double)(endTime - startTime));
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
	private void reportTime(String description, double t) {
		DecimalFormat df = new DecimalFormat("###.##");
		TestStepReporter.reportln(description + t + "ms (~" + df.format(t/1000) + "sec)");
	}
	
}
