package com.tsms.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsms.web.entity.UserMaster;

@Repository
public interface HrDao extends JpaRepository<UserMaster, Long>  {


	UserMaster findByEmpCode(String msgSender);
	


}
