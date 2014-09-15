package com.br.tests;

import org.testng.annotations.Test;

import com.br.core.BaseTest;
import com.br.core.CsvDataProvider;
import com.br.core.DriverMaster;
import com.br.core.web.pages.LoginPage;
import com.br.core.web.pages.PreviewCoursePage;
import com.br.core.web.pages.WMFileLevelPage;
import com.br.core.web.pages.WMHomePage;
import com.br.core.web.pages.WMPreviewFormPage;
import com.br.core.web.pages.doc.PageItem1;
import com.br.data.objects.User;
import com.br.utils.TimeReporter;

public class PreviewTest extends BaseTest {
	
	@Test(dataProvider = "provideRandomUserFromList", dataProviderClass = CsvDataProvider.class, enabled = false, invocationCount = 1, threadPoolSize = 1)
	public void previewDocument1(User user) {
		long startTime = System.currentTimeMillis();
//		HomePage homePage = new LoginPage(DriverMaster.getDriverInstance(), "Login page")
//			.loginAs(user);
		long endTime = System.currentTimeMillis();
		TimeReporter.reportTime("Logging in took: ", (double)(endTime - startTime));
		startTime = System.currentTimeMillis();
		PageItem1 pageItem1_1 = new PageItem1(DriverMaster.getDriverInstance(), "Page #1").loadAndWaitUntilAvailable();
		endTime = System.currentTimeMillis();
		TimeReporter.reportTime("Opening document's 1st page took: ", (double)(endTime - startTime));
	}
	
	@Test(dataProvider = "provideRandomUserFromList", dataProviderClass = CsvDataProvider.class, enabled = true, invocationCount = 1, threadPoolSize = 1)
	public void previewDocument(User user) {
		long startTime = System.currentTimeMillis();
		WMHomePage wsHomePage = new LoginPage(DriverMaster.getDriverInstance(), "Login Page")
			.loginAs(user);
		long endTime = System.currentTimeMillis();
		TimeReporter.reportTime("Logging in to Workspace Manager: ", (double)(endTime - startTime));
		WMFileLevelPage wsFileLevelPage = wsHomePage.navigateToTestFolder();
		WMPreviewFormPage wsPreviewForm = wsFileLevelPage.clickOnCourseToPreview();
		PreviewCoursePage previewCoursePage = wsPreviewForm.previewCourse();
		
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		startTime = System.currentTimeMillis();
//		PageItem1 pageItem1_1 = new PageItem1(DriverMaster.getDriverInstance(), "Page #1").loadAndWaitUntilAvailable();
//		endTime = System.currentTimeMillis();
//		TimeReporter.reportTime("Opening document's 1st page took: ", (double)(endTime - startTime));
	}
	
}
