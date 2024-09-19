package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.BasePage;
import com.ui.driver.DriverManager;

public class DashboardPage extends BasePage {
	
	WebDriver driver;
	
	@FindBy(css = ".oxd-topbar-header-breadcrumb h6")
	WebElement header;
	
	@FindBy(css = "a[href*='admin/viewAdminModule']")
	WebElement adminLink;
	
	public DashboardPage() {
		
		super();
		this.driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
		
	}
	
	public String getPageHeader() {
		waitForWebElement(header);
		return getText(header);
	}
	
	public AdminPage navigateToAdminPage() {
		clickOnElement(adminLink);
		return new AdminPage();
	}
	
	public void waitForPageToLoad() {
		waitUntilTextBecomes(header, "Dashboard");
	}

}
