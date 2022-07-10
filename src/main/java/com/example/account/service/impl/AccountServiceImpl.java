package com.example.account.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.account.model.Account;
import com.example.account.model.Customer;
import com.example.account.model.TransactionRequest;
import com.example.account.model.TransactionType;
import com.example.account.repository.AccountRepository;
import com.example.account.repository.CustomerRepository;
import com.example.account.service.AccountService;

/**
 * Implementation class for defining various operations on Account entity
 * 
 * @author PUJITHA
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Method to get all Accounts data
	 */
	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	/**
	 * Method to get Account data based on accountId
	 */
	@Override
	public Account getAccountDetails(Long accountId) {
		// Check if accountId is valid
		validateAccountId(accountId);

		return accountRepository.findByAccountId(accountId);
	}

	/**
	 * Method to create a new account for a customer
	 */
	@Override
	public String createAccountForCustomer(Long customerId) throws ValidationException {

		// Check if customerId is valid
		validateCustomerId(customerId);

		Customer cust = customerRepository.findByCustomerId(customerId);

		Account acc = new Account();
		acc.setBalance(0);

		try {
			acc = accountRepository.save(acc);

			// Adding newly created account to existing account list for a customer
			if (cust.getAccounts() != null) {
				cust.getAccounts().add(acc);
				customerRepository.save(cust);
			}
		} catch (Exception e) {
			return "Account creation failed.";
		}

		return "Account created successfully for Customer Id : " + customerId + ". Account Id is :"
				+ acc.getAccountId();
	}

	/**
	 * Method to create a update account for a customer
	 */
	@Override
	public String updateAmount(TransactionRequest request) throws ValidationException {

		Set<Account> accountList = new HashSet<>();
		Account acc = new Account();
		float bal = 0;

		// Check if the request is valid
		validateTransactionRequest(request);

		// Check if the customer id is valid
		validateCustomerId(request.getCustomerId());

		// Check if the account id is valid
		validateAccountId(request.getAccountId());

		Customer cust = customerRepository.findByCustomerId(request.getCustomerId());

		accountList = cust.getAccounts();
		acc = accountList.stream().filter(a -> request.getAccountId() == a.getAccountId()).findAny().orElse(null);

		bal = acc.getBalance();
		if (TransactionType.CREDIT == request.getType()) {
			// Adding balance if the transaction type is CREDIT
			bal = bal + request.getAmount();
		} else if (TransactionType.DEBIT == request.getType()) {
			// Check if the debit amount is greater than available balance
			if (request.getAmount() > bal) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"Amount requested for withdrawal is greater the available balance.");
			} else {
				// Subtracting balance if the transaction type is DEBIT
				bal = bal - request.getAmount();
			}
		}
		acc.setBalance(bal);

		try {
			// Repository call to update balance in account entity
			accountRepository.updateAccountBalance(acc.getAccountId(), acc.getBalance());
		} catch (Exception e) {
			return "Credit/Debit Amount failed !!!";
		}

		return "Amount updated successfully in Account : " + request.getAccountId() + ". New Balance is : "
				+ acc.getBalance() + " ISK";
	}

	/**
	 * Method to delete account for a customer
	 */
	@Override
	public String deleteAccount(Long accountId) {
		validateAccountId(accountId);
		try {
			accountRepository.deleteById(accountId);
		} catch (Exception e) {
			return "Account Deletion failed";
		}
		return "Account deleted successfully. Account Id is :" + accountId;
	}

	/**
	 * Method to validate account id
	 */
	private void validateAccountId(Long accountId) {
		Account acc = accountRepository.findByAccountId(accountId);
		if (acc == null || (acc != null && acc.getAccountId() == 0)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Account Id");
		}
	}

	/**
	 * Method to validate customer id
	 */
	private void validateCustomerId(Long customerId) {
		Customer cust = customerRepository.findByCustomerId(customerId);
		if (cust == null || (cust != null && cust.getCustomerId() == 0)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Customer Id");
		}
	}

	/**
	 * Method to validate TransactionRequest
	 */
	private void validateTransactionRequest(TransactionRequest request) {
		if (request.getAmount() <= 0.0)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Amount.");

		if (request.getCustomerId() == null || request.getAccountId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer Id and Account Id should not be null.");
		}
	}

}
