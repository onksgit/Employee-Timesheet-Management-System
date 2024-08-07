package com.tsms.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsms.web.entity.AwardMaster;

public interface AwardsDao extends JpaRepository<AwardMaster, Long> {

}
