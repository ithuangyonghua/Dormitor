package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.Asset;
import com.ischoolbar.programmer.entity.admin.AssetVO;
import com.ischoolbar.programmer.entity.admin.DormitoryVO;

@Service
public interface AssetService {
	public List<AssetVO> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int insertSelective(Asset record);
	public int delete(String ids);//ɾ��
	public int edit(Asset record);//�޸���Ϣ
	public List<Asset> findALL(Map<String, Object> queryMap);
	

}
