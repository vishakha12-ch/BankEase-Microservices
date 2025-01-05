package com.account.Model;

import java.math.BigDecimal;

public class Balance {
	public BigDecimal amount;

	/**
	 * 
	 */
	public Balance() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param accountbalance
	 */
	public Balance(BigDecimal amount) {
		super();
		this.amount = amount;
	}

	/**
	 * @return the accountbalance
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param accountbalance the accountbalance to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	
	
	
	

}
