package com.customer.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.Entity.Customer;
import com.customer.Exception.CustomException;
import com.customer.Repository.CustomerRepo;
import com.customer.Service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerservice;
	
	@Autowired
	private CustomerRepo customerrepo;
	
	@PostMapping("/add")
	public ResponseEntity<?> AddCustomer(@RequestBody Customer customer )throws CustomException {
		try {
				Customer cust = customerservice.addcustomer(customer);
				return  new ResponseEntity<>(cust,HttpStatus.ACCEPTED);
			
		}catch(Exception e) {
			throw new CustomException(e.getMessage(),LocalDateTime.now());
		}
	}
	
	@GetMapping("/customers")
	public ResponseEntity<?> AllCustomer()throws CustomException{
		try {
			List<Customer> customers = customerservice.AllCustomer();
			return new ResponseEntity<>(customers,HttpStatus.ACCEPTED);
		}catch(Exception e) {
			throw new CustomException(e.getMessage(),LocalDateTime.now());
		}
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<?> Customer(@PathVariable("customerId") int customerId)throws CustomException{
		try {
			Customer customer = customerservice.getcustomer(customerId);
			return new ResponseEntity<>(customer,HttpStatus.ACCEPTED);	
		}catch(Exception e) {
			throw new CustomException(e.getMessage(),LocalDateTime.now());
		}
	}
	
	@PutMapping("/update/{customerId}")
	public ResponseEntity<?> UpdateCustomer(@RequestBody Customer customer ,@PathVariable("customerId") int customerId)throws CustomException{
		try {
			Customer upcustomer = customerservice.Updatecustomer(customer, customerId);
			return new ResponseEntity<>(upcustomer,HttpStatus.ACCEPTED);
		}catch(Exception e) {
			throw new CustomException(e.getMessage(),LocalDateTime.now());
		}
	}
	
	@DeleteMapping("/delete/{customerId}")
	public ResponseEntity<?> Deletecustomer(@PathVariable("customerId") int customerId)throws CustomException{
		try {
			String message = customerservice.Deletecustomer(customerId);
			return new ResponseEntity<>(message,HttpStatus.ACCEPTED);
		}catch(Exception e) {
			throw new CustomException(e.getMessage(),LocalDateTime.now());
		}
		
	}
	
	@GetMapping("/{customername}/{customerId}")
	public ResponseEntity<?> Any(@PathVariable("customername") String customername,@PathVariable("customerId") int customerId)throws CustomException{
		try {
			Customer name = customerrepo.findByCustomerIdAndCustomername(customerId, customername);
			if(name!=null) {
				return new ResponseEntity<>(name,HttpStatus.ACCEPTED);		
			}
			else {
				throw new CustomException("Customer Details are not valid.Please try with correct details",LocalDateTime.now());
			}
				
		}catch(Exception e) {
			throw new CustomException(e.getMessage(),LocalDateTime.now());
		}
		
	}
	
	
}
