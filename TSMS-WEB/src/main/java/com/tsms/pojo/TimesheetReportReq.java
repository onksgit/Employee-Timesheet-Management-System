package com.tsms.pojo;

public class TimesheetReportReq {
	
	private String reportType,fromDate,toDate;
	private String branch,approvalStatus,project,manager,employee;
	private String  isBranchSelected,isApprovalStatusSelected;
	private String excelOrPdf;
	private boolean ProjectSelected,ManagerSelected,EmployeeSelected;
	private String action;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public String getIsBranchSelected() {
		return isBranchSelected;
	}
	public void setIsBranchSelected(String isBranchSelected) {
		this.isBranchSelected = isBranchSelected;
	}
	public String getIsApprovalStatusSelected() {
		return isApprovalStatusSelected;
	}
	public void setIsApprovalStatusSelected(String isApprovalStatusSelected) {
		this.isApprovalStatusSelected = isApprovalStatusSelected;
	}
	public String getExcelOrPdf() {
		return excelOrPdf;
	}
	public void setExcelOrPdf(String excelOrPdf) {
		this.excelOrPdf = excelOrPdf;
	}
	public boolean isProjectSelected() {
		return ProjectSelected;
	}
	public void setProjectSelected(boolean projectSelected) {
		ProjectSelected = projectSelected;
	}
	public boolean isManagerSelected() {
		return ManagerSelected;
	}
	public void setManagerSelected(boolean managerSelected) {
		ManagerSelected = managerSelected;
	}
	public boolean isEmployeeSelected() {
		return EmployeeSelected;
	}
	public void setEmployeeSelected(boolean employeeSelected) {
		EmployeeSelected = employeeSelected;
	}
	@Override
	public String toString() {
		return "TimesheetReportReq [reportType=" + reportType + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", branch=" + branch + ", approvalStatus=" + approvalStatus + ", project=" + project + ", manager="
				+ manager + ", employee=" + employee + ", isBranchSelected=" + isBranchSelected
				+ ", isApprovalStatusSelected=" + isApprovalStatusSelected + ", excelOrPdf=" + excelOrPdf
				+ ", ProjectSelected=" + ProjectSelected + ", ManagerSelected=" + ManagerSelected
				+ ", EmployeeSelected=" + EmployeeSelected + ", action=" + action + "]";
	}
	
	
	
	

	
	
}
