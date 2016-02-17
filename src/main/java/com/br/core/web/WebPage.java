package com.br.core.web;

import static com.br.core.Configuration.getConfig;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.br.core.Configuration;
import com.br.core.Environment;
import com.br.utils.ConfigProperties;

public abstract class WebPage<T extends WebPage<T>> extends Component<T> {
	
	protected static final int DEFAULT_TIMEOUT = 30000;
	protected static final int DEFAULT_RETRY_DELAY = 100;

	private static final Configuration CONFIG = getConfig();
	private static final Environment ENVIRONMENT = CONFIG
			.getEnvironmentSettings();
	protected static final String BASE_XPE_HOST = ENVIRONMENT.protocol + "://"
			+ ENVIRONMENT.xpeHost;
	protected static final String LCMS_INSTANCE = ENVIRONMENT.lcmsInstance;
	protected static final String XPE_DOCUMENT_URL = ENVIRONMENT.documentUrl;
	protected static String TEST_FOLDER = ConfigProperties.getTestDataProperties("navigation.folder");
	protected static String FILE_GUID = ConfigProperties.getTestDataProperties("navigation.file.guid");
	protected static String OUTPUT_TYPE_OPTION = ConfigProperties.getTestDataProperties("preview.output.type");
	protected static String OUTPUT_PROFILE = ConfigProperties.getTestDataProperties("preview.output.profile");
	protected static String OUTPUT_ENV = ConfigProperties.getTestDataProperties("preview.output.env");
	protected static String ENV_TO_PROMOTE_FROM = ConfigProperties.getTestDataProperties("environment.to.promote.from");
	protected static String ENV_TO_PROMOTE_TO = ConfigProperties.getTestDataProperties("environment.to.promote.to");
	protected static String OUTPUT_TO_PROMOTE = ConfigProperties.getTestDataProperties("output.to.promote");

	public WebPage(WebDriver driver, String description) {
		super(driver, description);
	}

	public abstract T load();

	public T loadAndWaitUntilAvailable() {
		load();
		return waitUntilAvailable();
	}
	
	protected List<WebElement> getListOfWebElements(By findByMethod) {
		return driver.findElements(findByMethod);
	}
	
	protected int delay() {
		try {
			Thread.sleep(DEFAULT_RETRY_DELAY);
			return DEFAULT_RETRY_DELAY;
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
