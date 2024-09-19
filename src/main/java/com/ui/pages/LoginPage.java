package com.ui.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.BasePage;
import com.ui.driver.DriverManager;

public class LoginPage extends BasePage{
	
    WebDriver driver;
	@FindBy(css = "input[name='username']")
	WebElement inputUsername;
	@FindBy(css = "input[name='password']")
	WebElement inputPassword;
	@FindBy(css = "button[type='submit']")
	WebElement submitButton;
	
	public LoginPage() {
		
		super();
		this.driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	public void login(String username, String password ) {
		
		waitForWebElement(inputUsername);
		setText(inputUsername, username);
		setText(inputPassword, password);
		clickOnElement(submitButton);
	}

}
