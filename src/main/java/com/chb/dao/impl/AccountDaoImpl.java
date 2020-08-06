package com.chb.dao.impl;

import java.math.BigDecimal;
import java.util.Date;

import com.chb.dao.AccountDao;
import com.chb.dao.RecodeDao;
import com.chb.entity.Account;
import com.chb.entity.Recode;
import com.chb.service.impl.AccountServiceImpl;

public class AccountDaoImpl implements AccountDao{

	AccountServiceImpl asi = new AccountServiceImpl();
	@Override
	public void addAccount() {
		
		int row = asi.addAccount();
		if(row>0) {
			System.out.println("开户成功！");
		}
		
	}

	@Override
	public void updateAccount() {
		int row = asi.cunKuan();
		if(row>0) {
			System.out.println("存钱成功！");
			
			

		}
		
	}
	
	@Override
	public void updateAccountj() {
		int row = asi.quKuan();
		if(row>0) {
			System.out.println("取钱成功！");
		}
		
	}

	@Override
	public void chaXun() {
		int row = asi.chaXun();
		if(row<0) {
			System.out.println("账户或密码错误！");
		}
		
	}
	@Override
	public void updatePwd() {
		int row = asi.xiuGaiMiMa();
		if(row>0) {
			System.out.println("密码修改成功！");
		}else {
			System.out.println("账号或密码有错误！");
		}
		
	}
	@Override
	public void zhuanZhang() {
		int row = asi.zhuanZhang();
//		System.out.println(row);
		if(row>0) {
			System.out.println("转账成功！");
		}
	}
	@Override
	public void xiaoHu() {
		int row = asi.xiaoHu();
		if(row>0) {
			System.out.println("账户已注销！");
		}else {
			System.out.println("账户不存在");
		}
		
	}
	@Override
	public void tuiChu() {
		asi.tuiChu();
		
	}

	@Override
	public void gengHuanShouJiHao() {
		int row = asi.getHuanShouJiHao();
		if(row>0) {
			System.out.println("手机号修改成功！");
		}else {
			System.out.println("手机号修改失败！");
		}
		
	}

	@Override
	public void chaKanLiuShui() {
		
		asi.chaKanLiuShui();
		// TODO Auto-generated method stub
		
	}

}
