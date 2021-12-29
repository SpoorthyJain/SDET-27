package com.rmgyantra.testScript;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

public class AddProjectFromGUI 
{
	public static void main(String[] args) throws SQLException 
	{
		//Adding the project
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8085/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		driver.findElement(By.name("projectName")).sendKeys("TestYantra006");
		driver.findElement(By.name("createdBy")).sendKeys("Alia");
		WebElement dropdown = driver.findElement(By.xpath("(//select[@class='form-control'])[2]"));
		Select s=new Select(dropdown);
		s.selectByIndex(1);
		driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
		driver.quit();
		
		
		//Verifying the data from database
		String expectedproject_name="TestYantra006";
		Connection connection = null;
			//register the database
		try
		{
			Driver driver1 = new Driver();
			DriverManager.registerDriver(driver1);
				//establish the connection with database
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
				//issue the statement
			Statement statement = connection.createStatement();
				//execute queries
			ResultSet result = statement.executeQuery("select * from project");
			while(result.next())
			{
				if(result.getString(4).equals(expectedproject_name))
				{
					System.out.println("***Data is present***");
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
			//close the database connection
		finally
		{
		connection.close();
		}
	}
}
