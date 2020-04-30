package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.TeacherEntity;

@Service
public interface TeacherService {
	public TeacherEntity findByTeachername(String id);//��ѯ�û��Ƿ����
	public int add(TeacherEntity teacherEntity);//�����ʦ
	public List<TeacherEntity> findList(Map<String, Object> queryMap);//��ȡ�û���Ϣ
	public int getTotal(Map<String, Object> queryMap);//��ȡ������
	public List<TeacherEntity> findALL();
	public int edit(TeacherEntity teacherEntity);//�޸���Ϣ
	public int editPassword(TeacherEntity teacherEntity);//�޸�����
	public int delete(String ids);//ɾ��
}
