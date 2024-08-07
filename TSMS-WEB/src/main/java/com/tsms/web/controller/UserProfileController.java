package com.tsms.web.controller;

import java.util.List;

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
import com.tsms.web.dao.UserDao;
import com.tsms.web.dto.HrSendMSGDto;
import com.tsms.web.dto.UserMasterDto;
import com.tsms.web.entity.HrSendMSG;
import com.tsms.web.entity.UserMaster;
import com.tsms.web.service.AdminService;
import com.tsms.web.service.HrService;
import com.tsms.web.session.SessionDetails;
import com.tsms.web.utils.WebUtils;

@Controller
public class UserProfileController {
	
	@Autowired
	UserDao userDao;
	@Autowired
	private ModelMapper modelmapper;
	@Autowired
	private AdminService admnseService;
	@Autowired
	private HrService hrservice;
	
	
//	@GetMapping("EmployeeDashboard.html")
//	public String ManagerDashboard(Model model ,@ModelAttribute("hrSendMSGDto") HrSendMSGDto hrSendMSGDto,HttpServletRequest httpServletRequest)
//	{		
//		String view;
//		List<HrSendMSG> mHrSendMSGs = null;
//		SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
//		String reciver = sessionDetails.getLoggedInUser().getEmpCode();
//		boolean isManager = sessionDetails.getLoggedInUser().getFkUserRoleId().getProfilePrefix().equalsIgnoreCase("MAN");
//		boolean isEmployee = sessionDetails.getLoggedInUser().getFkUserRoleId().getProfilePrefix().equalsIgnoreCase("EMP");
//
//
//	try {
//		  mHrSendMSGs = hrservice.getLast7daysMessageFromDb(reciver, isManager,isEmployee);
//	} catch (Exception e) {	
//		e.printStackTrace();
//	}
//	model.addAttribute("mHrSendMSGs", mHrSendMSGs);
//
//	view = WebUtils.setModel(model, "ManagerDashboard.url", "Dashboard.page", "meta.key.desc.dashboard.title",
//			"meta.key.dashboard.title");
//	return view;
//		
//	}
	
	@GetMapping("UserProfile.html")
	public String getUserProfile(Model model, HttpServletRequest request) {
		String view;
		SessionDetails sessionDetails = SessionDetails.getSessionDetails(request);
		UserMaster user = userDao.findByUserName(sessionDetails.getEmpCode());
		UserMaster userMaster= this.modelmapper.map(user, UserMaster.class);
		model.addAttribute("user" , userMaster);
		view = WebUtils.setModel(model, "UserProfile.url", "UserProfile.page", "meta.key.desc.profile.title",
				"meta.key.profile.title");
		return view;
		
	}
	
	@GetMapping("EditProfile.html")
	public String editUserProfile(Model model, HttpServletRequest request,@RequestParam(name = "empCode") String empCode,@ModelAttribute("addUserMasterPojo") AddUserMasterPojo addUserMasterPojo)  {
		String view;
//		SessionDetails sessionDetails = SessionDetails.getSessionDetails(request);
		UserMaster user = userDao.findByUserName(empCode);
		UserMasterDto userMasterDto= this.modelmapper.map(user, UserMasterDto.class);
		model.addAttribute("userMasterDto" , userMasterDto);
		view = WebUtils.setModel(model, "UserEditProfile.url", "UserEditProfile.page", "meta.key.desc.editprofile.title",
				"meta.key.editprofile.title");
		return view;
		
	}
	
	
	
//	@PostMapping("userPostDetails.html")
//	public String userPostDetails(Model model, HttpServletRequest request,@ModelAttribute("userMaster") UserMaster user)  {
//		String view="";
//
//		try {
//			SessionDetails sessionDetails = SessionDetails.getSessionDetails(request);
//			UserMaster user1 = userDao.findByUserName(sessionDetails.getEmpCode());
//			UserMaster userMaster= this.modelmapper.map(user1, UserMaster.class);
//			model.addAttribute("user" , userMaster);
//			view = WebUtils.setModel(model, "UserProfile.url", "UserProfile.page", "meta.key.desc.profile.title",
//					"meta.key.profile.title");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return view;
//	}

	@PostMapping("userPostDetails.html")
	public String userPostDetails(Model model, HttpServletRequest request,@ModelAttribute("addUserMasterPojo") AddUserMasterPojo addUserMasterPojo,RedirectAttributes rd) {
	String view="";
	try {
	SessionDetails sessionDetails = SessionDetails.getSessionDetails(request);
	
	if (addUserMasterPojo.getProfileImageFile() != null && !addUserMasterPojo.getProfileImageFile().isEmpty()) 
	   {
		addUserMasterPojo= admnseService.updateProfileImage(addUserMasterPojo);
	   }
	
	UserMaster user1 = userDao.findByUserName(sessionDetails.getEmpCode());
	UserMaster user= this.modelmapper.map(addUserMasterPojo, UserMaster.class);
	if(user.getMobileNumber()!=null)
		user1.setMobileNumber(user.getMobileNumber());
	if(user.getMaritalStatus()!=null)
		user1.setMaritalStatus(user.getMaritalStatus());
	if(user.getOfficeEmail()!=null)
		user1.setOfficeEmail(user.getOfficeEmail());
	if(user.getAdharNo()!=null)
		user1.setAdharNo(user.getAdharNo());
	if(user.getPanNo()!=null)
		user1.setPanNo(user.getPanNo());
	if(user.getPresentAddress()!=null)
		user1.setPresentAddress(user.getPresentAddress());
	if(user.getPermanentAddress()!=null)
		user1.setPermanentAddress(user.getPermanentAddress());
	if(user.getProfileImage()!=null)
		user1.setProfileImage(addUserMasterPojo.getProfileImage());
	
	UserMaster id= userDao.save(user1);
	if(id!=null)
	{
		rd.addFlashAttribute("messageSuccess", "user.profile.update.success");
	}
	else		
	{
		rd.addFlashAttribute("messageError", "user.profile.update.fail");
	}
	
	UserMaster userNew = userDao.findByUserName(user1.getEmpCode());
	sessionDetails.setLoggedInUser(userNew);

	view = "redirect:UserProfile.html";
	} catch (Exception e) {
	e.printStackTrace();
	}

	return view;

	}
	
	
	
}
