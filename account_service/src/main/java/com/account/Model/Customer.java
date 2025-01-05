package com.account.Model;

public class Customer {
	
	private int customerId;
	private String customername;
	private String customeremail;
	private String gender;
	private int age;

	/**
	 * 
	 */
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param customerId
	 * @param customername
	 * @param customeremail
	 * @param gender
	 * @param age
	 */
	public Customer(int customerId, String customername, String customeremail, String gender, int age) {
		super();
		this.customerId = customerId;
		this.customername = customername;
		this.customeremail = customeremail;
		this.gender = gender;
		this.age = age;
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
	 * @return the customeremail
	 */
	public String getCustomeremail() {
		return customeremail;
	}

	/**
	 * @param customeremail the customeremail to set
	 */
	public void setCustomeremail(String customeremail) {
		this.customeremail = customeremail;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}


}
