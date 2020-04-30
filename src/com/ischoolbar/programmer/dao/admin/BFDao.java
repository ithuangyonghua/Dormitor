package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import com.ischoolbar.programmer.entity.admin.AssetVO;

public interface BFDao {
	//±¨·ÏÄ£¿é
		public List<AssetVO> findBFList(Map<String, Object> queryMap);
		public int getBFTotal(Map<String, Object> queryMap);
}
