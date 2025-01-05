package com.account.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.account.Entity.Account;
import com.account.Exception.CustomException;
import com.account.Model.AccountResponse;
import com.account.Model.Balance;
import com.account.Model.Customer;
import com.account.Repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountrepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Account Addaccount(Account account)throws CustomException{
		if(account.getAccountbalance()!=null  && account.getCustomername()!=null && account.getCustomerId()!=0 && account.getAccounttype()!=null) {
			int customerId = account.getCustomerId();
			String customername = account.getCustomername();
			Account acc = accountrepo.findByCustomerId(customerId);
			if(acc==null) {
				return accountrepo.save(account);	
			}	
			else {
				throw new CustomException("Customer account already exits",LocalDateTime.now());	
			}
		}
		else {
			throw new CustomException("Enter valid details",LocalDateTime.now());
		}
			
	}
	
	public String AddMoney(String customername,int customerId,BigDecimal money) throws CustomException {
		
		String url = "lb://customermanagement-service/customer/{customername}/{customerId}";
		
		Customer cust = this.restTemplate.getForObject(url,Customer.class,customername,customerId);
		
		if(cust.getCustomerId()==customerId && cust.getCustomername().equals(customername)) {
			Account acc = accountrepo.findByCustomerIdAndCustomername(customerId, customername);
			BigDecimal amount = acc.getAccountbalance();
			BigDecimal totalamount = amount.add(money);
			acc.setAccountbalance(totalamount);
		    accountrepo.save(acc);
		    return "Amount Added Successfuly";	
		}
		else {
			throw new CustomException("Customer Details are not valid.Please try with correct details",LocalDateTime.now());
		}	
	}
	
    public String WithdrawMoney(String customername,int customerId,BigDecimal money) throws CustomException {
    	Account acc = accountrepo.findByCustomerIdAndCustomername(customerId, customername);
    	if(money.compareTo(acc.getAccountbalance())== -1) {
    		String url = "lb://customermanagement-service/customer/{customername}/{customerId}";
    		Customer cust = this.restTemplate.getForObject(url,Customer.class,customername,customerId);
    		if(cust.getCustomerId()==customerId && cust.getCustomername().equals(customername)) {
    			BigDecimal amount = acc.getAccountbalance();
    			BigDecimal totalamount = amount.subtract(money);
    			acc.setAccountbalance(totalamount);
    		    accountrepo.save(acc);
    		    return "Amount Withdraw Successfuly";	
    		}
    		else {
    			throw new CustomException("Customer Details are not valid.Please try with correct details",LocalDateTime.now());
    		}	
    	}
    	else {
			throw new CustomException("Withdraw amount is greater than accountbalance",LocalDateTime.now());
		}
		
	}
    
    public void Delete(int id) throws CustomException{
    	 Account acc = accountrepo.findById(id);
    	 if(acc!= null ) {
    		 accountrepo.delete(acc);
    		 //return "Account deleted Successfully";	 
    	 }
    	 else {
    		 throw new CustomException("Cannot find account with these details",LocalDateTime.now());
    	 }	 
    }
    
    public Account GetAccount(int id)throws CustomException {
    	Account acc = accountrepo.findByCustomerId(id);
    	if(acc!=null) {
    		return acc;
    	}
    	else {
    		throw new CustomException("Cannot find account for this CustomerId.Please enter valid Id",LocalDateTime.now());
    	}
    }
    
    public List<AccountResponse> GetAllAccount(){
    	List<Account> allaccount = accountrepo.findAll();
    	List<AccountResponse> accountdetails = new ArrayList<AccountResponse>();
    	for(Account acc:allaccount) {
    		int customerId = acc.getCustomerId();
    		String url = "lb://customermanagement-service/customer/{customerId}";
    		Customer cust = this.restTemplate.getForObject(url,Customer.class,customerId);
    		AccountResponse accountResponse = new AccountResponse();
    		accountResponse.setAccount(acc);;
    		accountResponse.setCustomer(cust);
    		accountdetails.add(accountResponse); 		
    	}
    	return accountdetails;
    }
}
