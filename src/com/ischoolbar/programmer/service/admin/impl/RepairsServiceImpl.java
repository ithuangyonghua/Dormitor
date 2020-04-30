package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.RepairsDao;
import com.ischoolbar.programmer.entity.admin.RepairsEntity;
import com.ischoolbar.programmer.entity.admin.RepairsVO;
import com.ischoolbar.programmer.service.admin.RepairsService;

@Service
public class RepairsServiceImpl implements RepairsService {
    @Autowired
    RepairsDao repairsDao;
	@Override
	public List<RepairsVO> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return repairsDao.findList(queryMap);
	}
	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return repairsDao.getTotal(queryMap);
	}
	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return repairsDao.delete(ids);
	}
	@Override
	public int add(RepairsEntity repairsEntity) {
		// TODO Auto-generated method stub
		return repairsDao.add(repairsEntity);
	}
	@Override
	public int edit(RepairsEntity repairsEntity) {
		// TODO Auto-generated method stub
		return repairsDao.edit(repairsEntity);
	}

}
