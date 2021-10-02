package com.wi.main.util.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	private static final ExtentReports extentReports = new ExtentReports();

	public synchronized static ExtentReports getExtentReports() {
		ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/extent-report.html");
		reporter.config().setReportName("LiveStreamBoard Testing Result");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Company", "Wiinvent");
		extentReports.setSystemInfo("Author", "Shine");
		return extentReports;
	}
}
