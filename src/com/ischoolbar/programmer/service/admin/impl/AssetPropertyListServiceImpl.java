package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.AssetPeopertyListDao;
import com.ischoolbar.programmer.entity.admin.AssetPropertyListEntity;
import com.ischoolbar.programmer.entity.admin.AssetPropertyListVO;
import com.ischoolbar.programmer.service.admin.AssetPropertyListService;

@Service
public class AssetPropertyListServiceImpl implements AssetPropertyListService {
    @Autowired
    AssetPeopertyListDao assetPeopertyListDao;
	@Override
	public List<AssetPropertyListVO> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return assetPeopertyListDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return assetPeopertyListDao.getTotal(queryMap);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return assetPeopertyListDao.delete(ids);
	}

	@Override
	public int edit(AssetPropertyListEntity assetPropertyListEntity) {
		// TODO Auto-generated method stub
		return assetPeopertyListDao.edit(assetPropertyListEntity);
	}

	@Override
	public int insertSelective(AssetPropertyListEntity assetPropertyListEntity) {
		// TODO Auto-generated method stub
		return assetPeopertyListDao.insertSelective(assetPropertyListEntity);
	}

	@Override
	public List<AssetPropertyListEntity> getAeestByCate(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return assetPeopertyListDao.getAeestByCate(queryMap);
	}

}
