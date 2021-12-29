package com.crm.autodesk.orgtest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.autodesk.ObjectRepository.CreateOrganizationPage;
import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.OrganizationsInfoPage;
import com.crm.autodesk.ObjectRepository.OrganizationsPage;
import com.crm.autodesk.genericutility.BaseClass;

@Listeners(com.crm.autodesk.genericutility.ListenerImplementation.class)
public class CreateOrganizationWith_TypeTest extends BaseClass
{
	@Test
	public void createOrganizationWith_TypeTest() throws Throwable 
	{
		//Get Random Numbers
		int ranDomNum = jLib.getRanDomNumber();
		
		//Read The Organization Name And Type From Excel Sheet
		String orgName = eLib.getDataFromExcel("Sheet1", 1, 2) + ranDomNum;
		String type = eLib.getDataFromExcel("Sheet1", 4, 4);
		
		//Step 1: Navigate to Organization Module
		HomePage homePg = new HomePage(driver);
		homePg.clickOnOrganizationsLink();
		
		//Step 2: Click On "Create Organisation Button"
		OrganizationsPage orgPg = new OrganizationsPage(driver);
		orgPg.clickOnCreateOrg();
		
		//Step 3: Enter all the detailes and create new Organization
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createOrgWithType(orgName, type);
		
		//Step 4: Verifying the Organization name and Type is successfully created
		OrganizationsInfoPage oip = new OrganizationsInfoPage(driver);
		String textMsg = oip.getOrgInfo() ;
		Assert.assertTrue(textMsg.contains(orgName));
		Reporter.log(textMsg+" ***Campaign Information Updated Today***", true);
	}
	
	@Test
	public void createOrganizationWith_TypeTest1()
	{
		System.out.println("This is Regional Regression Testing");
	}
}