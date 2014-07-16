package com.br.core;

import static org.openqa.selenium.Platform.LINUX;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.br.utils.ConfigProperties;
import com.br.utils.TestStepReporter;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.thoughtworks.selenium.BrowserConfigurationOptions;

public class DriverMaster {

	private static HashMap<Long, WebDriver> driverMap = new HashMap<Long, WebDriver>();
	private static String hubURL = ConfigProperties
			.getSystemProperties("hub.protocol")
			+ "://"
			+ ConfigProperties.getSystemProperties("hub.ip")
			+ ":"
			+ ConfigProperties.getSystemProperties("hub.port") + "/wd/hub";
	private static String ffProfile = ConfigProperties
			.getSystemProperties("firefox.profile");

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
			capabilitiesRC.setBrowserName(DesiredCapabilities.firefox()
					.getBrowserName());
			/*
			ProfilesIni allProfiles = new ProfilesIni();
			FirefoxProfile ffp = allProfiles.getProfile(ffProfile);
			*/
			/*
			 * FirefoxProfile ffp = new FirefoxProfile();
			 * ffp.setPreference("plugins.click_to_play", false);
			 * ffp.setPreference("plugin.default_plugin_disabled", false);
			*/
//			capabilitiesRC.setCapability(FirefoxDriver.PROFILE, ffp);
			break;
		case CHROME:
			capabilitiesRC.setBrowserName(DesiredCapabilities.chrome()
					.getBrowserName());
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
//			options.addArguments("");
			capabilitiesRC.setCapability(ChromeOptions.CAPABILITY, options);
//			capabilitiesRC.setCapability("chrome.switches", Arrays.asList("--load-extension=/path/to/extension/directory"));
			break;
		case IE:
			capabilitiesRC.setBrowserName(DesiredCapabilities.internetExplorer()
					.getBrowserName());
			break;
		case HTML:
			capabilitiesRC.setBrowserName(DesiredCapabilities.htmlUnitWithJs()
					.getBrowserName());
			capabilitiesRC.setJavascriptEnabled(true);
//			capabilitiesRC.setCapability(BrowserConfigurationOptions.BROWSER_MODE, BrowserVersion.FIREFOX_17);
			break;
		default:
			capabilitiesRC.setBrowserName(DesiredCapabilities.firefox()
					.getBrowserName());
			break;
		}
		/*
		System.out.println("getPlatform(): " + capabilitiesRC.getPlatform());
		System.out.println("getBrowserName(): " + capabilitiesRC.getBrowserName());
		System.out.println("getVersion(): " + capabilitiesRC.getVersion());
		*/
		driver = new RemoteWebDriver(new URL(hubURL), capabilitiesRC);
		driver.manage().window().maximize();
		driverMap.put(Thread.currentThread().getId(), driver);
		getIPOfNode(driver);
		return driver;
	}

	private static void getIPOfNode(WebDriver driver) {
		RemoteWebDriver remoteDriver = (RemoteWebDriver) driver;
		try {
			HttpCommandExecutor ce = (HttpCommandExecutor) remoteDriver
					.getCommandExecutor();
			String hostName = ce.getAddressOfRemoteServer().getHost();
			int port = ce.getAddressOfRemoteServer().getPort();
			HttpHost host = new HttpHost(hostName, port);
			DefaultHttpClient client = new DefaultHttpClient();
			URL sessionURL = new URL("http://" + hostName + ":" + port
					+ "/grid/api/testsession?session="
					+ remoteDriver.getSessionId());
			BasicHttpEntityEnclosingRequest r = new BasicHttpEntityEnclosingRequest(
					"POST", sessionURL.toExternalForm());
			HttpResponse response = client.execute(host, r);
			JSONObject object = extractObject(response);
			URL myURL = new URL(object.getString("proxyId"));
			if ((myURL.getHost() != null) && (myURL.getPort() != -1)) {
				TestStepReporter.reportln("Remote host: " + myURL.getHost() + ":" + myURL.getPort());
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private static JSONObject extractObject(HttpResponse resp)
			throws IOException, JSONException {
		InputStream contents = resp.getEntity().getContent();
		StringWriter writer = new StringWriter();
		IOUtils.copy(contents, writer, "UTF8");
		JSONObject objToReturn = new JSONObject(writer.toString());
		return objToReturn;
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

}
