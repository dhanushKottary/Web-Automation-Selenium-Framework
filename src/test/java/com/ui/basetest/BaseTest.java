package com.ui.basetest;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.driver.DriverManager;
import com.ui.utils.ExtentManager;
import com.ui.utils.ExtentManagerUseNode;
import com.ui.utils.TakeScreenshot;

public class BaseTest {
	
//	ExtentReports extent = new ExtentReports();
//	ExtentSparkReporter spark = new ExtentSparkReporter("target/report.html");
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	String methodName;
	
	
	@BeforeSuite
	public void setUpReport() {
//		System.out.println("Setting up the driver..");
//		DriverManager.init();
		//extent.attachReporter(spark);
		
	}
	
    @BeforeClass
    public void setUpClass(ITestContext context) {
        // Get the class name dynamically using ITestContext
        String className = this.getClass().getSimpleName();

        // Log the class name as the parent test in ExtentReports
        //ExtentManager.createParentTest(className);
        ExtentManagerUseNode.createParentTest(className);
    }
	
	
	@BeforeMethod
	public void setUp(Method method) {
		System.out.println("Setting up the driver..");
		DriverManager.init();
		methodName = method.getName();
		//test = ExtentManager.createTest(methodName);
		test.set(ExtentManagerUseNode.createChildTest(methodName));
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			test.get().fail("Test case failed!!");
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			test.get().pass("Test case passed!!");
		}
		DriverManager.down();
	}
	
	@AfterSuite
	public void closeReport() {
		ExtentManagerUseNode.getInstance().flush();
		//DriverManager.down();
	}
	
	public void logInfo(String logMessage, boolean enableScreenshot) {
		test.get().log(Status.INFO, logMessage);
		if(enableScreenshot) {
			try {
				String screenshotPath = TakeScreenshot.getScreenshot(DriverManager.getDriver(),methodName );
				MediaEntityBuilder screenshot = MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath);
				//test.info(logMessage).addScreenCaptureFromPath(screenshotPath);
				test.get().log(Status.INFO, screenshot.build());
				}catch(Exception e) {
					e.printStackTrace();
				}			
		}	
	}
	

}
