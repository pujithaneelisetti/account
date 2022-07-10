package com.example.account.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.account.model.Account;

/**
 * Repository class for database operations on Account table
 * @author PUJITHA
 *
 */
@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	//Method definition for finding account by id
	Account findByAccountId(Long accountId);
	
	//Method to update balance in account table
	@Modifying
	@Query("UPDATE Account a SET a.balance = :balance WHERE a.accountId = :accountId" )
	void updateAccountBalance(@Param("accountId") Long accountId, @Param("balance") float balance);

}
