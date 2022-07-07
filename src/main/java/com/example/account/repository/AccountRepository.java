package com.example.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.account.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Account findByAccountId(Long accountId);
	
	@Query("UPDATE ACCOUNT a SET a.BALANCE = :balance WHERE a.ACCOUNT_ID = :accountId" )
	void updateAccountBalance(@Param("accountId") Long accountId, @Param("balance") float balance);

}
