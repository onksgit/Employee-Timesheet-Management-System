package com.tsms.web.dto;

import java.util.List;

import com.tsms.web.entity.UserMaster;

public class ProjectMasterDto {

	private Long primaryId;
	private String projectName;
	private String projectDescription;
	private UserMaster projectManager;
	private String department;

	public Long getPrimaryId() {
		return primaryId;
	}

	public void setPrimaryId(Long primaryId) {
		this.primaryId = primaryId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public UserMaster getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(UserMaster projectManager) {
		this.projectManager = projectManager;
	}

}
