package com.crm.autodesk.contacttest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.autodesk.ObjectRepository.ContactsInfoPage;
import com.crm.autodesk.ObjectRepository.ContactsPage;
import com.crm.autodesk.ObjectRepository.CreateContactPage;
import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.genericutility.BaseClass;

@Listeners(com.crm.autodesk.genericutility.ListenerImplementation.class)
public class CreateContactTest extends BaseClass 
{
	@Test(groups = "smokeSuite")
	public void createContactTest() throws Throwable 
	{
		//Get random numbers
		int ranDomNum = jLib.getRanDomNumber();
		
		//Read the data from excel sheet
		String lastName = eLib.getDataFromExcel("Sheet2", 1, 2) + ranDomNum;
		
		//Step 1: Navigate to Contacts Module
		HomePage homePg = new HomePage(driver);
		homePg.clickOnContactsLink();
		
		//Step 2: Click On "Create Contacts Button"
		ContactsPage contactPg = new ContactsPage(driver);
		contactPg.clickOnCreateContactLookUp();
		
		//Step 3: Enter last name and create new contact
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.createContact(lastName);
		
		//Step 4: Verify the last name in header of the message
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String actSuc_msg = cip.getContactInfo();
		Assert.assertTrue(actSuc_msg.contains(lastName));
		Reporter.log(actSuc_msg+" ***Contact is Successfully Created***", true);
	}
}
