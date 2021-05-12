package com.qa.linkedin.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
public class LinkedinHomePage extends  BasePageWeb {
	private Logger log = Logger.getLogger(LinkedinHomePage.class);
	
	
	//create a constructor
	
public LinkedinHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@class='nav__logo-link']")
private WebElement linkedInLogo;
	
@FindBy(xpath="//a[contains(@href,'nav-header-signin')]")
private WebElement signInLink;

@FindBy(xpath="//h1[@class='header__content__heading ']")
private WebElement signInHeaderText;

@FindBy(xpath="//input[contains(@id,'username')]")
private WebElement emailEditBox;


@FindBy(name="session_password")
private WebElement passwordEditBox;

@FindBy(xpath="//button[@type='submit' and@aria-label='Sign in']")
private WebElement signInbutton;

String homePageTitle = "LinkedIn: Log In or Sign Up";
String signInPagetitle = "LinkedIn Login, Sign in | LinkedIn";

public void verifyLinkedinHomePageTitle() {
	log.debug("wait for the linked in home page title");
	wait.until(ExpectedConditions.titleContains(homePageTitle));
	Assert.assertEquals(driver.getTitle(), homePageTitle);
}

public void verifyLinkedInLogo() {
	log.debug("wait for the linkedin logo");
	wait.until(ExpectedConditions.visibilityOf( linkedInLogo));
	Assert.assertTrue(linkedInLogo.isDisplayed(),  "linkedInLogo is not present");
	
}
public void clickSignInLink() throws InterruptedException {
	log.debug("Click on sign in link on home page");
	click(signInLink);
}

public void verifySigninHomePageTitle() {
	log.debug("wait for the Signin home page title");
	wait.until(ExpectedConditions.titleContains(signInPagetitle));
	Assert.assertEquals(driver.getTitle(),signInPagetitle);
}
public void verifySigninHeaderText() {
	log.debug("wait for the Signin header text");
	wait.until(ExpectedConditions.visibilityOf(signInHeaderText));
	//Assert.assertEquals(signInHeaderText.isDisplayed(),"signInHeaderText is present");

}
public void clickSignInButton() throws InterruptedException {
	log.debug("Click on signin button in sign in page");
	click(signInbutton);
}


public LinkedinLoggedInPage doLogIn(String uname, String pwd) throws InterruptedException {
	log.debug("Started Log in Process...");
	sendKey(emailEditBox, uname);
	sendKey(passwordEditBox, pwd);
	clickSignInButton();
	return new LinkedinLoggedInPage();
}

}




