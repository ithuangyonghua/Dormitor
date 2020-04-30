package com.ischoolbar.programmer.entity.admin;

public class AssetCategoryEntity {
	private Integer id;// id
	private String cname;// 分类名
	private Integer cyear;// 使用年限

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getCyear() {
		return cyear;
	}

	public void setCyear(Integer cyear) {
		this.cyear = cyear;
	}

}
