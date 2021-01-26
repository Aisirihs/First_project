package com.capgemini.utility;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.capgemini.beans.Account;

public class DatabaseCode {
	Connection conn = null;
	ResultSet rs = null;
	Statement st = null;

	public boolean searchData(int account_Number)
	{
		try {
			conn  = Connect.connectdb();
			st = conn.createStatement();
			String select_query = "select * from bank where accountnumber = '"+account_Number+"'";
			rs = st.executeQuery(select_query);
			return rs.next();
		}
		catch(Exception e){
			return false;
		}	
	}

	public boolean insertData(Account account) {

		boolean flag = false;
		try {
			conn  = Connect.connectdb();
			st = conn.createStatement();
			String insert_query = "insert into bank(accountnumber, amount) " + "values ('"+account.getAccountnumber()+"','"+account.getAmount()+"')";
			int row = st.executeUpdate(insert_query);
			if(row>0)
			{
				flag = true;
				return flag;
			}
			else {
				return flag;				
			}
		}
		catch(Exception e){
			return flag;
		}	
	}

	public int updateData(Account account)
	{
		int flag = 0;
		try {
			conn  = Connect.connectdb();
			st = conn.createStatement();
			String update_query = "update bank set amount ='"+account.getAmount()+"'where accountnumber="+account.getAccountnumber();
			int row = st.executeUpdate(update_query);
			if(row>0)
			{
				flag = 1;
				return flag;
			}
			else {
				flag = 0;
				return flag;				
			}
		}
		catch(Exception e){

		}	
		return flag;
	}


	public Account fetchData(int accountNo) throws SQLException
	{
		//		try {
		conn = Connect.connectdb();
		st = conn.createStatement();
		String fetch_query = "select * from bank where accountnumber = '"+accountNo+"'";
		rs = st.executeQuery(fetch_query);
		rs.next();
		Account account = new Account();
		account.setAccountnumber(rs.getInt("accountnumber"));
		account.setAmount(rs.getInt("amount"));
		return account;
		//		}
		//		catch(Exception e) {
		//			return "Error!!!";
	}
}
