package com.dynamics.crm4.poqa.proMXSeleniumFramework.pages;

import static com.dynamics.crm4.poqa.proMXSeleniumFramework.constants.AppConstants.CHOOSE_LOGIN_TYPE_PAGE_TITLE;
import static com.dynamics.crm4.poqa.proMXSeleniumFramework.constants.AppConstants.LONG_DEFAULT_TIME_OUT;
import static com.dynamics.crm4.poqa.proMXSeleniumFramework.constants.AppConstants.MEDIUM_DEFAULT_TIME_OUT;
import static com.dynamics.crm4.poqa.proMXSeleniumFramework.constants.AppConstants.SMALL_DEFAULT_TIME_OUT;
import static com.dynamics.crm4.poqa.proMXSeleniumFramework.constants.AppConstants.VERY_LONG_DEFAULT_TIME_OUT;

import java.text.ParseException;
import java.time.Duration;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	private By projectTask = By.xpath("//input[@aria-label='Project Task, Lookup']");
	private By description = By.xpath("//input[contains(@id, 'description.fieldControl-text-box-text')]");
	private By showAsButton = By.xpath("//span[text()='Show As']");
	private By readOnlyButton = By.xpath("//span[text()='Read Only Grid']");
	private By descriptionHeader = By.xpath("//label[@title = 'Description']");
	private By descFilterBy = By.xpath("//span[text()='Filter by']");
	private By inputFilterBy = By.xpath("//input[@aria-label='Filter by value']");
	private By filterApplyButton = By.xpath("//button[@type='submit' and contains(@class,'ms-Button')]");
	private By entryStatus = By.xpath("//select[contains(@id,'msdyn_entrystatus.fieldControl-option-set-select')]");
	private By submitTimeEntry = By.xpath("//button[(@aria-label='Submit')]");
	private By userImageLink = By.xpath("//div[contains(@id,'mectrl_headerPicture')]");
	private By signOut = By.xpath("//button[contains(@id,'mectrl_body_signOut')]");
	private By userAnotherAccount = By.xpath("//div[@id='otherTileText']");

	public MyWeeklyTimeEntriesPage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
	}

	public String populateTimeEntries(String dt, String uniqueDesc) throws ParseException, InterruptedException {
		Thread.sleep(3000l);
		el.waitForElementPresence(newTimeEntry, MEDIUM_DEFAULT_TIME_OUT).click();
		el.waitForElementVisibility(startDate, SMALL_DEFAULT_TIME_OUT).sendKeys(dt);
		el.waitForElementVisibility(endDate, SMALL_DEFAULT_TIME_OUT).sendKeys(dt);
		el.waitForElementVisibility(description, SMALL_DEFAULT_TIME_OUT).sendKeys(Keys.chord(Keys.CONTROL, "a"),
				uniqueDesc);
		el.waitForElementVisibility(duration, SMALL_DEFAULT_TIME_OUT).sendKeys(Keys.chord(Keys.CONTROL, "a"),
				"30 minutes");
		el.waitForElementVisibility(startTime, SMALL_DEFAULT_TIME_OUT).sendKeys(Keys.chord(Keys.CONTROL, "a"), "08:00");
		el.waitForElementVisibility(endTime, SMALL_DEFAULT_TIME_OUT).sendKeys(Keys.chord(Keys.CONTROL, "a"), "08:30");

		selectValueFromLookUp(projectLookup, "Sof");
		selectValueFromLookUp(projectTask, null);
		el.waitForElementVisibility(saveAndClose, MEDIUM_DEFAULT_TIME_OUT).click();

		Thread.sleep(12000l); // There is a small dynamic div (view entry box) which gives element not
								// interactable as its take the focus and take a while to disappear

		goToTimeEntryAndSubmit(uniqueDesc);
		Thread.sleep(8000l); // Added because the field entryStatus takes some time to have updated value,
								// else its value is coming as draft

		return el.waitForElementVisibility(entryStatus, SMALL_DEFAULT_TIME_OUT).getAttribute("title");

	}

	public LoginUserPage logOutUserAndGoToLOginScreen() {
		el.waitForElementVisibility(userImageLink, SMALL_DEFAULT_TIME_OUT).click();
		el.waitForElementVisibility(signOut, MEDIUM_DEFAULT_TIME_OUT).click();
		el.waitForTitleToBe(VERY_LONG_DEFAULT_TIME_OUT, CHOOSE_LOGIN_TYPE_PAGE_TITLE);
		el.waitForElementVisibility(userAnotherAccount, MEDIUM_DEFAULT_TIME_OUT).click();

		return new LoginUserPage(driver);
	}

	public void goToTimeEntryAndSubmit(String uniqueDesc) {

		viewTimeEntry(uniqueDesc);
		el.waitForElementVisibility(submitTimeEntry, MEDIUM_DEFAULT_TIME_OUT).click();

	}

	public WebElement fluentWait(final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(40L))
				.pollingEvery(Duration.ofSeconds(2l)).ignoring(ElementClickInterceptedException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});

		return foo;
	};

	public void viewTimeEntry(String uniqueDesc) {
		try {
			By rowToClick = By.xpath("//span[text()='" + uniqueDesc + "']");
			Thread.sleep(3000); // this is added here as showAs button takes some time to be fully loaded and
								// clickable

			fluentWait(showAsButton).click();
			el.waitForElementVisibility(readOnlyButton, MEDIUM_DEFAULT_TIME_OUT).click();
			el.waitForElementVisibility(descriptionHeader, MEDIUM_DEFAULT_TIME_OUT).click();
			el.waitForElementVisibility(descFilterBy, MEDIUM_DEFAULT_TIME_OUT).click();
			el.waitForElementVisibility(inputFilterBy, MEDIUM_DEFAULT_TIME_OUT).sendKeys(uniqueDesc);

			Thread.sleep(3000l); // added coz apply button is fully loaded after some time only and then only
									// clickable

			el.waitForElementVisibility(filterApplyButton, MEDIUM_DEFAULT_TIME_OUT).click();
			el.waitForElementVisibility(rowToClick, LONG_DEFAULT_TIME_OUT).click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectValueFromLookUp(final By locator, final String val) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(MEDIUM_DEFAULT_TIME_OUT));

		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {

				try {
					WebElement element = driver.findElement(locator);
					element.click();
					if (StringUtils.isNotBlank(val))
						element.sendKeys(Keys.chord(Keys.CONTROL, "a"), val);

					Thread.sleep(600l);

					element.sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(600l);
					element.sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(600l);
					element.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
				} catch (InterruptedException | StaleElementReferenceException | NoSuchElementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return true;
			}
		});

	}

	public String getTimeEntryStatus(String uniqueDesc) {
		viewTimeEntry(uniqueDesc);
		return el.waitForElementVisibility(entryStatus, SMALL_DEFAULT_TIME_OUT).getAttribute("title");
	};

}
