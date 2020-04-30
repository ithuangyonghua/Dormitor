package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.DormitoryDao;
import com.ischoolbar.programmer.entity.admin.DormitoryEntity;
import com.ischoolbar.programmer.entity.admin.DormitoryVO;
import com.ischoolbar.programmer.service.admin.DormitoryService;

@Service
public class DormitoryServiceImpl implements DormitoryService {
   
	@Autowired
	DormitoryDao dormitoryDao;
	
	@Override
	public List<DormitoryVO> findList(Map<String, Object> queryMap) {
		return dormitoryDao.findList(queryMap);
	}
	@Override
	public int getTotal(Map<String, Object> queryMap) {
		return dormitoryDao.getTotal(queryMap);
	}
	@Override
	public List<DormitoryEntity> findTopList() {
		// TODO Auto-generated method stub
		return dormitoryDao.findTopList();
	}
	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return dormitoryDao.delete(ids);
	}
	@Override
	public int add(DormitoryVO dormitoryVO) {
		// TODO Auto-generated method stub
		return dormitoryDao.add(dormitoryVO);
	}
	@Override
	public int edit(DormitoryVO dormitoryVO) {
		// TODO Auto-generated method stub
		return dormitoryDao.edit(dormitoryVO);
	}
	@Override
	public List<DormitoryEntity> findALL(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return dormitoryDao.findALL(queryMap);
	}
	

}
