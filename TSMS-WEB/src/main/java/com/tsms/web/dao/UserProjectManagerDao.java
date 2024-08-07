package com.tsms.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsms.web.entity.ProjectMaster;
import com.tsms.web.entity.UserMaster;
import com.tsms.web.entity.UserProjectManager;

public interface UserProjectManagerDao extends JpaRepository<UserProjectManager, Long> {


	List<UserProjectManager> findByFkUser(UserMaster usermaster);

	List<UserProjectManager> findByFkUserAndFkManager(UserMaster reciver,UserMaster reciver1);

	List<UserProjectManager> findByFkManager(UserMaster reciver);


}
