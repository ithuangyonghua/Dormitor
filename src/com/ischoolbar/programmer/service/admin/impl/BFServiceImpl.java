package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.BFDao;
import com.ischoolbar.programmer.entity.admin.AssetVO;
import com.ischoolbar.programmer.service.admin.BFService;

@Service
public class BFServiceImpl implements BFService {
	@Autowired
	BFDao bfDao;

	@Override
	public List<AssetVO> findBFList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return bfDao.findBFList(queryMap);
	}

	@Override
	public int getBFTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return bfDao.getBFTotal(queryMap);
	}

}
