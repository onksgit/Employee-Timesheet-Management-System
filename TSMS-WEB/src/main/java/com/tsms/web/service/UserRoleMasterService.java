package com.tsms.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsms.web.dao.UserRoleMasterDao;
import com.tsms.web.entity.UserRoleMaster;

@Service
public class UserRoleMasterService {
	
	@Autowired
	private UserRoleMasterDao userRoleMasterDao;

	public List<UserRoleMaster> getAlluserRoleMastersList() {
		return userRoleMasterDao.findAll();
	}

}
