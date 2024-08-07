package com.tsms.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsms.web.dao.DesignationDao;
import com.tsms.web.entity.DesignationMaster;

@Service
public class DesignationService {

	@Autowired
	DesignationDao designationDao;

	public List<DesignationMaster> getAllDesignationList() {
		return designationDao.findAll();
	}
	
	
}
