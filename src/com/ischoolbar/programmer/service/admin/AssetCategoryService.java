package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.AssetCategoryEntity;

@Service
public interface AssetCategoryService {
	public List<AssetCategoryEntity> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);//ɾ��
	public int edit(AssetCategoryEntity assetCategoryEntity);//�޸���Ϣ
	public int insertSelective(AssetCategoryEntity assetCategoryEntity);//����
	public List<AssetCategoryEntity> findAll();
}
