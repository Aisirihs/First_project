package com.Service;
import java.util.*;

import com.Beans.Account;
import com.Exceptions.InsufficientBalanceException;
import com.Exceptions.InvalidAccountNumberException;
import com.Exceptions.InsufficientOpeningAmountException;

public class HDFCBank implements Bank{
	LinkedList<Account> accounts=new LinkedList<>();
@Override
public String createAccount(int accountNumber, int amount) throws InsufficientOpeningAmountException 
{
	if(amount>=500) {
		Account account=new Account(accountNumber,amount);
		accounts.add(account);
		return "account created successfully";
	}
	else throw new InsufficientOpeningAmountException();
	
}

private Account searchAccount(int accountNumber)throws InvalidAccountNumberException
{
	 for(Account account : accounts)
	 {
		 if(account.getAccountNumber()==accountNumber)
		 {
			 return account;
		 }
	 }
	 throw new InvalidAccountNumberException();
	 
}

@Override
public int withdrawAmount(int accountNumber, int amount)
		throws InvalidAccountNumberException, InsufficientBalanceException 
{
	Account account=searchAccount(accountNumber);
	
	if((account.getAmmount()-amount)>=0)
    {
	account.setAmmount(account.getAmmount()-amount);
	return account.getAmmount();
    }
	throw new InsufficientBalanceException();
}

@Override
public int depositAmount(int accountNumber, int amount) throws InvalidAccountNumberException 
{
	Account account=searchAccount(accountNumber);
	
	account.setAmmount(account.getAmmount()+amount);
	return account.getAmmount();
	
}

@Override
public int[] fundTransfer(int accountNumber1, int accountNumber2, int amount)throws InvalidAccountNumberException, InsufficientBalanceException
{
	Account account1=searchAccount(accountNumber1);
	Account account2=searchAccount(accountNumber2);
	
	if((account1.getAmmount()-amount)>=0)
    {
    	int[] a = {account1.getAmmount()-amount,account2.getAmmount()+amount};
    	account1.setAmmount(account1.getAmmount()-amount);
    	account2.setAmmount(account2.getAmmount()+amount);
    	
    	return a;
    }
    
    else throw new InsufficientBalanceException();

    
}
	

}

