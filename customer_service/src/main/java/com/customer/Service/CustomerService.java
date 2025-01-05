package com.customer.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.RestTemplate;

import com.customer.Entity.Customer;
import com.customer.Exception.CustomException;
import com.customer.Model.Account;
import com.customer.Repository.CustomerRepo;


@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepo customerrepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Customer addcustomer(Customer customer)throws CustomException {
		if(customer.getCustomeremail()!=null && customer.getCustomername()!=null && customer.getGender()!=null && customer.getAge()>0 ) {
			Customer cust = customerrepo.findByCustomeremail(customer.getCustomeremail());
			if(cust==null) {
				return customerrepo.save(customer);
			}
			else {
				throw new CustomException("Customer with this email already exits",LocalDateTime.now());
			}
			
		}
		else {
			throw new CustomException("Please enter valid details",LocalDateTime.now());
		}
		
			
	}
	
	public List<Customer> AllCustomer() throws CustomException {
		return customerrepo.findAll();
	}
	
	public Customer getcustomer(int customerid) throws CustomException{
		Customer cust = customerrepo.findById(customerid);
		if(cust!=null) {
			return cust;
			
		}
		else {
			throw new CustomException("CustomerId is not Valid.Please enter valid Id",LocalDateTime.now());
		}
		
	}
	
	public Customer Updatecustomer(Customer customer ,int customerid)throws CustomException {
		if(customer.getCustomeremail()!=null && customer.getCustomername()!=null && customer.getGender()!=null && customer.getAge()>0 ) {
			Customer cust = customerrepo.findById(customerid);
			cust.setCustomername(customer.getCustomername());
			cust.setCustomeremail(customer.getCustomeremail());
			cust.setGender(customer.getGender());
			cust.setAge(customer.getAge());
			return customerrepo.save(cust);
		}
		else {
			throw new CustomException("Please enter valid details",LocalDateTime.now());
		}
		
	}
	
	public String Deletecustomer(int customerId) {
		Customer cust = customerrepo.findById(customerId);
		String url = "lb://accountmanagement-service/account/{customerId}";
		if(cust!=null) {
			
			Account acc = this.restTemplate.getForObject(url,Account.class,customerId);
		
			if(acc!=null) {
				int id = acc.getAccountId();
				System.out.println(id);
				String url1 = "lb://accountmanagement-service/account/delete/"+id;
//				this.restTemplate.delete(url1,id);
				this.restTemplate.delete(url1);
			}
			
			
			customerrepo.delete(cust);
			return "success";
		}
		else {
			throw new CustomException("Cannot find customer with these details",LocalDateTime.now());
		}	
	}
	
	
}
