package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genericutility.WebDriverUtility;

public class OrganizationsInfoPage extends WebDriverUtility //Step 1: Create sepearate class for Web Page
{
	//Step 2: Identify all the WebElements and declare them as private
	@FindBy(className = "dvHeaderText")
	private WebElement orgHeaderText;
	
	@FindBy(id = "dtlview_Industry")
	private WebElement orgIndustryInfoText;
	
	@FindBy(id = "dtlview_Type")
	private WebElement orgTypeInfoText;
	
	//Step 3: Initialise the element using Constructor
	public OrganizationsInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Step 4: Utilization by providing Getter methods		
	public WebElement getOrgHeaderText()
	{
		return orgHeaderText;
	}

	public WebElement getOrgIndustryText() 
	{
		return orgIndustryInfoText;
	}

	public WebElement getOrgTypeText()
	{
		return orgTypeInfoText;
	}
	
	//Business Library for Confirmation of Organization Creation
	public String getOrgInfo()
	{
		return (orgHeaderText.getText());
	}
	
	//Business Library for Industry Selected Confirmation
	public String getIndustryInfo()
	{
		return (orgIndustryInfoText.getText());
	}
	
	//Business Library for Type Selected Confirmation
	public String getTypeInfo()
	{
		return (orgTypeInfoText.getText());
	}
}
