package com.chb.bank.bankxt;

import java.util.Scanner;

import com.chb.dao.impl.AccountDaoImpl;
import com.chb.entity.Account;
import com.chb.service.AccountService;
import com.chb.service.impl.AccountServiceImpl;
import com.chb.util.JdbcUtil;

public class TestMain {


	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		AccountService accsi= new AccountServiceImpl();
		accsi.showMenu();

	
	}
}