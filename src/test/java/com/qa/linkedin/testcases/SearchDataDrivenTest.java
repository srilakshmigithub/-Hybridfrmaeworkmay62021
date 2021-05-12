package com.qa.linkedin.testcases;

import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.page.LinkedinHomePage;
import com.qa.linkedin.page.LinkedinLoggedInPage;
import com.qa.linkedin.page.SearchResultsPage;
import com.qa.linkedin.util.ExcelUtils; 
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.apache.log4j.Logger;

import org.testng.annotations.AfterClass;

public class SearchDataDrivenTest extends TestBase {

	private Logger log = Logger.getLogger(SearchDataDrivenTest.class);
	private String path = System.getProperty("user.dir")
			+ "\\src\\test\\java\\com\\qa\\linkedin\\data\\searchdata.xlsx";

	LinkedinHomePage lHmPage;
	LinkedinLoggedInPage llPage;
	SearchResultsPage srPage;

	@BeforeClass
	public void beforeClass() throws Exception {
		
		
		log.debug("Pages initilization...");
		
		lHmPage = new LinkedinHomePage();
		System.out.println("111111");
		llPage = new LinkedinLoggedInPage();
		srPage = new SearchResultsPage();
		System.out.println("22222222");
		log.debug("calling title verification methods");
		lHmPage.verifyLinkedinHomePageTitle();
		lHmPage.verifyLinkedInLogo();
		lHmPage.clickSignInLink();
		System.out.println("333333333");
		lHmPage.verifySigninHeaderText();
		llPage = lHmPage.doLogIn(readPropertyValue("username"), readPropertyValue("password"));
		llPage.verifyProfileRailCard();
		System.out.println("44444444");
		

	}

	@AfterClass
	public void afterClass() throws Exception {
		log.debug("Perform the logout operation from application");
		llPage.doLogOut();
		lHmPage.verifyLinkedinHomePageTitle();
	}

	@Test(dataProvider = "getData")
	public void searchDataDrivenTest(String keyword) throws Exception {
		log.debug("Started executing the search test for people" + keyword);
		srPage = llPage.doPeopleSearch(keyword);
		srPage.verifySearchResultsPageTitle();
		long cnt = srPage.getResultsCount();
		log.debug("Search results count for" + keyword + "is" + cnt);
		log.debug("click on home tab to go to loggedin page");
		srPage.clickHomeTab();
		log.debug("**************iteration done******************");
	}

	@DataProvider
	public Object[][] getData() throws Exception {

		Object[][] data = new ExcelUtils().getTestData(path, "Sheet1");
		return data;
	}

}
