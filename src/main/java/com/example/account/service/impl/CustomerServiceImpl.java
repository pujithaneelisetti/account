package com.example.account.service.impl;

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

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerDetails(Long customerId) {
		validateCustomerId(customerId);
		return customerRepository.findByCustomerId(customerId);
	}

	@Override
	public String createCustomer(Customer customer) throws ValidationException {

		validateCustomerDetails(customer);
	
		Customer cust = new Customer();
		try {
			cust = customerRepository.save(customer);
		} catch (Exception e) {
			return "Customer creation failed.";
		}

		return "Customer created successfully. Customer Id is :" + cust.getCustomerId();
	}

	@Override
	public String updateCustomer(Customer customer) {

		validateCustomerId(customer.getCustomerId());
		validateCustomerDetails(customer);

		try {
			customerRepository.updateCustomer(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(),
					customer.getPhoneNumber(), customer.getAge(), customer.getEmailId());
		} catch (Exception e) {
			return "Updation failed !!!";
		}
		return "Customer updated successfully. CustomerId is :" + customer.getCustomerId();
	}

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

	private void validateCustomerId(Long customerId) {
		Customer cust = customerRepository.findByCustomerId(customerId);

		if (cust == null || (cust != null && cust.getCustomerId() == 0)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Customer Id");
		}
	}

	private void validateCustomerDetails(Customer customer) {
		if (StringUtils.isEmpty(customer.getFirstName()) || StringUtils.isEmpty(customer.getLastName())
				|| StringUtils.isEmpty(customer.getPhoneNumber())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"First Name, Last Name and Phone Number are mandatory");
		}
	}

}
