package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.AssetCategoryDao;
import com.ischoolbar.programmer.entity.admin.AssetCategoryEntity;
import com.ischoolbar.programmer.service.admin.AssetCategoryService;

@Service
public class AssetCategoryServiceImpl implements AssetCategoryService {
	@Autowired
	AssetCategoryDao assetCategoryDao;

	@Override
	public List<AssetCategoryEntity> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return assetCategoryDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return assetCategoryDao.getTotal(queryMap);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return assetCategoryDao.delete(ids);
	}

	@Override
	public int edit(AssetCategoryEntity assetCategoryEntity) {
		// TODO Auto-generated method stub
		return assetCategoryDao.edit(assetCategoryEntity);
	}

	@Override
	public int insertSelective(AssetCategoryEntity assetCategoryEntity) {
		// TODO Auto-generated method stub
		return assetCategoryDao.insertSelective(assetCategoryEntity);
	}

	@Override
	public List<AssetCategoryEntity> findAll() {
		// TODO Auto-generated method stub
		return assetCategoryDao.findAll();
	}

}
