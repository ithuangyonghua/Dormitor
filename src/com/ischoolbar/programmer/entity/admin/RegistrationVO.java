package com.ischoolbar.programmer.entity.admin;

public class RegistrationVO extends RegistrationEntity {
    private String assetname;//财产名
    private String name;//寝室名 
    private String username;//老师名 
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}   
}
