package com.dynamics.crm4.poqa.proMXSeleniumFramework.tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dynamics.crm4.poqa.proMXSeleniumFramework.base.BaseTest;
import com.dynamics.crm4.poqa.proMXSeleniumFramework.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;



@Epic("Epic-1: Design Time entries & Approval Flows")
@Story("US-1:Design Time entries Creation")
public class MyWeeklyTimeEntriesTest extends BaseTest {
	private String uniqueDesc;
	 
   
	@BeforeClass
	public void setupHomePageTests()
	{
		uniqueDesc= UUID.randomUUID().toString(); // UUID is used to uniquely identify the submiutted rows in the table containing all submitted/draft rows.
		loginPage = loginUserPage.EnterUserName(prop.getProperty("user"));
		homePage = loginPage.LogMeIn(prop.getProperty("password"));
		salesActivityDashBoardPage = homePage.gotoPageSalesActivityDashBoard();
		 myWeeklyTimeEntriesPage= salesActivityDashBoardPage.gotoPageMyWeeklyTimeEntries();
	}
	
	@Test()
	@Description("Verify New time entry is created and Submitted")
	public void createTimeEntryAndSubmitTest() throws ParseException, InterruptedException
	{
		System.out.println(uniqueDesc);
		SimpleDateFormat sdf = new SimpleDateFormat(AppConstants.NEW_TIME_DF);
		   Calendar cal = Calendar.getInstance();  
		 int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); 
		  
	   if(dayOfWeek >= 2 && dayOfWeek <= 6)// taking the date after 7 days (9 for weekends) to avoid the failure when 24 hrs are filled for a day
		   cal.add(Calendar.DAY_OF_MONTH,7);
	   else
	      cal.add(Calendar.DAY_OF_MONTH,9);   
		
		 
		  Date dt = cal.getTime();
		String actual =myWeeklyTimeEntriesPage.populateTimeEntries(sdf.format(dt), uniqueDesc);
		
		Assert.assertEquals(actual, "Submitted");
	}
	
	
}
