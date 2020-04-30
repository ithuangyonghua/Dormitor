package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.RepairsEntity;
import com.ischoolbar.programmer.entity.admin.RepairsVO;

@Service
public interface RepairsService {
	
	public List<RepairsVO> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);//É¾³ý
	public int add(RepairsEntity repairsEntity);
	public int edit(RepairsEntity repairsEntity);//ÐÞ¸ÄÐÅÏ¢
}
