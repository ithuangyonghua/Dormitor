package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.User;

/**
 * user用户dao
 * @author llq
 *
 */
@Repository
public interface UserDao {
	public User findByUsername(String username);//查询
	public int add(User user);//添加用户
	public int edit(User user);//修改用公户
	
	public int editPassword(User user);//修改密码
	public int delete(String ids);//删除
	public List<User> findList(Map<String, Object> queryMap);//获取用户信息
	public int getTotal(Map<String, Object> queryMap);//获取总条数
	
}
