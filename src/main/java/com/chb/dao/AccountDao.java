package com.chb.dao;

import java.math.BigDecimal;

import com.chb.entity.Account;

public interface AccountDao {

	/**
	 * 添加账号
	 * @param account
	 * @return
	 */
	public void addAccount();
	
	
	/**
	 * 根据账号信息和密码，修改金额
	 * 可以使用取款  存款
	 * update account set balance = ? where accountNumber = ? and password= ?
	 * @param accountNumber
	 * @param password
	 * @param money
	 * @return
	 */
	public void updateAccount(); 
	
	public void updateAccountj();
	
	
	
	
	
	
	
	public void chaXun();
	public void updatePwd();
	public void zhuanZhang();
	public void xiaoHu();
	public void tuiChu();
	public void gengHuanShouJiHao();
	public void chaKanLiuShui();
	
	
}














