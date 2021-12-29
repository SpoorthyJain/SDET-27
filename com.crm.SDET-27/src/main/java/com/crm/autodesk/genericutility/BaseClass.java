package com.crm.autodesk.genericutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.LoginPage;

/**
 * 
 * @author Spoorthy
 *
 */
public class BaseClass 
{
	public DataBaseUtility dLib = new DataBaseUtility();
	public FileUtility fLib = new FileUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public JavaUtility jLib = new JavaUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public WebDriver driver;
	public static WebDriver sdriver;
	
	@BeforeSuite(groups = {"smokeSuite","RegressionSuite"})
	public void dbConnection()
	{
		dLib.connectToDB();
		System.out.println("========>Database Connection Successfull<========");
	}
	
	//@Parameters("browser")
	@BeforeClass(groups = {"smokeSuite","RegressionSuite"})
	public void launchBrowser() throws Throwable
	{
		//Read the Data
		String BROWSER = fLib.getPropertyKeyValue("browser");
		String URL = fLib.getPropertyKeyValue("url");
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("FIREFOX"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("Invalid Browser");
		}
		sdriver=driver;
		
		wLib.maximizeWindow(driver);
		wLib.waitForPageToLoad(driver);
		driver.get(URL);
		System.out.println("========>Browser Launched<========");
	}
	
	@BeforeMethod(groups = {"smokeSuite","RegressionSuite"})
	public void loginApp() throws Throwable
	{
		//Read the Data
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD = fLib.getPropertyKeyValue("password");
		
		LoginPage loginPg = new LoginPage(driver);
		loginPg.login(USERNAME, PASSWORD);
		System.out.println("========>Login Successfull<========");
	}
	
	@AfterMethod(groups = {"smokeSuite","RegressionSuite"})
	public void logoutOfApp()
	{
		HomePage homePg = new HomePage(driver);
		homePg.logout(driver);
		System.out.println("========>Logout Successfull<========");
	}
	
	@AfterClass(groups = {"smokeSuite","RegressionSuite"})
	public void closeBrowser()
	{
		driver.quit();
		System.out.println("========>Browser Closed<========");
	}
	
	@AfterSuite(groups = {"smokeSuite","RegressionSuite"})
	public void closeDBConnection()
	{
		dLib.closeDB();
		System.out.println("========>Database Connection Closed<========");
	}
}
