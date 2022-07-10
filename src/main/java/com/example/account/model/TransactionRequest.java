package com.example.account.model;

/**
 * Request POJO for credit or debit amount to account.
 * @author PUJITHA
 *
 */
public class TransactionRequest {
	
	private Long customerId;
	private Long accountId;
	private TransactionType type;
	private float amount;
	
	public TransactionRequest() {
		
	}
	
	public TransactionRequest(Long customerId, Long accountId, TransactionType type, float amount) {
		super();
		this.customerId = customerId;
		this.accountId = accountId;
		this.type = type;
		this.amount = amount;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public TransactionType getType() {
		return type;
	}
	public void setType(TransactionType type) {
		this.type = type;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}

}
