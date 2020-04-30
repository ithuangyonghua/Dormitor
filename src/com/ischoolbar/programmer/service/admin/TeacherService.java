package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.TeacherEntity;

@Service
public interface TeacherService {
	public TeacherEntity findByTeachername(String id);//查询用户是否存在
	public int add(TeacherEntity teacherEntity);//添加老师
	public List<TeacherEntity> findList(Map<String, Object> queryMap);//获取用户信息
	public int getTotal(Map<String, Object> queryMap);//获取总条数
	public List<TeacherEntity> findALL();
	public int edit(TeacherEntity teacherEntity);//修改信息
	public int editPassword(TeacherEntity teacherEntity);//修改密码
	public int delete(String ids);//删除
}
