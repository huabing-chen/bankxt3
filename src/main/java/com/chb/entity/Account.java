package com.chb.entity;

import java.math.BigDecimal;

public class Account {

	private Integer accountId;
	private String userName;
	private BigDecimal balance;
	private String idCard;
	private String telphone;
	private String password;
	private String accountNumber;
	private Integer state;
	
	/**
	 * 开户实体类
	 */
	public Account() {}
	
	public Account(String userName, BigDecimal balance, String idCard, String telphone, String password) {
		
		this.userName = userName;
		this.balance = balance;
		this.idCard = idCard;
		this.telphone = telphone;
		this.password = password;
	}
	
	
	
	public Account(Integer accountId,String userName, BigDecimal balance, String idCard, String telphone, String password) {
		this.accountId = accountId;
		this.userName = userName;
		this.balance = balance;
		this.idCard = idCard;
		this.telphone = telphone;
		this.password = password;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
