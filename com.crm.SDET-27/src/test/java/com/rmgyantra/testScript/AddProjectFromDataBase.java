package com.rmgyantra.testScript;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class AddProjectFromDataBase 
{
	public static void main(String[] args) throws SQLException 
	{
		Connection connection = null;
		try
		{
			//regestering the database
			Driver driver2 = new Driver();
			DriverManager.registerDriver(driver2);
		
			//establish connection with database
		    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		
			//issue the statement
			Statement statement = connection.createStatement();
		
			//execute queries
			int result = statement.executeUpdate("insert into project(project_id, created_by, created_on, project_name, status, team_size) value('TY_PROJ_008', 'Ashwini', '16/12/2021', 'ITC Infotec', 'On Going', '0')");
			if(result==1)
			{
				System.out.println("*-*-One set of data is added to database-*-*");
			}
			else
			{
				System.out.println("*-*-Data is not added to database-*-*");
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
