package org.example.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Developed by Anand Singh on 26/Oct/2021, 8:25 PM.
 * Copyright (c) 2021. All rights reserved.
 */
public class Listener implements ITestListener {

    ExtentReports extent = ExtentReportNG.getReportObject();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentPool = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentPool.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentPool.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentPool.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
