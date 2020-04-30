package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Asset;
import com.ischoolbar.programmer.entity.admin.RepairsEntity;
import com.ischoolbar.programmer.entity.admin.RepairsVO;
import com.ischoolbar.programmer.entity.admin.TeacherEntity;

@Repository
public interface RepairsDao {
	public List<RepairsVO> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);//ɾ��
	public int add(RepairsEntity repairsEntity);//�����ʦ
	public int edit(RepairsEntity repairsEntity);//�޸���Ϣ
}
