package com.ui.driver;

import java.util.Objects;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
	
	
	private static final ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
	
	public static void setDriver(WebDriver driver) {
		dr.set(driver);
	}
	
	public static WebDriver getDriver() {
		return dr.get();
	}
	
	public static void unload() {
		dr.remove();
	}
	
	public synchronized static void init() {
		if(Objects.isNull(DriverManager.getDriver()) || isSessionInvalid()) {
			
			WebDriver driver = new ChromeDriver();
			setDriver(driver);
		}
	}
	
	public static void down() {
		dr.get().quit();
	}
	
    private static boolean isSessionInvalid() {
        try {
            // Try to get the current window handle to check if session is valid
            dr.get().getWindowHandle();
            return false;
        } catch (NoSuchSessionException | IllegalStateException e) {
            // Session is invalid or closed
            return true;
        }
    }

}
