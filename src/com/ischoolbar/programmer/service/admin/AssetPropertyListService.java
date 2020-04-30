package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.AssetPropertyListEntity;
import com.ischoolbar.programmer.entity.admin.AssetPropertyListVO;

@Service
public interface AssetPropertyListService {
	public List<AssetPropertyListVO> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);//ɾ��
	public int edit(AssetPropertyListEntity assetPropertyListEntity);//�޸���Ϣ
	public int insertSelective(AssetPropertyListEntity assetPropertyListEntity);//����
	
	public List<AssetPropertyListEntity> getAeestByCate(Map<String, Object> queryMap);
}
