package com.crm.autodesk.orgtest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.autodesk.ObjectRepository.CreateOrganizationPage;
import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.OrganizationsInfoPage;
import com.crm.autodesk.ObjectRepository.OrganizationsPage;
import com.crm.autodesk.genericutility.BaseClass;

@Listeners(com.crm.autodesk.genericutility.ListenerImplementation.class)
public class CreateOrganizationWithIndustryAndTypeTest extends BaseClass
{
	@Test(groups = "RegressionSuite")
	public void createOrganizationWithIndustryAndTypeTest() throws Throwable 
	{
		//getRandom numbers
		int ranDomNum = jLib.getRanDomNumber();
		
		//read test data from excel file
		String orgName = eLib.getDataFromExcel("Sheet1", 1, 2) + ranDomNum;
		String industry = eLib.getDataFromExcel("Sheet1", 4, 3);
		String type = eLib.getDataFromExcel("Sheet1", 4, 4);

		//Step 1: Navigate to Organization Module
		HomePage homePg = new HomePage(driver);
		homePg.clickOnOrganizationsLink();
		
		//Step 2: Click On "Create Organisation Button"
		OrganizationsPage orgPg = new OrganizationsPage(driver);
		orgPg.clickOnCreateOrg();
		
		//Step 3: Enter all the detailes and create new Organization
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createOrgWithIndustryAndType(orgName, industry, type);
		
		/*driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		WebElement ele = driver.findElement(By.name("industry"));
		wLib.select(ele, industry);
		
		WebElement ele1 = driver.findElement(By.name("accounttype"));
		wLib.select(ele1, type);
		
		driver.findElement(By.name("button")).click();*/
		
		//Step 4: Verify the Organization name, Industry and Type is successfully created
		OrganizationsInfoPage oip = new OrganizationsInfoPage(driver);
		String actSuc_msg = oip.getOrgInfo();
		Assert.assertTrue(actSuc_msg.contains(orgName));
		Reporter.log(actSuc_msg+" ***Organization is Successfully Created***", true);
		
		String industries = oip.getIndustryInfo();
		SoftAssert sftAssert = new SoftAssert();
		sftAssert.assertTrue(industries.contains(industry));
		Reporter.log(industries+" ***Industry is Successfully Added***", true);
		
		String types = oip.getTypeInfo();
		sftAssert.assertTrue(types.contains(type));
		Reporter.log(types+" ***Type is Successfully Added***", true);
		sftAssert.assertAll();
	}
}
