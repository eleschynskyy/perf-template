package com.br.core;

import static com.br.core.Configuration.setGlobalEnvironment;
import static com.br.core.DriverMaster.startDriverInstance;
import static com.br.core.DriverMaster.stopDriverInstance;

import java.net.MalformedURLException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "platform", "browser", "version", "environment" })
	public void setUp(@Optional("WINDOWS") String platform,
			@Optional("firefox") String browser,
			@Optional("WINDOWS") String version,
			@Optional("TEST") String environment) {
		try {
			startDriverInstance(platform, browser, version);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		setGlobalEnvironment(environment);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		stopDriverInstance();
	}

}
