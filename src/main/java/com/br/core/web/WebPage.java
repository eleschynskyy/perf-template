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

}
