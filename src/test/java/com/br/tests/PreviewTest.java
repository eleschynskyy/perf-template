package com.br.tests;

import java.text.DecimalFormat;

import org.testng.annotations.Test;

import com.br.core.BaseTest;
import com.br.core.CsvDataProvider;
import com.br.core.DriverMaster;
import com.br.core.web.pages.doc.PageItem1;
import com.br.core.web.pages.doc.PageItem2;
import com.br.core.web.pages.doc.PageItem3;
import com.br.core.web.pages.doc.PageItem4;
import com.br.core.web.pages.doc.PageItem5;
import com.br.core.web.pages.HomePage;
import com.br.core.web.pages.LoginPage;
import com.br.data.objects.User;
import com.br.utils.TestStepReporter;

public class PreviewTest extends BaseTest {
	
	@Test(dataProvider = "provideRandomUserFromList", dataProviderClass = CsvDataProvider.class, enabled = true, invocationCount = 1, threadPoolSize = 1)
	public void previewDocument(User user) {
		//logging in
		long startTime = System.currentTimeMillis();
//		TestStepReporter.reportln("START: " + System.currentTimeMillis());
		HomePage homePage = new LoginPage(DriverMaster.getDriverInstance(), "Login page")
			.loginWithRightCredentialsAs(user);
//		TestStepReporter.reportln("LOGGED IN: " + System.currentTimeMillis());
		long endTime = System.currentTimeMillis();
		reportTime("Logging in took: ", (double)(endTime - startTime));
		
		//getting 1st page
		startTime = System.currentTimeMillis();
		PageItem1 pageItem1_1 = new PageItem1(DriverMaster.getDriverInstance(), "Page #1").loadAndWaitUntilAvailable();
		endTime = System.currentTimeMillis();
		reportTime("Opening document's 1st page took: ", (double)(endTime - startTime));
		/*
		//getting 2nd page
		startTime = System.currentTimeMillis();
		PageItem2 pageItem1_2 = pageItem1_1.navigateToNextPage().waitUntilAvailable();
//		TestStepReporter.reportln("PAGE #2 LOADED: " + System.currentTimeMillis());
		endTime = System.currentTimeMillis();
		reportTime("Opening document's 2nd page took: ", (double)(endTime - startTime));
		//getting 3rd page
		startTime = System.currentTimeMillis();
		PageItem3 pageItem1_3 = pageItem1_2.navigateToNextPage().waitUntilAvailable();
		endTime = System.currentTimeMillis();
		reportTime("Opening document's 3rd page took: ", (double)(endTime - startTime));
		
		//getting 4th page
		startTime = System.currentTimeMillis();
		PageItem4 pageItem1_4 = pageItem1_3.navigateToNextPage().waitUntilAvailable();
		endTime = System.currentTimeMillis();
		reportTime("Opening document's 4th page took: ", (double)(endTime - startTime));
		
		//getting 5th page
		startTime = System.currentTimeMillis();
		PageItem5 pageItem1_5 = pageItem1_4.navigateToNextPage().waitUntilAvailable();
		endTime = System.currentTimeMillis();
		reportTime("Opening document's 5th page took: ", (double)(endTime - startTime));
		
		//getting 4th page back
		startTime = System.currentTimeMillis();
		pageItem1_4 = pageItem1_5.navigateToPreviousPage().waitUntilAvailable();
		endTime = System.currentTimeMillis();
		reportTime("Opening document's 4th page(back) took: ", (double)(endTime - startTime));
		
		//getting 3rd page back
		startTime = System.currentTimeMillis();
		pageItem1_3 = pageItem1_4.navigateToPreviousPage().waitUntilAvailable();
		endTime = System.currentTimeMillis();
		reportTime("Opening document's 3rd page(back) took: ", (double)(endTime - startTime));
		
		//getting 2nd page back
		startTime = System.currentTimeMillis();
		pageItem1_2 = pageItem1_3.navigateToPreviousPage().waitUntilAvailable();
		endTime = System.currentTimeMillis();
		reportTime("Opening document's 2nd page(back) took: ", (double)(endTime - startTime));
		
		//getting 1st page back
		startTime = System.currentTimeMillis();
		pageItem1_1 = pageItem1_2.navigateToPreviousPage().waitUntilAvailable();
		endTime = System.currentTimeMillis();
		reportTime("Opening document's 1st page(back) took: ", (double)(endTime - startTime));
		*/
	}
	
	private void reportTime(String description, double t) {
		DecimalFormat df = new DecimalFormat("###.##");
		TestStepReporter.reportln(description + t + "ms (~" + df.format(t/1000) + "sec)");
	}
	
}
