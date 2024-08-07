package com.tsms.web.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.tsms.web.session.SessionDetails;
import com.tsms.web.dao.UserDao;
import com.tsms.web.dto.ChangePasswordDto;
import com.tsms.web.dto.LoginDto;
import com.tsms.web.entity.UserMaster;
import com.tsms.web.utils.BundleUtils;

@Service
public class LoginValidation implements Validator {
	
	@Autowired
	UserDao userDao;

	@Override
	public void validate(Object object, Errors errors) {
		LoginDto loginDto = (LoginDto) object;
		String userName= loginDto.getUsername().toUpperCase();
		String password= loginDto.getPassword();
		boolean checkLogin = true;
		
		if(userName==null){
			addError(errors,"username","loginID.invalid");
			checkLogin = false;
		}else if (password==null) {
			addError(errors,"password","loginID.invalid");
			checkLogin = false;
		}

		if(checkLogin) {
			try {
				checkUser(errors,userName,password, loginDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
	}
	
	private void checkUser(Errors errors, String userName, String password, LoginDto loginDto){
		try
		{
		 UserMaster usermaster = userDao.findByUserName(userName);
		 if(usermaster != null){
			boolean flag = PasswordValidations.isValidPassword(password, usermaster.getPassword(), usermaster.getSecretKey(), usermaster.getSecretIV());
			 if(flag) {
				 if(usermaster.getIsActive()) {
					 SessionDetails session=new SessionDetails();
					 session.setUsername(usermaster.getUserName());
					 session.setFirstName(usermaster.getFirstName());
					 session.setLastName(usermaster.getLastName());
					 session.setEmpCode(usermaster.getEmpCode());
					 session.setOfficeEmail(usermaster.getOfficeEmail());
					 session.setPersonalEmail(usermaster.getPersonalEmail());
					 session.setDepartment(usermaster.getDepartment());
					 session.setIsLogin(usermaster.getIsLogin());
					 session.setAddress1(usermaster.getPresentAddress());
					 session.setAddedDateTime(usermaster.getAddedDateTime());
					 session.setFkDesiginaton(usermaster.getFkdesignation().getPrimaryId());
					 session.setLoggedInUser(usermaster);
					 loginDto.setSessionDetails(session);
				 }else {
					 addError(errors,"username","login.inactive");
				 }
			 }else {
				 addError(errors,"password","login.password");
			 }
		 }else {
			 addError(errors,"username","loginID.invalid");
		 }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void validateUser(LoginDto loginForm, Errors errors) {
		String userName=loginForm.getUsername().toUpperCase();
		boolean checkLogin = true;
		if(userName==null || userName.equals("") || userName=="") {
			 addError(errors,"username","invalid.username");
			 checkLogin = false;
		}
		if(checkLogin) {
			UserMaster usermaster = userDao.findByUserName(userName);
			if(usermaster==null) {
				addError(errors,"username","invalid.username");
			}
		}
	}
	
	public void validateUser(ChangePasswordDto changePasswordDto, Errors errors) throws Exception {
		String userName=changePasswordDto.getUserName().toUpperCase();
		boolean checkLogin = true;
		if(userName==null || userName.equals("") || userName=="") {
			 addError(errors,"username","invalid.username");
			 checkLogin = false;
		}
		if(checkLogin) {
			UserMaster usermaster = userDao.findByUserName(userName);
			if(usermaster==null) {
				addError(errors,"username","invalid.username");
			}else {
				boolean flag = PasswordValidations.isValidPassword(changePasswordDto. getOldPassword(), usermaster.getPassword(), usermaster.getSecretKey(), usermaster.getSecretIV());
				if(!flag) {
					addError(errors,"oldPassword","old.password");
				}
			}
		}
	}
	
	public static void addError(Errors errors, String paramName, String messageCode) {
		errors.rejectValue(paramName, paramName, BundleUtils.message(messageCode));
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

}
