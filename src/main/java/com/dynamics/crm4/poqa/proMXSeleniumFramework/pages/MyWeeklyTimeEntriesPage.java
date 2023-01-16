package com.dynamics.crm4.poqa.proMXSeleniumFramework.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

import com.dynamics.crm4.poqa.proMXSeleniumFramework.constants.AppConstants;
import com.dynamics.crm4.poqa.proMXSeleniumFramework.util.ElementUtil;

public class MyWeeklyTimeEntriesPage {
	private WebDriver driver;
	ElementUtil el;
	WebDriverWait wait;

	// ******Locators***
	private By newTimeEntry = By.xpath("//span[text() ='New']");
	private By saveAndClose = By.xpath("//span[text() ='Save and Close']");

	
	private By startDate = By.xpath("//input[@id='DatePicker23-label']");
	private By startTime = By.xpath("//input[@aria-label='Time of Start']");
	private By endTime = By.xpath("//input[@aria-label='Time of End']");
	private By endDate = By.xpath("//input[@id='DatePicker30-label']");
	private By duration = By.xpath("//input[contains(@id, 'duration-combobox_textInput')]");
	
	private By projectLookup = By.xpath("//input[@aria-label='Project, Lookup']");
	
	private By description = By.xpath("//input[contains(@id, 'description.fieldControl-text-box-text')]");
	
	private By showAsButton = By.xpath("//button[contains(@id,'changeVisualization')]");
	private By readOnlyButton = By.xpath("//span[text()='Read Only Grid']");
	private By descriptionHeader = By.xpath("//label[@title = 'Description']");
	private By descFilterBy = By.xpath("//span[text()='Filter by']");
	private By inputFilterBy = By.xpath("//input[@aria-label='Filter by value']");
	private By filterApplyButton = By.xpath("//button[@type='submit' and contains(@class,'ms-Button')]");
	
	private By entryStatus = By.xpath("//select[contains(@id,'msdyn_entrystatus.fieldControl-option-set-select')]");
	
	private By submitTimeEntry = By.xpath("//button[(@aria-label='Submit')]");

	public MyWeeklyTimeEntriesPage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
	}

	public String populateTimeEntries(String dt, String uniqueDesc) throws ParseException, InterruptedException {
		System.out.println("populateTimeEntries");
		System.out.println("uniqueDesc");
		
		el.waitForElementVisibility(newTimeEntry, AppConstants.MEDIUM_DEFAULT_TIME_OUT).click();

		
		el.waitForElementVisibility(startDate, AppConstants.SMALL_DEFAULT_TIME_OUT).sendKeys(dt);
		el.waitForElementVisibility(endDate, AppConstants.SMALL_DEFAULT_TIME_OUT).sendKeys(dt);
		el.waitForElementVisibility(description, AppConstants.SMALL_DEFAULT_TIME_OUT).sendKeys(Keys.chord(Keys.CONTROL, "a"), uniqueDesc);
		el.waitForElementVisibility(duration, AppConstants.SMALL_DEFAULT_TIME_OUT).sendKeys(Keys.chord(Keys.CONTROL, "a"), "30 minutes");
		
		el.waitForElementVisibility(startTime, AppConstants.SMALL_DEFAULT_TIME_OUT).sendKeys(Keys.chord(Keys.CONTROL, "a"), "08:00");
		el.waitForElementVisibility(endTime, AppConstants.SMALL_DEFAULT_TIME_OUT).sendKeys(Keys.chord(Keys.CONTROL, "a"), "08:30");
		
		selectValueFromLookUp(projectLookup);
			
		el.waitForElementVisibility(saveAndClose, AppConstants.MEDIUM_DEFAULT_TIME_OUT).click();
		
        Thread.sleep(10000l); // There is a small dynamic div (view entry box) which gives element not interactable as its take the focus and take a while to disappear
		
		
		goToTimeEntry(uniqueDesc);
		 Thread.sleep(8000l); // Added because the field entryStatus takes some time to have updated value, else its value is coming as draft
		
		return el.waitForElementVisibility(entryStatus,  AppConstants.SMALL_DEFAULT_TIME_OUT).getAttribute("title");
		
		
	}

	public void  goToTimeEntry(String uniqueDesc)  {
		
		viewTimeEntry(uniqueDesc);
		el.waitForElementVisibility(submitTimeEntry, AppConstants.MEDIUM_DEFAULT_TIME_OUT).click();
		
	}
	

	public WebElement fluentWait(final By locator) {
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	            .withTimeout(Duration.ofSeconds(40L))
	            .pollingEvery(Duration.ofSeconds(2l))
	            .ignoring(ElementClickInterceptedException.class);

	    WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
	        public WebElement apply(WebDriver driver) {
	            return driver.findElement(locator);
	        }
	    });

	      return foo;
	};
	
	public void viewTimeEntry(String uniqueDesc)
	{
		By rowToClick = By.xpath("//span[text()='" + uniqueDesc + "']");
		
		//wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.VERY_LONG_DEFAULT_TIME_OUT));
		//wait.ignoring(ElementClickInterceptedException.class);
		//wait.until(ExpectedConditions.presenceOfElementLocated(showAsButton)).click();
		fluentWait(showAsButton).click();
		el.waitForElementVisibility(readOnlyButton, AppConstants.MEDIUM_DEFAULT_TIME_OUT).click();
		 el.waitForElementVisibility(descriptionHeader,AppConstants.MEDIUM_DEFAULT_TIME_OUT).click();
		 el.waitForElementVisibility(descFilterBy,AppConstants.MEDIUM_DEFAULT_TIME_OUT).click();
		 el.waitForElementVisibility(inputFilterBy,AppConstants.MEDIUM_DEFAULT_TIME_OUT).sendKeys(uniqueDesc);
		 try {
			Thread.sleep(300l);      // added coz apply button is fully loaded after some time only and then only clickable
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 el.waitForElementVisibility(filterApplyButton, AppConstants.MEDIUM_DEFAULT_TIME_OUT).click();
		 el.waitForElementVisibility(rowToClick, AppConstants.LONG_DEFAULT_TIME_OUT).click();
	}
	

	
	
	public void selectValueFromLookUp( final By locator) {
		
		 wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MEDIUM_DEFAULT_TIME_OUT));
	   
	     wait.until(new Function<WebDriver, Boolean>() {
	        public Boolean apply(WebDriver driver) {

	        	try {
		        	WebElement element = driver.findElement(locator);
				element.click();
				element.sendKeys(Keys.chord(Keys.CONTROL, "a"), "Sof");
				
				
					Thread.sleep(600l);
				
				//element.sendKeys(Keys.ARROW_DOWN)  ; 
				//Thread.sleep(600l);
				element.sendKeys(Keys.ARROW_DOWN) ;
				Thread.sleep(600l);
				element.sendKeys(Keys.ARROW_DOWN, Keys.ENTER)  ; 
				} catch (InterruptedException | StaleElementReferenceException |NoSuchElementException e) {
					// TODO Auto-generated catch block
					
				}
				
				return true;
				
	        }
	    });

	     
	};
	
	
}
