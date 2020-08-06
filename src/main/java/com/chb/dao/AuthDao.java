package com.chb.dao;

import java.util.List;

import com.chb.entity.Menu;

public interface AuthDao {

	public List<Menu> selectMenu(String userName,String password);
//	public Menu selectMenu(String userName,String password);
}
