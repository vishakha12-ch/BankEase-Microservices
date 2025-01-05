package com.account.Entity;

import java.math.BigDecimal;
import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;
	private String customername;
	private int customerId;
	private String accounttype;
	private String accountnumber;
	private BigDecimal accountbalance;
	/**
	 * 
	 */
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param accountId
	 * @param customername
	 * @param customerId
	 * @param accounttype
	 * @param accountnumber
	 * @param accountbalance
	 */
	public Account(int accountId, String customername, int customerId, String accounttype, String accountnumber,
			BigDecimal accountbalance) {
		super();
		this.accountId = accountId;
		this.customername = customername;
		this.customerId = customerId;
		this.accounttype = accounttype;
		this.accountnumber = accountnumber;
		this.accountbalance = accountbalance;
	}
	/**
	 * @return the accountId
	 */
	public int getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the customername
	 */
	public String getCustomername() {
		return customername;
	}
	/**
	 * @param customername the customername to set
	 */
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the accounttype
	 */
	public String getAccounttype() {
		return accounttype;
	}
	/**
	 * @param accounttype the accounttype to set
	 */
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	/**
	 * @return the accountnumber
	 */
	public String getAccountnumber() {
		return accountnumber;
	}
	/**
	 * @param accountnumber the accountnumber to set
	 */
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	/**
	 * @return the accountbalance
	 */
	public BigDecimal getAccountbalance() {
		return accountbalance;
	}
	/**
	 * @param accountbalance the accountbalance to set
	 */
	public void setAccountbalance(BigDecimal accountbalance) {
		this.accountbalance = accountbalance;
	}
	
	@PrePersist
    public void generateAccountNumber() {
        // Generate a random 10-digit account number
        StringBuilder accountNumberBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            accountNumberBuilder.append(random.nextInt(10)); // Only digits
        }
        this.accountnumber = accountNumberBuilder.toString();
    }
	
	
	
	
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", customername=" + customername + ", customerId=" + customerId
				+ ", accounttype=" + accounttype + ", accountnumber=" + accountnumber + ", accountbalance="
				+ accountbalance + "]";
	}
	
	

	
}
