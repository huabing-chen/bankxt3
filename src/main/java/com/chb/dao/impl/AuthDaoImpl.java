package com.chb.dao.impl;

import java.util.List;

import com.chb.dao.AuthDao;
import com.chb.entity.Menu;
import com.chb.util.JdbcUtil;

public class AuthDaoImpl implements AuthDao {

	@Override
	public List<Menu> selectMenu(String accountNumber, String password) {
		List<Menu> queryAll = null;
		String sql ="select m.* from account  a\r\n" + 
				"INNER JOIN account_role ar on a.accountId = ar.accountId\r\n" + 
				"INNER JOIN role r on r.id = ar.roleId\r\n" + 
				"INNER JOIN menu_role mr on mr.roleId = r.id\r\n" + 
				"INNER JOIN menu m on m.id = mr.menuId\r\n" + 
				"where a.accountNumber = ? and a.`password` = ?";

		try {
			
			queryAll = JdbcUtil.queryAll(Menu.class, sql, accountNumber,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryAll;
	}

}
