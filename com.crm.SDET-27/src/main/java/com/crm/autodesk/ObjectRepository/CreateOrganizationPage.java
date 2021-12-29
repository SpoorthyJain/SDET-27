package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genericutility.WebDriverUtility;

public class CreateOrganizationPage extends WebDriverUtility //Step 1: Create sepearate class for Web Page
{
	//Step 2: Identify all the WebElements and declare them as private
	@FindBy(name = "accountname")
	private WebElement organizationNameEdt;
	
	@FindBy(name = "industry")
	private WebElement industryDropDown;
	
	@FindBy(name = "accounttype")
	private WebElement accountDropDown;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/select.gif']")
	private WebElement createMemberOfLookUpImg;
	
	@FindBy(id = "search_txt")
	private WebElement searchMemberOfTextEdt;
	
	@FindBy(linkText = "TestYantra")
	private WebElement searchNowMemberOfTextBtn;
	
	@FindBy(className = "dvHeaderText")
	private WebElement implicitWaitCondition;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	
	//Step 3: Initialise the element using Constructor
	public CreateOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Step 4: Utilization by providing Getter methods
	public WebElement getOrganizationNameEdt() 
	{
		return organizationNameEdt;
	}

	public WebElement getIndustryDropDown() 
	{
		return industryDropDown;
	}

	public WebElement getTypeDropDown() 
	{
		return accountDropDown;
	}

	public WebElement getCreateMemberOfLookUpImg() 
	{
		return createMemberOfLookUpImg;
	}

	public WebElement getSearchMemberOfTextEdt() 
	{
		return searchMemberOfTextEdt;
	}

	public WebElement getSearchNowMemberOfTextBtn() 
	{
		return searchNowMemberOfTextBtn;
	}

	public WebElement getSaveBtn() 
	{
		return saveBtn;
	}
	
	public WebElement getImplicitWaitCondition() 
	{
		return implicitWaitCondition;
	}

	//Business Library for Creating Organization
	public void createOrg(String orgName)
	{
		organizationNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
	
	//Business Library for Creating Organization with Industry Dropdown and Type Dropdown
	public void createOrgWithIndustryAndType(String orgName, String industryType, String type)
	{
		organizationNameEdt.sendKeys(orgName);
		select(industryDropDown, industryType);
		select(accountDropDown, type);
		saveBtn.click();
	}
	
	//Business Library for Creating Organization with Type Dropdown
	public void createOrgWithType(String orgName, String type)
	{
		organizationNameEdt.sendKeys(orgName);
		select(accountDropDown, type);
		saveBtn.click();
	}
	
	//Business Library for Creating Organization with Member Of 
	public void createOrgWithMemberOf(String orgName, WebDriver driver, String memberOf)  
	{
		organizationNameEdt.sendKeys(orgName);
		createMemberOfLookUpImg.click();
		switchToWindow(driver, "Account");
		searchMemberOfTextEdt.sendKeys(memberOf);
		searchNowMemberOfTextBtn.click();
		driver.findElement(By.xpath("//a[text()='"+memberOf+"']")).click();
		switchToAlertWindowAndAccept(driver); 
		switchToWindow(driver, "Organizations");
		saveBtn.click();
	}
	
	//Business Library for Element to be Clickable
	public void waitForElementToBeClickAble(WebDriver driver)
	{
		WebElement headerWB = driver.findElement(By.className("dvHeaderText"));
		waitForElementToBeClickAble(driver, headerWB);
	}
	
}
