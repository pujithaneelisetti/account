package com.example.account.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import com.example.account.model.Customer;

/**
 * Interface for defining various operations on Customer entity
 * @author PUJITHA
 *
 */
public interface CustomerService {
	
	List<Customer> getAllCustomers();
	
	Customer getCustomerDetails(Long customerId);
	
	String createCustomer(Customer customer);
	
	String updateCustomer(Customer customer);
	
	String deleteCustomer(Long customerId);
}
