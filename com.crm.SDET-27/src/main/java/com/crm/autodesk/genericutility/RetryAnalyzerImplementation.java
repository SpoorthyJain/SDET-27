package com.crm.autodesk.genericutility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * This class provoides implementation for iRetryAnalyzer
 * @author Spoorthy
 *
 */
public class RetryAnalyzerImplementation implements IRetryAnalyzer  
{
	int count = 0;
	
	int retryCount = 2;
	
	public boolean retry(ITestResult result) 
	{ 
		while(count<=retryCount)
		{
			count++;
			return true;
		}
		return false;
	}
}