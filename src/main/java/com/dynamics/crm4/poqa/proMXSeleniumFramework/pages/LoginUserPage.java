package com.dynamics.crm4.poqa.proMXSeleniumFramework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dynamics.crm4.poqa.proMXSeleniumFramework.constants.AppConstants;
import com.dynamics.crm4.poqa.proMXSeleniumFramework.util.ElementUtil;

public class LoginUserPage {
 private WebDriver driver;
 ElementUtil el;
 
 //*******************Locators********************
 private By loginUserName = By.name("loginfmt");
 private By nextButton = By.id("idSIButton9");
 
 public LoginUserPage(WebDriver driver)
 {
	 this.driver = driver;
	 el = new ElementUtil(driver);
 }
 
 public LoginPage EnterUserName(String userName)
 {
	 el.waitForElementVisibility(loginUserName, AppConstants.MEDIUM_DEFAULT_TIME_OUT).sendKeys(userName);
	 el.waitForElementVisibility(nextButton, AppConstants.MEDIUM_DEFAULT_TIME_OUT).click();
	 return new LoginPage(driver);
	 
 }
 
}
