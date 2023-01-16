package com.dynamics.crm4.poqa.proMXSeleniumFramework.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	 private WebDriver driver;
	 private Actions act ;
	 
	 public ElementUtil(WebDriver driver)
	 {
		 this.driver = driver;
		 this.act= new Actions(driver);
	 }
	 
	 
	 
	 public void waitForFrameUsingLocatorAndSwitchToIt(int timeOut, By locator)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		}
	 
	
	 
	 

		
		public WebElement waitForElementVisibility(By locator, int timeOut)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		
		

 }