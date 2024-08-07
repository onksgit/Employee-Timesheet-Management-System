package com.tsms.web.dto;

public class TimesheetStatusDto {

	private String remark;
	private Long rating;
	private String empname;
	private String empCode;
	private String awardname;
	private String weeklyTimesheetId;

	public String getAwardname() {
		return awardname;
	}

	public void setAwardname(String awardname) {
		this.awardname = awardname;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getWeeklyTimesheetId() {
		return weeklyTimesheetId;
	}

	public void setWeeklyTimesheetId(String weeklyTimesheetId) {
		this.weeklyTimesheetId = weeklyTimesheetId;
	}

}
