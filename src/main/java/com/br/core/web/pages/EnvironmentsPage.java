package com.br.core.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.br.core.web.WebPage;
import com.br.core.web.elements.Button;
import com.br.core.web.elements.CustomElement;
import com.br.core.web.elements.Link;

public class EnvironmentsPage extends WebPage<EnvironmentsPage> {

	private static final String PAGE_URL = BASE_XPE_HOST + "/" + LCMS_INSTANCE
			+ "/admin/environment/names";
	private static final String NOT_SET = "Not set";

	public EnvironmentsPage(WebDriver driver, String description) {
		super(driver, description);
		waitUntilAvailable();
	}

	@Override
	public EnvironmentsPage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		return getWorkspace().isAvailable();
	}

	public void expandWorkspaceOptions() {
		getExpandButton().click();
	}

	public void definePromoteTarget() {
		if (!isPromoteTargetDefined()) {
			selectPromoteTarget();
		}
	}

	public void promote() {
		getPromoteDropdownButton().click();
		getPromoteButton().click();
	}

	private Link getPromoteButton() {
		return new Link(
				driver,
				By.xpath("//a[contains(text(), 'Promote') and ancestor::ul[preceding-sibling::a[parent::div[@class='dropdown open' and ancestor::td[preceding-sibling::td[contains(text(), '"
						+ OUTPUT_TO_PROMOTE
						+ "') and preceding::td[contains(text(), '"
						+ ENV_TO_PROMOTE_FROM
						+ "')] and parent::tr[@class='ng-scope']] and descendant::div[@class='dropdown open']]]]]]"),
				"Promote button");
	}

	private Link getPromoteDropdownButton() {
		return new Link(
				driver,
				By.xpath("//a[parent::div[@class='dropdown' and ancestor::td[preceding-sibling::td[contains(text(), '"
						+ OUTPUT_TO_PROMOTE
						+ "') and preceding::td[contains(text(), '"
						+ ENV_TO_PROMOTE_FROM
						+ "')] and parent::tr[@class='ng-scope']] and descendant::div[@class='dropdown']]]]"),
				"Promote dropdown");
	}

	private void selectPromoteTarget() {
		getPromoteTarget().click();
		waitUntilSelectIsPresent();
		getSeleniumSelect(getPromoteTargetSelect()).selectByVisibleText(
				ENV_TO_PROMOTE_TO);
		getConfirmPromoteTargetButton().click();
		waitUntilPromoteTargetIsPresent();
	}

	private void waitUntilPromoteTargetIsPresent() {
		int timePassed = 0;
		while (timePassed < DEFAULT_TIMEOUT) {
			if (getPromoteTargetSelected().isAvailable()
					&& getPromoteTargetSelected().getText().equals(
							ENV_TO_PROMOTE_TO)) {
				break;
			}
			timePassed = timePassed + delay();
		}
		if (!(getPromoteTargetSelected().isAvailable() && getPromoteTargetSelected()
				.getText().equals(ENV_TO_PROMOTE_TO))) {
			throw new TimeoutException("There's no proper promote target selected");
		}
	}

	private Link getPromoteTargetSelected() {
		return new Link(
				driver,
				By.xpath("//a[ancestor::td[preceding-sibling::td[contains(text(), '"
						+ ENV_TO_PROMOTE_FROM
						+ "')] and @class='editable']][1]"),
				"Selected Promote Target Link");
	}

	private void waitUntilSelectIsPresent() {
		int timePassed = 0;
		while (timePassed < DEFAULT_TIMEOUT) {
			if (getPromoteTargetSelect().isAvailable()) {
				break;
			}
			timePassed = timePassed + delay();
		}
		if (!getPromoteTargetSelect().isAvailable()) {
			throw new TimeoutException(
					"There's no select box for Promote Target");
		}
	}

	private Button getConfirmPromoteTargetButton() {
		return new Button(
				driver,
				By.xpath("//button[preceding::select[ancestor::td[preceding-sibling::td[contains(text(), '"
						+ ENV_TO_PROMOTE_FROM
						+ "')] and @class='editable']]][1]"),
				"Confirm Promote Target button");
	}

	private CustomElement getPromoteTarget() {
		return new CustomElement(
				driver,
				By.xpath("//a[parent::td[preceding-sibling::td[contains(text(), '"
						+ ENV_TO_PROMOTE_FROM + "')] and @class='editable']]"),
				"Promote Target::" + ENV_TO_PROMOTE_FROM);
	}

	private CustomElement getPromoteTargetSelect() {
		return new CustomElement(
				driver,
				By.xpath("//select[ancestor::td[preceding-sibling::td[contains(text(), '"
						+ ENV_TO_PROMOTE_FROM + "')] and @class='editable']]"),
				"Promote Target select");
	}

	private boolean isPromoteTargetDefined() {
		return !getPromoteTargetText().equals(NOT_SET);
	}

	private String getPromoteTargetText() {
		String promoteTargetText = new CustomElement(
				driver,
				By.xpath("//a[parent::td[preceding-sibling::td[contains(text(), '"
						+ ENV_TO_PROMOTE_FROM + "')] and @class='editable']]"),
				"Promote Target text::" + ENV_TO_PROMOTE_FROM).getText().trim();
		// System.out.println("Promote Target for '" + ENV_TO_PROMOTE_FROM +
		// "': '" + promoteTargetText + "'");
		return promoteTargetText;
	}

	private CustomElement getWorkspace() {
		return new CustomElement(driver, By.xpath("//td[contains(text(), '"
				+ ENV_TO_PROMOTE_FROM + "')]"), ENV_TO_PROMOTE_FROM + " option");
	}

	private CustomElement getExpandButton() {
		return new CustomElement(
				driver,
				By.xpath("//a[parent::td[following-sibling::td[contains(text(), '"
						+ ENV_TO_PROMOTE_FROM + "')]]]"), "Expand button::"
						+ ENV_TO_PROMOTE_FROM);
	}

	private Select getSeleniumSelect(CustomElement e) {
		return new Select(e.getAsWebElementType());
	}

}
