package com.ischoolbar.programmer.entity.admin;

public class RepairsVO extends RepairsEntity {
	private String username;// 学生名字
	private String assetname;// 财产名
	private String name;// 宿舍名

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAssetname() {
		return assetname;
	}

	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
