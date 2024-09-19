package com.ui.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ui.base.BasePage;
import com.ui.driver.DriverManager;

public class AdminPage extends BasePage{
	
	WebDriver driver;
	By usernameInRecordLocator = By.cssSelector("div:nth-child(2)");
	By userDeleteButton = By.cssSelector("div:nth-child(6) button:nth-child(1)");
	
	@FindBy(css = "div.orangehrm-header-container button")
	WebElement addUserButton;

	@FindBy(xpath = "(//div[@class='oxd-select-wrapper'])[1]/div")
	WebElement userRole;

	@FindBy(xpath = "//div[@role='listbox']//*[contains(text(),'Admin')]")
	WebElement adminOption;

	@FindBy(css = "input[placeholder='Type for hints...']")
	WebElement employeeName;

	@FindBy(xpath = "//div[@role='listbox']//*[contains(text(),'Charles  Carter')]")
	WebElement employeeNameOption;

	@FindBy(xpath = "(//div[@class='oxd-select-wrapper'])[2]/div")
	WebElement status;

	@FindBy(xpath = "//div[@role='listbox']//*[contains(text(),'Enabled')]")
	WebElement enabledOption;

	@FindBy(xpath = "//label[contains(text(),'Username')]/parent::div/following-sibling::div/input")
	WebElement username;

	@FindBy(xpath = "//label[text()='Password']/parent::div/following-sibling::div/input")
	WebElement password;

	@FindBy(xpath = "//label[text()='Confirm Password']/parent::div/following-sibling::div/input")
	WebElement confirmPassword;

	@FindBy(css = "button[type='submit']")
	WebElement saveButton;
	
	@FindBy(css = "div.oxd-table-card div[role='row']")
	List<WebElement> recordTableRows;

	@FindBy(xpath = "//button[text()=' Search ']")
	WebElement searchButton;

	@FindBy(xpath = "//button[text()=' Yes, Delete ']")
	WebElement confirmDeleteButton;

	@FindBy(xpath = "//button[text()=' Reset ']")
	WebElement resetButton;

	@FindBy(xpath = "//div[@class='orangehrm-header-container']/following-sibling::div/div/span")
	WebElement tableHeader;
	
	@FindBy(css = "h6[class*='oxd-topbar-header-breadcrumb-module']")
	WebElement pageHeader;
	
	public AdminPage() {
		
		super();
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	public void addUser() {
		
		clickOnElement(addUserButton);
		clickOnElement(userRole);
		clickOnElement(adminOption);
		setText(employeeName, "Charles Carter");
		clickOnElement(employeeNameOption);
		clickOnElement(status);
		clickOnElement(enabledOption);
		setText(username, "Joseph_Evans101");
		setText(password, "Joseph_Evans101");
		setText(confirmPassword, "Joseph_Evans101");
		clickOnElement(saveButton);
		waitForWebElements(recordTableRows);

	}
	
	public boolean verifyUserIsAdded(String userName) {
		searchUser(userName);
		waitForWebElements(recordTableRows);
		System.out.println(recordTableRows.size());
		boolean isUserAdded = false;
		for(WebElement row: recordTableRows) {
			String actualUsername = getText(getWebElement(row, usernameInRecordLocator));
			System.out.println(actualUsername);
			if(actualUsername.equals(userName)) {
				isUserAdded = true;
				break;
			}
		}
		return isUserAdded;
	}
	
	public void removeUser(String userName) {
		searchUser(userName);
		//waitForWebElements(recordTableRows);
		
		waitUntilTextBecomes(tableHeader, "(1) Record Found");
		System.out.println(recordTableRows.size());
		for(WebElement row: recordTableRows) {
			String actualUsername = getText(getWebElement(row, usernameInRecordLocator));
			System.out.println(actualUsername);
			if(actualUsername.equals(userName)) {
				clickOnElement(getWebElement(row, userDeleteButton));
				break;
			}
		}
		clickOnElement(confirmDeleteButton);
		clickOnElement(resetButton);
		waitForWebElements(recordTableRows);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.oxd-table-card div[role='row']")));
	}
	
	
	public String noRecordsFoundMessage() {
		
		return getText(tableHeader);
	}
	
	public List<WebElement> searchUser(String userName) {
		setText(username, userName);
		clickOnElement(searchButton);
		//waitForWebElements(recordTableRows);
		return recordTableRows;
	}
	
	public void waitForPageToLoad() {
		waitUntilTextBecomes(pageHeader, "Admin");
	}



}
