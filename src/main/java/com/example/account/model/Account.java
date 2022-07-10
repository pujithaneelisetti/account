package com.example.account.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class to define Account Entity
 * @author PUJITHA
 *
 */
@Entity
@Table(name="ACCOUNT")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	
//	@ManyToOne
//	@JoinColumn(name="CUSTOMER_ID")
//	private Customer customer;
	
	@Column(name="BALANCE")
	private float balance;
	
	public Account() {
		
	}
	public Account(Long customerId, float balance) {
		super();
		this.balance = balance;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
//	public Customer getCustomer() {
//		return customer;
//	}
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
}
