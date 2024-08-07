package com.tsms.web.dto;

import java.util.Optional;

import com.tsms.web.entity.DesignationMaster;

public class TimesheetDataPojo {

	private String empCode;
	private String firstName;
	private String lastName;
	private String timesheetdate;
	
	private String branch;
	private String department;
	private String designation;
	private String grade;
	private String isActive;
	private String joinDate;
	private String title;
	private String approvalStatus;
	private String isawardGiven; //Yes or No
	
		
	
	public String getIsawardGiven() {
		return isawardGiven;
	}
	public void setIsawardGiven(String isawardGiven) {
		this.isawardGiven = isawardGiven;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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
	public String getTimesheetdate() {
		return timesheetdate;
	}
	public void setTimesheetdate(String timesheetdate) {
		this.timesheetdate = timesheetdate;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesignation() { 
		return designation;
	}
	public void setDesignation(String designationMaster) {
		this.designation = designationMaster;
	}
	

}
