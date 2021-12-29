package practice;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.autodesk.ObjectRepository.ContactsPage;
import com.crm.autodesk.ObjectRepository.CreateOrganizationPage;
import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.LoginPage;
import com.crm.autodesk.ObjectRepository.OrganizationsPage;
import com.crm.autodesk.genericutility.ExcelUtility;
import com.crm.autodesk.genericutility.FileUtility;
import com.crm.autodesk.genericutility.JavaUtility;
import com.crm.autodesk.genericutility.WebDriverUtility;

public class CreateContactAndLinkNewOrganization 
{
	public static void main(String[] args) throws Throwable 
	{
		/*Create object to libraries*/
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		
		//Read the login credentials from property file
		String URL = fLib.getPropertyKeyValue("url");
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD = fLib.getPropertyKeyValue("password");
		String BROWSER = fLib.getPropertyKeyValue("browser");
		
		WebDriver driver;
		if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else
		{
			driver = new ChromeDriver();
		}
		
		//Step 1: Login
		driver.manage().window().maximize();
		wLib.waitForPageToLoad(driver);
		driver.get(URL);
		LoginPage loginPg = new LoginPage(driver);
		loginPg.login(USERNAME, PASSWORD);
		
		//Step 2: Navigate to Organization Module
		HomePage homePg = new HomePage(driver);
		homePg.clickOnOrganizationsLink();
				
		//Step 3: Click On "Create Organisation Button"
		OrganizationsPage orgPg = new OrganizationsPage(driver);
		orgPg.clickOnCreateOrg();
				
		//Step 4: Enter all the detailes and create new Organization
		driver.findElement(By.name("accountname")).sendKeys("Audi");
		Thread.sleep(2500);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

			//Wait for the element to be active
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.waitForElementToBeClickAble(driver);
		
		/*WebElement headerWB = driver.findElement(By.className("dvHeaderText"));
		wLib.waitForElementToBeClickAble(driver, headerWB);*/
		
		//Step 5: Navigate to Contacts Module
		homePg.clickOnContactsLink();
				
		//Step 6: Click On "Create Contacts Button"
		ContactsPage contactPg = new ContactsPage(driver);
		contactPg.clickOnCreateContactLookUp();
				
		//Step 7: Enter last name and create new contact and link organization
		driver.findElement(By.name("lastname")).sendKeys("Spoorthy Jain");
		String parentwindoHandle = driver.getWindowHandle();
		String parentTitle = driver.getTitle();
		driver.findElement(By.xpath("//img[@title='Select']")).click();
		
		Set<String> child = driver.getWindowHandles();
		System.out.println(child);
		for(String b:child)
		{
			driver.switchTo().window(b);
			if (!(driver.getTitle().equals(parentTitle))) 
			{
				break;
			}	
		}
		driver.findElement(By.name("search_text")).sendKeys("Audi");
		driver.findElement(By.name("search")).click();
		WebElement ele = driver.findElement(By.xpath("//a[text()='Audi']"));
		wLib.doubleClickOnElement(driver, ele);
		driver.switchTo().window(parentwindoHandle);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 8: Logout
		homePg.logout(driver);
		driver.close() ;
		System.out.println("***A new organization is created and linked to contact***");
		driver.quit();
	}
}
