package com.ischoolbar.programmer.entity.admin;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Asset {
	private Integer assetid;// �Ʋ�ID
	private Integer bedchamberid;// ����ID
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createtime;// ����ʱ��
	private Float price;// �۸�
	private Integer categoryid;// ���ID
	private Integer repairstatus;// ����״̬
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date scraptime;// ����ʱ��
	private String scrapto;// ����ȥ��
	private String scrapreason;// ��������
	private Integer registrationstatus;// �Ǽ�״̬
	private String assetname;// ����
	private String uses;// ʹ����

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
