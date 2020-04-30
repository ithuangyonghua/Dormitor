package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.RegistrationEntity;
import com.ischoolbar.programmer.entity.admin.RegistrationVO;

@Service
public interface RegistrationService {
	public List<RegistrationVO> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);//É¾³ý
	public int insertSelective(RegistrationEntity registrationEntity);
	public int updateByExampleSelective(RegistrationEntity registrationEntity);
}
