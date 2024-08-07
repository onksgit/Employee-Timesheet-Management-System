package com.tsms.web.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tsms.pojo.AwardRequest;
import com.tsms.web.dao.AwardDao;
import com.tsms.web.dao.AwardGivenDao;
import com.tsms.web.dao.Managerdao;
import com.tsms.web.dao.TimesheetUnlockDao;
import com.tsms.web.dao.UserDao;
import com.tsms.web.dao.UserProjectManagerDao;
import com.tsms.web.dao.UserRoleMasterDao;
import com.tsms.web.dto.HrSendMSGDto;
import com.tsms.web.dto.TimesheetDataPojo;
import com.tsms.web.dto.TimesheetMasterDto;
import com.tsms.web.dto.TimesheetStatusDto;
import com.tsms.web.entity.Award;
import com.tsms.web.entity.AwardMaster;
import com.tsms.web.entity.AwardsGiven;
import com.tsms.web.entity.HrSendMSG;
import com.tsms.web.entity.TimesheetMaster;
import com.tsms.web.entity.TimesheetUnlock;
import com.tsms.web.entity.UserMaster;
import com.tsms.web.entity.UserProjectManager;
import com.tsms.web.entity.UserRoleMaster;
import com.tsms.web.service.EmployeeTimesheetService;
import com.tsms.web.service.HrService;
import com.tsms.web.session.SessionDetails;
import com.tsms.web.utils.DateUtils;
import com.tsms.web.utils.WebUtils;

@Controller
public class ManagerController {

	@Autowired
	private EmployeeTimesheetService employeeTimesheetService;
	@Autowired
	private HrService hrservice;
	@Autowired
	private AwardDao awardDao;
	@Autowired
	private AwardGivenDao awardGivenDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TimesheetUnlockDao timesheetUnlockDao;
	@Autowired
	Managerdao managerDao;
	@Autowired
	UserProjectManagerDao userProjectManagerDao;
	@Autowired
	UserRoleMasterDao userRoleMasterDao;
	
	
	
	
	
	@GetMapping("ManagerDashboard.html")
	public String ManagerDashboard(Model model ,@ModelAttribute("hrSendMSGDto") HrSendMSGDto hrSendMSGDto,HttpServletRequest httpServletRequest)
	{		
		String view;
		List<UserMaster> list = userDao.findAll();
		SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
		UserMaster reciver = sessionDetails.getLoggedInUser();
//		List<UserMaster> AllEmployeeList = hrservice.getAllEmployeeList();
		
		List<UserProjectManager> findAllUser = userProjectManagerDao.findByFkUser(reciver);
		List<UserProjectManager> findAllUser2 = userProjectManagerDao.findByFkManager(reciver);

		List<UserMaster> mgrList = new ArrayList<>();
		List<UserMaster> usrList = new ArrayList<>();


		for(UserProjectManager finMaster:findAllUser)
		{
			mgrList.add(finMaster.getFkManager());
		}
		for(UserProjectManager finMaster:findAllUser2)
		{
			usrList.add(finMaster.getFkUser());
		}
		
		UserRoleMaster hr=userRoleMasterDao.getById(2L);
		List<UserMaster> allHrList = userDao.findByfkUserRoleId(hr);
        List<UserMaster> AllEmployeeList = new ArrayList<>();
        AllEmployeeList.addAll(mgrList);
        AllEmployeeList.addAll(usrList);
        AllEmployeeList.addAll(allHrList);

		model.addAttribute("AllEmployeeList", AllEmployeeList);
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
			List<TimesheetMasterDto> timesheet = employeeTimesheetService.getDailyCountManTimesheet(reciver.getPrimaryId());
			List<TimesheetMasterDto> timesheetweekly = employeeTimesheetService.getWeeklyCountManTimesheet(reciver.getPrimaryId());
			List<TimesheetMasterDto> timesheetmonthly = employeeTimesheetService.getMonthlyCountManTimesheet(reciver.getPrimaryId());
			
			List<TimesheetMasterDto> timesheetAppr = employeeTimesheetService.getDailyApprCountManTimesheet(reciver.getPrimaryId());
			List<TimesheetMasterDto> timesheetweeklyAppr = employeeTimesheetService.getWeeklyApprCountManTimesheet(reciver.getPrimaryId());
			List<TimesheetMasterDto> timesheetmonthlyAppr = employeeTimesheetService.getMonthlyApprCountManTimesheet(reciver.getPrimaryId());
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
		
		List<HrSendMSG> mHrSendMSGs = null;
		List<TimesheetMasterDto> listTimesheetDto=null;
//		SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
//		UserMaster reciver = sessionDetails.getLoggedInUser();
		boolean isManager = sessionDetails.getLoggedInUser().getFkUserRoleId().getProfilePrefix().equalsIgnoreCase("MAN");
		boolean isEmployee = sessionDetails.getLoggedInUser().getFkUserRoleId().getProfilePrefix().equalsIgnoreCase("EMP");


	try {
		 mHrSendMSGs = hrservice.getLast7daysMessageFromDb(reciver, isManager,isEmployee);
//		 listTimesheetDto= employeeTimesheetService.getLast7DaysWork(sessionDetails.getLoggedInUser());
		
	} catch (Exception e) {	
		e.printStackTrace();
	}
	model.addAttribute("mHrSendMSGs", mHrSendMSGs);
//	model.addAttribute("listTimesheetDto", listTimesheetDto);

	view = WebUtils.setModel(model, "ManagerDashboard.url", "Dashboard.page", "meta.key.desc.dashboard.title",
			"meta.key.dashboard.title");
	return view;
		
	}
	
	@GetMapping("ApproveRequestDate.html")
	public String approveRequestDate(Model model, HttpServletRequest httpServletRequest,
			@ModelAttribute("timesheetUnlock") TimesheetUnlock timesheetUnlock) {
		String view = null;
		SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
		List<TimesheetUnlock> list = timesheetUnlockDao.findByFkManagerIdAndStatus(sessionDetails.getLoggedInUser().getPrimaryId());
		model.addAttribute("list", list);
		view = WebUtils.setModel(model, "ApproveRequestTimesheet.url", "AddTimesheet.page", "meta.key.desc.AddTimesheet.title",
				"meta.key.AddTimesheet.title");
		return view;
	}
	
	
	@GetMapping("ApprovedDateLock.html")
	public String approvedDateLock(Model model, HttpServletRequest httpServletRequest,RedirectAttributes rd) {
		String view = null;

		try {
			String id= httpServletRequest.getParameter("id");
			System.out.println(id);
			boolean flag = employeeTimesheetService.approvedDateLock(Long.valueOf(id));
			if (flag)
				rd.addFlashAttribute("messageSuccess", "date.request.status.msg.success");
			else
				rd.addFlashAttribute("messageError", "date.request.status.msg.error");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		rd.addAttribute("messageSuccess","Approved");
		view="redirect:ApproveRequestDate.html";
		return view;
	}
	
	
	@PostMapping("ApprovedDate.html")
	public @ResponseBody List<TimesheetMasterDto> getMenuHandler(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
		SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
		String empCode =sessionDetails.getEmpCode();
		List<TimesheetMasterDto> list = employeeTimesheetService.getApprovedDates(empCode);
		return list;
	}
	
	
	
	
	@GetMapping("MonthlyAwards.html")
	public String getMonthlyAwards(Model model, HttpServletRequest httpServletRequest,
			@ModelAttribute("timesheetStatusDto") TimesheetStatusDto timesheetStatusDto) {
		String view;
		try {
			SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
			UserMaster userMaster = sessionDetails.getLoggedInUser();
			
//			List<TimesheetDataPojo> timesheetMasterDtoList = employeeTimesheetService.getEmployees(userMaster);
//			model.addAttribute("timesheetMasterDtoList", timesheetMasterDtoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		view = WebUtils.setModel(model, "MonthlyAwards.url", "AddTimesheet.page", "meta.key.desc.AddTimesheet.title",
				"meta.key.AddTimesheet.title");
		return view;
	}
	
	@PostMapping("MonthlyAwards.html")
	public String getMonthlyAwardsPost(Model model, HttpServletRequest httpServletRequest,
			@ModelAttribute("timesheetStatusDto") TimesheetStatusDto timesheetStatusDto,@RequestParam("month")
	String month,@RequestParam("year") String year) {
		String view;
		try {
			SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
			UserMaster userMaster = sessionDetails.getLoggedInUser();
			
			List<TimesheetDataPojo> timesheetMasterDtoList = employeeTimesheetService.getEmployees(userMaster,month,year);
			
			
			
			model.addAttribute("timesheetMasterDtoList", timesheetMasterDtoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		view = WebUtils.setModel(model, "MonthlyAwards.url", "AddTimesheet.page", "meta.key.desc.AddTimesheet.title",
				"meta.key.AddTimesheet.title");
		return view;
	}
	
	
	
	@PostMapping("AddAward.html")
	public String GiveMonthly(Model model, HttpServletRequest httpServletRequest,
			@ModelAttribute("awardRequest") AwardRequest awardRequest,RedirectAttributes redirectAttributes) {
		SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
		UserMaster userMaster = sessionDetails.getLoggedInUser();
		AwardsGiven pkid=null;
//		employeeTimesheetService.updateEmployeeWithAwards(timesheetStatusDto,userMaster);

		try {
		UserMaster employee = userDao.findByUserName(awardRequest.getEmpCode());	
		UserMaster manager = sessionDetails.getLoggedInUser();
		if(awardRequest.getAwardId() !=null)
		{
	    List<Long> awardIdList = Arrays.stream(awardRequest.getAwardId().split(",")).map(Long::parseLong).collect(Collectors.toList());
	    List<String> justifications = Arrays.stream(awardRequest.getJustification().split(",")).collect(Collectors.toList());

		for (int i = 0; i < awardIdList.size(); i++) {
			Award award = awardDao.findById(awardIdList.get(i)).orElseThrow(() -> new RuntimeException("Award not found"));
			AwardsGiven awardsGiven = new AwardsGiven();
			awardsGiven.setEmployee(employee);
			awardsGiven.setManager(manager);
			awardsGiven.setAward(award);
			awardsGiven.setDateGiven(DateUtils.getCurrentDate());
			awardsGiven.setRemark(awardRequest.getRemark());
			awardsGiven.setJustification(justifications.get(i));
			awardsGiven.setRating(awardRequest.getRating());
			pkid = awardGivenDao.save(awardsGiven);
		}
		}
		else
		{
			AwardsGiven awardsGiven = new AwardsGiven();
			awardsGiven.setEmployee(employee);
			awardsGiven.setManager(manager);
			awardsGiven.setDateGiven(DateUtils.getCurrentDate());
			awardsGiven.setRemark(awardRequest.getRemark());
			awardsGiven.setRating(awardRequest.getRating());
			pkid = awardGivenDao.save(awardsGiven);
		}
		}
		catch (Exception e) {
		e.printStackTrace();
		redirectAttributes.addFlashAttribute("messageError", "save.request.award.fail.msg");
		}
		
		if(pkid!=null)
		{
		redirectAttributes.addFlashAttribute("messageSuccess", "save.request.award.success.msg");
		}
		else
		{
			redirectAttributes.addFlashAttribute("messageError", "save.request.award.fail.msg");
		}
		return "redirect:MonthlyAwards.html";
		
	}
	
	@GetMapping("viewEmployeeMonthlyWorkForAwards.html")
	public String viewEmployeeMonthlyWorkForAwards(Model model, HttpServletRequest httpServletRequest,
			@RequestParam(name = "empCode") String empCode)
	{
		String view=null;
		try
		{
		SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
		List<TimesheetMasterDto> MonthlyTimesheetWorkList = employeeTimesheetService.getMonthlyTimesheetWork(empCode,sessionDetails.getLoggedInUser().getPrimaryId());
		model.addAttribute("MonthlyTimesheetWorkList",MonthlyTimesheetWorkList);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		view = WebUtils.setModel(model, "viewMonthlyWork.url", "EmployeeMonthlyWork.page", "meta.key.desc.EmployeeMonthlyWork.title",
				"meta.key.EmployeeMonthlyWork.title");
		return view;
	}
	
	@GetMapping("giveMonthlyAwardPage.html")
	public String giveMonthlyAwardPage(Model model, HttpServletRequest httpServletRequest,
			@RequestParam(name = "empCode") String empCode,@ModelAttribute("awardRequest") AwardRequest awardRequest)
	{
		String view=null;
		try
		{
		SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
		model.addAttribute("empCode",empCode);
		model.addAttribute("managerId",sessionDetails.getLoggedInUser().getPrimaryId());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		view = WebUtils.setModel(model, "giveMonthlyAwardPage.url", "EmployeeMonthlyWork.page", "meta.key.desc.EmployeeMonthlyWork.title",
				"meta.key.EmployeeMonthlyWork.title");
		return view;
	}
	
	
	
	
	

}
