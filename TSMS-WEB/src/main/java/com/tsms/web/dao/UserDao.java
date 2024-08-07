package com.tsms.web.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsms.web.entity.UserMaster;
import com.tsms.web.entity.UserRoleMaster;

public interface UserDao extends JpaRepository<UserMaster, Long> {

	UserMaster findByUserName(String username);
	
	UserMaster getBypersonalEmail(String mailid);

	List<UserMaster> findByfkUserRoleId(UserRoleMaster userRoleMasterInstance);

	List<UserMaster> findByFkUserRoleIdOrderByFirstNameAsc(UserRoleMaster userRoleMasterInstance);


}
