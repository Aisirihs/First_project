package com.capgemini.service;
import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientOpeningAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.repo.AccountRepo;
import com.capgemini.test.AccountServiceImplTest;

public class AccountServiceImpl implements AccountService {
	AccountRepo accountRepo;
	public AccountServiceImpl(AccountRepo accountRepo) {
		super();
		this.accountRepo= accountRepo;
	}

	@Override
	public Account createAccount(int accountNumber, int amount) throws InsufficientOpeningAmountException {

		if(amount<500)
		{
			throw new InsufficientOpeningAmountException();
		}
		AccountServiceImplTest.account.setAccountNumber(accountNumber);
		AccountServiceImplTest.account.setAmount(amount);

		if(accountRepo.save(AccountServiceImplTest.account))
		{
			return AccountServiceImplTest.account;
		}
		return AccountServiceImplTest.account;		
	}

	@Override
	public Account depositAmount(int accountNumber, int amount) throws InvalidAccountNumberException {

		if(accountRepo.searchAccount(accountNumber)!= AccountServiceImplTest.account)
		{
			throw new InvalidAccountNumberException();
		}
		AccountServiceImplTest.account.setAmount(AccountServiceImplTest.account.getAmount()+amount);
		return AccountServiceImplTest.account;
	}

	@Override
	public Account withdrawAmount(int accountNumber, int amount) throws InvalidAccountNumberException, InsufficientBalanceException {

		if(accountRepo.searchAccount(accountNumber) != AccountServiceImplTest.account)
		{
			throw new InvalidAccountNumberException();
		}
		if(AccountServiceImplTest.account.getAmount()-amount<0) 
		{
			throw new InsufficientBalanceException();
		}
		AccountServiceImplTest.account.setAmount(AccountServiceImplTest.account.getAmount()-amount);

		return AccountServiceImplTest.account;
	}	
}
	
