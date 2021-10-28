package org.example.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * Developed by Anand Singh on 26/Oct/2021, 8:21 PM.
 * Copyright (c) 2021. All rights reserved.
 */
public class ExtentReportNG {

    static ExtentReports extent;

    public static ExtentReports getReportObject(){
        String path = System.getProperty("user.dir")+"/extentReports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Anand Singh");
        return extent;
    }
}
