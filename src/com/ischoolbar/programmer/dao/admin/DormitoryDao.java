package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import com.ischoolbar.programmer.entity.admin.DormitoryEntity;
import com.ischoolbar.programmer.entity.admin.DormitoryVO;
import com.ischoolbar.programmer.entity.admin.TeacherEntity;

public interface DormitoryDao {
	public List<DormitoryEntity> findTopList();
	public List<DormitoryVO> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);//É¾³ý
	public int add(DormitoryVO dormitoryVO);//
	public int edit(DormitoryVO dormitoryVO);//ÐÞ¸ÄÐÅÏ¢
	public List<DormitoryEntity> findALL(Map<String, Object> queryMap);
}
