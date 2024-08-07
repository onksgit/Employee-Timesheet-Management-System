package com.tsms.web.controller;

import static com.tsms.web.utils.WebUtils.setModel;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tsms.pojo.AddUserMasterPojo;
import com.tsms.web.dao.BranchDao;
import com.tsms.web.dao.ProjectDao;
import com.tsms.web.dao.UserDao;
import com.tsms.web.dao.UserProjectManagerDao;
import com.tsms.web.dto.BranchMasterDto;
import com.tsms.web.dto.HrSendMSGDto;
import com.tsms.web.dto.ProjectMasterDto;
import com.tsms.web.dto.TimesheetMasterDto;
import com.tsms.web.dto.UserMasterDto;
import com.tsms.web.entity.BranchMaster;
import com.tsms.web.entity.DesignationMaster;
import com.tsms.web.entity.HrSendMSG;
import com.tsms.web.entity.ProjectMaster;
import com.tsms.web.entity.UserMaster;
import com.tsms.web.entity.UserProjectManager;
import com.tsms.web.entity.UserRoleMaster;
import com.tsms.web.service.AdminService;
import com.tsms.web.service.DesignationService;
import com.tsms.web.service.EmployeeTimesheetService;
import com.tsms.web.service.HrService;
import com.tsms.web.service.UserManagementService;
import com.tsms.web.service.UserRoleMasterService;
import com.tsms.web.session.SessionDetails;
import com.tsms.web.utils.AESEncDecUtils;
import com.tsms.web.utils.DateUtils;
import com.tsms.web.utils.WebUtils;
import com.tsms.web.validation.MailUtils;

@Controller
public class AdminController { 
	
	@Autowired
	private AdminService adminService;
	@Autowired
	UserManagementService userManagementService;
	@Autowired
	DesignationService designationService;
	@Autowired
	UserRoleMasterService userRoleMasterService;
	@Autowired
	UserDao userDao;
	@Autowired
	private ModelMapper modelmapper;
	@Autowired
	private EmployeeTimesheetService employeeTimesheetService;
	
	@Autowired
	private HrService hrservice;
	
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private BranchDao branchDao;
	@Autowired
	private UserProjectManagerDao userProjectManagerDao;
	
	
	@GetMapping("AdminDashboard.html")
	public String AdminDashbord(Model model,HttpServletRequest httpServletRequest,@ModelAttribute("hrSendMSGDto") HrSendMSGDto hrSendMSGDto)
	{

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
//			List<TimesheetMasterDto> totalApprtimesheet = employeeTimesheetService.getTotalApprTimesheet();
			List<TimesheetMasterDto> timesheet = employeeTimesheetService.getDailyCountTimesheet();
			List<TimesheetMasterDto> timesheetweekly = employeeTimesheetService.getWeeklyCountTimesheet();
			List<TimesheetMasterDto> timesheetmonthly = employeeTimesheetService.getMonthlyCountTimesheet();
			
			List<TimesheetMasterDto> timesheetAppr = employeeTimesheetService.getDailyApprCountTimesheet();
			List<TimesheetMasterDto> timesheetweeklyAppr = employeeTimesheetService.getWeeklyApprCountTimesheet();
			List<TimesheetMasterDto> timesheetmonthlyAppr = employeeTimesheetService.getMonthlyApprCountTimesheet();
			SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
			UserMaster reciver = sessionDetails.getLoggedInUser();
			boolean isManager = sessionDetails.getLoggedInUser().getFkUserRoleId().getProfilePrefix()
					.equalsIgnoreCase("MAN");
			boolean isEmployee = sessionDetails.getLoggedInUser().getFkUserRoleId().getProfilePrefix()
					.equalsIgnoreCase("EMP");

			List<HrSendMSG> mHrSendMSGs = hrservice.getLast7daysMessageFromDb(reciver, isManager, isEmployee);
			List<BranchMaster> branchList=(List<BranchMaster>) branchDao.findAll(); 
			List<UserMaster> AllEmployeeList = hrservice.getAllEmployeeList();
			model.addAttribute("AllEmployeeList", AllEmployeeList);
			model.addAttribute("mHrSendMSGs", mHrSendMSGs);
			model.addAttribute("branchList", branchList);

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
		view = setModel(model, "Dashboard.url", "Dashboard.page", "meta.key.desc.dashboard.title",
				"meta.key.dashboard.title");
		return view;
	}
	

	@GetMapping(value = { "AddEmployee.html" })
	public String getAddEmpForm(Model model, @ModelAttribute("addUserMasterPojo") AddUserMasterPojo addUserMasterPojo,RedirectAttributes rd) {
		List<UserMaster> managerList= adminService.getAllManagerList();
		List<ProjectMaster> projectList=adminService.getAllProjectList();
		List<DesignationMaster> designationList=designationService.getAllDesignationList();
		List<UserRoleMaster> userRoleMasters=userRoleMasterService.getAlluserRoleMastersList();
		List<BranchMaster> branchList=(List<BranchMaster>) branchDao.findAll();
		
		model.addAttribute("userRoleMasters", userRoleMasters);
		model.addAttribute("managerList", managerList);
		model.addAttribute("projectList", projectList);
		model.addAttribute("branchList", branchList);
		model.addAttribute("designationList", designationList);


		
		String view = WebUtils.setModel(model, "addEmployee.url", "AddEmpoyee.page", "meta.key.desc.AddEmpoyee.title",
				"meta.key.AddEmpoyee.title");
		return view;
	}

	@PostMapping("SaveEmployeeToDb.html")
	public String AddEmployee(@ModelAttribute("userMasterDto") AddUserMasterPojo addUserMasterPojo,RedirectAttributes rd) throws Exception {
		
		try {
		addUserMasterPojo.setAddedDateTime(DateUtils.getCurrentDateTime());
		addUserMasterPojo.setUserName(addUserMasterPojo.getEmpCode());
		addUserMasterPojo.setIsActive(true);
		UserMaster usermaster = adminService.saveEmployee(addUserMasterPojo);
		if(usermaster.getPrimaryId()!=null)
		{
		String newPassword = AESEncDecUtils.generateAESPassword(6);
		SecretKey secretKey = AESEncDecUtils.generateSecretKey();
		IvParameterSpec secretIv = AESEncDecUtils.generateIv();
		String encryptedPassword = AESEncDecUtils.encrypt(newPassword,secretKey,secretIv);
        String encodedSecretKey = AESEncDecUtils.encodeSecretKey(secretKey);
        String encodedIv = AESEncDecUtils.encodeIv(secretIv);
		UserMaster userMaster = userManagementService.updateUserPassword(usermaster, encryptedPassword, encodedSecretKey,encodedIv);
		MailUtils.sendEmailNewEmployee(usermaster.getPersonalEmail(), userMaster.getEmpCode(),newPassword);
		rd.addFlashAttribute("messageSuccess", "employee.save.success.msg");
		}
		else {
			rd.addFlashAttribute("messageError", "employee.save.fail.msg");
		}
		}
		catch (Exception e) {
			e.printStackTrace();
			rd.addFlashAttribute("messageError", "employee.save.fail.msg");
		}
        String view = "redirect:ViewAllEmployee.html";
        return view;
	}
	
	//Edit Employee Details
		@GetMapping("EditEmployee.html")
		public String EditEmployee(Model model, HttpServletRequest request,@RequestParam(name = "empCode") String empCode,@ModelAttribute("addUserMasterPojo") AddUserMasterPojo addUserMasterPojo)  {
			String view="";
			
//			List<UserMaster> managerList= adminService.getAllManagerList();
			List<ProjectMaster> projectList=adminService.getAllProjectList();
			List<DesignationMaster> designationList=designationService.getAllDesignationList();
			List<UserRoleMaster> userRoleMasters=userRoleMasterService.getAlluserRoleMastersList();
			List<BranchMaster> branchList=(List<BranchMaster>) branchDao.findAll();
			model.addAttribute("userRoleMasters", userRoleMasters);
//			model.addAttribute("managerList", managerList);
			model.addAttribute("projectList", projectList);
			model.addAttribute("branchList", branchList);
			model.addAttribute("designationList", designationList);
			
			UserMaster user = userDao.findByUserName(empCode);
			List<UserProjectManager> userProjectManager=userProjectManagerDao.findByFkUser(user);
			UserMasterDto userMasterDto= this.modelmapper.map(user, UserMasterDto.class);
			model.addAttribute("userMasterDto" , userMasterDto);
			model.addAttribute("userProjectManager" , userProjectManager);
			view = WebUtils.setModel(model, "AdminEditEmployee.url", "adminEditEmployee.page", "meta.key.desc.adminEditEmployee.title",
					"meta.key.adminEditEmployee.title");
			return view;
		}
		
		@PostMapping("UpdateEmployeeToDb.html")
		public String UpdateEmployeeToDb(Model model, HttpServletRequest request,@ModelAttribute("addUserMasterPojo") AddUserMasterPojo addUserMasterPojo,RedirectAttributes rd) {
			String view = null;
			SessionDetails sessionDetails=SessionDetails.getSessionDetails(request);
			try {
				
				if (addUserMasterPojo.getProfileImageFile() != null && !addUserMasterPojo.getProfileImageFile().isEmpty()) 
				   {
					addUserMasterPojo= adminService.updateProfileImage(addUserMasterPojo);
				   }
				
				UserMaster usermaster = userDao.findByUserName(addUserMasterPojo.getEmpCode());
				
				UserMaster user= this.modelmapper.map(addUserMasterPojo, UserMaster.class);	
				if (user.getMobileNumber() != null )
					usermaster.setMobileNumber(user.getMobileNumber());
				if (user.getMaritalStatus() != null )
					usermaster.setMaritalStatus(user.getMaritalStatus());
				if (user.getPersonalEmail() != null)
					usermaster.setPersonalEmail(user.getPersonalEmail());
				if (user.getPersonalEmail() != null)
					usermaster.setOfficeEmail(user.getPersonalEmail());
				if (user.getProfileImage() != null)
					usermaster.setProfileImage(addUserMasterPojo.getProfileImage());
				if (user.getFirstName() != null )
					usermaster.setFirstName(user.getFirstName());
				if (user.getFatherName() != null)
					usermaster.setFatherName(user.getFatherName());
				if (user.getLastName() != null)
					usermaster.setLastName(addUserMasterPojo.getLastName());
				if (user.getTitle() != null)
					usermaster.setTitle(user.getTitle());
				if (user.getGender() != null)
					usermaster.setGender(user.getGender());
				if (user.getAdharNo() != null)
					usermaster.setAdharNo(user.getAdharNo());
				if (user.getPanNo() != null)
					usermaster.setPanNo(user.getPanNo());
				if (user.getBirthDate() != null)
					usermaster.setBirthDate(user.getBirthDate());
				if (user.getBranch() != null)
					usermaster.setBranch(user.getBranch());
				if (user.getFkdesignation() != null )
					usermaster.setFkdesignation(user.getFkdesignation());
//				if (user.getFkManager() != null )
//					usermaster.setFkManager(user.getFkManager());
//				if (user.getFkProject() != null )
//					usermaster.setFkProject(user.getFkProject());
				if (user.getFkUserRoleId() != null )
					usermaster.setFkUserRoleId(user.getFkUserRoleId());
				if (user.getJoinDate() != null )
					usermaster.setJoinDate(user.getJoinDate());
				if (user.getPermanentAddress() != null)
					usermaster.setPermanentAddress(user.getPermanentAddress());
				if (user.getPresentAddress() != null)
					usermaster.setPresentAddress(user.getPresentAddress());
				if (user.getSalary() > 0 )
					usermaster.setSalary(user.getSalary());
					usermaster.setUpdatedDateTime(DateUtils.getCurrentDate());
				if (user.getIsActive() != null )
					usermaster.setIsActive(user.getIsActive());
				if (user.getExitDate() != null)
					usermaster.setExitDate(user.getExitDate());
				
				UserMaster id= userDao.save(usermaster);
				
				// Retrieve existing userProjectManagers
				List<UserProjectManager> userProjectManagers = userProjectManagerDao.findByFkUser(usermaster);

				// Retrieve project list
				List<ProjectMaster> pjlist = addUserMasterPojo.getFkProject();
				// Create a set to store primary IDs of projects in pjlist
				Set<Long> projectIdsInPjlist = pjlist.stream().map(ProjectMaster::getPrimaryId).collect(Collectors.toSet());

				// Create a map for quick lookup of existing UserProjectManagers by project ID
				Map<Long, UserProjectManager> userProjectManagerMap = new HashMap<>();
				for (UserProjectManager upm : userProjectManagers) {
					 if (!projectIdsInPjlist.contains(upm.getFkProject().getPrimaryId())) {
					        // Delete userProjectManager from database if not in pjlist
					        userProjectManagerDao.delete(upm);
					    }
					 else
					 {
				    userProjectManagerMap.put(upm.getFkProject().getPrimaryId(), upm);
					 }
				}

				// Iterate over project list and update or create UserProjectManager instances
				for (ProjectMaster plist : pjlist) {
				    UserProjectManager userProjectManager = userProjectManagerMap.get(plist.getPrimaryId());
				    if (userProjectManager == null) {
				        // If no existing UserProjectManager, create a new one
				        userProjectManager = new UserProjectManager();
				    }
				    userProjectManager.setFkUser(usermaster);
				    userProjectManager.setFkProject(plist);
				    userProjectManager.setFkManager(plist.getProjectManager());

				    // Save the updated or new UserProjectManager instance
				    userProjectManagerDao.save(userProjectManager);
				}
				
				if(id!=null)
				{
					rd.addFlashAttribute("messageSuccess", "admin.employee.update.success");
				}
				else		
				{
					rd.addFlashAttribute("messageError", "admin.employee.update.fail");
				}

				view = "redirect:ViewAllEmployee.html";
				} catch (Exception e) {
				e.printStackTrace();
				}
				return view;

		}

		

	@GetMapping(value = { "AddProject.html" })
	public String getAddEmpForm(Model model, @ModelAttribute("projectMaster") ProjectMaster projectMaster) {
		List<UserMaster> managerList = adminService.getAllManagerList();
		model.addAttribute("managerList", managerList);
		String view = WebUtils.setModel(model, "addProject.url", "AddProject.page", "meta.key.desc.AddProject.title",
				"meta.key.AddProject.title");
		return view;
	}
	
	@PostMapping("saveProjectToDB.html")
	public String saveProjectToDB(Model model, @ModelAttribute("projectMaster") ProjectMaster projectMaster,RedirectAttributes rd) {
		ProjectMaster id = adminService.saveProject(projectMaster);
		if (id.getPrimaryId() != null) {
			rd.addFlashAttribute("messageSuccess", "project.save.success.msg");
		} else {
			rd.addFlashAttribute("messageError", "project.save.fail.msg");
		}
		return "redirect:ViewProjects.html";
	}
	
	
	
	@GetMapping(value = { "ViewProjects.html" })
	public String ViewProjects(Model model, @ModelAttribute("projectMaster") ProjectMaster projectMaster) {
		List<ProjectMaster> projectList = adminService.getAllProjectList();
		model.addAttribute("projectList", projectList);
		
		String view = WebUtils.setModel(model, "ViewProjects.url", "AddProject.page", "meta.key.desc.AddProject.title",
				"meta.key.AddProject.title");
		return view;
	}
	
	@GetMapping(value = { "EditProject.html" })
	public String EditProject(Model model, @RequestParam("projectId") Long projectId) {
		ProjectMaster project = adminService.findProjectById(projectId);
		model.addAttribute("projectMaster", project);
		List<UserMaster> managerList = adminService.getAllManagerList();
		model.addAttribute("managerList", managerList);
		
		String view = WebUtils.setModel(model, "EditProject.url", "AddProject.page", "meta.key.desc.AddProject.title",
				"meta.key.AddProject.title");
		return view;
	}
	
	@PostMapping("UpdateProjectToDB.html")
	public String UpdateProjectToDB(RedirectAttributes rd, @ModelAttribute("projectMasterDto") ProjectMasterDto projectMasterDto) {
		String view = null;
		try {
			
			ProjectMaster project=this.modelmapper.map(projectMasterDto, ProjectMaster.class);
			ProjectMaster projectMaster=projectDao.findByPrimaryId(projectMasterDto.getPrimaryId());
			
			if(project.getProjectName()!=null)
				projectMaster.setProjectName(project.getProjectName());
			if(project.getProjectDescription()!=null && !(project.getProjectDescription().trim().equals("")))
				projectMaster.setProjectDescription(project.getProjectDescription());
			if(project.getDepartment()!=null)
				projectMaster.setDepartment(project.getDepartment());
			if(project.getProjectManager()!=null)
				projectMaster.setProjectManager(project.getProjectManager());
			
			ProjectMaster id = projectDao.save(projectMaster);

			if (id != null) {
				rd.addFlashAttribute("messageSuccess", "admin.project.update.success");
			} else {
				rd.addFlashAttribute("messageError", "admin.project.update.fail");
			}

		} catch (Exception e) {
			e.printStackTrace();
			rd.addFlashAttribute("messageError", "admin.project.update.fail");

		}

		view = "redirect:ViewProjects.html";
		return view;
	}
	
	
	@GetMapping(value = { "AddBranch.html" })
	public String AddBranch(Model model, @ModelAttribute("branchMaster") BranchMaster branchMaster) {
		String view = WebUtils.setModel(model, "addBranch.url", "addBranch.page", "meta.key.desc.addBranch.title",
				"meta.key.addBranch.title");
		return view;
	}
	
	@PostMapping("saveBranchToDB.html")
	public String saveBranchToDB(Model model, @ModelAttribute("branchMaster") BranchMaster branchMaster,RedirectAttributes rd) {
		try {

		BranchMaster branchmaster = new BranchMaster();
		branchmaster.setBranchName(branchMaster.getBranchName().trim());
		BranchMaster id = branchDao.save(branchmaster);
		
		if (id.getPrimaryId() != null) {
			rd.addFlashAttribute("messageSuccess", "branch.save.success.msg");
		} else {
			rd.addFlashAttribute("messageError", "branch.save.fail.msg");
		}
		}
		catch (Exception e) {
			e.printStackTrace();
			rd.addFlashAttribute("messageError", "branch.save.fail.msg");

		}
		
		return "redirect:ViewBranches.html";
	}
	
	
	
	@GetMapping(value = { "ViewBranches.html" })
	public String ViewBranches(Model model, @ModelAttribute("branchMaster") BranchMaster branchMaster) {
		String view="";
		List<BranchMaster> branchtList = (List<BranchMaster>) branchDao.findAll();
		model.addAttribute("branchtList", branchtList);
		 view = WebUtils.setModel(model, "ViewBranches.url", "addBranch.page", "meta.key.desc.addBranch.title",
				"meta.key.addBranch.title");
		return view;
	}
	
	@GetMapping("EditBranch.html")
	public String EditBranch(Model model, @RequestParam("branchId") Long branchId) {
		String view = "";
		try {
			BranchMaster branch = branchDao.findByPrimaryId(branchId);
			model.addAttribute("branchMaster", branch);
			view = WebUtils.setModel(model, "EditBranch.url", "addBranch.page", "meta.key.desc.addBranch.title",
					"meta.key.addBranch.title");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@PostMapping("UpdateBranchToDB.html")
	public String UpdateBranchToDB(RedirectAttributes rd, @ModelAttribute("branchMasterDto") BranchMasterDto branchMasterDto) {
		String view = null;
		try {
			
			BranchMaster branch=this.modelmapper.map(branchMasterDto, BranchMaster.class);
			BranchMaster branchmaster =branchDao.findByPrimaryId(branchMasterDto.getPrimaryId());
			
			if(branch.getBranchName()!=null)
			{
				branchmaster.setBranchName(branch.getBranchName());
			}
			
			BranchMaster id = branchDao.save(branchmaster);

			if (id != null) {
				rd.addFlashAttribute("messageSuccess", "admin.branch.update.success");
			} else {
				rd.addFlashAttribute("messageError", "admin.branch.update.fail");
			}

		} catch (Exception e) {
			e.printStackTrace();
			rd.addFlashAttribute("messageError", "admin.branch.update.fail");

		}

		view = "redirect:ViewBranches.html";
		return view;
	}


}
