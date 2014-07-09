package com.br.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.br.core.DriverMaster;

public class TestExecutionListener extends TestListenerAdapter {
	
	private static final String SCREENSHOT_FOLDER = "target/screenshots/";
	private static final String SCREENSHOT_FORMAT = ".png";

	/**
	 * Prints the test results to report.
	 *
	 * @param result the result
	 */
	private void printTestResults(ITestResult result) {
		/*
		if (result.getParameters().length != 0) {
			String params = "";
			for (Object parameter : result.getParameters()) {
				params += parameter.toString() + ", ";
			}

			TestStepReporter.reportln("Test Method had the following parameters: ", params);
			
		}
		*/
		String status = null;
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			status = "Passed";
			break;
		case ITestResult.FAILURE:
			status = "Failed";
			break;
		case ITestResult.SKIP:
			status = "Skipped";
			break;
		}
		long executionTime = result.getEndMillis() - result.getStartMillis();
		Map<String, String> params = result.getTestClass().getXmlTest().getTestParameters();
//		TestStepReporter.reportln("Host:", result.getHost() == null ? "localhost" : result.getHost());
		TestStepReporter.reportln("Platform: OS=" + params.get("platform") + "; browser=" + params.get("browser") + "; version=" + params.get("version"));
		TestStepReporter.reportln("Total execution time:", executionTime + " ms");
		TestStepReporter.reportln("Status:", status);
		TestStepReporter.reportln("Screenshot:");
		takeScreenshot(result);
	}

	public void onTestSkipped(ITestResult arg0) {
		printTestResults(arg0);
	}

	public void onTestSuccess(ITestResult arg0) {
		printTestResults(arg0);
	}

	public void onTestFailure(ITestResult arg0) {
		printTestResults(arg0);
	}
	
	@Override
	public void onStart(ITestContext testContext) {
		  super.onStart(testContext);
//		  testContext.
	}
	
	public void takeScreenshot(ITestResult result){
		String folder = SCREENSHOT_FOLDER + result.getName();
		File dir = new File(folder);
		if (!dir.exists()){
			dir.mkdir();
		}
		try {
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			File f = ((TakesScreenshot)DriverMaster.getDriverInstance()).getScreenshotAs(OutputType.FILE);
			String fileName = result.getName() + "_" + formater.format(Calendar.getInstance().getTime()) + SCREENSHOT_FORMAT;
			FileUtils.copyFile(f, new File(dir.getAbsoluteFile() + "/" + fileName));
			File directory = new File(".");
     		String newFileNamePath = directory.getCanonicalPath();
     		Reporter.log("<a href=" + newFileNamePath + "/" + folder + "/" + fileName + " target='_blank' >" +
     					 "<p><br/><img src=\"file:///" + newFileNamePath + "/" + folder + "/" + fileName + 
     					 "\" width=\"300\" height=\"150\" alt=\"\"/>" +
     					 "<br/></p></a>", true);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}              
}

}
