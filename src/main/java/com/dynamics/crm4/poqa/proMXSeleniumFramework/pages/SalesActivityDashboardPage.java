package com.dynamics.crm4.poqa.proMXSeleniumFramework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dynamics.crm4.poqa.proMXSeleniumFramework.constants.AppConstants;
import com.dynamics.crm4.poqa.proMXSeleniumFramework.util.ElementUtil;

public class SalesActivityDashboardPage {
	private WebDriver driver;
	 ElementUtil el;
	 
	 //******Locators***
	private By timeEntry = By.xpath("//span[text() ='Time Entries']");
	 
	 public SalesActivityDashboardPage(WebDriver driver)
	 {
		 this.driver = driver;
		 el = new ElementUtil(driver);
	 }
	 
	 public MyWeeklyTimeEntriesPage gotoPageMyWeeklyTimeEntries()
	 {
		 extracted(timeEntry);
		 return new MyWeeklyTimeEntriesPage(driver);
	 }

	private void extracted(By xpath) {
		el.waitForElementVisibility(xpath, AppConstants.MEDIUM_DEFAULT_TIME_OUT).click();
	}
	 
	  
}
