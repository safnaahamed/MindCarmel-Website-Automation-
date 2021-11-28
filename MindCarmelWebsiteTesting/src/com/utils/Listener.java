package com.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener{

	public void onTestFailedWithTimeout(ITestResult result) {
		
		String [] temp = result.toString().split("f");
		String locator = temp[1].trim().substring(0, temp[1].trim().length()-1);
		System.out.println("Test Case Failed due to timeout:: "+locator);
	
		
}
	public void onTestSuccess(ITestResult result) {
		String [] temp = result.toString().split("f");
		String locator = temp[1].trim().substring(0, temp[1].trim().length()-1);
		System.out.println("Test Case Passed:: "+locator);

	} 
	public void onTestStart(ITestResult result) {
		String [] temp = result.toString().split("f");
		String locator = temp[1].trim().substring(0, temp[1].trim().length()-1);
		System.out.println("Test Case Started successfully:: "+locator);
	}
	
}
