package com.account.Model;

import com.account.Entity.Account;

public class AccountResponse {
	
	private Account account;
	private Customer customer;
	/**
	 * 
	 */
	public AccountResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param account
	 * @param customer
	 */
	public AccountResponse(Account account, Customer customer) {
		super();
		this.account = account;
		this.customer = customer;
	}
	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

}
