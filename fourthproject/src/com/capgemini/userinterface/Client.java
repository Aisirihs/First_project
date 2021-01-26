package com.capgemini.userinterface;

import java.sql.SQLException;
import java.util.Scanner;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientOpeningAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.service.Bank;
import com.capgemini.service.HDFCBank;

public class Client {

	public static void main(String[] args)throws InsufficientBalanceException, SQLException {
		Bank bank = new HDFCBank();
		Scanner sc = new Scanner(System.in);
		int accNo;
		int amt;
		int bal = -1;
		while (true) {
			System.out.println("MENU:");
			System.out.println("1.Create New Account\n2.Existing Account\n3.EXIT\nENTER YOUR CHOICE");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter Account Number:");
				accNo = sc.nextInt();
				System.out.println("Enter Deposit Amount:");
				Integer deposit = sc.nextInt();
				try {
					if (bank.createAccount(accNo, deposit) == false)
						System.out.println("Unable to open Account : Record exist with this Account Number");
					else
						System.out.println("Account Created Successfully!");
				} catch (InsufficientOpeningAmountException e) {
					System.out.println("UNABLE TO OPEN ACCOUNT : Minimum opening balance is 500");
				}
				break;
			case 2:
				System.out.println("Enter Account Number:");
				accNo = sc.nextInt();
				try {
					if(bank.validateAccount(accNo) ) 
					{
						System.out.println("WELCOME!");
						System.out.println("MENU:");
						System.out.println("1.Deposit\n2.Withdraw\n3.Transfer\n"
								+ "4.Exit\nENTER YOUR CHOICE");
						int ch= sc.nextInt();
						switch (ch)
						{
						case 1:
							System.out.println("Enter Amount:");
							amt = sc.nextInt();
//							bal =bank.depositAmount(accNo, amt);
//							if(bal!=null) {
								System.out.println("Amount Deposit Successfully!");
								System.out.println("Current Balance: "+bank.depositAmount(accNo, amt));
//							}
							break;
						case 2:
							System.out.println("Enter Amount:");
							amt = sc.nextInt();
							try {
								bal = bank.withdrawAmount(accNo, amt);
								if (bal != -1) {
									System.out.println("Amount Withdrawn Successfully!");
									System.out.println("Current Balance: " + bal);
								}
							} catch (InsufficientBalanceException e) {
								System.out.println("INSUFFICIENT BALANCE!");
							}

							break;
						case 3:
							System.out.println("Enter Reciever's Account No:");
							Integer rAcc = sc.nextInt();
							try {
								if (bank.validateAccount(rAcc)) {
									System.out.println("Enter Amount:");
									amt = sc.nextInt();
									try {
										int[] arr = bank.fundTransfer(accNo,rAcc,amt);
										if (arr != null) {
											System.out.println("Amount Trasferred Successfully!");
											System.out.println("Sender's Current Balance:" + arr[0]);
											System.out.println("Reciever's Current Balance:" + arr[1]);
										}
									} catch (InsufficientBalanceException e) {
										System.out.println("INSUFFICIENT BALANCE!");
									}
								}
							} catch (InvalidAccountNumberException e) {
								System.out.println("Reciever's Account Not Found!");
							}

							break;
						case 4:
							System.exit(0);
						default:
							System.out.println("Please Enter Valid Choice!");
						}
					}
				} catch (InvalidAccountNumberException e) {
					System.out.println("INVALID ACCOUNT NUMBER: No record found for entered number");
				}

				break;
			case 3:
				System.exit(0);
			default:
				System.out.println("Please Enter Valid Choice!");
			}
		}

	}

}
