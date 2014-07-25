package com.br.core;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.br.utils.WebDriverListener;
import com.br.utils.ConfigProperties;

public class DriverMaster {
	
	private static String ffProfile = ConfigProperties
			.getSystemProperties("firefox.profile");

	private static HashMap<Long, WebDriver> driverMap = new HashMap<Long, WebDriver>();
	private static HashMap<Long, EventFiringWebDriver> eventDriverMap = new HashMap<Long, EventFiringWebDriver>();

	private DriverMaster() {
	};

	public static WebDriver startDriverInstance(String driverKey) {
		BrowserType browser = BrowserType.get(driverKey);
		WebDriver driver;
//		EventFiringWebDriver eventDriver;
		switch (browser) {
		case FIREFOX:
			final String addOnPath = "addons/aeffagent.xpi";
			File addOnFile = new File(addOnPath);
			FirefoxProfile ffp = new FirefoxProfile();
			try {
				ffp.addExtension(addOnFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			driver = new FirefoxDriver(ffp);
			driver = new FirefoxDriver();
			/*
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference( "browser.startup.homepage", "about:addons" );
			driver = new FirefoxDriver(profile);
			driver.get("about:addons");
			String source= driver.getPageSource();
			final String addOnsSectionStart         = "<browser id=\"discover-browser\"";
			source = source.substring( source.indexOf( addOnsSectionStart ) + addOnsSectionStart.length());
			source = source.substring( source.indexOf( "{%22" ) );
			source = source.substring( 0, source.indexOf( "\" clickthrough" ) );
			try {
				source = URLDecoder.decode( source, "UTF-8" );
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			         
			JSONObject addonObjects;
			try {
				addonObjects = new JSONObject(source);
				JSONArray jsonAddonArray = addonObjects.names();
				System.out.println(
					    String.format(
					        "%-10s\t%-15s\t%-15s\t%-15s\t%-15s\t%s",
					        "type",
					        "version",
					        "user disabled",
					        "is compatible",
					        "is blocklisted",
					        "name"
					    )
					);
					         
					for(int i = 0; i < jsonAddonArray.length(); i++)
					{
					    JSONObject jsonAddonObject = addonObjects.getJSONObject(jsonAddonArray.getString(i));
					    System.out.println(
					        String.format(
					            "%-10s\t%-15s\t%-15s\t%-15s\t%-15s\t%s",
					            jsonAddonObject.getString("type"),
					            jsonAddonObject.getString("version"),
					            jsonAddonObject.getString("userDisabled"),
					            jsonAddonObject.getString("isCompatible"),
					            jsonAddonObject.getString("isBlocklisted"),
					            jsonAddonObject.getString("name")
					        )
					    );
					}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
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
//		eventDriver = new EventFiringWebDriver(driver);
//		eventDriverMap.put(Thread.currentThread().getId(), eventDriver);
//		eventDriver.register(new WebDriverListener());
//		return eventDriver;
		return driver;
	}

	public static WebDriver getDriverInstance() {
//		WebDriver driver = eventDriverMap.get(Thread.currentThread().getId());
		WebDriver driver = driverMap.get(Thread.currentThread().getId());
		return driver;
	}

	public static void stopDriverInstance() {
//		WebDriver driver = eventDriverMap.get(Thread.currentThread().getId());
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
