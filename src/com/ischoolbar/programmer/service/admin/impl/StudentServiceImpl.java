package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.StudentDao;
import com.ischoolbar.programmer.entity.admin.StudentEntity;
import com.ischoolbar.programmer.entity.admin.StudentVO;
import com.ischoolbar.programmer.service.admin.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao StudentDao;

	@Override
	public StudentEntity findByStudentname(String id) {
		return StudentDao.findByStudentname(id);
	}

	@Override
	public int add(StudentEntity studentEntity) {
		return StudentDao.add(studentEntity);
	}

	@Override
	public List<StudentVO> findList(Map<String, Object> queryMap) {
		List<StudentVO> findList = StudentDao.findList(queryMap);
		for(StudentEntity i:findList){
			System.out.println(i);
		}
		return findList;
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		return StudentDao.getTotal(queryMap);
	}

	@Override
	public int edit(StudentEntity studentEntity) {
		return StudentDao.edit(studentEntity);
	}

	@Override
	public int editPassword(StudentEntity studentEntity) {
		return StudentDao.editPassword(studentEntity);
	}

	@Override
	public int delete(String ids) {
		return StudentDao.delete(ids);
	}

	@Override
	public List<StudentEntity> findALL(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return StudentDao.findALL(queryMap);
	}

	

}
