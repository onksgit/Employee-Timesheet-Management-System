package com.tsms.web.dto;

public class UserRoleMasterDto {
	
	private Long primaryId;
	private String profilePrefix;
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
