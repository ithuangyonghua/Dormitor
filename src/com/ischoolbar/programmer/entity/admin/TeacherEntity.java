package com.ischoolbar.programmer.entity.admin;

/**
 * �޹���ʦʵ��
 * 
 * @author Hyh
 *
 */
public class TeacherEntity {
	private String photo;// ͷ����Ƭ��ַ
	private String id;// ְ����2
	private String username;// ����3
	private String passWord;// ����4
	private Integer sex;// �Ա�5
	private Integer age;// ����6
	private String address;// ��ַ7
	private Integer roleId;// Ȩ��ID
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}
