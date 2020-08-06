package com.chb.service;

/**
 * 业务层对应该系统中的每一个功能
 * @author Administrator
 *
 */
public interface AccountService {
	public void showMenu();

	/**
	 * 
	 * 1.开户
	 */
	public int addAccount();
	
	/**
	 * 2.存款
	 */
	public int cunKuan();
	
	/**
	 * 3.取款
	 */
	public int quKuan();
	
	/**
	 * 4.查询
	 */
	public int chaXun();
	
	/**
	 *5. 转账
	 */
	public int zhuanZhang();
	
	/**
	 *6. 修改密码
	 */
	public int xiuGaiMiMa();
	
	
	/**
	 * 7.销户
	 */
	public int xiaoHu();
	
	/**
	 * 8.退出
	 */
	public int tuiChu();
	
	/**
	 * 9:更换手机号
	 */
	public int getHuanShouJiHao();
	
	/**
	 * 10:查看流水
	 */
	public int chaKanLiuShui();
}














