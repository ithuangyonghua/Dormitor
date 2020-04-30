package com.ischoolbar.programmer.entity.admin;



import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class RepairsEntity {
	private Integer id;
	private String stuid;
	private Integer assetid;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createtime;
	private Integer status;
	private String brank;//±¸×¢

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStuid() {
		return stuid;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	public Integer getAssetid() {
		return assetid;
	}

	public void setAssetid(Integer assetid) {
		this.assetid = assetid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBrank() {
		return brank;
	}

	public void setBrank(String brank) {
		this.brank = brank;
	}

}
