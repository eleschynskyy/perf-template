package com.br.core;

import static com.br.core.Configuration.setGlobalEnvironment;
import static com.br.core.DriverMaster.startDriverInstance;
import static com.br.core.DriverMaster.stopDriverInstance;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser", "environment" })
	public void setUp(@Optional("firefox") String browser, @Optional("XPE_PERF") String environment) {
		startDriverInstance(browser);
		setGlobalEnvironment(environment);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		stopDriverInstance();
	}

}
