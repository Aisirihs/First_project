package com.Service;
import com.Exceptions.InsufficientBalanceException;
import com.Exceptions.InsufficientOpeningAmountException;
import com.Exceptions.InvalidAccountNumberException;

public interface Bank {
      String createAccount(int accountNumber,int amount)throws InsufficientOpeningAmountException;
      int withdrawAmount(int accountNumber,int amount)throws InvalidAccountNumberException,InsufficientBalanceException;
      int depositAmount(int accountNumber,int amount)throws InvalidAccountNumberException;
      int[] fundTransfer(int accountNumber1,int accountNumber2,int amount)throws InvalidAccountNumberException,InsufficientBalanceException;
}
