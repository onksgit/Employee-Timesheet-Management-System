package com.tsms.web.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_user_project_manager")
public class UserProjectManager {

@Id
@SequenceGenerator(name = "user_project_manager_table_sqc", sequenceName = "user_project_manager_table_sqc", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_project_manager_table_sqc")
@Column(name = "pk_id")
private Long primaryId;

@OneToOne
@JoinColumn(name = "fk_user")
private UserMaster fkUser;

@OneToOne
@JoinColumn(name = "fk_project")
private ProjectMaster fkProject;

@OneToOne
@JoinColumn(name = "fk_manager")
private UserMaster fkManager;


public Long getPrimaryId() {
	return primaryId;
}

public void setPrimaryId(Long primaryId) {
	this.primaryId = primaryId;
}

public UserMaster getFkUser() {
	return fkUser;
}

public void setFkUser(UserMaster fkUser) {
	this.fkUser = fkUser;
}

public ProjectMaster getFkProject() {
	return fkProject;
}

public void setFkProject(ProjectMaster fkProject) {
	this.fkProject = fkProject;
}

public UserMaster getFkManager() {
	return fkManager;
}

public void setFkManager(UserMaster fkManager) {
	this.fkManager = fkManager;
}





}