package com.capgemini.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientOpeningAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.repo.AccountRepo;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;

public class AccountServiceImplTest {
	AccountService accountService;
	public static Account account = new Account();

	
    @Mock
    AccountRepo accountRepo;
    
    
    @Before
	public void setUp() throws Exception {
    	MockitoAnnotations.initMocks(this);
    	accountService = new AccountServiceImpl(accountRepo);
	}

	@Test(expected=com.capgemini.exceptions.InsufficientOpeningAmountException.class)
	public void whenTheAmountIsLessThanFiveHundredSystemShouldThrowException()throws InsufficientOpeningAmountException
	{
		accountService.createAccount(101,300);
	}
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientOpeningAmountException
	{
		account =new Account(101,5000);
		when(accountRepo.save(account)).thenReturn(true);
		assertEquals(account, accountService.createAccount(101, 5000));
	}
	
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenTheAccountNumberIsInvalidDuringDepositSystemShouldThrowException() throws InvalidAccountNumberException,InsufficientOpeningAmountException
	{
		accountService.depositAmount(101, 2000);
	}
	
	@Test
	public void whenTheValidInfoIsPassedAmountShouldBeDeposited() throws InvalidAccountNumberException, InsufficientBalanceException,InsufficientOpeningAmountException
	{	
		account = new Account(101,600);
		when(accountRepo.searchAccount(101)).thenReturn(account);
		assertEquals(account, accountService.depositAmount(101, 5000));
	}
	
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenTheAccountNumberIsInvalidDuringWithdrawSystemShouldThrowException() throws InvalidAccountNumberException, InsufficientBalanceException, InsufficientOpeningAmountException
	{
		accountService.withdrawAmount(100, 2000);
	}
	@Test(expected=com.capgemini.exceptions.InsufficientBalanceException.class)
	public void whenTheAmountWithdrawnIsMoreThanCurrentBalanceSystemShouldThrowException() throws InvalidAccountNumberException, InsufficientBalanceException, InsufficientOpeningAmountException
	{
		account = new Account(101,700);
		when(accountRepo.searchAccount(101)).thenReturn(account);
		accountService.withdrawAmount(101, 800);
	}
	@Test
	public void whenTheValidInfoIsPassedAmountShouldBeWithdrawn() throws InvalidAccountNumberException, InsufficientBalanceException,InsufficientOpeningAmountException
	{
		account = new Account(101,600);
		when(accountRepo.searchAccount(101)).thenReturn(account);
		assertEquals(account, accountService.withdrawAmount(101, 300));
	}
	

}
