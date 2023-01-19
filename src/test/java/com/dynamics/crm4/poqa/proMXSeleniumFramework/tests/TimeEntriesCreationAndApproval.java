package com.dynamics.crm4.poqa.proMXSeleniumFramework.tests;

import java.text.ParseException;
import java.util.UUID;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.dynamics.crm4.poqa.proMXSeleniumFramework.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("Epic-1: Design Time entries & Approval Flows")
@Story("US-1:Design Time entries Creation")
public class TimeEntriesCreationAndApproval extends BaseTest {
	private String uniqueDesc;

	@BeforeClass
	public void setupTimeEntriesCreationTests() {
		uniqueDesc = UUID.randomUUID().toString(); // UUID is used to uniquely identify the submiutted rows in the table
													// // containing all submitted/draft rows.
		loginPage = loginUserPage.EnterUserName(prop.getProperty("user"));
		homePage = loginPage.LogMeIn(prop.getProperty("password"));
		salesActivityDashBoardPage = homePage.gotoPageSalesActivityDashBoard();
		myWeeklyTimeEntriesPage = salesActivityDashBoardPage.gotoPageMyWeeklyTimeEntries();
	}

	
	
	
	@Test(priority = 1)
	@Description("Verify New time entry is created and Submitted")
	public void createTimeEntryAndSubmitTest() throws ParseException, InterruptedException {

		String actual = myWeeklyTimeEntriesPage.populateTimeEntries(getFormattedDated(), uniqueDesc);
		Assert.assertEquals(actual, "Submitted");
	}

	
	
	
	
	@Test(dependsOnMethods = { "createTimeEntryAndSubmitTest" })
	@Description("Approve the new time entry")
	public void ApproveTimeEntryTest() throws ParseException, InterruptedException {

		// Approve Time entry
		loginUserPage = myWeeklyTimeEntriesPage.logOutUserAndGoToLOginScreen(); // logout so as we can login again from
																				// // managers user
		loginPage = loginUserPage.EnterUserName(prop.getProperty("Manager"));
		timeEntriesForApprovalPage = loginPage.LogMeIn(prop.getProperty("ManagerPwd")).gotoPageTimeEntriesForApproval();
		timeEntriesForApprovalPage.approveTimeEntry(uniqueDesc);

	}

	
	
	
	@Test(dependsOnMethods = { "ApproveTimeEntryTest" })
	@Description("Verify New time entry is Approved")
	public void validateTimeEntryTest() throws ParseException, InterruptedException {

		// Approve Time entry
		loginUserPage = myWeeklyTimeEntriesPage.logOutUserAndGoToLOginScreen(); // logout so as we can login again from
																				// // managers user
		loginPage = loginUserPage.EnterUserName(prop.getProperty("user"));
		myWeeklyTimeEntriesPage = loginPage.LogMeIn(prop.getProperty("password")).gotoPageMyWeeklyTimeEntries();
		String timeEntryStatus = myWeeklyTimeEntriesPage.getTimeEntryStatus(uniqueDesc);
		Assert.assertEquals(timeEntryStatus, "Approved");
	}

}
