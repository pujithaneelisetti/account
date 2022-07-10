package com.example.account;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import com.example.account.model.Account;
import com.example.account.model.Customer;
import com.example.account.model.TransactionRequest;
import com.example.account.model.TransactionType;
import com.example.account.service.AccountService;
import com.example.account.service.CustomerService;

/**
 * Junit Test class to test various operations on Customer and Account
 * @author PUJITHA
 *
 */
@SpringBootTest
class AccountApplicationTests {

	@Autowired
	AccountService accService;
	
	@Autowired
	CustomerService custService;
	
	/**
	 * Test create customer - positive scenario
	 */
	@Test
	public void testCustomerCreate() {
		Customer cust = new Customer();
		cust.setFirstName("Ram");
		cust.setLastName("Gopal");
		cust.setEmailId("r.g@gmail.com");
		cust.setKennitalaNumber(1112833478L);
		cust.setPhoneNumber(9876543);
		String result = custService.createCustomer(cust);
		assertTrue(result.contains("Customer created successfully."));
	}
	
	/**
	 * Test create customer - error scenario
	 */
	@Test
	public void testCustomerCreateWithException() {
		Customer cust = new Customer();
		cust.setFirstName("");
		cust.setLastName("Gopal");
		cust.setEmailId("r.g@gmail.com");
		cust.setKennitalaNumber(null);
		cust.setPhoneNumber(9876543);
		assertThrows(ResponseStatusException.class, () -> custService.createCustomer(cust));
		
	}
	
	/**
	 * Test create account - positive scenario
	 */
	@Test
	public void testCreateAccount() {
		String res = accService.createAccountForCustomer(3L);
		assertNotNull(res);
	}
	
	/**
	 * Test create account - error scenario
	 */
	@Test
	public void testCreateAccountWithInvalidCustomerId() {		
		assertThrows(ResponseStatusException.class, () -> accService.createAccountForCustomer(3000L));
	}
	
	/**
	 * Test get customer details based on id - positive scenario
	 */
	@Test
	public void testGetCustomer() {
		Customer res = custService.getCustomerDetails(9l);
		assertNotNull(res);
		assertEquals(res.getKennitalaNumber(),1112833478L);
	}

	/**
	 * Test get customer details based on id - error scenario
	 */
	@Test
	public void testGetCustomerWithInvalidCustomerId() {		
		assertThrows(ResponseStatusException.class, () -> custService.getCustomerDetails(3000L));
	}
	
	/**
	 * Test get account details based on id - positive scenario
	 */
	@Test
	public void testGetAccount() {
		Account res = accService.getAccountDetails(7l);
		assertNotNull(res);
	}
	
	/**
	 * Test get account details based on id - error scenario
	 */
	@Test
	public void testGetAccountWithInvalidAccountId() {
		assertThrows(ResponseStatusException.class, () -> accService.getAccountDetails(140l));
	}
	
	/**
	 * Test update customer details - positive scenario
	 */
	@Test
	public void testUpdateCustomerDetails() {
		Customer cust = new Customer();
		cust.setCustomerId(9l);
		cust.setFirstName("Ram");
		cust.setLastName("Gopal");
		cust.setEmailId("ram.g@gmail.com");
		cust.setKennitalaNumber(1112833478L);
		cust.setAge(55);
		cust.setPhoneNumber(9876543);
		custService.updateCustomer(cust);
		Customer res = custService.getCustomerDetails(9l);
		assertEquals(cust.getAge(),res.getAge());
	}
	
	/**
	 * Test update customer details - error scenario
	 */
	@Test
	public void testUpdateCustomerDetailsWithInvalidData() {
		Customer cust = new Customer();
		cust.setCustomerId(9l);
		cust.setFirstName("");
		cust.setLastName("Gopal");
		cust.setEmailId("ram.g@gmail.com");
		cust.setKennitalaNumber(1112833478L);
		cust.setAge(55);
		cust.setPhoneNumber(9876543);
		assertThrows(ResponseStatusException.class, () -> custService.updateCustomer(cust));
	}
	
	/**
	 * Test update account details - positive scenario
	 */
	@Test
	public void testUpdateAccount() {
		TransactionRequest req = new TransactionRequest();
		req.setCustomerId(6l);
		req.setAccountId(7l);
		req.setType(TransactionType.CREDIT);
		req.setAmount(1150);
		
//		String res = accService.updateAmount(req);
//		assertNotNull(res);
	}
	
	/**
	 * Test update account details - error scenario
	 */
	@Test
	public void testUpdateAccountWithInvalidData() {
		TransactionRequest req = new TransactionRequest();
		req.setCustomerId(600l);
		req.setAccountId(7l);
		req.setType(TransactionType.CREDIT);
		req.setAmount(1150);
		assertThrows(ResponseStatusException.class, () -> accService.updateAmount(req));
	}
	
	/**
	 * Test delete account - positive scenario
	 */
	@Test
	public void testDeleteAccount() {
		String res = accService.deleteAccount(14l);
		assertTrue(res.contains("Account deleted successfully"));
	}
	
	/**
	 * Test delete account - error scenario
	 */
	@Test
	public void testDeleteAccountWithInvalidAccountId() {
		assertThrows(ResponseStatusException.class, () -> accService.deleteAccount(1400l));
	}
	
	/**
	 * Test delete customer - positive scenario
	 */
	@Test
	public void testDeleteCustomer() {
		String res = custService.deleteCustomer(10l);
		assertTrue(res.contains("Customer deleted successfully."));
	}
	
	/**
	 * Test delete customer - error scenario
	 */
	@Test
	public void testDeleteCustomerWithInvalidCustomerId() {
		assertThrows(ResponseStatusException.class, () -> custService.deleteCustomer(1010l));
	}
}
