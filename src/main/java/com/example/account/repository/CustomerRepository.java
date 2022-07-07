package com.example.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.account.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByCustomerId(Long customerId);

	@Query("UPDATE CUSTOMER c SET c.FIRST_NAME = :firstName AND c.LAST_NAME = :lastName AND c.PHONE_NUMBER = :phoneNumber AND c.AGE = :age AND c.EMAIL_ID = :emailId where c.CUSTOMER_ID = :customerId")
	void updateCustomer(@Param("customerId") Long customerId, @Param("firstName") String firstName,
			@Param("lastName") String lastName, @Param("phoneNumber") long phoneNumber, @Param("age") int age,
			@Param("emailId") String emailId);

}