package com.tsms.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "t_mst_timesheet")
public class TimesheetMaster {

	@Id
	@SequenceGenerator(name = "timesheet_table_sqc", sequenceName = "timesheet_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timesheet_table_sqc")
	@Column(name = "pk_id")
	private Long primaryId;

	@OneToOne
	@JoinColumn(name = "fkUser")
	private UserMaster fkUser;
	
	@OneToOne
	@JoinColumn(name = "fkManager")
	private UserMaster fkManager;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "added_date_time")
	private Date addeDateTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "timesheet_date")
	private Date timesheetDate;

	@Column(name = "project_name")
	private String projectName;

	@Column(name = "emp_name")
	private String empName;
	
	@Column(name = "emp_code")
	private String empCode;
	
	@Column(name = "work_done")
	private String workDone;

	@Column(name = "approval_status")
	private String approvalStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "approval_date")
	private Date approvalDate;

	@Column(name = "manager_Name")
	private String managerName;

	@Column(name = "awards")
	private String awards;

	@Column(name = "award_justification")
	private String awardJustification;

	@Column(name = "manager_warning")
	private String managerWarning;

	@Column(name = "manager_remark")
	private String managerRemark;
	
	@Column(name = "manager_rating")
	private String managerRating;
	
	@Column(name = "work_done_image")
	private String workDoneImage;
	
	@Column(name = "start_location")
	private String startLocation;
	
	@Column(name = "intermediate_location")
	private String intermediateLocation;

	@Column(name = "end_location")
	private String endLocation;
	
	@Column(name = "travelling_image1")
	private String travellingImage1;
	
	@Column(name = "travelling_image2")
	private String travellingImage2;
	
	@Column(name = "kilometer")
	private String kilometer;
	
	@Column(name = "start_meter_reading")
	private String startMeterReadingtext;
	
	@Column(name = "end_meter_reading")
	private String endMeterReadingtext;
	

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

	public Date getAddeDateTime() {
		return addeDateTime;
	}
	
	public UserMaster getFkManager() {
		return fkManager;
	}

	public void setFkManager(UserMaster fkManager) {
		this.fkManager = fkManager;
	}

	public void setAddeDateTime(Date addeDateTime) {
		this.addeDateTime = addeDateTime;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getWorkDone() {
		return workDone;
	}

	public void setWorkDone(String workDone) {
		this.workDone = workDone;
	}

	public Date getTimesheetDate() {
		return timesheetDate;
	}

	public void setTimesheetDate(Date timesheetDate) {
		this.timesheetDate = timesheetDate;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public String getAwardJustification() {
		return awardJustification;
	}

	public void setAwardJustification(String awardJustification) {
		this.awardJustification = awardJustification;
	}

	public String getManagerWarning() {
		return managerWarning;
	}

	public void setManagerWarning(String managerWarning) {
		this.managerWarning = managerWarning;
	}

	public String getManagerRemark() {
		return managerRemark;
	}

	public void setManagerRemark(String managerRemark) {
		this.managerRemark = managerRemark;
	}

	public String getManagerRating() {
		return managerRating;
	}

	public void setManagerRating(String managerRating) {
		this.managerRating = managerRating;
	}

	public String getWorkDoneImage() {
		return workDoneImage;
	}

	public void setWorkDoneImage(String workDoneImage) {
		this.workDoneImage = workDoneImage;
	}

	public String getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	public String getIntermediateLocation() {
		return intermediateLocation;
	}

	public void setIntermediateLocation(String intermediateLocation) {
		this.intermediateLocation = intermediateLocation;
	}

	public String getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(String endLocation) {
		this.endLocation = endLocation;
	}

	public String getTravellingImage1() {
		return travellingImage1;
	}

	public void setTravellingImage1(String travellingImage1) {
		this.travellingImage1 = travellingImage1;
	}

	public String getTravellingImage2() {
		return travellingImage2;
	}

	public void setTravellingImage2(String travellingImage2) {
		this.travellingImage2 = travellingImage2;
	}

	public String getKilometer() {
		return kilometer;
	}

	public void setKilometer(String kilometer) {
		this.kilometer = kilometer;
	}

	public String getStartMeterReadingtext() {
		return startMeterReadingtext;
	}

	public void setStartMeterReadingtext(String startMeterReadingtext) {
		this.startMeterReadingtext = startMeterReadingtext;
	}

	public String getEndMeterReadingtext() {
		return endMeterReadingtext;
	}

	public void setEndMeterReadingtext(String endMeterReadingtext) {
		this.endMeterReadingtext = endMeterReadingtext;
	}
	
	
}
