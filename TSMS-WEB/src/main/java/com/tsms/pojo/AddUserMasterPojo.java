
package com.tsms.pojo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.tsms.web.entity.DesignationMaster;
import com.tsms.web.entity.ProjectMaster;
import com.tsms.web.entity.UserMaster;
import com.tsms.web.entity.UserRoleMaster;

public class AddUserMasterPojo {

	private Long primaryId;
	private String firstName;
	private String fatherName;
	private String lastName;
	private String gender;
	private String title;
	private String accessRole;
	private String maritalStatus;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joinDate;
	private String grade;
	private String branch;
	private String empCode;
	private String userName;
	private String department;
	private String personalEmail;
	private String officeEmail;
	private String password;
	private String profileImage;
	private String presentAddress;
	private String permanentAddress;
	private Boolean isLogin;
	private String adharNo;
	private String panNo;
	private double salary;
	private Boolean isActive;
	private String secretKey;
	private String secretIV;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date addedDateTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date exitDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date passwordUpdatedOn;
	private String mobileNumber;
	private String saltValue;
	private MultipartFile profileImageFile;

	private DesignationMaster fkDesignation; 
	private List<ProjectMaster> fkProject;
	private UserMaster fkManager;
	private UserRoleMaster fkUserRoleId;
	public Long getPrimaryId() {
		return primaryId;
	}
	public void setPrimaryId(Long primaryId) {
		this.primaryId = primaryId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	
	public MultipartFile getProfileImageFile() {
		return profileImageFile;
	}
	public void setProfileImageFile(MultipartFile profileImageFile) {
		this.profileImageFile = profileImageFile;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAccessRole() {
		return accessRole;
	}
	public void setAccessRole(String accessRole) {
		this.accessRole = accessRole;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public String getPresentAddress() {
		return presentAddress;
	}
	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public Boolean getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}
	public String getAdharNo() {
		return adharNo;
	}
	public void setAdharNo(String adharNo) {
		this.adharNo = adharNo;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getSecretIV() {
		return secretIV;
	}
	public void setSecretIV(String secretIV) {
		this.secretIV = secretIV;
	}
	public Date getAddedDateTime() {
		return addedDateTime;
	}
	public void setAddedDateTime(Date addedDateTime) {
		this.addedDateTime = addedDateTime;
	}
	public Date getExitDate() {
		return exitDate;
	}
	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}
	public Date getPasswordUpdatedOn() {
		return passwordUpdatedOn;
	}
	public void setPasswordUpdatedOn(Date passwordUpdatedOn) {
		this.passwordUpdatedOn = passwordUpdatedOn;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getSaltValue() {
		return saltValue;
	}
	public void setSaltValue(String saltValue) {
		this.saltValue = saltValue;
	}
	public DesignationMaster getFkDesignation() {
		return fkDesignation;
	}
	public void setFkDesignation(DesignationMaster fkDesignation) {
		this.fkDesignation = fkDesignation;
	}
//	public ProjectMaster getFkProject() {
//		return fkProject;
//	}
//	public void setFkProject(ProjectMaster fkProject) {
//		this.fkProject = fkProject;
//	}
	
	public UserMaster getFkManager() {
		return fkManager;
	}
	public List<ProjectMaster> getFkProject() {
		return fkProject;
	}
	public void setFkProject(List<ProjectMaster> fkProject) {
		this.fkProject = fkProject;
	}
	public void setFkManager(UserMaster fkManager) {
		this.fkManager = fkManager;
	}
	public UserRoleMaster getFkUserRoleId() {
		return fkUserRoleId;
	}
	public void setFkUserRoleId(UserRoleMaster fkUserRoleId) {
		this.fkUserRoleId = fkUserRoleId;
	}
	

}
