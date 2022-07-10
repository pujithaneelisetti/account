package com.example.account.controller;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.account.model.Account;
import com.example.account.model.Customer;
import com.example.account.model.TransactionRequest;
import com.example.account.model.TransactionType;
import com.example.account.service.AccountService;
import com.example.account.service.CustomerService;

/**
 * Controller class where all the Rest Endpoints are configured.
 * @author PUJITHA
 *
 */
@RestController
@RequestMapping(path = "/ams")
public class MainController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AccountService accountService;

	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/accounts")
	public List<Account> getAccounts() {
		return accountService.getAllAccounts();
	}

	@GetMapping("/customer/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") Long id) {
		return customerService.getCustomerDetails(id);
	}

	@GetMapping("/account/{accountId}")
	public Account getAccountById(@PathVariable("accountId") Long id) {
		return accountService.getAccountDetails(id);
	}

	@PostMapping("/customer/create")
	public String createCustomer(@RequestBody Customer customer) throws ValidationException {
		return customerService.createCustomer(customer);
	}

	@PostMapping("/account/create/{customerId}")
	public String createAccount(@PathVariable("customerId") Long customerId) throws ValidationException {
		return accountService.createAccountForCustomer(customerId);
	}

	@PutMapping("/customer/update")
	public String updateCustomer(@RequestBody Customer customer) {
		return customerService.updateCustomer(customer);
	}

	@PutMapping("/account/update")
	public String updateAccount(@RequestBody TransactionRequest request)
			throws ValidationException {
		return accountService.updateAmount(request);
	}
	
	@PostMapping("/account/delete/{accountId}")
	public String deleteAccount(@PathVariable("accountId") Long accountId) {
		return accountService.deleteAccount(accountId);
	}
	
	@PostMapping("/customer/delete/{customerId}")
	public String deleteCustomer(@PathVariable("customerId") Long customerId) {
		return customerService.deleteCustomer(customerId);
	}

}
