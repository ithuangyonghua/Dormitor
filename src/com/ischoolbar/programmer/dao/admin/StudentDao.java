package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import com.ischoolbar.programmer.entity.admin.StudentEntity;
import com.ischoolbar.programmer.entity.admin.StudentVO;
import com.ischoolbar.programmer.entity.admin.TeacherEntity;

public interface StudentDao {
	public StudentEntity findByStudentname(String id);
	public int add(StudentEntity studentEntity);//���ѧ��
	public List<StudentVO> findList(Map<String, Object> queryMap);//��ȡ�û���Ϣ
	public int getTotal(Map<String, Object> queryMap);//��ȡ������
	
	public int edit(StudentEntity studentEntity);//�޸���Ϣ
	public int editPassword(StudentEntity studentEntity);//�޸�����
	public int delete(String ids);//ɾ��
	
	public List<StudentEntity> findALL(Map<String, Object> queryMap);
}
