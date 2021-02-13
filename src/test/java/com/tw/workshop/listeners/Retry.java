package com.tw.workshop.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

import com.tw.workshop.utils.ReadProperties;

public class Retry implements IRetryAnalyzer, ITestNGListener {
	
	private int count = 0;

	public boolean retry(ITestResult result) {		
		if(!result.isSuccess() && count < Integer.parseInt(ReadProperties.readConfig("No.of.retry"))) {			
			count++;
			return true;
		}
		return false;
	}

}