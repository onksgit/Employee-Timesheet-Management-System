package com.tsms.web.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tsms.web.entity.ProjectMaster;
import com.tsms.web.entity.UserMaster;

public interface ProjectDao extends CrudRepository<ProjectMaster, Long> {

	ProjectMaster findByPrimaryId(Long projectId);

	List<ProjectMaster> findAllByOrderByProjectNameAsc();


}
