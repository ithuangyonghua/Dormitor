package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import com.ischoolbar.programmer.entity.admin.Asset;
import com.ischoolbar.programmer.entity.admin.AssetVO;

public interface AssetDao {
	public List<AssetVO> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int insertSelective(Asset record);
	public int delete(String ids);//É¾³ý
	public int edit(Asset record);//ÐÞ¸ÄÐÅÏ¢
	public List<Asset> findALL(Map<String, Object> queryMap);
	
	
	
}
