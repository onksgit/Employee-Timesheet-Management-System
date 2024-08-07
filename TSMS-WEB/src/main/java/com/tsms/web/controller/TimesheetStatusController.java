package com.tsms.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tsms.web.entity.AwardMaster;
import com.tsms.web.entity.TimesheetMaster;
import static com.tsms.web.utils.WebUtils.setModel;

import java.util.List;

import com.tsms.web.dao.AwardsDao;
import com.tsms.web.dto.TimesheetDataPojo;
import com.tsms.web.dto.TimesheetMasterDto;
import com.tsms.web.dto.TimesheetStatusDto;
import com.tsms.web.entity.UserMaster;
import com.tsms.web.service.EmployeeTimesheetService;
import com.tsms.web.session.SessionDetails;
import com.tsms.web.utils.WebUtils;

@Controller
public class TimesheetStatusController {
	

	
	@Autowired
	private EmployeeTimesheetService employeeTimesheetService;

	@GetMapping("TimesheetStatus.html")
	public String getStatus(Model model, HttpServletRequest httpServletRequest,
			@ModelAttribute("timesheetStatusDto") TimesheetStatusDto timesheetStatusDto) {
		String view;
		try {
			SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
			UserMaster userMaster = sessionDetails.getLoggedInUser();
			List<TimesheetDataPojo> list = employeeTimesheetService.getPendingtimesheet(userMaster);
			
		
			
			String paramters =httpServletRequest.getParameter("weeklyTimesheetId");
			model.addAttribute("list", list);
			model.addAttribute("weeklyTimesheetId", paramters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		view = WebUtils.setModel(model, "ApproveTimesheet.url", "AddTimesheet.page", "meta.key.desc.AddTimesheet.title",
				"meta.key.AddTimesheet.title");
		return view;
	}
	
	@GetMapping("WeeklyWork.html")
	public String getEmployeeWeeklyWork(Model model,HttpServletRequest httpServletRequest,@ModelAttribute("timesheetStatusDto") TimesheetStatusDto timesheetStatusDto) {
		String view;
		String empcode = httpServletRequest.getParameter("empcode");
		SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
		UserMaster userMaster = sessionDetails.getLoggedInUser();
		List<TimesheetMasterDto> timesheetMasterDtoList = employeeTimesheetService.getEmployeeWeeklyWork(empcode,userMaster);
		view = WebUtils.setModel(model, "WeeklyTimesheet1.url", "AddTimesheet.page", "meta.key.desc.AddTimesheet.title",
				"meta.key.AddTimesheet.title");
		model.addAttribute("timesheetMasterDtoList", timesheetMasterDtoList);
		return view;
	}

	@PostMapping("AddRemark.html")
	public String getRemark(Model model, HttpServletRequest httpServletRequest,
			@ModelAttribute("timesheetStatusDto") TimesheetStatusDto timesheetStatusDto, RedirectAttributes rd) {
		String view = null;
//		timesheetStatusDto.setEmpCode(httpServletRequest.getParameter("empcode"));
		SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
		if(timesheetStatusDto.getWeeklyTimesheetId() !=null && timesheetStatusDto.getWeeklyTimesheetId() !="") {			
			employeeTimesheetService.updateRemark(timesheetStatusDto, sessionDetails);
			rd.addFlashAttribute("messageSuccess", "remark.add.success");
		}else {
			rd.addFlashAttribute("messageError", "view.weekly.timesheet");
		}
		setModel(model, "AddTimesheet.page", "meta.key.desc.AddTimesheet.title", "meta.key.AddTimesheet.title");
		view = "redirect:TimesheetStatus.html";
	
		return view;
	}

	
}
