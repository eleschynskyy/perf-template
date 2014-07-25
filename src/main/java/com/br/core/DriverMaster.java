package com.br.core;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.br.utils.WebDriverListener;

import com.br.utils.ConfigProperties;

public class DriverMaster {

	private static HashMap<Long, WebDriver> driverMap = new HashMap<Long, WebDriver>();
	private static HashMap<Long, EventFiringWebDriver> eventDriverMap = new HashMap<Long, EventFiringWebDriver>();

	private DriverMaster() {
	};

	public static WebDriver startDriverInstance(String driverKey) {
		BrowserType browser = BrowserType.get(driverKey);
		WebDriver driver;
		EventFiringWebDriver eventDriver;
		switch (browser) {
		case FIREFOX:
			driver = new FirefoxDriver();
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
		driver.manage().window().maximize();
		driverMap.put(Thread.currentThread().getId(), driver);
		eventDriver = new EventFiringWebDriver(driver);
		eventDriverMap.put(Thread.currentThread().getId(), eventDriver);
		eventDriver.register(new WebDriverListener());
		return eventDriver;
//		return driver;
	}

	public static WebDriver getDriverInstance() {
		WebDriver driver = eventDriverMap.get(Thread.currentThread().getId());
//		WebDriver driver = driverMap.get(Thread.currentThread().getId());
		return driver;
	}

	public static void stopDriverInstance() {
		WebDriver driver = eventDriverMap.get(Thread.currentThread().getId());
//		WebDriver driver = driverMap.get(Thread.currentThread().getId());
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
