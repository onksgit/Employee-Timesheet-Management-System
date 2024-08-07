package com.tsms.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "t_mst_user")
public class UserMaster {

	@Id
	@SequenceGenerator(name = "user_table_sqc", sequenceName = "user_table_sqc", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "user_table_sqc")
	@Column(name = "pk_id")
	private Long primaryId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "father_name")
	private String fatherName;

	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "titile")
	private String title;
	
	@Column(name = "access_role")
	private String accessRole;

	@Column(name = "gender")
	private String gender;

	@Column(name = "marital_status")
	private String maritalStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birth_date")
	private Date birthDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "join_date")
	private Date joinDate;

	@Column(name = "grade")
	private String grade;

	@Column(name = "branch")
	private String branch;

	@Column(name = "emp_code")
	private String empCode;

	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "profile_image")
	private String profileImage;

	@Column(name = "department")
	private String department;

	@Column(name = "personal_email")
	private String personalEmail;

	@Column(name = "office_email")
	private String officeEmail;

	@Column(name = "password")
	private String password;

	@Column(name = "present_addredd")
	private String presentAddress;

	@Column(name = "permanent_address")
	private String permanentAddress;

	@Column(name = "is_login")
	private Boolean isLogin;

	@Column(name = "adhar_no")
	private String adharNo;

	@Column(name = "pan_no")
	private String panNo;

	@Column(name = "salary")
	private double salary;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "secret_key")
	private String secretKey;

	@Column(name = "secret_iv")
	private String secretIV;
	
	@Column(name = "is_birthday_send")
	private String isBirthdaySend;
	
	@Column(name = "is_anniversary_send")
	private String isAnniversarySend;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "added_date_time")
	private Date addedDateTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "updated_date_time")
	private Date updatedDateTime;
	
	@Column(name = "updated_by")
	public String updatedBy;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "exit_date")
	private Date exitDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "password_updated_on")
	private Date passwordUpdatedOn;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@ManyToOne
	@JoinColumn(name = "fk_designation")
	private DesignationMaster fkdesignation;

	@ManyToOne
	@JoinColumn(name = "fkManager")
	private UserMaster fkManager;

	@ManyToOne
	@JoinColumn(name = "fkProject")
	private ProjectMaster fkProject;
	
	@OneToOne
	@JoinColumn(name = "fk_user_role_id")
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
	public String getProfileImage() {
		return profileImage;
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

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public DesignationMaster getFkdesignation() {
		return fkdesignation;
	}

	public void setFkdesignation(DesignationMaster fkdesignation) {
		this.fkdesignation = fkdesignation;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public UserMaster getFkManager() {
		return fkManager;
	}

	public void setFkManager(UserMaster fkManager) {
		this.fkManager = fkManager;
	}

	public ProjectMaster getFkProject() {
		return fkProject;
	}

	public void setFkProject(ProjectMaster fkProject) {
		this.fkProject = fkProject;
	}

	public UserRoleMaster getFkUserRoleId() {
		return fkUserRoleId;
	}

	public void setFkUserRoleId(UserRoleMaster fkUserRoleId) {
		this.fkUserRoleId = fkUserRoleId;
	}

	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getIsBirthdaySend() {
		return isBirthdaySend;
	}

	public void setIsBirthdaySend(String isBirthdaySend) {
		this.isBirthdaySend = isBirthdaySend;
	}

	public String getIsAnniversarySend() {
		return isAnniversarySend;
	}

	public void setIsAnniversarySend(String isAnniversarySend) {
		this.isAnniversarySend = isAnniversarySend;
	}


	
	

}