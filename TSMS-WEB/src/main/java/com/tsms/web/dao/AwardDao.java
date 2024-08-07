package com.tsms.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsms.web.entity.Award;

@Repository
public interface AwardDao extends JpaRepository<Award, Long> {

}
