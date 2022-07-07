package com.example.account.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import com.example.account.model.Customer;

public interface CustomerService {
	
	List<Customer> getAllCustomers();
	
	Customer getCustomerDetails(Long customerId);
	
	String createCustomer(Customer customer) throws ValidationException;
	
	String updateCustomer(Customer customer);
	
	String deleteCustomer(Long customerId);
}
