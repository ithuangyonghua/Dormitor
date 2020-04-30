package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.RegistrationDao;
import com.ischoolbar.programmer.entity.admin.RegistrationEntity;
import com.ischoolbar.programmer.entity.admin.RegistrationVO;
import com.ischoolbar.programmer.service.admin.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	@Autowired
	RegistrationDao registrationDao;

	@Override
	public List<RegistrationVO> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return registrationDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return registrationDao.getTotal(queryMap);
	}

	@Override
	public int insertSelective(RegistrationEntity registrationEntity) {
		// TODO Auto-generated method stub
		return registrationDao.insertSelective(registrationEntity);
	}

	@Override
	public int updateByExampleSelective(RegistrationEntity registrationEntity) {
		// TODO Auto-generated method stub
		return registrationDao.updateByExampleSelective(registrationEntity);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return registrationDao.delete(ids);
	}

}
