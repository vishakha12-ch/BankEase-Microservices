package com.account.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.account.Entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer > {
	
	public Account findById(int id);
	public Account findByCustomerIdAndCustomername(int id, String name);
	public Account findByCustomerId(int id);

}
