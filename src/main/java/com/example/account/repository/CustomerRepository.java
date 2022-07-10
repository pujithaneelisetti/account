package com.example.account.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.account.model.Customer;

/**
 * Repository class for database operations on Customer table
 * 
 * @author PUJITHA
 *
 */
@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	// Method for finding customer by id
	Customer findByCustomerId(Long customerId);

	// Method for updating customer details to database
	@Modifying
	@Query("UPDATE Customer c SET c.firstName = :firstName, c.lastName = :lastName, c.phoneNumber = :phoneNumber, c.age = :age, c.emailId = :emailId, c.kennitalaNumber = :kennitala where c.customerId = :customerId")
	void updateCustomer(@Param("customerId") Long customerId, @Param("firstName") String firstName,
			@Param("lastName") String lastName, @Param("phoneNumber") long phoneNumber, @Param("age") int age,
			@Param("emailId") String emailId, @Param("kennitala") Long kennitala);

}