package com.dynamics.crm4.poqa.proMXSeleniumFramework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dynamics.crm4.poqa.proMXSeleniumFramework.constants.AppConstants;
import com.dynamics.crm4.poqa.proMXSeleniumFramework.util.ElementUtil;

public class LoginPage {
 private WebDriver driver;
 ElementUtil el;
 
 //******Locators***
 
 private By pwd = By.id("passwordInput");
 private By submitButton = By.id("submitButton");
 public LoginPage(WebDriver driver)
 {
	 this.driver = driver;
	 el = new ElementUtil(driver);
 }
 
 public HomePage LogMeIn(String password)
 {
	 el.waitForElementVisibility(pwd, AppConstants.MEDIUM_DEFAULT_TIME_OUT).sendKeys(password);
	 el.waitForElementVisibility(submitButton, AppConstants.MEDIUM_DEFAULT_TIME_OUT).submit();
	 
	 return new HomePage(driver);
 }
 
}
