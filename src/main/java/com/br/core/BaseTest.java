package com.br.core;

import static com.br.core.Configuration.setGlobalEnvironment;
import static com.br.core.DriverMaster.startDriverInstance;
import static com.br.core.DriverMaster.stopDriverInstance;
import static com.br.core.DriverMaster.getDriverInstance;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import net.lightbody.bmp.core.har.Har;

import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.br.core.web.WebPage;

public abstract class BaseTest {

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser", "environment" })
	public void setUp(@Optional("firefox") String browser,
			@Optional("TEST") String environment) {
		try {
			startDriverInstance(browser);
			getDriverInstance();
			DriverMaster.server.newHar(WebPage.getBaseUrl());
		} catch (Exception e) {
			e.getMessage();
		}
		setGlobalEnvironment(environment);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		try {
			DriverMaster.server.stop();
			Har har = DriverMaster.server.getHar();
			File file = new File("target/harfile.har");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			try {
				har.writeTo(fos);
			} finally {
				fos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			stopDriverInstance();
		}
	}

}
