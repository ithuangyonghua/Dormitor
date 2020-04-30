package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import com.ischoolbar.programmer.entity.admin.AssetCategoryEntity;

public interface AssetCategoryDao {
	public List<AssetCategoryEntity> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);//ɾ��
	public int edit(AssetCategoryEntity assetCategoryEntity);//�޸���Ϣ
	public int insertSelective(AssetCategoryEntity assetCategoryEntity);//����
	public List<AssetCategoryEntity> findAll();
}
