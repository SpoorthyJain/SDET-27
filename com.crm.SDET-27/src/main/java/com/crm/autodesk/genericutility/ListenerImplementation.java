package com.crm.autodesk.genericutility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
/**
 * This class provoides implementation for iTestListener
 * @author Spoorthy
 *
 */
public class ListenerImplementation implements ITestListener
{

	public void onTestStart(ITestResult result) 
	{
		// TODO Auto-generated method stub	
	}

	public void onTestSuccess(ITestResult result)
	{
		// TODO Auto-generated method stub
	}

	public void onTestFailure(ITestResult result)
	{
		//Capture the Method Name
		String methodName = result.getMethod().getMethodName();
		
		//Capture the Current Date
		String currentDate = new JavaUtility().getSystemDateWithFormat();
		
		//Concatenate for Screenshot Name
		String screenShotName = methodName+"_"+currentDate;

		try 
		{
			new WebDriverUtility().takeScreenshot(BaseClass.sdriver, screenShotName);
		} 
		catch (Throwable e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) 
	{
		// TODO Auto-generated method stub	
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		// TODO Auto-generated method stub	
	}

	public void onStart(ITestContext context) 
	{
		// TODO Auto-generated method stub	
	}

	public void onFinish(ITestContext context) 
	{
		// TODO Auto-generated method stub
	}
	
}
