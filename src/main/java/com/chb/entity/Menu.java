package com.chb.entity;

public class Menu {

	private Integer id;
	private String menuName;
	
	public Menu() {
		super();
	}
	public Menu(Integer id, String menuName) {
		super();
		this.id = id;
		this.menuName = menuName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", menuName=" + menuName + "]";
	}
	
	
}
