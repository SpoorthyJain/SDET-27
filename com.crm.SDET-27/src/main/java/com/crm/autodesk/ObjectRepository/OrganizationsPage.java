package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genericutility.WebDriverUtility;

public class OrganizationsPage extends WebDriverUtility //Step 1: Create sepearate class for Web Page
{
	//Step 2: Identify all the WebElements and declare them as private
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement createOrgLookUpImg;
	
	@FindBy(name = "search_text")
	private WebElement searchTextEdt;
	
	@FindBy(name = "submit")
	private WebElement searchNowBtn;
	
	//Step 3: Initialise the element using Constructor
	public OrganizationsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Step 4: Utilization by providing Getter methods

	public WebElement getOrganizationsLnk() 
	{
		return createOrgLookUpImg;
	}

	public WebElement getSearchTextEdt() 
	{
		return searchTextEdt;
	}

	public WebElement getSearchNowBtn()
	{
		return searchNowBtn;
	}
	
	//Business Library
	public void clickOnCreateOrg()
	{
		createOrgLookUpImg.click();
	}
}
