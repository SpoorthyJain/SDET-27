package com.crm.autodesk.orgtest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.OrganizationsInfoPage;
import com.crm.autodesk.ObjectRepository.OrganizationsPage;
import com.crm.autodesk.genericutility.BaseClass;

@Listeners(com.crm.autodesk.genericutility.ListenerImplementation.class)
public class CreateOrganizationWith_MemberOfTest extends BaseClass
{
	@Test
	public void createOrganizationWith_MemberOfTest() throws Throwable 
	{
		//Get Random Numbers
		int ranDomNum = jLib.getRanDomNumber();
		
		//Read The Data From Excel Sheet
		String orgName = eLib.getDataFromExcel("Sheet1", 1, 2) + ranDomNum;
		String memberOf = eLib.getDataFromExcel("Sheet1", 1, 3);
		
		//Step 1: Navigate to Organization Module
		HomePage homePg = new HomePage(driver);
		homePg.clickOnOrganizationsLink();
		
		//Step 2: Click On "+"(Create Organisation Button)
		OrganizationsPage orgPg = new OrganizationsPage(driver);
		orgPg.clickOnCreateOrg();
		
		//Step 3: Enter all the detailes and create new Organization
		/*CreateOrganizationPage cop = new CreateOrganizationPage(driver);		
		cop.createOrgWithMemberOf(orgName, driver, memberOf);*/
	
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		
		wLib.switchToWindow(driver, "Accounts");
	
		driver.findElement(By.id("search_txt")).sendKeys(memberOf);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+memberOf+"']")).click();
		wLib.switchToAlertWindowAndAccept(driver);

		wLib.switchToWindow(driver, "Organizations");
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 4: Verifying the Organization name and Member Of is successfully created
		OrganizationsInfoPage oip = new OrganizationsInfoPage(driver);
		String textMsg = oip.getOrgInfo();
		Assert.assertTrue(textMsg.contains(orgName));
		Reporter.log(textMsg+" ***Campaign Information Updated Today***", true);
	}
}
