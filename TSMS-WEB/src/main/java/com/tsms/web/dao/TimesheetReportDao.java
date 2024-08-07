package com.tsms.web.dao;

import java.util.List;

import com.tsms.pojo.TimesheetReportReq;
import com.tsms.pojo.TimesheetReportResponsePojo;
import com.tsms.web.entity.UserMaster;

public interface TimesheetReportDao {

	public List<TimesheetReportResponsePojo> getTimesheetReportList(TimesheetReportReq timesheetReportReq,int  offset,int limit);

	public List<TimesheetReportResponsePojo> getProjectWiseReport(Long projectID, String startDate, String endDate);

	public List<TimesheetReportResponsePojo> getManagerWiseReport(Long managerID, String startDate, String endDate);

	public List<TimesheetReportResponsePojo> getlistBranchWiseReport(String branch, String startDate, String endDate);

	public List<TimesheetReportResponsePojo> getDetailedTimesheetReportList(TimesheetReportReq timesheetReportReq,int offset,int limit);

	public List<UserMaster> getTimesheetNotFilledReport(String fromDate, String toDate);

	public List<TimesheetReportResponsePojo> getEmployeeWiseReport(Long empID, String startDate, String endDate);

}
