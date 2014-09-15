package com.br.core;

import static org.openqa.selenium.Platform.LINUX;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverMaster {

	private static HashMap<Long, WebDriver> driverMap = new HashMap<Long, WebDriver>();
	/*
	private static String hubURL = ConfigProperties
			.getSystemProperties("hub.protocol")
			+ "://"
			+ ConfigProperties.getSystemProperties("hub.ip")
			+ ":"
			+ ConfigProperties.getSystemProperties("hub.port") + "/wd/hub";
	private static String ffProfile = ConfigProperties
			.getSystemProperties("firefox.profile");
	*/

	private DriverMaster() {
	};

	public static WebDriver startDriverInstance(String platformKey,
			String driverKey, String version) throws MalformedURLException {
		BrowserType browser = BrowserType.get(driverKey);
		PlatformType platform = PlatformType.get(platformKey);
		WebDriver driver;
		DesiredCapabilities capabilitiesRC = new DesiredCapabilities();
		capabilitiesRC.setVersion(version);
		switch (platform) {
		case WINDOWS:
			capabilitiesRC.setPlatform(Platform.WINDOWS);
			break;
		case LINUX:
			capabilitiesRC.setPlatform(LINUX);
			break;
		default:
			capabilitiesRC.setPlatform(Platform.WINDOWS);
			break;
		}
		switch (browser) {
		case FIREFOX:
			final String addOnPath = "addons/aeffagent.xpi";
			File addOnFile = new File(addOnPath);
			FirefoxProfile ffp = new FirefoxProfile();
			try {
				ffp.addExtension(addOnFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			driver = new FirefoxDriver(ffp);
			break;
		case CHROME:
			setChromeDriver();
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		    ChromeOptions options = new ChromeOptions();
		    options.addArguments("test-type");
		    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		    driver = new ChromeDriver(capabilities);
			break;
		case IE:
			setIEDriver();
			driver = new InternetExplorerDriver();
			break;
		default:
			driver = new FirefoxDriver();
			break;
		}
//		driver = new RemoteWebDriver(new URL(hubURL), capabilitiesRC);
		driver.manage().window().maximize();
		driverMap.put(Thread.currentThread().getId(), driver);
//		getIPOfNode(driver);
		return driver;
	}

	public static WebDriver getDriverInstance() {
		WebDriver driver = driverMap.get(Thread.currentThread().getId());
		return driver;
	}

	public static void stopDriverInstance() {
		WebDriver driver = driverMap.get(Thread.currentThread().getId());
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
	
	private static void setChromeDriver() {
		String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
		String chromeBinary = "src/main/resources/drivers/chrome/win/chromedriver"
				+ (os.equals("win") ? ".exe" : "");
		System.setProperty("webdriver.chrome.driver", chromeBinary);
	}

	private static void setIEDriver() {
		String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
		String ieBinary = "src/main/resources/drivers/ie/win/IEDriverServer"
				+ (os.equals("win") ? ".exe" : "");
		System.setProperty("webdriver.ie.driver", ieBinary);
	}


}
