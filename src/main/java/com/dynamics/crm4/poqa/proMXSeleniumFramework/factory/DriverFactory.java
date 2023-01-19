package com.dynamics.crm4.poqa.proMXSeleniumFramework.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author kanchan
 *
 */

public class DriverFactory {

	public static WebDriver driver;
	public Properties prop;

	/**
	 * This method is used to initialize the driver based on browser name.
	 * 
	 * @param browserName
	 * @return driver
	 */
	public WebDriver initDriver(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		} else {

		}

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));

		return driver;
	}

	public Properties initProp() {
		try {
			this.prop = new Properties();
			FileInputStream ip = null;

			ip = new FileInputStream("./src/test/resources/config/config.properties");

			// log.info("loading prop");
			this.prop.load(ip);

		} catch (IOException e) {
			// log.error("Error :::", e);
		}

		return prop;
	}

	/**
	 * Takes Screenshot and return the path
	 */

	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "./screenshot/" + methodName + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			// log.error("Error :::", e);
		}

		return path;
	}
}
