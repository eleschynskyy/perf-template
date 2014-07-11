package com.br.tests;

import java.text.DecimalFormat;

import org.testng.annotations.Test;

import com.br.core.BaseTest;
import com.br.core.CsvDataProvider;
import com.br.core.DriverMaster;
import com.br.core.web.pages.doc.PageItem1_1;
import com.br.core.web.pages.doc.PageItem1_2;
import com.br.core.web.pages.doc.PageItem1_3;
import com.br.core.web.pages.HomePage;
import com.br.core.web.pages.LoginPage;
import com.br.data.objects.User;
import com.br.utils.TestStepReporter;

public class PreviewTest extends BaseTest {
	
	@Test(dataProvider = "provideRandomUserFromList", dataProviderClass = CsvDataProvider.class, enabled = true, invocationCount = 1, threadPoolSize = 5)
	public void previewDocument(User user) {
		//logging in
		long startTime = System.currentTimeMillis();
		HomePage homePage = new LoginPage(DriverMaster.getDriverInstance())
			.loginWithRightCredentialsAs(user);
		long endTime = System.currentTimeMillis();
		reportTime("Logging in took: ", (double)(endTime - startTime));
		//getting 1st page
		startTime = System.currentTimeMillis();
		PageItem1_1 pageItem1_1 = new PageItem1_1(DriverMaster.getDriverInstance()).loadAndWaitUntilAvailable();
		endTime = System.currentTimeMillis();
		reportTime("Opening document's 1st page took: ", (double)(endTime - startTime));
		//getting 2nd page
		startTime = System.currentTimeMillis();
		PageItem1_2 pageItem1_2 = pageItem1_1.navigateToNextPage().waitUntilAvailable();
		endTime = System.currentTimeMillis();
		reportTime("Opening document's 2nd page took: ", (double)(endTime - startTime));
		//getting 3rd page
		startTime = System.currentTimeMillis();
		PageItem1_3 pageItem1_3 = pageItem1_2.navigateToNextPage().waitUntilAvailable();
		endTime = System.currentTimeMillis();
		reportTime("Opening document's 3rd page took: ", (double)(endTime - startTime));
	}
	
	private void reportTime(String description, double t) {
		DecimalFormat df = new DecimalFormat("###.##");
		TestStepReporter.reportln(description + t + "ms (~" + df.format(t/1000) + "sec)");
	}
	
}
