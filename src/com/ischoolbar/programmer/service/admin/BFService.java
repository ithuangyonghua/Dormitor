package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.AssetVO;

@Service
public interface BFService {
	//±¨·ÏÄ£¿é
			public List<AssetVO> findBFList(Map<String, Object> queryMap);
			public int getBFTotal(Map<String, Object> queryMap);
}