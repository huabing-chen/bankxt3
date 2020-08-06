package com.chb.dao.impl;

import java.math.BigDecimal;
import java.util.Date;

import com.chb.dao.RecodeDao;
import com.chb.entity.Recode;
import com.chb.util.JdbcUtil;

public class RecodeDaoImpl implements RecodeDao {

	//转账
	@Override
	public int addTrecode(Recode r) {
		String sql ="insert into recode(modulename,transfermoney,accountNumber,recordtime) values(?,?,?,?)";
		int row = JdbcUtil.insertOrUpdateOrDelete1(sql, r.getModuleName(),r.getTransferMoney(),
				r.getAccountNumber(),r.getRecordTime());
		
		return row;
	}

	//取款  BigDecimal withdrawalMoney,String moduleName, String accountNumber, Date recordTime
	@Override
	public int addWrecode(Recode r) {
		String sql = "insert into recode(withdrawalMoney,modulename,accountNumber,RecordTime) values(?,?,?,?)";
		int row = JdbcUtil.insertOrUpdateOrDelete1(sql, r.getWithdrawalMoney(),r.getModuleName(),
				r.getAccountNumber(),r.getRecordTime());
		
		return row;
	}

	//存款	BigDecimal depositMoney, String accountNumber, Date recordTime,String moduleName
	@Override		
	public int addDrecode(Recode r) {
		String sql = "insert into recode(ModuleName,DepositMoney,accountNumber,RecordTime) values(?,?,?,?)";
				
		int row = JdbcUtil.insertOrUpdateOrDelete1(sql,r.getModuleName(), r.getDepositMoney(),r.getAccountNumber(),
				r.getRecordTime());
		return row;
	}

}
