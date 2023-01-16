package com.dynamics.crm4.poqa.proMXSeleniumFramework.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.dynamics.crm4.poqa.proMXSeleniumFramework.factory.DriverFactory;
import com.dynamics.crm4.poqa.proMXSeleniumFramework.pages.HomePage;
import com.dynamics.crm4.poqa.proMXSeleniumFramework.pages.LoginPage;
import com.dynamics.crm4.poqa.proMXSeleniumFramework.pages.LoginUserPage;

import com.dynamics.crm4.poqa.proMXSeleniumFramework.pages.MyWeeklyTimeEntriesPage;
import com.dynamics.crm4.poqa.proMXSeleniumFramework.pages.SalesActivityDashboardPage;



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
	
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName)
	{
		
       
        this.df = new DriverFactory();
        this.prop = df.initProp();
        
        if(browserName != null)
        {
         prop.setProperty("browser", browserName);
        }
        
		this.driver = df.initDriver(prop.getProperty("browser"));
		loginUserPage = new LoginUserPage(driver);
		
		
	}

	@AfterTest
	public void tearDown()
	{
		
		driver.quit();
	}
}
