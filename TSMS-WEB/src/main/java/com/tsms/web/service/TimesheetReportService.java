package com.tsms.web.service;

import java.util.List;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsms.pojo.ReportPojo;
import com.tsms.pojo.TimesheetReportReq;
import com.tsms.pojo.TimesheetReportResponsePojo;
import com.tsms.web.dao.TimesheetReportDao;
import com.tsms.web.entity.UserMaster;
import com.tsms.web.utils.DateUtils;

@Service
public class TimesheetReportService {
	
	@Autowired
	TimesheetReportDao timesheetReportDao;
//
	public List<TimesheetReportResponsePojo> getTimesheetReportList(TimesheetReportReq timesheetReportReq,int  offset,int limit) {
		return timesheetReportDao.getTimesheetReportList(timesheetReportReq,offset,limit);
	}
	public List<TimesheetReportResponsePojo> getDetailedTimesheetReportList(TimesheetReportReq timesheetReportReq,int  offset,int limit) {
		return timesheetReportDao.getDetailedTimesheetReportList(timesheetReportReq,offset,limit);
	}

	public List<TimesheetReportResponsePojo> getProjectWiseReport(ReportPojo reportPojo) {
		Long projectID = Long.parseLong(reportPojo.getProject());
		String startDate = DateUtils.formatDate1(reportPojo.getFrmDate());
		String endDate = DateUtils.formatDate1(reportPojo.getToDate());
		return timesheetReportDao.getProjectWiseReport(projectID, startDate, endDate);
	}

	public List<TimesheetReportResponsePojo> getManagerWiseReport(ReportPojo reportPojo) {
		Long managerID = Long.parseLong(reportPojo.getManagername());
		String startDate = DateUtils.formatDate1(reportPojo.getFrmDate());
		String endDate = DateUtils.formatDate1(reportPojo.getToDate());
		return timesheetReportDao.getManagerWiseReport(managerID, startDate, endDate);
	}

	public List<TimesheetReportResponsePojo> getlistBranchWiseReport(ReportPojo reportPojo) {
		String branch = reportPojo.getBranch();
		String startDate = DateUtils.formatDate1(reportPojo.getFrmDate());
		String endDate = DateUtils.formatDate1(reportPojo.getToDate());
		return timesheetReportDao.getlistBranchWiseReport(branch, startDate, endDate);
	}
	public List<UserMaster> getTimesheetNotFilledReport(String fromDate, String toDate) {
		return timesheetReportDao.getTimesheetNotFilledReport(fromDate,toDate);

	}
	public List<TimesheetReportResponsePojo> getEmployeeWiseReport(ReportPojo reportPojo) {
		Long EmpID = Long.parseLong(reportPojo.getEmployeename());
		String startDate = DateUtils.formatDate1(reportPojo.getFrmDate());
		String endDate = DateUtils.formatDate1(reportPojo.getToDate());
		return timesheetReportDao.getEmployeeWiseReport(EmpID, startDate, endDate);
	}

	

}
