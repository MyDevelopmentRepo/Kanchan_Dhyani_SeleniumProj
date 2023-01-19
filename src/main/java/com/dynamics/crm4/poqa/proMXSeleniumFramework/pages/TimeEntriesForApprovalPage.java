package com.dynamics.crm4.poqa.proMXSeleniumFramework.pages;

import static com.dynamics.crm4.poqa.proMXSeleniumFramework.constants.AppConstants.CHOOSE_LOGIN_TYPE_PAGE_TITLE;
import static com.dynamics.crm4.poqa.proMXSeleniumFramework.constants.AppConstants.LONG_DEFAULT_TIME_OUT;
import static com.dynamics.crm4.poqa.proMXSeleniumFramework.constants.AppConstants.MEDIUM_DEFAULT_TIME_OUT;
import static com.dynamics.crm4.poqa.proMXSeleniumFramework.constants.AppConstants.SMALL_DEFAULT_TIME_OUT;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.dynamics.crm4.poqa.proMXSeleniumFramework.util.ElementUtil;

public class TimeEntriesForApprovalPage {

	private WebDriver driver;
	ElementUtil el;

	// ******Locators***
	private By timeEntryHeader = By.xpath("//div[text() = 'Time Entry']");
	private By descFilterBy = By.xpath("//span[text()='Filter by']");
	private By inputFilterBy = By.xpath("//input[@aria-label='Filter by Value']");
	private By filterApplyButton = By.xpath("//span[text()='Apply' and contains(@class,'ms-Button')]");
	private By approveButton = By.xpath("//span[text() ='Approve']");
	private By OkButtonApproval = By.xpath("//span[contains(@id,'okButtonText')]");
	private By userImageLink = By.xpath("//div[contains(@id,'mectrl_headerPicture')]");
	private By signOut = By.xpath("//button[contains(@id,'mectrl_body_signOut')]");
	private By userAnotherAccount = By.xpath("//div[@id='otherTileText']");
	private By conditionDropdown = By.xpath("//span[text()='Equals']");
	private By conditionValueContains = By.xpath("//span[text()='Contains']");

	public TimeEntriesForApprovalPage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
	}

	public void approveTimeEntry(String uniqueDesc) {

		try {
			Thread.sleep(5000); // this will ensure that showAs button has fully loaded and clickable.
			viewTimeEntryForApproval(uniqueDesc);
			el.waitForElementVisibility(approveButton, MEDIUM_DEFAULT_TIME_OUT).click();
			el.waitForElementVisibility(OkButtonApproval, MEDIUM_DEFAULT_TIME_OUT).click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public LoginUserPage logOutUserAndGoToLOginScreen() {
		el.waitForElementVisibility(userImageLink, SMALL_DEFAULT_TIME_OUT).click();
		el.waitForElementVisibility(signOut, MEDIUM_DEFAULT_TIME_OUT).click();
		el.waitForTitleToBe(LONG_DEFAULT_TIME_OUT, CHOOSE_LOGIN_TYPE_PAGE_TITLE);
		el.waitForElementVisibility(userAnotherAccount, MEDIUM_DEFAULT_TIME_OUT).click();

		return new LoginUserPage(driver);
	}

	public void viewTimeEntryForApproval(String uniqueDesc) {
		By rowToClick = By.xpath("//span[text()='" + uniqueDesc + "']");
		Actions actions = new Actions(driver);
		try {
			Thread.sleep(1000);
			el.waitForElementVisibility(timeEntryHeader, MEDIUM_DEFAULT_TIME_OUT).click();
			el.waitForElementVisibility(descFilterBy, MEDIUM_DEFAULT_TIME_OUT).click();
			el.waitForElementVisibility(conditionDropdown, MEDIUM_DEFAULT_TIME_OUT).click();
			el.waitForElementVisibility(conditionValueContains, MEDIUM_DEFAULT_TIME_OUT).click();
			el.waitForElementVisibility(inputFilterBy, MEDIUM_DEFAULT_TIME_OUT).sendKeys(uniqueDesc.substring(1));
			Thread.sleep(300);
			el.waitForElementVisibility(filterApplyButton, MEDIUM_DEFAULT_TIME_OUT).click();
			Thread.sleep(300); // added coz apply button is fully loaded after some time only and then only // clickable
			actions.doubleClick(el.waitForElementVisibility(rowToClick, MEDIUM_DEFAULT_TIME_OUT)).perform();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WebElement fluentWait(final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(40L))
				.pollingEvery(Duration.ofSeconds(2l)).ignoring(ElementClickInterceptedException.class);

		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
	};

}
