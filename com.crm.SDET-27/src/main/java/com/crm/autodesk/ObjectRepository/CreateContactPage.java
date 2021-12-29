package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genericutility.WebDriverUtility;

public class CreateContactPage extends WebDriverUtility //Step 1: Create sepearate class for Web Page
{
	//Step 2: Identify all the WebElements and declare them as private
	@FindBy(name = "lastname")
	private WebElement contactLastNameEdt;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement orgNameLookUpImg;
	
	@FindBy(id = "search_txt")
	private WebElement searchOrgTextEdt;
	
	@FindBy(name = "search")
	private WebElement searchNowOrgBtn;
	
	
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	//Step 3: Initialise the element using Constructor
	public CreateContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Step 4: Utilization by providing Getter methods

	public WebElement getContactLastNameEdt() 
	{
		return contactLastNameEdt;
	}

	public WebElement getOrgNameLookUpImg()
	{
		return orgNameLookUpImg;
	}

	public WebElement getSaveBtn() 
	{
		return saveBtn;
	}
	
	public WebElement getSearchOrgTextEdt() 
	{
		return searchOrgTextEdt;
	}

	public WebElement getSearchNowOrgBtn()
	{
		return searchNowOrgBtn;
	}

	//Business Library for Creating Contacts
	public void createContact(String lastName)
	{
		contactLastNameEdt.sendKeys(lastName);
		saveBtn.click();
	}
	
	//Business Library for Creating Contacts With Organization
	public void createContactWithOrganization(WebDriver driver, String lastName, String orgName)
	{
		contactLastNameEdt.sendKeys(lastName);
		orgNameLookUpImg.click();
		switchToWindow(driver, "Accounts");
		searchOrgTextEdt.sendKeys(orgName);
		searchNowOrgBtn.click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		switchToWindow(driver, "Contacts");
		saveBtn.click();
	}
}
