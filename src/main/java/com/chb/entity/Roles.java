package com.chb.entity;

public class Roles {

	private Integer accountId;
	private Integer roleId;
	public Roles() {
		
	}
	public Roles(Integer accountId, Integer roleId) {
		
		this.accountId = accountId;
		this.roleId = roleId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	
}
