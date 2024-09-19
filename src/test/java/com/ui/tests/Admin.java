package com.ui.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.basetest.BaseTest;
import com.ui.pages.AdminPage;
import com.ui.pages.DashboardPage;
import com.ui.pages.LoginPage;

public class Admin extends BaseTest{
	
	AdminPage adminPage;
	@Test(enabled = true)
	public void addUser() {
		
		LoginPage loginPage = new LoginPage();
		loginPage.goToURL("https://opensource-demo.orangehrmlive.com/");
		loginPage.login("Admin", "admin123");
		logInfo("Logged in", true);
		
		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.getPageHeader();
		logInfo("Waiting for dasboard page to load", true);
		
		adminPage = dashboardPage.navigateToAdminPage();
		logInfo("Navigated to admin Page", true);
		adminPage.waitForPageToLoad();
        adminPage.addUser();
        boolean result = adminPage.verifyUserIsAdded("Joseph_Evans101");
        logInfo("Checking if user is added", true);
		Assert.assertTrue(result);
	}
	
	@Test(enabled = true)
	public void removeUser() {
	
		LoginPage loginPage = new LoginPage();
		loginPage.goToURL("https://opensource-demo.orangehrmlive.com/");
		loginPage.login("Admin", "admin123");
		logInfo("Logging into the portal", true);
		
		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.getPageHeader();
		logInfo("Waiting for dashboard page", true);
		
		adminPage = dashboardPage.navigateToAdminPage();
		logInfo("We are in admin page", true);
		adminPage.waitForPageToLoad();
		adminPage.searchUser("Joseph_Evans101");
		logInfo("Searching the user", true);
		adminPage.removeUser("Joseph_Evans101");
		logInfo("Removed the user",true);
		int tableRows = adminPage.searchUser("Joseph_Evans101").size();
		Assert.assertEquals(tableRows, 0);
		String noRecordsFoundMessage = adminPage.noRecordsFoundMessage();
		Assert.assertEquals(noRecordsFoundMessage, "No Records Found");
		logInfo("user is removed successfully",true);
		

	}
	
	

}
