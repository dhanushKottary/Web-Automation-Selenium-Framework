package com.ui.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManagerUseNode {

	private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> childTest = new ThreadLocal<>();
	
    public synchronized static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/report.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
    
    public synchronized static ExtentTest createParentTest(String className) {
        ExtentTest test = getInstance().createTest(className);  // Create parent test
        parentTest.set(test);
        return parentTest.get();
    }
    
    public synchronized static ExtentTest createChildTest(String testName) {
        ExtentTest child = parentTest.get().createNode(testName);  // Create child test under parent
        childTest.set(child);
        return childTest.get();
    }
    
    public static ExtentTest getChildTest() {
        return childTest.get();
    }
}
