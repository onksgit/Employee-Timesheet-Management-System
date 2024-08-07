package com.tsms.web.dto;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.tsms.web.entity.UserMaster;

public class TimesheetMasterDto {

	private Long primaryId;
	private UserMaster fkUser;
	private UserMaster fkManager;
	private String projectName;
	private String empName;
	private String empCode;
	private String workDone;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date timesheetDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date addeDateTime;
	private String approvalStatus;
	private Date approvalDate;
	private String managerName;
	private String awards;
	private String awardJustification;
	private String managerWarning;
	private String managerRemark;
	private String requestDateReason;
	private String requestDateApprovalStatus;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date requestDate;
	private int dateUpdateCount;
	private boolean requestFlag;
	
	private MultipartFile workDoneImageFile;
	private MultipartFile travellingImage1File;
	private MultipartFile travellingImage2File;
	private String workDoneImage;
	private String startLocation;
	private String intermediateLocation;
	private String endLocation;
	private String travellingImage1;
	private String travellingImage2;
	
	private String startMeterReadingtext;
	private String endMeterReadingtext;
	
	private String kilometer;
	private String timesheetType;
	
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
	public UserMaster getFkManager() {
		return fkManager;
	}

	public void setFkManager(UserMaster fkManager) {
		this.fkManager = fkManager;
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

	public Date getAddeDateTime() {
		return addeDateTime;
	}

	public void setAddeDateTime(Date addeDateTime) {
		this.addeDateTime = addeDateTime;
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

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public int getDateUpdateCount() {
		return dateUpdateCount;
	}

	public void setDateUpdateCount(int dateUpdateCount) {
		this.dateUpdateCount = dateUpdateCount;
	}
	public String getRequestDateReason() {
		return requestDateReason;
	}

	public void setRequestDateReason(String requestDateReason) {
		this.requestDateReason = requestDateReason;
	}

	public String getRequestDateApprovalStatus() {
		return requestDateApprovalStatus;
	}

	public void setRequestDateApprovalStatus(String requestDateApprovalStatus) {
		this.requestDateApprovalStatus = requestDateApprovalStatus;
	}

	public boolean isRequestFlag() {
		return requestFlag;
	}

	public void setRequestFlag(boolean requestFlag) {
		this.requestFlag = requestFlag;
	}

	public MultipartFile getWorkDoneImageFile() {
		return workDoneImageFile;
	}

	public void setWorkDoneImageFile(MultipartFile workDoneImageFile) {
		this.workDoneImageFile = workDoneImageFile;
	}

	public MultipartFile getTravellingImage1File() {
		return travellingImage1File;
	}

	public void setTravellingImage1File(MultipartFile travellingImage1File) {
		this.travellingImage1File = travellingImage1File;
	}

	public MultipartFile getTravellingImage2File() {
		return travellingImage2File;
	}

	public void setTravellingImage2File(MultipartFile travellingImage2File) {
		this.travellingImage2File = travellingImage2File;
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

	public String getTimesheetType() {
		return timesheetType;
	}

	public void setTimesheetType(String timesheetType) {
		this.timesheetType = timesheetType;
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
