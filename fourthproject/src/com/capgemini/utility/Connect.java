package com.capgemini.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InvalidAccountNumberException;

public class Connect {
	public static Connection connectdb()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/test","root","Aisiri6April");
			return conn;
		}
		catch(Exception e)
		{
			return null;
		}
	}

}
