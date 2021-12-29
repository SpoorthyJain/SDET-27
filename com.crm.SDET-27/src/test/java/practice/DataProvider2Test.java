package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvider2Test 
{
	@Test(dataProvider = "getData")
	public void readDataFromDataProviderTest(String Name, int qty, String colour)
	{
		System.out.println("Shoes Brand--->"+Name+"---Quantity--->"+qty+"---Colour--->"+colour);
		System.out.println(" ");
	}
	
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] objArr = new Object[5][3];
		
		objArr[0][0] = "Puma";
		objArr[0][1] = 10;
		objArr[0][2] = "Off-White";
		
		objArr[1][0] = "Nike";
		objArr[1][1] = 20;
		objArr[1][2] = "Blue";
			
		objArr[2][0] = "Converse";
		objArr[2][1] = 30;
		objArr[2][2] = "Red";
		
		objArr[3][0] = "One8";
		objArr[3][1] = 40;
		objArr[3][2] = "Black";
		
		objArr[4][0] = "Gucci";
		objArr[4][1] = 10;
		objArr[4][2] = "Green";
		
		return objArr;
			
	}
}
