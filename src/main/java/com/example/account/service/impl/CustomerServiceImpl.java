package com.example.account.service.impl;

/**
 * Implementation class for defining various operations on Customer entity
 * @author PUJITHA
 *
 */
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import com.example.account.model.Customer;
import com.example.account.repository.CustomerRepository;
import com.example.account.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Method to get all customers
	 */
	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	/**
	 * Method to get customer details based on id
	 */
	@Override
	public Customer getCustomerDetails(Long customerId) {
		// Check if customer id is valid
		validateCustomerId(customerId);
		return customerRepository.findByCustomerId(customerId);
	}

	/**
	 * Method to create customer
	 */
	@Override
	public String createCustomer(Customer customer) {

		// Check if customer details are valid
		validateCustomerDetails(customer);

		Customer cust = new Customer();
		try {
			cust = customerRepository.save(customer);
		} catch (Exception e) {
			return "Customer creation failed.";
		}

		return "Customer created successfully. Customer Id is :" + cust.getCustomerId();
	}

	/**
	 * Method to update customer details
	 */
	@Override
	public String updateCustomer(Customer customer) {

		// Check if customer id is valid
		validateCustomerId(customer.getCustomerId());

		// Check if customer details are valid
		validateCustomerDetails(customer);

		try {
			customerRepository.updateCustomer(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(),
					customer.getPhoneNumber(), customer.getAge(), customer.getEmailId(), customer.getKennitalaNumber());
		} catch (Exception e) {
			return "Updation failed !!!";
		}
		return "Customer updated successfully. CustomerId is :" + customer.getCustomerId();
	}

	/**
	 * Method to delete customer details
	 */
	@Override
	public String deleteCustomer(Long customerId) {

		validateCustomerId(customerId);

		try {
			customerRepository.deleteById(customerId);
		} catch (Exception e) {
			return "Customer deletion failed !!!";
		}
		return "Customer deleted successfully. Customer Id deleted is :" + customerId;
	}

	/**
	 * Method to check if the customer id is valid
	 */
	private void validateCustomerId(Long customerId) {
		Customer cust = customerRepository.findByCustomerId(customerId);

		if (cust == null || (cust != null && cust.getCustomerId() == 0)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Customer Id");
		}
	}

	/**
	 * Method to check if create customer request is valid
	 */
	private void validateCustomerDetails(Customer customer) {
		if (StringUtils.isEmpty(customer.getFirstName()) || StringUtils.isEmpty(customer.getLastName())
				|| StringUtils.isEmpty(customer.getPhoneNumber())
				|| (customer.getKennitalaNumber() != null && customer.getKennitalaNumber() == 0)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"First Name, Last Name, Phone Number and Kennitala are mandatory");
		}
	}

}
