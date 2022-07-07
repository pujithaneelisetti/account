package com.example.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.account.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findByCustomerId(Long customerId);

}