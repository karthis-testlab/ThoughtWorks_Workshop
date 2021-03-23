package com.tw.workshop.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporter {
	
	public static ExtentHtmlReporter html;
	public static ExtentReports extent;
	private static ThreadLocal<ExtentTest> testCases = new ThreadLocal<ExtentTest>();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	
	public static void createInstance(String fileName) {
		html = new ExtentHtmlReporter("./Reports/"+fileName+".html");		
		html.config().setTestViewChartLocation(ChartLocation.TOP);
		html.config().setChartVisibilityOnOpen(true);
	    html.config().setTheme(Theme.STANDARD);
	    html.config().setDocumentTitle(fileName);
	    html.config().setEncoding("utf-8");
	    html.config().setReportName(fileName);
	    
	    extent = new ExtentReports();
	    extent.attachReporter(html);	
	}
	
	public static synchronized void createTestCases(String testCaseName) {
		ExtentTest parent = extent.createTest(testCaseName);
		testCases.set(parent);
	}
	
	public static synchronized void createTest(String testName) {
		ExtentTest child = testCases.get().createNode(testName);
		test.set(child);
	}
	
	public static synchronized void reportLog(String status, String details) {
		if(status.equalsIgnoreCase("PASS")) {
			test.get().pass(MarkupHelper.createLabel(details, ExtentColor.GREEN));
		} else if(status.equalsIgnoreCase("FAIL")) {
			test.get().fail(MarkupHelper.createLabel(details, ExtentColor.RED));
		} else if(status.equalsIgnoreCase("SKIP")) {
			test.get().skip(MarkupHelper.createLabel(details, ExtentColor.YELLOW));
		}
	}
	
	public static void tearDownInstance() {
		extent.flush();
	}

}