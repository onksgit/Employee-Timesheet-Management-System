package com.tsms.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsms.web.entity.DesignationMaster;

public interface DesignationDao extends JpaRepository<DesignationMaster, Long> {

}
