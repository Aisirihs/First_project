package com.capgemini.userinterface;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientOpeningAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.service.Bank;
import com.capgemini.service.HDFCBank;
import com.capgemini.service.BankRunner;


public class Client {
public static void main(String[] args) {
		HDFCBank bank=new HDFCBank();
		BankRunner bankrunner=new BankRunner(bank);
		try {
		System.out.println("Account creation");
		System.out.println(bank.createAccount(101,5000));
		System.out.println(bank.createAccount(102,8500));
		Thread firstThread=new Thread(bankrunner,"first");
		firstThread.start();
		Thread secondThread=new Thread(bankrunner,"second");
		secondThread.start();
		System.out.println("After depositing amount in account no 101 is : "+bank.depositAmount(101,3000));
		int []transfer=bank.fundTransfer(101, 102, 3000);
		System.out.println("After fund transfer new balance of sender: "+transfer[0]+"  New balance of receiver: "+transfer[1]);
		}catch(InsufficientOpeningAmountException ins) {
			System.out.println("Insufficient amount for account opening");
		}	catch(InsufficientBalanceException inb) {
			System.out.println("insufficient balance");
		}catch(InvalidAccountNumberException ina) {
			System.out.println("invalid account number");
		}
	}
		
		
}



