package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.AssetDao;
import com.ischoolbar.programmer.entity.admin.Asset;
import com.ischoolbar.programmer.entity.admin.AssetVO;
import com.ischoolbar.programmer.service.admin.AssetService;

@Service
public class AssetServiceImpl implements AssetService {
	@Autowired
	AssetDao  assetDao;

	@Override
	public List<AssetVO> findList(Map<String, Object> queryMap) {
		return assetDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		return assetDao.getTotal(queryMap);
	}

	@Override
	public int insertSelective(Asset record) {
		// TODO Auto-generated method stub
		return assetDao.insertSelective(record);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return assetDao.delete(ids);
	}

	@Override
	public int edit(Asset record) {
		// TODO Auto-generated method stub
		return assetDao.edit(record);
	}

	@Override
	public List<Asset> findALL(Map<String, Object> queryMap) {
		return assetDao.findALL(queryMap);
	}

	

}
