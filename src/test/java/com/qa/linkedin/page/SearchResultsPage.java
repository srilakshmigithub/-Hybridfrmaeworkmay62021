package com.qa.linkedin.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class SearchResultsPage extends BasePageWeb {
	private Logger log = Logger.getLogger(SearchResultsPage.class);

	public SearchResultsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class,'search-results__cluster-bottom-banner')]/a")
	private WebElement seeallPeopleResults;
	@FindBy(xpath = "//div[contains(@class,'search-results-container')]/div")
	private WebElement searchResultsText;

	@FindBy(xpath = "//ul[contains(@class,'global-nav__primary-item')]/li[1]")
	private WebElement homeTab;

	String searchResultsPageTitle = "Search | LinkedIn";

	public void verifySearchResultsPageTitle() {
		log.debug("wait for the verifySearchResultsPageTitle");
		wait.until(ExpectedConditions.titleContains(searchResultsPageTitle));
		Assert.assertTrue(driver.getPageSource().contains(searchResultsPageTitle));
	}

	public long getResultsCount() {

		log.debug("performing fetching results count for the given people");
		try {
			if (isPresentElement(seeallPeopleResults)) {
				click(seeallPeopleResults);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("wait for the search results text");
		wait.until(ExpectedConditions.visibilityOf(searchResultsText));
		log.debug("Get the search results text from webpage");
		String txt = searchResultsText.getText();
		System.out.println("Serach results text is:" + txt);
		// txt=About 272,000 results
		String[] str = txt.split(" ");
		// str[]=["About", "272,000","results"]
		// 0 1 2

		log.debug("results count in string format---->" + str[1]);
		String finalStringcnt = str[1].replace(",", "").trim();
		log.debug("Convert String into long format");
		long count = Long.parseLong(finalStringcnt);
		System.out.println("Serach results:"+ count);
		
		return count;
	}

	public void clickHomeTab() throws InterruptedException {
		log.debug("click on home tab");
		clickUsingJsExecutor(homeTab);
	}

}
