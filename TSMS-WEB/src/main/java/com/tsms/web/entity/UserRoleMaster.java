package com.tsms.web.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_user_roles")
public class UserRoleMaster {

	@Id
	@SequenceGenerator(name = "seq_user_role_id", sequenceName = "seq_user_role_pk_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user_role_id")
	@Column(name = "pk_id")
	private Long primaryId;

	@Column(name = "profile_prefix")
	private String profilePrefix;

	@Column(name = "profile_name")
	private String profileName;

	public Long getPrimaryId() {
		return primaryId;
	}

	public void setPrimaryId(Long primaryId) {
		this.primaryId = primaryId;
	}

	public String getProfilePrefix() {
		return profilePrefix;
	}

	public void setProfilePrefix(String profilePrefix) {
		this.profilePrefix = profilePrefix;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	
	

	}