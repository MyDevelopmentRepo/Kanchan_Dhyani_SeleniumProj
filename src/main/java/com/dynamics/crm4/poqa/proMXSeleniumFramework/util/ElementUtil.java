package com.dynamics.crm4.poqa.proMXSeleniumFramework.util;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;
	private JavascriptExecutor js;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
	}

	public void clickElementByJs(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}

	public void waitForFrameUsingLocatorAndSwitchToIt(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public WebElement waitForElementPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementVisibility(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public String waitForTitleToBe(int timeOut, String titleValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleIs(titleValue))) {
			return driver.getTitle();
		} else
			return null;

	}

	public String waitForTitleContains(int timeOut, String titleValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleContains(titleValue))) {
			return driver.getTitle();
		} else
			return null;

	}

}