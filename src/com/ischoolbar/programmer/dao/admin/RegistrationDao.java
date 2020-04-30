package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.RegistrationEntity;
import com.ischoolbar.programmer.entity.admin.RegistrationVO;
/**
 * ÅÌµã
 * @author Hyh
 *
 */
@Repository
public interface RegistrationDao {
	/**
	 * ²éÑ¯
	 * @param queryMap
	 * @return
	 */
	public List<RegistrationVO> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);//É¾³ý
	public int insertSelective(RegistrationEntity registrationEntity);
	public int updateByExampleSelective(RegistrationEntity registrationEntity);
}
