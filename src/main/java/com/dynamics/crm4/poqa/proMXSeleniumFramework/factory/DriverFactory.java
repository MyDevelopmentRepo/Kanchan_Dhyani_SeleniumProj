package com.dynamics.crm4.poqa.proMXSeleniumFramework.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author kanchan
 *
 */
public class DriverFactory {
	
	public  static WebDriver driver;
	public Properties prop;
	
	
	/**
	 * This method is used to initialize the driver based on browser name.
	 * @param browserName
	 * @return driver
	 */
	public WebDriver initDriver(String browserName)
	{
		System.out.println("Browser name is : " + browserName);
	//	optionsManager= new OptionsManager(prop);
		if(browserName.equalsIgnoreCase("chrome"))
		{
			
			    WebDriverManager.chromedriver().setup();
			    driver= new ChromeDriver();
			  //  tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			
				WebDriverManager.firefoxdriver().setup();
				driver= new FirefoxDriver();
				
		    
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			
				WebDriverManager.edgedriver().setup();
				driver= new EdgeDriver();
				
		    
		}
		else
		{
			System.out.println("Please pass the right browser Name" );
		}
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
	
	return driver;
	}
	
	

	
	
	
	
	
	
	
	
	public Properties initProp()
	{
		try {
		 this.prop = new Properties();
		 FileInputStream ip = null;
		 
		// String envName = System.getProperty("env");
		 ip = new FileInputStream("./proMXSeleniumFramework/src/test/resources/config/config.properties");
		 
		
		 
		 System.out.println("loading prop");
			this.prop.load(ip);
		 
		 
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return prop;
	}
	

	
	/**
	 * Takes Screenshot and return the path
	 */

	 public  static String getScreenshot(String methodName)
	 {
		File srcFile =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"./screenshot/"+methodName+System.currentTimeMillis()+".png";
		
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	 }
}
