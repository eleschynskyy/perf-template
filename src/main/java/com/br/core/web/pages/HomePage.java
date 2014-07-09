package com.br.core.web.pages;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.br.core.web.WebPage;
import com.br.core.web.elements.Link;
import com.br.core.web.elements.Text;
import com.br.data.objects.User;

public class HomePage extends WebPage<HomePage> {

	private static final String PAGE_URL = BASE_URL;
	private static final String PAGINATION_INFO_PATTERN = "1-(\\d+)\\sof\\s(\\d+)";
	private User user;

	public HomePage(WebDriver driver, User user) {
		super(driver);
		this.user = user;
	}

	@Override
	public HomePage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		return getDocumentsLink().isAvailable() &&
			   getLoggedInUser().isAvailable() &&
			   isProperUserLoggedIn(user) &&
			   getPaginationInfo().isAvailable() &&
			   isPaginationInfoPatternConformsWith(getPaginationInfo().getText()) &&
			   areAllChannelsLoaded(getNumberOfChannelsOnFirstPageByPaginationInfo());
	}

	public RootDocumentsPage navigateToDocumentsPage() {
		getDocumentsLink().click();
		return new RootDocumentsPage(driver);
	}

	private Link getDocumentsLink() {
		return new Link(driver,	By.xpath("//a[@href='/documents' and ancestor::div[@id='top-navbar-menu']]"));
	}
	
	private Text getLoggedInUser() {
		return new Text(driver, By.xpath("//span[@id='current-user-name' and ancestor::div[@id='top-navbar']]"));
	}
	
	private Text getPaginationInfo() {
		return new Text(driver, By.xpath("//small[ancestor::div[@id='channels'] and parent::div[following-sibling::ul[@class='pagination']]]"));
	}
	
	private boolean areAllChannelsLoaded(int n) {
		List<WebElement> channelsList = getListOfWebElements(By.xpath("//img[@class='channel-picture' and @src!='../images/loader.gif' and (@src='../images/default-channel.png' or starts-with(@src,'https://'))]"));
		System.out.println("checking for visibility...");
//		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfAllElements(channelsList));
		if (channelsList.size() == n) {
			for (WebElement image: channelsList){
				if (!isImageVisible(image)) {
					System.out.println("Image: <img class=\"" + image.getAttribute("class") + "\" src=\"" + image.getAttribute("src") + "\"> NOT YET");
					return false;
				}
				System.out.println("Image: <img class=\"" + image.getAttribute("class") + "\" src=\"" + image.getAttribute("src") + "\"> OK");
			}
		}
		return true;
	}
	
	private boolean isImageVisible(WebElement image) {
		String js = "return (typeof arguments[0].naturalWidth!=\"undefined\" && arguments[0].naturalWidth > 0)";
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (Boolean)jsExecutor.executeScript(js, image);
	}
	
	private int getNumberOfChannelsOnFirstPageByPaginationInfo() {
		String numberOfChannels = getPaginationInfo().getTextByPatternGroup(PAGINATION_INFO_PATTERN, 1);
		if (numberOfChannels != null) {
			return Integer.valueOf(numberOfChannels);
		}
		return 0;
	}
	
	private boolean isProperUserLoggedIn(User user) {
		return (user.getFirstName() + " " + user.getLastName()).contains(removeDotsIfNeededFrom(getLoggedInUser().getText()));
	}
	
	private String removeDotsIfNeededFrom(String text) {
		if (text.endsWith("...")) {
			return text.substring(0, text.length() - 3);
		} 
		return text;
	}
	
	public boolean isPaginationInfoPatternConformsWith(String text) {
		Pattern p = Pattern.compile(PAGINATION_INFO_PATTERN);
		Matcher m = p.matcher(text);
		if (m.matches() && 
			!(m.group(1).equals("") || m.group(1).equals("0")) &&
			!(m.group(2).equals("") || m.group(2).equals("0"))) {
			return true;
		}
		return false;
	}
	
	
	
}
