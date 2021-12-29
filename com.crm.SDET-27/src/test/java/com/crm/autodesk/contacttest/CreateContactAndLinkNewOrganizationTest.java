package com.crm.autodesk.contacttest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.autodesk.ObjectRepository.ContactsInfoPage;
import com.crm.autodesk.ObjectRepository.ContactsPage;
import com.crm.autodesk.ObjectRepository.CreateContactPage;
import com.crm.autodesk.ObjectRepository.CreateOrganizationPage;
import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.OrganizationsPage;
import com.crm.autodesk.genericutility.BaseClass;

@Listeners(com.crm.autodesk.genericutility.ListenerImplementation.class)
public class CreateContactAndLinkNewOrganizationTest extends BaseClass
{
	@Test(groups = "RegressionSuite")
	public void createContactAndLinkNewOrganizationTest() throws Throwable 
	{
		//Get random numbers
		int ranDomNum = jLib.getRanDomNumber();
		 
		//Read the data from excel sheet-Organization
		String orgName = eLib.getDataFromExcel("Sheet1", 1, 2) + ranDomNum;
		String lastName = eLib.getDataFromExcel("Sheet2", 1, 2) + ranDomNum;
	    
		//Step 1: Navigate to Organization Module
		HomePage homePg = new HomePage(driver);
		homePg.clickOnOrganizationsLink();
		
		//Step 2: Click On "Create Organisation Button"
		OrganizationsPage orgPg = new OrganizationsPage(driver);
		orgPg.clickOnCreateOrg();
		      
		//Step 3: Enter all the detailes and create new Organization
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createOrg(orgName);
	      
			//Wait for the element to be active
		cop.waitForElementToBeClickAble(driver);
		
		/*WebElement headerWB = driver.findElement(By.className("dvHeaderText"));
		wLib.waitForElementToBeClickAble(driver, headerWB);*/
	      
		//Step 4: Navigate to Contacts Module
		homePg.clickOnContactsLink();
		 
		//Step 5: Click On "Create Contacts Button"
		ContactsPage contactPg = new ContactsPage(driver);
		contactPg.clickOnCreateContactLookUp();
		 
		//Step 6: Enter last name and create new contact and link organization
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.createContactWithOrganization(driver, lastName, orgName);
		/*driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
          
		wLib.switchToWindow(driver, "Accounts");
          
		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
          
		wLib.switchToWindow(driver, "Contacts");
          
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();*/
          
		//Step 7: Verify the last name and organization name
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String actSuc_msg = cip.getContactInfo();
		Assert.assertTrue(actSuc_msg.contains(lastName));
		Reporter.log(actSuc_msg+" ***Contact is Successfully Created***", true);
  		  
		String org = cip.getOrganizationInfo();
		SoftAssert sftAssert = new SoftAssert();
		sftAssert.assertTrue(org.contains(orgName));
		Reporter.log(org+" ***Organization is Successfully Linked to Contact***", true);
		sftAssert.assertAll();
	}
}
