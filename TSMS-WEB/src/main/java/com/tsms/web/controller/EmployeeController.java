package com.tsms.web.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tsms.web.dao.UserDao;
import com.tsms.web.dao.UserProjectManagerDao;
import com.tsms.web.dao.UserRoleMasterDao;
import com.tsms.web.dto.HrSendMSGDto;
import com.tsms.web.dto.TimesheetMasterDto;
import com.tsms.web.entity.HrSendMSG;
import com.tsms.web.entity.UserMaster;
import com.tsms.web.entity.UserProjectManager;
import com.tsms.web.entity.UserRoleMaster;
import com.tsms.web.service.EmployeeTimesheetService;
import com.tsms.web.service.HrService;
import com.tsms.web.session.SessionDetails;
import com.tsms.web.utils.WebUtils;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeTimesheetService employeeTimesheetService;

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleMasterDao userRoleMasterDao;
	@Autowired
	private UserProjectManagerDao userProjectManagerDao;
	
	@Autowired
	private HrService hrservice;

	@GetMapping("EmployeeDashboard.html")
	public String ManagerDashboard(Model model, @ModelAttribute("hrSendMSGDto") HrSendMSGDto hrSendMSGDto,
			HttpServletRequest httpServletRequest) {
		String view;
		List<HrSendMSG> mHrSendMSGs = null;
		SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
//		List<TimesheetMasterDto> timesheetMasterDtoList = employeeTimesheetService
//				.getLast7DaysWork(sessionDetails.getLoggedInUser());
		UserMaster reciver = sessionDetails.getLoggedInUser();
		boolean isManager = sessionDetails.getLoggedInUser().getFkUserRoleId().getProfilePrefix()
				.equalsIgnoreCase("MAN");
		boolean isEmployee = sessionDetails.getLoggedInUser().getFkUserRoleId().getProfilePrefix()
				.equalsIgnoreCase("EMP");

		try {
			mHrSendMSGs = hrservice.getLast7daysMessageFromDb(reciver, isManager, isEmployee);
//			List<UserMaster> AllEmployeeList = hrservice.getAllEmployeeList();

//			List<UserProjectManager> findAllUser = userProjectManagerDao.findByFkUserAndFkManager(reciver,reciver);
			List<UserProjectManager> findAllUser = userProjectManagerDao.findByFkUser(reciver);
			List<UserMaster> mgrList = new ArrayList<>();

			for(UserProjectManager finMaster:findAllUser)
			{
				System.out.println("findAllUser"+finMaster);
				mgrList.add(finMaster.getFkManager());
			}
			
			UserRoleMaster hr=userRoleMasterDao.getById(2L);
			List<UserMaster> allHrList = userDao.findByfkUserRoleId(hr);
			
			
			
	        List<UserMaster> AllEmployeeList = new ArrayList<>();
	        AllEmployeeList.addAll(mgrList);
	        AllEmployeeList.addAll(allHrList);

			

			model.addAttribute("AllEmployeeList", AllEmployeeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<UserMaster> list = userDao.findAll();
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
		String formattedDate = localDate.format(formatter);
		List<UserMaster> birthdays = new ArrayList<>();
		List<UserMaster> annivarsay = new ArrayList<>();
		try {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getBirthDate() != null) {
					String mydate = list.get(i).getBirthDate().toString().substring(5, 10);
					if (mydate.equals(formattedDate)) {
						birthdays.add(list.get(i));
					}

					if (list.get(i).getJoinDate() != null) {
						String anniv = list.get(i).getJoinDate().toString().substring(5, 10);
						if (anniv.equals(formattedDate)) {
							annivarsay.add(list.get(i));
						}
					}
				}
			}
			List<TimesheetMasterDto> timesheet = employeeTimesheetService.getDailyCountTimesheet(reciver.getPrimaryId());
			List<TimesheetMasterDto> timesheetweekly = employeeTimesheetService.getWeeklyCountTimesheet(reciver.getPrimaryId());
			List<TimesheetMasterDto> timesheetmonthly = employeeTimesheetService.getMonthlyCountTimesheet(reciver.getPrimaryId());
			
			List<TimesheetMasterDto> timesheetAppr = employeeTimesheetService.getDailyApprCountTimesheet(reciver.getPrimaryId());
			List<TimesheetMasterDto> timesheetweeklyAppr = employeeTimesheetService.getWeeklyApprCountTimesheet(reciver.getPrimaryId());
			List<TimesheetMasterDto> timesheetmonthlyAppr = employeeTimesheetService.getMonthlyApprCountTimesheet(reciver.getPrimaryId());
			model.addAttribute("dailyCount",timesheet.size());
			model.addAttribute("weeklyCount",timesheetweekly.size());
			model.addAttribute("monthlyCount",timesheetmonthly.size());
			model.addAttribute("dailyApprCount",timesheetAppr.size());
			model.addAttribute("weeklyApprCount",timesheetweeklyAppr.size());
			model.addAttribute("monthlyApprCount",timesheetmonthlyAppr.size());
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		model.addAttribute("birthdays", birthdays);
		model.addAttribute("annivarsay", annivarsay);
//		model.addAttribute("listTimesheetDto", timesheetMasterDtoList);
		model.addAttribute("mHrSendMSGs", mHrSendMSGs);

		view = WebUtils.setModel(model, "EmployeeDashboard.url", "Dashboard.page", "meta.key.desc.dashboard.title",
				"meta.key.dashboard.title");
		return view;

	}

}
