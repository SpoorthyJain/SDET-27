package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genericutility.WebDriverUtility;

public class ContactsInfoPage extends WebDriverUtility //Step 1: Create sepearate class for Web Page
{
	//Step 2: Identify all the WebElements and declare them as private
	@FindBy(className = "dvHeaderText")
	private WebElement contactHeaderInfoText;
	
	@FindBy(id = "mouseArea_Organization Name")
	private WebElement contactOrgInfoText;
	
	//Step 3: Initialise the element using Constructor
	public ContactsInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Step 4: Utilization by providing Getter methods	

	public WebElement getContactHeaderInfoText() 
	{
		return contactHeaderInfoText;
	}
	
	public WebElement getContactOrgInfoText() 
	{
		return contactOrgInfoText;
	}

	//Business Library for Confirmation of Contact Creation
	public String getContactInfo()
	{
		return (contactHeaderInfoText.getText());
	}
	
	//Business Library for Confirmation of Organization Linked to Contact
	public String getOrganizationInfo()
	{
		return (contactOrgInfoText.getText());
	}
}
