package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import com.ischoolbar.programmer.entity.admin.Asset;
import com.ischoolbar.programmer.entity.admin.AssetPropertyListEntity;
import com.ischoolbar.programmer.entity.admin.AssetPropertyListVO;

public interface AssetPeopertyListDao {
	public List<AssetPropertyListVO> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);//ɾ��
	public int edit(AssetPropertyListEntity assetPropertyListEntity);//�޸���Ϣ
	public int insertSelective(AssetPropertyListEntity assetPropertyListEntity);//����
	
	public List<AssetPropertyListEntity> getAeestByCate(Map<String, Object> queryMap);
}
