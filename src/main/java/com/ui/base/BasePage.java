package com.ui.base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ui.driver.DriverManager;

public class BasePage {

	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	public BasePage() {
		this.driver = DriverManager.getDriver();
		implicitWait();
		setWebDriverWait();
	}
	
	public void setText(By locator, String text) {
		driver.findElement(locator).sendKeys(Keys.chord(Keys.CONTROL, "a"), text);
	}
	
	public void setText(WebElement wb, String text) {
		wb.sendKeys(Keys.chord(Keys.CONTROL, "a"), text);
	}
	
	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}
	
	public String getText(WebElement wb) {
		return wb.getText();
	}
	
	public WebElement getWebElement(By locator) {
		return driver.findElement(locator);
	}
	
	public WebElement getWebElement(WebElement wb, By locator) {
		return wb.findElement(locator);
	}
	
	private void setWebDriverWait() {
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
	}
	
	public void waitForVisibilityOfElement(By locator) {
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	
	public void waitForWebElement(WebElement wb) {
		this.wait.until(ExpectedConditions.visibilityOf(wb));
	}
	
	public void waitForWebElements(List<WebElement> wbs) {
		this.wait.until(ExpectedConditions.visibilityOfAllElements(wbs));
	}
	
	public void clickOnElement(By locator) {
		this.driver.findElement(locator).click();
	}
	
	public void clickOnElement(WebElement wb) {
		wb.click();
	}
	
	public void goToURL(String url) {
		this.driver.get(url);
	}
	
	public void waitUntilTextBecomes(WebElement wb, String text) {
		this.wait.until(ExpectedConditions.textToBePresentInElement(wb, text));
	}
	
	public void implicitWait() {
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
}
