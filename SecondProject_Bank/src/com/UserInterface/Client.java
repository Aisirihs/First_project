package com.UserInterface;
import com.Exceptions.InsufficientBalanceException;
import com.Exceptions.InvalidAccountNumberException;
import com.Exceptions.InsufficientOpeningAmountException;
import com.Service.HDFCBank;
public class Client {

	public static void main(String[] args) {
		
		HDFCBank bank=new HDFCBank();
		
		try {
			System.out.println("Create Account");
			System.out.println(bank.createAccount(101,3000));
			System.out.println(bank.createAccount(102,5000));
		}catch(InsufficientOpeningAmountException e) {
			System.out.println("Insufficient opening amount!");
		}
		try {
			System.out.println("Withdrawing Amount");
			System.out.println("New Balance= "+bank.withdrawAmount(101,1000));
			}catch(InsufficientBalanceException e) {
				System.out.println("Insufficient Balance Exception");
			}
		    catch(InvalidAccountNumberException e) {
		    	System.out.println("Invalid account number");
		    }
		    try {
		    	System.out.println("Depositing Amount");
		    	System.out.println("New Balance = "+bank.depositAmount(101,500));
		    }catch(InvalidAccountNumberException e) {
		    	System.out.println("Invalid Account Number");
		    }
		    try {
		    	System.out.println("Transferring fund");
		    	int[] a = bank.fundTransfer(101,102,200);
		    	System.out.println("New balance of Account Number 101=" +a[0] + "\nNew balance of Account Number 102= "+a[1]);
		    
		    }catch(InvalidAccountNumberException e) {
		    	System.out.println("Invalid Account Number");
		    }
		    catch(InsufficientBalanceException e) {
		    	System.out.println("Insufficient balance exception");
		    }
		

	}
}

