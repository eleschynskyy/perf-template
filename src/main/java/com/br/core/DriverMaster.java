package com.br.core;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import net.lightbody.bmp.proxy.ProxyServer;

import com.br.utils.WebDriverListener;
import com.br.utils.ConfigProperties;

public class DriverMaster {

	private static String chromeDriverMode = ConfigProperties
			.getSystemProperties("chromeDriverMode");

	private static HashMap<Long, WebDriver> driverMap = new HashMap<Long, WebDriver>();
	private static HashMap<Long, EventFiringWebDriver> eventDriverMap = new HashMap<Long, EventFiringWebDriver>();
	
	public static ProxyServer server;

	private DriverMaster() {
	};

	public static WebDriver startDriverInstance(String driverKey) throws Exception {
//		Map<String, String> proxyOptions = new HashMap<String, String>();
//		proxyOptions.put("httpProxy", "127.0.0.1:4444");
		server = new ProxyServer(4444);
		server.start();
//		server.setLocalHost(InetAddress.getByName("127.0.0.1"));
//		server.setOptions(proxyOptions);
		Proxy proxy = server.seleniumProxy();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.PROXY, proxy);
		BrowserType browser = BrowserType.get(driverKey);
		WebDriver driver;
		EventFiringWebDriver eventDriver;
		switch (browser) {
		case FIREFOX:
			driver = new FirefoxDriver(capabilities);
			break;
		case CHROME:
			setChromeDriver();
		    ChromeOptions options = new ChromeOptions();
		    options.addArguments("test-type");
		    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		    driver = new ChromeDriver(capabilities);
			break;
		case IE:
			setIEDriver();
			driver = new InternetExplorerDriver(capabilities);
			break;
		default:
			driver = new FirefoxDriver(capabilities);
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
		String ieBinary = "src/main/resources/drivers/ie/IEDriverServer"
				+ (os.equals("win") ? ".exe" : "");
		System.setProperty("webdriver.ie.driver", ieBinary);
	}

}
