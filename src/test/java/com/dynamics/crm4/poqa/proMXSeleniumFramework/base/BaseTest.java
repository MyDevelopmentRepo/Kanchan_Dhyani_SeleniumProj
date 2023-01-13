package com.dynamics.crm4.poqa.proMXSeleniumFramework.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.dynamics.crm4.poqa.proMXSeleniumFramework.factory.DriverFactory;



public class BaseTest {
	
	public WebDriver driver;
    public DriverFactory df;
	public Properties prop;
	public SoftAssert softAssert;
	
	
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
		
		this.softAssert = new SoftAssert();
		
	}

	@AfterTest
	public void tearDown()
	{
		
		driver.quit();
	}
}
