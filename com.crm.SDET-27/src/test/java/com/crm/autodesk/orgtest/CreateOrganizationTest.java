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
public class CreateOrganizationTest extends BaseClass
{
	@Test(groups = "smokeSuite")
	public void createOrganizationTest() throws Throwable  
	{
		//getRandom numbers
		int ranDomNum = jLib.getRanDomNumber();
		
		//read test data from excel file
		String orgName = eLib.getDataFromExcel("Sheet1", 1, 2) + ranDomNum;
		
		//Step 1: Navigate to Organization Module
		HomePage homePg = new HomePage(driver);
		homePg.clickOnOrganizationsLink();
		
		//Step 2: Click On "Create Organisation Button"
		OrganizationsPage orgPg = new OrganizationsPage(driver);
		orgPg.clickOnCreateOrg();
		
		//Step 3: Enter all the detailes and create new Organization
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createOrg(orgName);
		
		//Step 4: Verify the Organization name in header of the message
		OrganizationsInfoPage oip = new OrganizationsInfoPage(driver);
		String actSuc_msg = oip.getOrgInfo();
		Assert.assertTrue(actSuc_msg.contains(orgName));
		Reporter.log(actSuc_msg+" ***Organization is Successfully Created***", true);
	}
}
