package com.br.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.br.core.BaseTest;
import com.br.core.CsvDataProvider;
import com.br.core.DriverMaster;
import com.br.core.web.pages.HomePage;
import com.br.core.web.pages.LoginPage;
import com.br.core.web.pages.RootDocumentsPage;
import com.br.data.objects.User;

public class DeleteDocumentsTest extends BaseTest {

	@Test(dataProvider = "provideUser", dataProviderClass = CsvDataProvider.class, enabled = false)
	public void deleteDocumentsTest(User user) {
//		HomePage homePage = new LoginPage(DriverMaster.getDriverInstance())
//				.loginWithRightCredentialsAs(user);
//		RootDocumentsPage documentsPage = new LoginPage(DriverMaster.getDriverInstance())
//				.loginWithRightCredentialsAs(user)
//				.navigateToDocumentsPage()
//				.selectAllDocumentsWithTitle("??????????? ????????????");
		// assertThat("Page Error Message should be as expected",
		// loginPage.getPageErrorMessage(),equalTo(msg.getMessage()));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
