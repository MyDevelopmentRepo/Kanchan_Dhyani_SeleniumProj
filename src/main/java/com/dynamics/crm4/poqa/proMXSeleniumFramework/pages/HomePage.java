package com.dynamics.crm4.poqa.proMXSeleniumFramework.pages;

import static com.dynamics.crm4.poqa.proMXSeleniumFramework.constants.AppConstants.MEDIUM_DEFAULT_TIME_OUT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dynamics.crm4.poqa.proMXSeleniumFramework.util.ElementUtil;

public class HomePage {

	private WebDriver driver;
	ElementUtil el;

	// ******Locators***
	private By projectOperations = By.id("AppTileContainerSec_1_LI_7");
	private By IframeLocator = By.id("AppLandingPage");
	private By approvals = By.xpath("//img[@title='Approvals']");
	private By timEntries = By.xpath("//span[text()='Time Entries']");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
	}

	public SalesActivityDashboardPage gotoPageSalesActivityDashBoard() {
		el.waitForFrameUsingLocatorAndSwitchToIt(MEDIUM_DEFAULT_TIME_OUT, IframeLocator);
		el.waitForElementVisibility(projectOperations, MEDIUM_DEFAULT_TIME_OUT).click();
		driver.switchTo().defaultContent();
		return new SalesActivityDashboardPage(driver);
	}

	public TimeEntriesForApprovalPage gotoPageTimeEntriesForApproval() {
		el.waitForElementVisibility(approvals, MEDIUM_DEFAULT_TIME_OUT).click();
		return new TimeEntriesForApprovalPage(driver);
	}

	public MyWeeklyTimeEntriesPage gotoPageMyWeeklyTimeEntries() {
		el.waitForElementVisibility(timEntries, MEDIUM_DEFAULT_TIME_OUT).click();
		return new MyWeeklyTimeEntriesPage(driver);
	}
}
