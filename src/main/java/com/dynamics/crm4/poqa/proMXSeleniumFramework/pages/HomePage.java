package com.dynamics.crm4.poqa.proMXSeleniumFramework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dynamics.crm4.poqa.proMXSeleniumFramework.constants.AppConstants;
import com.dynamics.crm4.poqa.proMXSeleniumFramework.util.ElementUtil;

public class HomePage {
	
	 private WebDriver driver;
	 ElementUtil el;
	 
	 //******Locators***
	 private By projectOperations = By.id("AppTileContainerSec_1_LI_7");
	 private By IframeLocator = By.id("AppLandingPage");
	 
	 public HomePage(WebDriver driver)
	 {
		 this.driver = driver;
		 el = new ElementUtil(driver);
	 }
	 
	 public SalesActivityDashboardPage gotoPageSalesActivityDashBoard()
	 {
		 el.waitForFrameUsingLocatorAndSwitchToIt(AppConstants.MEDIUM_DEFAULT_TIME_OUT, IframeLocator);
		 el.waitForElementVisibility(projectOperations, AppConstants.MEDIUM_DEFAULT_TIME_OUT).click();
		 driver.switchTo().defaultContent();
		 return new SalesActivityDashboardPage(driver);
	 }
	 
	 

}
