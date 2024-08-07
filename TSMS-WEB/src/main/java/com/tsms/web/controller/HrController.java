package com.tsms.web.controller;

import static com.tsms.web.utils.WebUtils.setModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tsms.pojo.AwardNomination;
import com.tsms.web.dao.BranchDao;
import com.tsms.web.dao.UserDao;
import com.tsms.web.dto.BranchMasterDto;
import com.tsms.web.dto.HrSendMSGDto;
import com.tsms.web.dto.TimesheetMasterDto;
import com.tsms.web.dto.UserMasterDto;
import com.tsms.web.entity.BranchMaster;
import com.tsms.web.entity.HrSendMSG;
import com.tsms.web.entity.UserMaster;
import com.tsms.web.service.EmployeeTimesheetService;
import com.tsms.web.service.HrService;
import com.tsms.web.session.SessionDetails;
import com.tsms.web.utils.DateUtils;
import com.tsms.web.utils.WebUtils;

@Controller
public class HrController {

	@Autowired
	private HrService hrservice;
	@Autowired
	UserDao userDao;
	
	@Autowired
	private EmployeeTimesheetService employeeTimesheetService;
	@Autowired
	private BranchDao branchDao;
	
	
	@GetMapping("HrDashboard.html")
	public String HrDashbord(Model model,@ModelAttribute("hrSendMSGDto") HrSendMSGDto hrSendMSGDto,HttpServletRequest httpServletRequest)
	{
		SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
		
		String view;
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
		String formattedDate = localDate.format(formatter);
		List<UserMaster> birthdays = new ArrayList<>();
		List<UserMaster> annivarsay = new ArrayList<>();
		boolean isManager = sessionDetails.getLoggedInUser().getFkUserRoleId().getProfilePrefix().equalsIgnoreCase("MAN");
		boolean isEmployee = sessionDetails.getLoggedInUser().getFkUserRoleId().getProfilePrefix().equalsIgnoreCase("EMP");
	try {
			List<UserMaster> list = userDao.findAll();
			List<HrSendMSG> mHrSendMSGs =mHrSendMSGs = hrservice.getLast7daysMessageFromDb(sessionDetails.getLoggedInUser(), isManager,isEmployee);
			List<UserMaster> AllEmployeeList = hrservice.getAllEmployeeList();
			model.addAttribute("AllEmployeeList", AllEmployeeList);
			model.addAttribute("mHrSendMSGs", mHrSendMSGs);
			
			
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
			List<TimesheetMasterDto> timesheetAppr = employeeTimesheetService.getDailyApprCountTimesheet();
			List<TimesheetMasterDto> timesheetweeklyAppr = employeeTimesheetService.getWeeklyApprCountTimesheet();
			List<TimesheetMasterDto> timesheetmonthlyAppr = employeeTimesheetService.getMonthlyApprCountTimesheet();
			List<BranchMaster> branchList=(List<BranchMaster>) branchDao.findAll(); 
			
			
			model.addAttribute("dailyCount",timesheet.size());
			model.addAttribute("weeklyCount",timesheetweekly.size());
			model.addAttribute("monthlyCount",timesheetmonthly.size());
			model.addAttribute("dailyApprCount",timesheetAppr.size());
			model.addAttribute("weeklyApprCount",timesheetweeklyAppr.size());
			model.addAttribute("monthlyApprCount",timesheetmonthlyAppr.size());
			model.addAttribute("branchList",branchList);
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		model.addAttribute("birthdays", birthdays);
		model.addAttribute("annivarsay", annivarsay);
		view = setModel(model, "Dashboard.url", "Dashboard.page", "meta.key.desc.dashboard.title",
				"meta.key.dashboard.title");
		return view;
	}

	@GetMapping("ViewAllEmployee.html")
	public String ViewAllEmployee(Model model, @ModelAttribute("userMasterDto") UserMasterDto userMasterDto,
			RedirectAttributes rd) {
		try {
			List<UserMaster> AllEmployeeList = hrservice.getAllEmployeeList();
			model.addAttribute("AllEmployeeList", AllEmployeeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String view = WebUtils.setModel(model, "ViewAllEmployee.url", "ViewAllEmployee.page",
				"meta.key.desc.ViewAllEmployee.title", "meta.key.ViewAllEmployee.title");
		return view;
	}

//	@GetMapping("SendHrMessages.html")
//	public String sendHrMessages(Model model, @ModelAttribute("hrSendMSGDto") HrSendMSGDto hrSendMSGDto,
//			RedirectAttributes rd) {
//
//		List<UserMaster> AllEmployeeList = hrservice.getAllEmployeeList();
//		model.addAttribute("AllEmployeeList", AllEmployeeList);
//
//		String view = WebUtils.setModel(model, "SendHrMessages.url", "SendHrMessages.page",
//				"meta.key.desc.SendHrMessages.title", "meta.key.SendHrMessages.title");
//		return view;
//	}

	@PostMapping("sendHrMessage.html")
	public String sendHrMessagesSAVETODB(Model model, @ModelAttribute("hrSendMSGDto") HrSendMSGDto hrSendMSGDto,
			RedirectAttributes rd, HttpServletRequest httpServletRequest) {

		SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);

		try {

			hrSendMSGDto.setMsgSender(sessionDetails.getLoggedInUser().getEmpCode());
			hrSendMSGDto.setMsgSentDate(DateUtils.getCurrentDateTime());
			Long hrSendMSG = hrservice.saveMessageTodb(hrSendMSGDto);

			if (hrSendMSG != null) {
				rd.addFlashAttribute("messageSuccess", "hr.message.send.success");
			} else {
				rd.addFlashAttribute("messageError", "hr.message.send.fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rd.addFlashAttribute("messageError", "hr.message.send.fail");
		}

		String view = "redirect:HrDashboard.html";
		return view;
	}

//	@GetMapping("ViewMessages.html")
//	public String viewmessagecontroller(Model model, @ModelAttribute("hrSendMSGDto") HrSendMSGDto hrSendMSGDto,
//			RedirectAttributes rd, HttpServletRequest httpServletRequest) {
//		SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
//		String reciver = sessionDetails.getLoggedInUser().getEmpCode();
//		boolean isManager = sessionDetails.getLoggedInUser().getFkUserRoleId().getProfilePrefix()
//				.equalsIgnoreCase("MAN");
//		boolean isEmployee = sessionDetails.getLoggedInUser().getFkUserRoleId().getProfilePrefix()
//				.equalsIgnoreCase("EMP");
//
//		List<HrSendMSG> mHrSendMSGs = hrservice.getLast7daysMessageFromDb(reciver, isManager, isEmployee);
//
//		model.addAttribute("mHrSendMSGs", mHrSendMSGs);
//
//		String view = WebUtils.setModel(model, "ViewHrMessages.url", "ViewHrMessages.page",
//				"meta.key.desc.ViewHrMessages.title", "meta.key.ViewHrMessages.title");
//		return view;
//	}
	
	@GetMapping("AwardNominations.html")
	public String AwardNominations(Model model, HttpServletRequest httpServletRequest) {

		String view = WebUtils.setModel(model, "AwardNominations.url", "AwardNominations.page",
				"meta.key.desc.AwardNominations.title", "meta.key.AwardNominations.title");
		return view;
	}
	
	@PostMapping("ViewAwardNomination.html")
	public String ViewAwardNomination(Model model, HttpServletRequest httpServletRequest,@RequestParam("month")
	String month,@RequestParam("year") String year)
	{
		try {
		List<AwardNomination> awardNominationsList= hrservice.getAwardNominationsList(month,year);
		model.addAttribute("awardNominationsList", awardNominationsList);
		
		
		}catch (Exception e) {
		e.printStackTrace();	
		}
	
		String view = WebUtils.setModel(model, "AwardNominations.url", "AwardNominations.page",
				"meta.key.desc.AwardNominations.title", "meta.key.AwardNominations.title");
		return view;
	}
	
	
	@GetMapping("WeaklyTimesheet.html")
	public String WeaklyTimesheet(Model model,HttpServletRequest httpServletRequest)
	{
		String view;
		List<TimesheetMasterDto> listTimesheetDto=null;
		SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
		try {
			 listTimesheetDto= employeeTimesheetService.getLast7DaysWork(sessionDetails.getLoggedInUser());
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		model.addAttribute("listTimesheetDto", listTimesheetDto);

		view = WebUtils.setModel(model, "WeaklyTimesheet.url", "WeaklyTimesheet.page", "meta.key.desc.WeaklyTimesheet.title",
				"meta.key.WeaklyTimesheet.title");
		return view;
	 }
	
	 @GetMapping("/PdfReadServletcon")
	    public void downloadFile(@RequestParam("param") String filePath, HttpServletResponse response) throws IOException {
	        // Validate and sanitize the file path
	        if (filePath != null && !filePath.isEmpty() && filePath.startsWith("/opt/DRA/MSGFiles/")) {
	            File file = new File(filePath);
	            if (file.exists() && !file.isDirectory()) {
	                String contentType = getContentType(file);
	                response.setContentType(contentType);
	                response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
	                response.setContentLength((int) file.length());

	                try (FileInputStream fileInputStream = new FileInputStream(file);
	                     OutputStream outputStream = response.getOutputStream()) {
	                    byte[] buffer = new byte[4096];
	                    int bytesRead;
	                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
	                        outputStream.write(buffer, 0, bytesRead);
	                    }
	                }
	            } else {
	                response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
	            }
	        } else {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid or unauthorized file path");
	        }
	    }

	    private String getContentType(File file) {
	        String fileName = file.getName().toLowerCase();
	        if (fileName.endsWith(".pdf")) {
	            return "application/pdf";
	        } else if (fileName.endsWith(".xls")) {
	            return "application/vnd.ms-excel";
	        } else if (fileName.endsWith(".xlsx")) {
	            return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	        } else if (fileName.endsWith(".doc")) {
	            return "application/msword";
	        } else if (fileName.endsWith(".docx")) {
	            return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	        } else {
	            return "application/octet-stream"; // Default for other types
	        }
	    }
	    
	    
	    
	    
	}



