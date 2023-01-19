package com.dynamics.crm4.poqa.proMXSeleniumFramework.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.dynamics.crm4.poqa.proMXSeleniumFramework.constants.AppConstants;
import com.dynamics.crm4.poqa.proMXSeleniumFramework.factory.DriverFactory;
import com.dynamics.crm4.poqa.proMXSeleniumFramework.pages.HomePage;
import com.dynamics.crm4.poqa.proMXSeleniumFramework.pages.LoginPage;
import com.dynamics.crm4.poqa.proMXSeleniumFramework.pages.LoginUserPage;
import com.dynamics.crm4.poqa.proMXSeleniumFramework.pages.MyWeeklyTimeEntriesPage;
import com.dynamics.crm4.poqa.proMXSeleniumFramework.pages.SalesActivityDashboardPage;
import com.dynamics.crm4.poqa.proMXSeleniumFramework.pages.TimeEntriesForApprovalPage;

public class BaseTest {

	public WebDriver driver;
	public DriverFactory df;
	public Properties prop;
	public SoftAssert softAssert;
	public LoginUserPage loginUserPage;
	public LoginPage loginPage;
	public HomePage homePage;
	public MyWeeklyTimeEntriesPage myWeeklyTimeEntriesPage;
	public SalesActivityDashboardPage salesActivityDashBoardPage;
	public TimeEntriesForApprovalPage timeEntriesForApprovalPage;

	@Parameters({ "browser" })
	@BeforeTest
	public void setup(String browserName) {

		this.df = new DriverFactory();
		this.prop = df.initProp();
		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}
		this.driver = df.initDriver(prop.getProperty("browser"));
		loginUserPage = new LoginUserPage(driver);
	}

	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	
	
	
	public String getFormattedDated() {
		SimpleDateFormat sdf = new SimpleDateFormat(AppConstants.NEW_TIME_DF);
		Calendar cal = Calendar.getInstance();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		if (dayOfWeek >= 2 && dayOfWeek <= 6)// taking the date after 7 days (9 for weekends) to avoid the failure when
												// 24 hrs are filled for a day
			cal.add(Calendar.DAY_OF_MONTH, 7);
		else
			cal.add(Calendar.DAY_OF_MONTH, 9);

		Date dt = cal.getTime();
		return sdf.format(dt);
	}
}
