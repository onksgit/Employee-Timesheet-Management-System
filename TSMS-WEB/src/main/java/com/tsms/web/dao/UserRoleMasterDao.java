package com.tsms.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsms.web.entity.UserRoleMaster;

@Repository
public interface UserRoleMasterDao extends JpaRepository<UserRoleMaster, Long> {

}
