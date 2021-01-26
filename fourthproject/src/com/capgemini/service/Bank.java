package com.capgemini.service;

import java.sql.SQLException;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientOpeningAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;

public interface Bank {
	boolean validateAccount(Integer AccNo) throws InvalidAccountNumberException;

	boolean createAccount(int accountnum, int amount) throws InsufficientOpeningAmountException;

	int depositAmount(int accountnum, int amount) throws SQLException;

	int withdrawAmount(int accountnumber, int amount) throws InsufficientBalanceException, SQLException;

	int[] fundTransfer(int sourceAccNo, int recieverAccNo, int amt)
			throws InsufficientBalanceException, SQLException;

}
