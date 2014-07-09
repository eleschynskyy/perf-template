package com.br.tests;

import java.text.DecimalFormat;

import org.testng.annotations.Test;

import com.br.core.BaseTest;
import com.br.core.CsvDataProvider;
import com.br.core.DriverMaster;
import com.br.core.web.pages.DocPage;
import com.br.core.web.pages.HomePage;
import com.br.core.web.pages.LoginPage;
import com.br.data.objects.User;
import com.br.utils.TestStepReporter;

public class PreviewTest extends BaseTest {
	
	@Test(dataProvider = "provideRandomUserFromList", dataProviderClass = CsvDataProvider.class, enabled = true, invocationCount = 5, threadPoolSize = 5)
	public void previewDocument(User user) {
		long startTime = System.currentTimeMillis();
		HomePage homePage = new LoginPage(DriverMaster.getDriverInstance())
			.loginWithRightCredentialsAs(user);
		long endTime = System.currentTimeMillis();
		reportTime("Logging in took: ", (double)(endTime - startTime));
		startTime = System.currentTimeMillis();
		DocPage docPage = new DocPage(DriverMaster.getDriverInstance()).loadAndWaitUntilAvailable();
		endTime = System.currentTimeMillis();
		reportTime("Opening document took: ", (double)(endTime - startTime));
	}
	
	private void reportTime(String description, double t) {
		DecimalFormat df = new DecimalFormat("###.##");
		TestStepReporter.reportln(description + t + "ms (~" + df.format(t/1000) + "sec)");
	}
	
}
