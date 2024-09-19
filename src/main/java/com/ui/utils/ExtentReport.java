package com.ui.utils;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("target/report.html");
	ExtentTest logger;
	WebDriver driver;
	
	public void reportSetup(){
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("OS", "Windows");
         //       extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
		extent.attachReporter(spark);
		
	}
	
	public ExtentTest createTest(String name) {
        return extent.createTest(name);
       
    }
}
