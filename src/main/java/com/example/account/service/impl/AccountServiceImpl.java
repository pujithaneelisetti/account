package com.example.account.service.impl;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.account.model.Account;
import com.example.account.model.Customer;
import com.example.account.repository.AccountRepository;
import com.example.account.repository.CustomerRepository;
import com.example.account.service.AccountService;

public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public Account getAccountDetails(Long accountId) {
		return accountRepository.findByAccountId(accountId);
	}

	@Override
	public String createAccountForCustomer(Long customerId) throws ValidationException {
		
		Customer cust = customerRepository.findByCustomerId(customerId);
		
		if(cust == null) {
			throw new ValidationException("Customer does not exist.");
		}
		
		Account acc = new Account();
		acc.setCustomer(cust);
		acc.setBalance(0);
		
		try {
			acc = accountRepository.save(acc);
		}
		catch(Exception e) {
			return "Account creation failed.";
		}
		
		return "Account created successfully for Customer Id : "+customerId+". Account Id is :"+ acc.getAccountId();
	}

}
