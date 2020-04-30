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
	public int delete(String ids);//删除
	public int edit(AssetPropertyListEntity assetPropertyListEntity);//修改信息
	public int insertSelective(AssetPropertyListEntity assetPropertyListEntity);//插入
	
	public List<AssetPropertyListEntity> getAeestByCate(Map<String, Object> queryMap);
}
