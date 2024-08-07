package com.tsms.web.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.tsms.web.entity.UserMaster;

public class EmpDateUnlockRequestDto {
	
private Long primaryId;
	
	private String dateUnlockreason;
	private Long requestedCountInMonth;
	private UserMaster fkManager;
	private UserMaster fkUser;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateToUnlock;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date requestedDate;
	public Long getPrimaryId() {
		return primaryId;
	}
	public void setPrimaryId(Long primaryId) {
		this.primaryId = primaryId;
	}
	public String getDateUnlockreason() {
		return dateUnlockreason;
	}
	public void setDateUnlockreason(String dateUnlockreason) {
		this.dateUnlockreason = dateUnlockreason;
	}
	public Long getRequestedCountInMonth() {
		return requestedCountInMonth;
	}
	public void setRequestedCountInMonth(Long requestedCountInMonth) {
		this.requestedCountInMonth = requestedCountInMonth;
	}
	public UserMaster getFkManager() {
		return fkManager;
	}
	public void setFkManager(UserMaster fkManager) {
		this.fkManager = fkManager;
	}
	public UserMaster getFkUser() {
		return fkUser;
	}
	public void setFkUser(UserMaster fkUser) {
		this.fkUser = fkUser;
	}
	public Date getDateToUnlock() {
		return dateToUnlock;
	}
	public void setDateToUnlock(Date dateToUnlock) {
		this.dateToUnlock = dateToUnlock;
	}
	public Date getRequestedDate() {
		return requestedDate;
	}
	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}
	@Override
	public String toString() {
		return "EmpDateUnlockRequestDto [primaryId=" + primaryId + ", dateUnlockreason=" + dateUnlockreason
				+ ", requestedCountInMonth=" + requestedCountInMonth + ", fkManager=" + fkManager + ", fkUser=" + fkUser
				+ ", dateToUnlock=" + dateToUnlock + ", requestedDate=" + requestedDate + "]";
	}
	
	
	

}
