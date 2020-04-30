package com.ischoolbar.programmer.entity.admin;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Asset {
	private Integer assetid;// 财产ID
	private Integer bedchamberid;// 寝室ID
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createtime;// 创建时间
	private Float price;// 价格
	private Integer categoryid;// 类别ID
	private Integer repairstatus;// 报修状态
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date scraptime;// 报废时间
	private String scrapto;// 报废去向
	private String scrapreason;// 报废事由
	private Integer registrationstatus;// 登记状态
	private String assetname;// 名称
	private String uses;// 使用者

	public Integer getBedchamberid() {
		return bedchamberid;
	}

	public void setBedchamberid(Integer bedchamberid) {
		this.bedchamberid = bedchamberid;
	}

	public Integer getAssetid() {
		return assetid;
	}

	public void setAssetid(Integer assetid) {
		this.assetid = assetid;
	}



	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}

	public String getUses() {
		return uses;
	}

	public void setUses(String uses) {
		this.uses = uses;
	}

	public Integer getRepairstatus() {
		return repairstatus;
	}

	public void setRepairstatus(Integer repairstatus) {
		this.repairstatus = repairstatus;
	}

	public Date getScraptime() {
		return scraptime;
	}

	public void setScraptime(Date scraptime) {
		this.scraptime = scraptime;
	}

	public String getScrapto() {
		return scrapto;
	}

	public void setScrapto(String scrapto) {
		this.scrapto = scrapto;
	}

	public String getScrapreason() {
		return scrapreason;
	}

	public void setScrapreason(String scrapreason) {
		this.scrapreason = scrapreason;
	}

	public Integer getRegistrationstatus() {
		return registrationstatus;
	}

	public void setRegistrationstatus(Integer registrationstatus) {
		this.registrationstatus = registrationstatus;
	}

	public String getAssetname() {
		return assetname;
	}

	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}

}
