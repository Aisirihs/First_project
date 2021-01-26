package com.capgemini.service;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientOpeningAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.utility.DatabaseCode;

public class HDFCBank implements Bank{
	DatabaseCode db = new DatabaseCode();

	@Override
	public boolean validateAccount(Integer AccNo) throws InvalidAccountNumberException
	{	
		if(db.searchData(AccNo))
			return true;
		else	
			throw new InvalidAccountNumberException();

	}

	@Override
	public boolean createAccount(int accountnum,int amount) throws InsufficientOpeningAmountException
	{
		Account account = new Account();
		if( db.searchData(accountnum))
		{
			return false;
		}
		else {
			account.setAccountnumber(accountnum);
			if(amount>=500)
			{
				account.setAmount(amount);
				boolean flag =db.insertData(account);
				return flag;
			}
			else
				throw new InsufficientOpeningAmountException();
		}		
	}	

	@Override
	public int depositAmount(int accountnum, int amount) throws SQLException {
		Account account = db.fetchData(accountnum);
		account.setAmount(account.getAmount()+amount);
		if(db.updateData(account)==1)
//		if(db.updateData(account))
		{
			return account.getAmount();
		}
		else if(db.updateData(account)==0) {
			return 0;
		}
		return 0;
//		throw new InvalidAccountNumberException();		
	}

	@Override
	public int withdrawAmount(int accountnumber, int amount) throws InsufficientBalanceException,SQLException
	{
		Account account = db.fetchData(accountnumber);
		if(account.getAmount()-amount>=500)
		{
			account.setAmount(account.getAmount()-amount);
			if(db.updateData(account)==1)
//					.updateData(account))
			{
				return account.getAmount();
			}
			else if(db.updateData(account)==0) {
				return 1;
			}
			return 1;
		}
		else
			throw new InsufficientBalanceException();
	}

	@Override
	public int[] fundTransfer(int sourceAccNo,int recieverAccNo, int amt) throws InsufficientBalanceException, SQLException {
		int[] balance =null;
		Account sourceAccount = db.fetchData(sourceAccNo); 
		Account recieverAccount = db.fetchData(recieverAccNo);
		if(sourceAccount.getAmount()-amt>=500)
		{
			sourceAccount.setAmount(sourceAccount.getAmount()-amt);
			recieverAccount.setAmount(recieverAccount.getAmount()+amt);
			if(db.updateData(sourceAccount)==1 && db.updateData(recieverAccount)==1)
			{
				balance = new int[] {sourceAccount.getAmount(), recieverAccount.getAmount()};
				return balance;
			}				
			else
				return null;
		}
			else
				throw new InsufficientBalanceException();
	}	


}
