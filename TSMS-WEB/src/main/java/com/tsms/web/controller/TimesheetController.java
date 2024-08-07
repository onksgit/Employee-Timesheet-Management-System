package com.tsms.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.tsms.pojo.AddUserMasterPojo;
import com.tsms.web.dao.ProjectDao;
import com.tsms.web.dao.TimesheetUnlockDao;
import com.tsms.web.dao.UserDao;
import com.tsms.web.dao.UserProjectManagerDao;
import com.tsms.web.dto.TimesheetMasterDto;
import com.tsms.web.dto.UserMasterDto;
import com.tsms.web.entity.ProjectMaster;
import com.tsms.web.entity.TimesheetMaster;
import com.tsms.web.entity.TimesheetUnlock;
import com.tsms.web.entity.UserMaster;
import com.tsms.web.entity.UserProjectManager;
import com.tsms.web.service.EmployeeTimesheetService;
import com.tsms.web.service.ProjectService;
import com.tsms.web.session.SessionDetails;
import com.tsms.web.utils.DateUtils;
import com.tsms.web.utils.WebUtils;

@Controller
public class TimesheetController {

	@Autowired
	ProjectService projectService;
	@Autowired
	private UserProjectManagerDao userProjectManagerDao;
	@Autowired
	private EmployeeTimesheetService employeeTimesheetService;
	UserMaster userMaster;
	List<TimesheetMasterDto> timesheetMasterDtoList;
	
	@Autowired
	UserDao userDao;
	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	TimesheetUnlockDao timesheetUnlockDao;

	@GetMapping("SaveEmpTimesheet.html")
	public String getAddTimesheetForm(Model model, @ModelAttribute("timesheetMaster") TimesheetMasterDto timesheetMaster, HttpServletRequest httpServletRequest) {
		try {
		String requestDate=httpServletRequest.getParameter("requestDate");
		SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);

//		timesheetMasterDtoList = employeeTimesheetService.getLast7DaysWork(sessionDetails.getLoggedInUser());
		Iterable<ProjectMaster> projectList = projectDao.findAllByOrderByProjectNameAsc();
		List<UserMaster> user =userDao.findAll();
		List<UserMasterDto> managerList = employeeTimesheetService.getAllManagerList();
		List<UserMasterDto> adminList=employeeTimesheetService.getAllAdminList();
		if(adminList!=null)
		{
			managerList.addAll(adminList);
		}
		
		List<UserProjectManager> projectManagerList=userProjectManagerDao.findByFkUser(sessionDetails.getLoggedInUser());
		
        LocalDate startDate = LocalDate.now().withDayOfMonth(1);
        LocalDate endDate = LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1);
        Date startDateAsDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDateAsDate = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<TimesheetUnlock> listRequestDate = timesheetUnlockDao.findByUserIdAndRequestDateBetween(sessionDetails.getLoggedInUser().getPrimaryId(),startDateAsDate,endDateAsDate);
        List<Date> dateReq = new ArrayList<Date>();
        if(listRequestDate.size() > 0){
        	for(int i=0;i<listRequestDate.size();i++) {
        		if(listRequestDate.get(i).getStatus().equals("Approved")) {        			
        			dateReq.add(listRequestDate.get(i).getRequestDate());
        		}
        	}
        }
		model.addAttribute("listRequestDate",dateReq);
		model.addAttribute("managerList", managerList);
		model.addAttribute("projectList", projectList);
		model.addAttribute("requestDate", requestDate);
//		model.addAttribute("timesheetMasterModel", timesheetMasterDtoList);
		model.addAttribute("sessionDetails",sessionDetails);
		model.addAttribute("user", user);
		model.addAttribute("projectManagerList",projectManagerList);
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		String view = WebUtils.setModel(model, "addTimesheet.url", "AddTimesheet.page",
				"meta.key.desc.AddTimesheet.title", "meta.key.AddTimesheet.title");
		return view;
	}
	
	@GetMapping("RequestDate.html")
	public String RequestDate(Model model, @ModelAttribute("timesheetUnlock") TimesheetUnlock timesheetUnlock
			,HttpServletRequest httpServletRequest) {
		
		try {
		SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
	
	        LocalDate startDate = LocalDate.now().withDayOfMonth(1);
	        LocalDate endDate = LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1);
	        Date startDateAsDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	        Date endDateAsDate = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		List<TimesheetUnlock> listRequestDate = timesheetUnlockDao.findByUserIdAndRequestDateBetween(sessionDetails.getLoggedInUser().getPrimaryId(),startDateAsDate,endDateAsDate);
		List<UserProjectManager> projectManagerList=userProjectManagerDao.findByFkUser(sessionDetails.getLoggedInUser());

		
		model.addAttribute("projectManagerList",projectManagerList);

		model.addAttribute("listRequestDate",listRequestDate);
		model.addAttribute("sessionDetails",sessionDetails);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		String view = WebUtils.setModel(model, "RequestDate.url", "RequestDate.page",
				"meta.key.desc.RequestDate.title", "meta.key.RequestDate.title");
		return view;
	}
	
	
	

	@PostMapping("SaveEmpTimesheet.html")
	public String addTimesheet(@ModelAttribute("timesheetMaster") TimesheetMasterDto timesheetMaster, Model model,
			HttpServletRequest httpServletRequest,RedirectAttributes redirectAttributes) {
			boolean isAlreadyaddedDate=false;
			String view="EmployeeDashboard.html";
		try {
			SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
//			List<TimesheetMasterDto> timesheetMasterDtoList=employeeTimesheetService.getAllTimesheetDetailes(sessionDetails.getLoggedInUser());
			List<TimesheetMaster> timesheetByUser=employeeTimesheetService.getTimeshDetailesByDate(timesheetMaster.getTimesheetDate(),sessionDetails.getLoggedInUser());
			TimesheetMaster timesheetdetalsByTimesheetDate=employeeTimesheetService.getTimeshDetailesByDate(timesheetMaster.getTimesheetDate(),sessionDetails.getLoggedInUser(),timesheetMaster.getProjectName() );
//			TimesheetMaster timesheetdetalsByRequestedDate=employeeTimesheetService.getTimeshDetailesByRequestedDate(timesheetMaster.getTimesheetDate(),sessionDetails.getLoggedInUser());
			TimesheetMaster id = null;
			if(timesheetByUser.size()<3) {
			if(timesheetdetalsByTimesheetDate!=null){
				isAlreadyaddedDate=true;
			}
//			else if(timesheetdetalsByRequestedDate!=null){
//				isAlreadyaddedDate=false;
//				timesheetdetalsByRequestedDate.setFkUser(sessionDetails.getLoggedInUser());
//				timesheetdetalsByRequestedDate.setAddeDateTime(DateUtils.getCurrentDateTime());
//				timesheetdetalsByRequestedDate.setProjectName(timesheetMaster.getProjectName());
//				timesheetdetalsByRequestedDate.setFkManager(timesheetMaster.getFkManager());
//				timesheetdetalsByRequestedDate.setApprovalStatus("Pending");
//				timesheetdetalsByRequestedDate.setWorkDone(timesheetMaster.getWorkDone());
//				timesheetdetalsByRequestedDate.setTimesheetDate(timesheetMaster.getTimesheetDate());
//				timesheetdetalsByRequestedDate.setEmpName(sessionDetails.getFirstName());
//				timesheetdetalsByRequestedDate.setEmpCode(sessionDetails.getEmpCode());
//				timesheetdetalsByRequestedDate.setStartLocation(timesheetMaster.getStartLocation());
//				timesheetdetalsByRequestedDate.setIntermediateLocation(timesheetMaster.getIntermediateLocation());
//				timesheetdetalsByRequestedDate.setEndLocation(timesheetMaster.getEndLocation());
//				timesheetdetalsByRequestedDate.setKilometer(timesheetMaster.getKilometer());
//				List<MultipartFile> file=new ArrayList<MultipartFile>();
//				if(timesheetMaster !=null) {
//					file.add(timesheetMaster.getWorkDoneImageFile());
//					file.add(timesheetMaster.getTravellingImage1File());
//					file.add(timesheetMaster.getTravellingImage2File());
//				}
//				insertProfileImage(file, timesheetdetalsByRequestedDate);
//				id = employeeTimesheetService.saveTimesheet(timesheetdetalsByRequestedDate);
//				redirectAttributes.addFlashAttribute("messageSuccess", "save.timesheet.success.msg");
//			}
//			else {	
//				TimesheetMaster master =new TimesheetMaster();
//				master.setFkUser(sessionDetails.getLoggedInUser());
//				master.setAddeDateTime(DateUtils.getCurrentDateTime());
//				master.setApprovalStatus("Pending");
//				master.setWorkDone(timesheetMaster.getWorkDone());
//				master.setTimesheetDate(timesheetMaster.getTimesheetDate());
//				master.setProjectName(timesheetMaster.getProjectName());
//				master.setFkManager(timesheetMaster.getFkManager());
//				master.setEmpName(sessionDetails.getFirstName());
//				master.setEmpCode(sessionDetails.getEmpCode());
//				master.setStartLocation(timesheetMaster.getStartLocation());
//				master.setIntermediateLocation(timesheetMaster.getIntermediateLocation());
//				master.setEndLocation(timesheetMaster.getEndLocation());
//				master.setKilometer(timesheetMaster.getKilometer());
//				String addedOn = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
//				master.setWorkDoneImage("/opt/DRA/TSMS/"+addedOn.concat("/")+timesheetMaster.getWorkDoneImageFile().getOriginalFilename());
//				master.setWorkDoneImage("/opt/DRA/TSMS/"+addedOn.concat("/")+timesheetMaster.getTravellingImage1File().getOriginalFilename());
//				master.setWorkDoneImage("/opt/DRA/TSMS/"+addedOn.concat("/")+timesheetMaster.getTravellingImage2File().getOriginalFilename());
//				List<MultipartFile> file=new ArrayList<MultipartFile>();
//				if(timesheetMaster !=null) {
//					file.add(timesheetMaster.getWorkDoneImageFile());
//					file.add(timesheetMaster.getTravellingImage1File());
//					file.add(timesheetMaster.getTravellingImage2File());
//				}
//				insertProfileImage(file);
//				timesheetUnlockDao.updateStatus(timesheetMaster.getTimesheetDate());
//				id = employeeTimesheetService.saveTimesheet(master);
//				redirectAttributes.addFlashAttribute("messageSuccess", "save.timesheet.success.msg");
//			}
			
			if (isAlreadyaddedDate) {
				redirectAttributes.addFlashAttribute("messageError", "save.timesheet.same.date.project.msg");
			}else if (!isAlreadyaddedDate) {
				TimesheetMaster master =new TimesheetMaster();
				master.setFkUser(sessionDetails.getLoggedInUser());
				master.setAddeDateTime(DateUtils.getCurrentDateTime());
				master.setApprovalStatus("Pending");
				master.setEmpName(sessionDetails.getFirstName());
				master.setEmpCode(sessionDetails.getEmpCode());
				master.setWorkDone(timesheetMaster.getWorkDone());
				master.setTimesheetDate(timesheetMaster.getTimesheetDate());
				master.setProjectName(timesheetMaster.getProjectName());
				master.setFkManager(timesheetMaster.getFkManager());
				master.setStartLocation(timesheetMaster.getStartLocation());
				master.setIntermediateLocation(timesheetMaster.getIntermediateLocation());
				master.setEndLocation(timesheetMaster.getEndLocation());
				master.setStartMeterReadingtext(timesheetMaster.getStartMeterReadingtext());
				master.setEndMeterReadingtext(timesheetMaster.getEndMeterReadingtext());
				master.setKilometer(timesheetMaster.getKilometer());
				String addedOn = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
				
				if(timesheetMaster.getTimesheetType().equalsIgnoreCase("Timesheet"))
				{
				master.setWorkDoneImage("/opt/DRA/TSMS/"+addedOn.concat("/")+timesheetMaster.getWorkDoneImageFile().getOriginalFilename());
				master.setTravellingImage1("/opt/DRA/TSMS/"+addedOn.concat("/")+timesheetMaster.getTravellingImage1File().getOriginalFilename());
				master.setTravellingImage2("/opt/DRA/TSMS/"+addedOn.concat("/")+timesheetMaster.getTravellingImage2File().getOriginalFilename());
				List<MultipartFile> file=new ArrayList<MultipartFile>();
				if(timesheetMaster !=null) {
					file.add(timesheetMaster.getWorkDoneImageFile());
					file.add(timesheetMaster.getTravellingImage1File());
					file.add(timesheetMaster.getTravellingImage2File());
				}
				insertProfileImage(file);
				}
				
				timesheetUnlockDao.updateStatus(timesheetMaster.getTimesheetDate());
				id = employeeTimesheetService.saveTimesheet(master);
				redirectAttributes.addFlashAttribute("messageSuccess", "save.timesheet.success.msg");
			} else {
				redirectAttributes.addFlashAttribute("messageError", "save.timesheet.error.msg");
			}
			
		}else {
			redirectAttributes.addFlashAttribute("messageError", "save.today.date.limit.fail.msg");
		}
			
			Long accessrole = sessionDetails.getLoggedInUser().getFkUserRoleId().getPrimaryId();
			if (accessrole == 1) {
				view = "redirect:AdminDashboard.html";
			} else if (accessrole == 3) {
				view = "redirect:ManagerDashboard.html";
			} else if (accessrole == 2) {
				view = "redirect:HrDashboard.html";
			} else {
				view = "redirect:EmployeeDashboard.html";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("messageError", "save.timesheet.error.msg");
		}
		return view;
	}
	
	@PostMapping("RequestDate.html")
	public String getRequestDate(Model model, HttpServletRequest httpServletRequest,@ModelAttribute("timesheetUnlock") 
	TimesheetUnlock timesheetUnlock,RedirectAttributes redirectAttributes) throws ParseException {
		String view = null;
		SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
		try {
			   LocalDate startDate = LocalDate.now().withDayOfMonth(1);
		        LocalDate endDate = LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1);
		        Date startDateAsDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		        Date endDateAsDate = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			int unlockRequestsCount = timesheetUnlockDao.countByUserIdAndRequestDateBetween(timesheetUnlock.getUserId(),
					startDateAsDate, endDateAsDate);
			TimesheetUnlock getprevousrequest = timesheetUnlockDao
					.findByUserIdAndRequestDate(timesheetUnlock.getUserId(), timesheetUnlock.getRequestDate());

			Date date = timesheetUnlock.getRequestDate();
			if (!(date.after(DateUtils.getCurrentDate()))) {
				if (unlockRequestsCount < 3 ) {
					if (getprevousrequest == null) {
						employeeTimesheetService.updateRequestdate(timesheetUnlock, sessionDetails);
						redirectAttributes.addFlashAttribute("messageSuccess", "save.request.date.success.msg");
					} else {
						redirectAttributes.addFlashAttribute("messageError", "save.request.date.samedate.fail.msg");
					}
				} else
					redirectAttributes.addFlashAttribute("messageError", "save.request.date.limit.fail.msg");
			} else
				redirectAttributes.addFlashAttribute("messageError", "save.request.date.after.date.msg");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("messageError", "something.went.wrong.msg");

		}
		view = "redirect:RequestDate.html";
		return view;
	}
	
	
	public void insertProfileImage(List<MultipartFile> file) throws IOException {
		String filePath = null;
		String addedOn = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		
		for(int i=0;i<file.size();i++) {
			if(file.get(i) !=null) {
				if (!file.isEmpty()) {
					String contentType =file.get(i).getContentType();
					byte[] bytes = file.get(i).getBytes();
					if (contentType.startsWith("image/jpg") || contentType.startsWith("image/png") || contentType.startsWith("image/jpeg")) {
						filePath = "/opt/DRA/TSMS/".concat(addedOn.concat("/"));
						Path path = Paths.get(filePath);
						if (!Files.exists(path)) {
							Files.createDirectories(path);
						}
						Path filePaths = Paths.get(filePath + file.get(i).getOriginalFilename());
						Files.write(filePaths, bytes);
					}
				}
			}
		}
	}
}
