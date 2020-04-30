package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.TeacherDao;
import com.ischoolbar.programmer.entity.admin.TeacherEntity;
import com.ischoolbar.programmer.service.admin.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	TeacherDao teacherDao;

	@Override
	public TeacherEntity findByTeachername(String id) {
		return teacherDao.findByTeachername(id);
	}

	@Override
	public int add(TeacherEntity teacherEntity) {
		return teacherDao.add(teacherEntity);
	}

	@Override
	public List<TeacherEntity> findList(Map<String, Object> queryMap) {
		List<TeacherEntity> findList = teacherDao.findList(queryMap);
		System.out.println("获取的老师列表"+findList);
		return findList;
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		return teacherDao.getTotal(queryMap);
	}

	@Override
	public int edit(TeacherEntity teacherEntity) {
		return teacherDao.edit(teacherEntity);
	}

	@Override
	public int editPassword(TeacherEntity teacherEntity) {
		return teacherDao.edit(teacherEntity);
	}

	@Override
	public int delete(String ids) {
		return teacherDao.delete(ids);
	}

	@Override
	public List<TeacherEntity> findALL() {
		// TODO Auto-generated method stub
		return teacherDao.findALL();
	}

}
