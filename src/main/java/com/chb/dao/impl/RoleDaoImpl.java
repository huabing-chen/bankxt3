package com.chb.dao.impl;


import com.chb.dao.RoleDao;
import com.chb.entity.Roles;
import com.chb.util.JdbcUtil;

public class RoleDaoImpl implements RoleDao{

	@Override
	public int rolesId(String accountNumber,String password) {
		Integer id =0;
		
		String sql = "select ar.roleId from account a \r\n" + 
				"INNER JOIN account_role  ar on a.accountId = ar.accountId\r\n" + 
				"where a.accountNumber = ? and a.password = ?";
		
		 Roles ro= JdbcUtil.query(Roles.class, sql, accountNumber,password);
		 id =ro.getRoleId();
		return id;
	}

	
}
