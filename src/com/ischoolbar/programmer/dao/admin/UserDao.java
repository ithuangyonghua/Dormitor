package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.User;

/**
 * user�û�dao
 * @author llq
 *
 */
@Repository
public interface UserDao {
	public User findByUsername(String username);//��ѯ
	public int add(User user);//����û�
	public int edit(User user);//�޸��ù���
	
	public int editPassword(User user);//�޸�����
	public int delete(String ids);//ɾ��
	public List<User> findList(Map<String, Object> queryMap);//��ȡ�û���Ϣ
	public int getTotal(Map<String, Object> queryMap);//��ȡ������
	
}
