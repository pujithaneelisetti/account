package com.example.account.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import com.example.account.model.Account;
import com.example.account.model.TransactionType;

public interface AccountService {

	List<Account> getAllAccounts();
	
	Account getAccountDetails(Long accountId);
	
	String createAccountForCustomer(Long customerId) throws ValidationException;
	
	String updateAmount(Long customerId,Long accountId, float amount, TransactionType type) throws ValidationException;
	
	String deleteAccount(Long accountId);
}
