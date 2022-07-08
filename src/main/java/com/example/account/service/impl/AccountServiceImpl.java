package com.example.account.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.account.model.Account;
import com.example.account.model.Customer;
import com.example.account.model.TransactionType;
import com.example.account.repository.AccountRepository;
import com.example.account.repository.CustomerRepository;
import com.example.account.service.AccountService;

@Service
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

		if (cust == null) {
			throw new ValidationException("Customer does not exist.");
		}

		Account acc = new Account();
		acc.setBalance(0);

		try {
			acc = accountRepository.save(acc);
			
			if(cust.getAccounts() != null) {
				cust.getAccounts().add(acc);
				customerRepository.save(cust);
			}
		} catch (Exception e) {
			return "Account creation failed.";
		}

		return "Account created successfully for Customer Id : " + customerId + ". Account Id is :"
				+ acc.getAccountId();
	}

	@Override
	public String updateAmount(Long customerId, Long accountId, float amount, TransactionType type)
			throws ValidationException {

		Set<Account> accountList = new HashSet<>();
		Account acc = new Account();
		float bal = 0;

		if (amount <= 0.0)
			throw new ValidationException("Invalid Amount.");

		if (customerId == null || accountId == null) {
			throw new ValidationException("Customer Id and Account Id should not be null.");
		}

		Customer cust = customerRepository.findByCustomerId(customerId);

		if (cust != null) {
			accountList = cust.getAccounts();
			acc = accountList.stream().filter(a -> accountId == a.getAccountId()).findAny().orElse(null);

			if (acc != null) {
				bal = acc.getBalance();
				if (TransactionType.CREDIT == type) {
					bal = bal + amount;
				} else if (TransactionType.DEBIT == type) {
					if (amount > bal) {
						throw new ValidationException(
								"Amount requested for withdrawal is greater the available balance.");
					} else {
						bal = bal - amount;
					}
				}

				acc.setBalance(bal);

				try {
					accountRepository.updateAccountBalance(acc.getAccountId(), acc.getBalance());
				} catch (Exception e) {
					return "Credit/Debit Amount failed !!!";
				}
			} else {
				throw new ValidationException("Invalid account id.");
			}
		} else {
			throw new ValidationException("Invalid customer id.");
		}

		return "Amount updated successfully in Account : " + accountId + ". New Balance is : " + acc.getBalance()
				+ " ISK";
	}

	@Override
	public String deleteAccount(Long accountId) {
		try {
			accountRepository.deleteById(accountId);
		} catch(Exception e) {
			return "Account Deletion failed";
		}
		return "Account deleted successfully. Account Id is :"+accountId;
	}

}
