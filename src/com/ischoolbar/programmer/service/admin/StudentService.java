package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.StudentEntity;
import com.ischoolbar.programmer.entity.admin.StudentVO;

@Service
public interface StudentService {
	public StudentEntity findByStudentname(String id);
	public int add(StudentEntity studentEntity);//添加学生
	public List<StudentVO> findList(Map<String, Object> queryMap);//获取用户信息
	public int getTotal(Map<String, Object> queryMap);//获取总条数
	
	public int edit(StudentEntity studentEntity);//修改信息
	public int editPassword(StudentEntity studentEntity);//修改密码
	public int delete(String ids);//删除
   
	public List<StudentEntity> findALL(Map<String, Object> queryMap);
}

