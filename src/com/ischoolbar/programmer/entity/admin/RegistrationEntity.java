package com.ischoolbar.programmer.entity.admin;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * �Ʋ��̵��
 * 
 * @author Hyh
 *
 */
public class RegistrationEntity {
	private Integer rid;
	private String teaid;// ְ����
	// private Integer dormitoryid;//����
	private Integer assetid;// �Ʋ�id
	private Integer rstatus;// ״̬
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date rcreatetime;// ����ʱ��

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
