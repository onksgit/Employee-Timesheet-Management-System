package com.tsms.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_mst_project")
public class ProjectMaster {

	@Id
	@SequenceGenerator(name = "project_table_sqc", sequenceName = "project_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_table_sqc")
	@Column(name = "pk_id")
	private Long primaryId;

	@Column(name = "project_name")
	private String projectName;

	@Column(name = "project_description")
	private String projectDescription;

	@OneToOne
	@JoinColumn(name = "project_manager")
	private UserMaster projectManager;

	@Column(name = "department")
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
