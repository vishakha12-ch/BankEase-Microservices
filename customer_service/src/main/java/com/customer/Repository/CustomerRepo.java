package com.customer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.Entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer > {
	
	public Customer findById(int id);
	public Customer findByCustomerIdAndCustomername(int id,String name);
	public Customer findByCustomeremail(String mail);

}
