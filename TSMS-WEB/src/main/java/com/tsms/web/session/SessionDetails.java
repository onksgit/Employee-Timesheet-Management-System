package com.tsms.web.session;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tsms.web.entity.UserMaster;

public class SessionDetails {
	private Long primaryId;
	private String firstName;
	private String lastName;
	private String empCode;
	private String department;
	private String personalEmail;
	private String officeEmail;
	private String password;
	private String address1;
	private Boolean isLogin;
	private String username;
	private Date addedDateTime;
	private Long FkDesiginaton;
	private UserMaster loggedInUser;

	
	public Long getPrimaryId() {
		return primaryId;
	}
	public void setPrimaryId(Long primaryId) {
		this.primaryId = primaryId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPersonalEmail() {
		return personalEmail;
	}
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}
	public String getOfficeEmail() {
		return officeEmail;
	}
	public void setOfficeEmail(String officeEmail) {
		this.officeEmail = officeEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public Boolean getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}
	public UserMaster getLoggedInUser() {
		return loggedInUser;
	}
	public void setLoggedInUser(UserMaster loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	public Long getFkDesiginaton() {
		return FkDesiginaton;
	}
	public void setFkDesiginaton(Long fkDesiginaton) {
		FkDesiginaton = fkDesiginaton;
	}
	public Date getAddedDateTime() {
		return addedDateTime;
	}
	public void setAddedDateTime(Date addedDateTime) {
		this.addedDateTime = addedDateTime;
	}
	public static SessionDetails getSessionDetails(HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession(false);
		SessionDetails sessionDetails = null;
		if (httpSession != null) {
			sessionDetails = (SessionDetails) httpSession.getAttribute("sessionDetails");
		}
		return sessionDetails;
	}
	
	
}
