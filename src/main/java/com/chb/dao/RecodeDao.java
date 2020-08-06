package com.chb.dao;

import com.chb.entity.Recode;

public interface RecodeDao {
	/**
	 * 转账
	 * @param r
	 * @return
	 */
	public int addTrecode(Recode r);
	
	/**
	 * 取款
	 * @param r
	 * @return
	 */
	public int addWrecode(Recode r);
	
	/**
	 * 存款
	 * @param r
	 * @return
	 */
	public int addDrecode(Recode r);
}
