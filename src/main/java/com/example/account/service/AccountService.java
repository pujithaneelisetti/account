package com.example.account.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import com.example.account.model.Account;

public interface AccountService {

	List<Account> getAllAccounts();
	
	Account getAccountDetails(Long accountId);
	
	String createAccountForCustomer(Long customerId) throws ValidationException;
}
