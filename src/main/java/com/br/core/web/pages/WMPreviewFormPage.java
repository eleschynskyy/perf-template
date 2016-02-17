package com.br.core.web.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.br.core.web.WebPage;
import com.br.core.web.elements.Button;
import com.br.core.web.elements.CustomElement;

public class WMPreviewFormPage extends WebPage<WMPreviewFormPage> {
	
	private static final String PAGE_URL = BASE_XPE_HOST + "/" + LCMS_INSTANCE + "/admin/documents/files/" + TEST_FOLDER + "?guid=" + FILE_GUID;
	private static final String WORKSPACE = "Workspace";

	public WMPreviewFormPage(WebDriver driver, String description) {
		super(driver, description);
		waitUntilAvailable();
//		TestStepReporter.reportln("LOGIN PAGE LOADED: " + System.currentTimeMillis());
	}

	@Override
	public WMPreviewFormPage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		 return getOutputTypeSelect().isAvailable() &&
				 getEnvironmentSelect().isAvailable();
	}
	
	public PreviewCoursePage previewCourse() {
		selectOutputType();
		selectEnvironment();
//		System.out.println("Before previewing course active window is: " + driver.getWindowHandle());
		final Set<String> oldWindowsSet = driver.getWindowHandles();
//		printAvailableWindows(oldWindowsSet);
		getPreviewButton().click();
        String newWindow = (new WebDriverWait(driver, 10))
            .until(new ExpectedCondition<String>() {
                public String apply(WebDriver driver) {
                    Set<String> newWindowsSet = driver.getWindowHandles();
//                    printAvailableWindows(newWindowsSet);
                    newWindowsSet.removeAll(oldWindowsSet);
                    return newWindowsSet.size() > 0 ?
                                 newWindowsSet.iterator().next() : null;
                  }
                }
            );
        driver.switchTo().window(newWindow);
		return new PreviewCoursePage(driver, "Preview course");
	}

	/*
	private void printAvailableWindows(Set<String> windowHandles) {
		System.out.println("----------------------------------------------");
		for (String i: windowHandles){
			System.out.println("WINDOW: " + i);
		}
		System.out.println("----------------------------------------------");
	}
	*/

	private void selectOutputType() {
		CustomElement s = getOutputTypeSelect(); 
		s.click();
		/*
		List<WebElement> lst = getSeleniumSelect(s).getOptions();
		Iterator<WebElement> it = lst.iterator();
		while (it.hasNext()) {
			WebElement el = it.next();
			System.out.println("OPTION: " + el.getText());
		}
		*/
		getSeleniumSelect(s).selectByVisibleText(OUTPUT_TYPE_OPTION);
	}
	
	private void selectEnvironment() {
		CustomElement s = getEnvironmentSelect();
		s.click();
		int timePassed = 0;
		while (timePassed < DEFAULT_TIMEOUT) {
			if (enoughOptions(s)) {
				getSeleniumSelect(s).selectByVisibleText(getEnvironmentOption());
				break;
			}
			timePassed = timePassed + delay();
		}
		if (!enoughOptions(s)) {
			throw new TimeoutException("There's no '" + OUTPUT_ENV + "' option in 'Workspace' list");
		}
	}
	
	private boolean enoughOptions(CustomElement e) {
		/*
		List<WebElement> lst = getSeleniumSelect(e).getOptions();
		Iterator<WebElement> it = lst.iterator();
		while (it.hasNext()) {
			WebElement el = it.next();
			System.out.println("OPTION: " + el.getText());
		}
		*/
		if (getSeleniumSelect(e).getOptions().size() > 1) {
			return true;
		}
		return false;
	}

	private String getEnvironmentOption() {
		if (OUTPUT_ENV.equals("dev")) {
			return WORKSPACE;
		}
		return OUTPUT_ENV;
	}

	private CustomElement getOutputTypeSelect() {
		return new CustomElement(driver, By.id("outputType"), "OutputType selector");
	}
	
	private CustomElement getEnvironmentSelect() {
		return new CustomElement(driver, By.id("environment"), "Environment selector");
	}
	
	private Button getPreviewButton() {
		return new Button(driver, By.linkText("Preview"), "Preview button");
	}

	private Select getSeleniumSelect(CustomElement e) {
		return new Select(e.getAsWebElementType());
	}

}
