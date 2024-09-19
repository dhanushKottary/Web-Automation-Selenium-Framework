package com.ui.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.basetest.BaseTest;
import com.ui.pages.DashboardPage;
import com.ui.pages.LoginPage;

public class Login extends BaseTest{

	@Test
	public void login() {
		
		LoginPage loginPage = new LoginPage();
		logInfo("Opening URL: https://opensource-demo.orangehrmlive.com/", false);
		loginPage.goToURL("https://opensource-demo.orangehrmlive.com/");
		loginPage.login("Admin", "admin123");
		logInfo("Entered user credentials", true);
		
		
		DashboardPage dashboardPage = new DashboardPage();
		String pageHeader = dashboardPage.getPageHeader();
		logInfo("Waiting for DashboardPage to open", true);
		Assert.assertEquals(pageHeader, "Dashboard");
	}
	
	@Test(enabled = true)
	public void login2() {
		
		LoginPage loginPage = new LoginPage();
		logInfo("Opening URL: https://opensource-demo.orangehrmlive.com/", true);
		loginPage.goToURL("https://opensource-demo.orangehrmlive.com/");
		loginPage.login("Admin", "admin123");
		logInfo("Entered user credentials", true);
		
		DashboardPage dashboardPage = new DashboardPage();
		String pageHeader = dashboardPage.getPageHeader();
		logInfo("Waiting for DashboardPage to open", false);
		Assert.assertEquals(pageHeader, "Dashboard");
	}
}
