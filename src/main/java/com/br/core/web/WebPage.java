package com.br.core.web;

import static com.br.core.Configuration.getConfig;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.br.core.Configuration;
import com.br.core.Environment;

public abstract class WebPage<T extends WebPage<T>> extends Component<T> {

	private static final Configuration CONFIG = getConfig();
	private static final Environment ENVIRONMENT = CONFIG
			.getEnvironmentSettings();
	protected static final String BASE_URL = ENVIRONMENT.scheme + "://"
			+ ENVIRONMENT.host;
	protected static final String LCMS_LOGIN_PAGE_URL = ENVIRONMENT.lcmsLoginUrl;
	protected static final String LCMS_HOME_PAGE_URL = ENVIRONMENT.lcmsHomeUrl;
	protected static final String XPE_DOCUMENT_URL = ENVIRONMENT.documentUrl;

	public WebPage(WebDriver driver) {
		super(driver);
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
