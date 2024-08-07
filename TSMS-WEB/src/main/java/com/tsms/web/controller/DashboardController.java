package com.tsms.web.controller;

import static com.tsms.web.utils.WebUtils.setModel;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tsms.web.dao.UserDao;
import com.tsms.web.dto.TimesheetMasterDto;
import com.tsms.web.entity.UserMaster;
import com.tsms.web.service.EmployeeTimesheetService;
import com.tsms.web.validation.MailUtils;

@Controller
public class DashboardController {

	@Autowired
	UserDao userDao;
	
	@Autowired
	private EmployeeTimesheetService employeeTimesheetService;

	@GetMapping("Dashboard.html")
	public String getDashboard(Model model) {
		String view;
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
			List<TimesheetMasterDto> timesheet = employeeTimesheetService.getDailyCountTimesheet();
			List<TimesheetMasterDto> timesheetweekly = employeeTimesheetService.getWeeklyCountTimesheet();
			List<TimesheetMasterDto> timesheetmonthly = employeeTimesheetService.getMonthlyCountTimesheet();
			model.addAttribute("dailyCount",timesheet.size());
			model.addAttribute("weeklyCount",timesheetweekly.size());
			model.addAttribute("monthlyCount",timesheetmonthly.size());
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		model.addAttribute("birthdays", birthdays);
		model.addAttribute("annivarsay", annivarsay);
		view = setModel(model, "Dashboard.url", "Dashboard.page", "meta.key.desc.dashboard.title",
				"meta.key.dashboard.title");
		return view;
	}
	
	@PostMapping("BirthdayWish.html")
	public @ResponseBody boolean sendBirthdayWish(Model model, HttpServletRequest request,RedirectAttributes rd) throws IOException {
		String mailid = request.getParameter("mailid");
		String fname = request.getParameter("fname");
		List<UserMaster> list = userDao.findAll();
		List<String> empMails = new ArrayList<>();
		
		
		
//		for(UserMaster lists:list) {
//			if(lists.getPersonalEmail()!=null && !lists.getPersonalEmail().equalsIgnoreCase("") && (lists.getPersonalEmail().contains("@gmail.com") || lists.getPersonalEmail().contains(".net")  ) )
//			{
//				empMails.add(lists.getPersonalEmail());
//			}
//			

//		}																																																																																																																																																																																										
		boolean flag = MailUtils.sendEmailBirthday(mailid,fname,empMails);																																																																																																																																																																																																																									
//		UserMaster master = userDao.getBypersonalEmail(mailid);																																																																																																																																																																																																																																																																																																																																																	
//		if(flag)
//			master.setIsBirthdaySend("true");
//		else
//			master.setIsBirthdaySend("false");
//		userDao.save(master);
		return flag;
	}
	
	@PostMapping("AnnivarsayWish.html")
	public @ResponseBody boolean sendAnnivarsayWish(Model model, HttpServletRequest request) throws IOException {
		String mailid = request.getParameter("mailid");
		String fname = request.getParameter("fname");
		List<UserMaster> list = userDao.findAll();
		List<String> empMails = new ArrayList<>();
		for(UserMaster lists:list) {
			empMails.add(lists.getPersonalEmail());
		}
		boolean flag =MailUtils.sendEmailAnniversary(mailid,fname,empMails);
//		UserMaster master = userDao.getBypersonalEmail(mailid);
//		if(flag)
//			master.setIsAnniversarySend("true");
//		else
//			master.setIsAnniversarySend("false");
//		userDao.save(master);
		return flag;
	}
}
