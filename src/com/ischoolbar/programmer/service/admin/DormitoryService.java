package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.DormitoryEntity;
import com.ischoolbar.programmer.entity.admin.DormitoryVO;

@Service
public interface DormitoryService {
	public List<DormitoryEntity> findTopList();
	public List<DormitoryVO> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);//ɾ��
	public int add(DormitoryVO dormitoryVO);//
	public int edit(DormitoryVO dormitoryVO);//�޸���Ϣ
	public List<DormitoryEntity> findALL(Map<String, Object> queryMap);
}
