package com.tsms.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_mst_designation")
public class DesignationMaster {

	@Id
	@SequenceGenerator(name = "designation_table_sqc", sequenceName = "designation_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "designation_table_sqc")
	@Column(name = "pk_id")
	private Long primaryId;

	@Column(name = "designation_name")
	private String designationName;

	@Column(name = "description")
	private String description;

	@Column(name = "profile_prefix")
	private String profilePrefix;

	public Long getPrimaryId() {
		return primaryId;
	}

	public void setPrimaryId(Long primaryId) {
		this.primaryId = primaryId;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProfilePrefix() {
		return profilePrefix;
	}

	public void setProfilePrefix(String profilePrefix) {
		this.profilePrefix = profilePrefix;
	}

}
