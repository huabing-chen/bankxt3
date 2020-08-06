package com.chb.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Recode {

//	recordId  int not null primary key auto_increment,
//	moduleName VARCHAR(20) not null,  # 转账  取款  存款
//	transferMoney DECIMAL(11,2),      #转账金额
//	withdrawalMoney DECIMAL(11,2),    #取款金额
//	depositMoney DECIMAL(11,2),       #存款金额
//	accountNumber varchar(19) not null,
//	recordTime   date                 #年  月  日
	
	private Integer recordId;
	private String moduleName;
	private BigDecimal transferMoney;
	private BigDecimal withdrawalMoney;
	private BigDecimal depositMoney;
	private String accountNumber;
	private Date recordTime;
	
	public Recode() {
		
	}
	
	/**
	 * #转账金额
	 * @param moduleName
	 * @param transferMoney
	 * @param accountNumber
	 * @param recordTime
	 */
	public Recode(String moduleName, BigDecimal transferMoney, String accountNumber, Date recordTime) {

		this.moduleName = moduleName;
		this.transferMoney = transferMoney;
		this.accountNumber = accountNumber;
		this.recordTime = recordTime;
	}

	
	/**
	 * #取款金额
	 * @param moduleName
	 * @param withdrawalMoney
	 * @param accountNumber
	 * @param recordTime
	 */
	public Recode( BigDecimal withdrawalMoney,String moduleName, String accountNumber, Date recordTime) {
	
		this.moduleName = moduleName;
		this.withdrawalMoney = withdrawalMoney;
		this.accountNumber = accountNumber;
		this.recordTime = recordTime;
	}
	
	
	/**
	 * #存款金额
	 * @param moduleName
	 * @param depositMoney
	 * @param accountNumber
	 * @param recordTime
	 */
	public Recode( BigDecimal depositMoney, String accountNumber, Date recordTime,String moduleName) {

		this.moduleName = moduleName;
		this.depositMoney = depositMoney;
		this.accountNumber = accountNumber;
		this.recordTime = recordTime;
	}

	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public BigDecimal getTransferMoney() {
		return transferMoney;
	}
	public void setTransferMoney(BigDecimal transferMoney) {
		this.transferMoney = transferMoney;
	}
	public BigDecimal getWithdrawalMoney() {
		return withdrawalMoney;
	}
	public void setWithdrawalMoney(BigDecimal withdrawalMoney) {
		this.withdrawalMoney = withdrawalMoney;
	}
	public BigDecimal getDepositMoney() {
		return depositMoney;
	}
	public void setDepositMoney(BigDecimal depositMoney) {
		this.depositMoney = depositMoney;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	@Override
	public String toString() {
		return "Recode [recordId=" + recordId + ", moduleName=" + moduleName + ", transferMoney=" + transferMoney
				+ ", withdrawalMoney=" + withdrawalMoney + ", depositMoney=" + depositMoney + ", accountNumber="
				+ accountNumber + ", recordTime=" + recordTime + "]";
	}
	
	
	
	
	
	
}















