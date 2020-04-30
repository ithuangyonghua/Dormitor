package com.ischoolbar.programmer.entity.admin;

/**
 * 宿管老师实体
 * 
 * @author Hyh
 *
 */
public class TeacherEntity {
	private String photo;// 头像照片地址
	private String id;// 职工号2
	private String username;// 姓名3
	private String passWord;// 密码4
	private Integer sex;// 性别5
	private Integer age;// 年龄6
	private String address;// 地址7
	private Integer roleId;// 权限ID
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
