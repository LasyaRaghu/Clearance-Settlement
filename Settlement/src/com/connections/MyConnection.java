package com.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	
	private static Connection con;
	
	static public Connection openConnection()
	{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver loaded successfully");
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433 ;databaseName=CITI", "sa","sa123");
			System.out.println("connection successful");
			
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return con;
	}
}
