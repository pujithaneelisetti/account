package com.example.account.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import com.example.account.model.Account;
import com.example.account.model.TransactionRequest;

/**
 * Interface for defining various operations on Account entity
 * @author PUJITHA
 *
 */
public interface AccountService {

	List<Account> getAllAccounts();
	
	Account getAccountDetails(Long accountId);
	
	String createAccountForCustomer(Long customerId) throws ValidationException;
	
	String updateAmount(TransactionRequest request) throws ValidationException;
	
	String deleteAccount(Long accountId);
}
