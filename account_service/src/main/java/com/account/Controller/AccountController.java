package com.account.Controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.account.Entity.Account;
import com.account.Exception.CustomException;
import com.account.Model.AccountResponse;
import com.account.Model.Balance;
import com.account.Model.Customer;
import com.account.Service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountservice;
	
	
	@PostMapping("/add")
	public ResponseEntity<?> AddAccount(@RequestBody Account account )throws CustomException {
		try {
			Account acc = accountservice.Addaccount(account);
			return  new ResponseEntity<>(acc,HttpStatus.ACCEPTED);
			
		}catch(Exception e) {
			throw new CustomException(e.getMessage(),LocalDateTime.now());
		}
	}
	
	@PostMapping("/addmoney/{customername}/{customerId}")
	public ResponseEntity<?> Addmoney(@RequestBody Balance money,@PathVariable("customername") String customername,@PathVariable("customerId") int customerId) throws CustomException{
		try {
			 //BigDecimal amount =money.getBalance();
			 if(money.getAmount() instanceof BigDecimal) { 
				 System.out.println(money.getAmount());
				 if(money.getAmount().compareTo(BigDecimal.ZERO)> 0){
					 String name = accountservice.AddMoney(customername,customerId,money.getAmount());
				     return new ResponseEntity<>(name,HttpStatus.ACCEPTED);			 
				 }
				 else {
						throw new CustomException("Amount must be greater than zero",LocalDateTime.now());
					}	
				 	
			}
			else {
				throw new CustomException("Please enter valid amount",LocalDateTime.now());
			}	
		}catch(Exception e) {
			throw new CustomException(e.getMessage(),LocalDateTime.now());
		}
		
	}
	
	@PostMapping("/withdrawmoney/{customername}/{customerId}")
	public ResponseEntity<?> Withdrawmoney(@RequestBody Balance money,@PathVariable("customername") String customername,@PathVariable("customerId") int customerId) throws CustomException{
		try {
			 //BigDecimal amount =money.getBalance();
			 if(money.getAmount() instanceof BigDecimal) { 
				 System.out.println(money.getAmount());
				 String name = accountservice.WithdrawMoney(customername,customerId,money.getAmount());
			     return new ResponseEntity<>(name,HttpStatus.ACCEPTED);		
			}
			else {
				throw new CustomException("Please enter valid amount",LocalDateTime.now());
			}	
		}catch(Exception e) {
			throw new CustomException(e.getMessage(),LocalDateTime.now());
		}
	}
	
	@DeleteMapping("/delete/{accountId}")
	public ResponseEntity<?> Delete(@PathVariable("accountId") int accountId) throws CustomException{
		try {
			System.out.println(accountId);
			 accountservice.Delete(accountId);
            return  new ResponseEntity<>(HttpStatus.ACCEPTED);	
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage(),LocalDateTime.now());
		}
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<?> GetAccountByCustomerId(@PathVariable("customerId") int customerId) throws CustomException{
		try {
			Account acc = accountservice.GetAccount(customerId);
			return new ResponseEntity<>(acc,HttpStatus.ACCEPTED);
		}catch(Exception e) {
			throw new CustomException(e.getMessage(),LocalDateTime.now());
		}
	}
	
	@GetMapping("/allaccounts")
	public ResponseEntity<?> GetAllAccount()throws CustomException{
		try {
			List<AccountResponse> accounts = accountservice.GetAllAccount();
			return new ResponseEntity<>(accounts,HttpStatus.ACCEPTED);
		}catch(Exception e) {
			throw new CustomException(e.getMessage(),LocalDateTime.now());
		}
		
	}
	
	

}
