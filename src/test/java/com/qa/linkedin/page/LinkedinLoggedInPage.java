package com.qa.linkedin.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinLoggedInPage extends BasePageWeb {
	private Logger log = Logger.getLogger(LinkedinLoggedInPage.class);

	// create a constructor
	public LinkedinLoggedInPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div[class*='profile-rail-card']")
	private WebElement profileRailCart;

	@FindBy(xpath = "//img[@class='global-nav__me-photo ember-view']")
	private WebElement profileImageIcon;

	@FindBy(xpath = "//a[@class='global-nav__secondary-link mv1'][contains(.,'Sign Out')]")
	private WebElement signOutLink;

	@FindBy(xpath = "//input[contains(@class,'search-global-typeahead')]")
	private WebElement searchEditBox;

	public void verifyProfileRailCard() {
		log.debug("wait for the ProfileRailCard");
		wait.until(ExpectedConditions.visibilityOf(profileRailCart));
		//Assert.assertEquals(profileRailCart.isDisplayed(), "profileRailCart is not present");

	}

	public void doLogOut() throws InterruptedException {
		log.debug("perform the logout operation from the application");
		click(profileImageIcon);
		click(signOutLink);
	}

	public SearchResultsPage doPeopleSearch(String keyword) throws InterruptedException {
		log.debug("Perform the people search");
		sendKey(searchEditBox, keyword);
		Thread.sleep(2000);
		log.debug("Press the enter key to submit the search form");
		searchEditBox.sendKeys(Keys.ENTER);
		return new SearchResultsPage();

	}

}
