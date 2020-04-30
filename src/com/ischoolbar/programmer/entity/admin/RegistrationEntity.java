package com.ischoolbar.programmer.entity.admin;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 财产盘点表
 * 
 * @author Hyh
 *
 */
public class RegistrationEntity {
	private Integer rid;
	private String teaid;// 职工号
	// private Integer dormitoryid;//宿舍
	private Integer assetid;// 财产id
	private Integer rstatus;// 状态
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date rcreatetime;// 创建时间

	public String getTeaid() {
		return teaid;
	}

	public void setTeaid(String teaid) {
		this.teaid = teaid;
	}

	

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	// public Integer getDormitoryid() {
	// return dormitoryid;
	// }
	// public void setDormitoryid(Integer dormitoryid) {
	// this.dormitoryid = dormitoryid;
	// }
	public Integer getAssetid() {
		return assetid;
	}

	public void setAssetid(Integer assetid) {
		this.assetid = assetid;
	}

	public Integer getRstatus() {
		return rstatus;
	}

	public void setRstatus(Integer rstatus) {
		this.rstatus = rstatus;
	}

	public Date getRcreatetime() {
		return rcreatetime;
	}

	public void setRcreatetime(Date rcreatetime) {
		this.rcreatetime = rcreatetime;
	}

}
