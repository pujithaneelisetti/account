package com.example.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.account.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Account findByAccountId(Long accountId);
	
	@Modifying
	@Query("UPDATE Account a SET a.balance = :balance WHERE a.accountId = :accountId" )
	void updateAccountBalance(@Param("accountId") Long accountId, @Param("balance") float balance);

}
