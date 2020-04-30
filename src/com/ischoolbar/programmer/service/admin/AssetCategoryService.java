package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.AssetCategoryEntity;

@Service
public interface AssetCategoryService {
	public List<AssetCategoryEntity> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);//删除
	public int edit(AssetCategoryEntity assetCategoryEntity);//修改信息
	public int insertSelective(AssetCategoryEntity assetCategoryEntity);//插入
	public List<AssetCategoryEntity> findAll();
}
