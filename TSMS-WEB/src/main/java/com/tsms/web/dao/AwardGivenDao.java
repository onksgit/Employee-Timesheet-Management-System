package com.tsms.web.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsms.web.entity.AwardsGiven;
import com.tsms.web.entity.UserMaster;

public interface AwardGivenDao extends JpaRepository<AwardsGiven, Long> {

	List<AwardsGiven> findByEmployeeAndDateGiven(UserMaster userMasterr, Date currentDate);

}
