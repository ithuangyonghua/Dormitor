package com.ischoolbar.programmer.entity.admin;

/**
 * ËŞÉáÂ¥ÊµÌå
 * 
 * @author Hyh
 *
 */
public class DormitoryEntity {
	
	private Integer dormitoryid;// ËŞÉáÂ¥±àºÅ
	private String name;// ËŞÉáÃû³Æ
//	private Integer index;
	private Integer teacherid;//ËŞ¹ÜÀÏÊ¦
	private String url;
	private String icon;
    private String address ;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getDormitoryid() {
		return dormitoryid;
	}
	public void setDormitoryid(Integer dormitoryid) {
		this.dormitoryid = dormitoryid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
//	public Integer getIndex() {
//		return index;
//	}
//	public void setIndex(Integer index) {
//		this.index = index;
//	}
	

}
