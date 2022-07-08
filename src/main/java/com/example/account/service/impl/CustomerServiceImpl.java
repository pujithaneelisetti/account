package com.example.account.service.impl;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.account.model.Customer;
import com.example.account.repository.CustomerRepository;
import com.example.account.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerDetails(Long customerId) {
		return customerRepository.findByCustomerId(customerId);
	}

	@Override
	public String createCustomer(Customer customer) throws ValidationException {
		
		if(StringUtils.isEmpty(customer.getFirstName()) || StringUtils.isEmpty(customer.getLastName()) || StringUtils.isEmpty(customer.getPhoneNumber())) {
			throw new ValidationException("First Name, Last Name and Phone Number are mandatory");
		}
		
		Customer cust = new Customer();
		try {
			cust = customerRepository.save(customer);
		}
		catch(Exception e) {
			return "Customer creation failed.";
		}
		
		return "Customer created successfully. Customer Id is :"+cust.getCustomerId();
	}

	@Override
	public String updateCustomer(Customer customer) {
		
		try {
			customerRepository.updateCustomer(customer.getCustomerId(),customer.getFirstName(),customer.getLastName(),customer.getPhoneNumber(),customer.getAge(),customer.getEmailId());
		} catch (Exception e) {
			return "Updation failed !!!";
		}
		return "Customer updated successfully. CustomerId is :" + customer.getCustomerId();
	}

	@Override
	public String deleteCustomer(Long customerId) {
		try {
			customerRepository.deleteById(customerId);
		} catch(Exception e) {
			return "Customer deletion failed !!!";
		}
		return "Customer deleted successfully. Customer Id deleted is :" + customerId;
	}

}
