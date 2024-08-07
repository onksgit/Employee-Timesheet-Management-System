package com.tsms.web.controller;

import static com.tsms.web.utils.WebUtils.setModel;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tsms.web.dto.ChangePasswordDto;
import com.tsms.web.dto.LoginDto;
import com.tsms.web.entity.UserMaster;
import com.tsms.web.service.UserManagementService;
import com.tsms.web.utils.AESEncDecUtils;
import com.tsms.web.utils.BundleUtils;
import com.tsms.web.utils.WebUtils;
import com.tsms.web.validation.LoginValidation;
import com.tsms.web.validation.MailUtils;

@Controller
public class PasswordManagementController {
	
	@Autowired
	LoginValidation loginValidation;
	
	@Autowired
	UserManagementService userManagementService;
	
	@GetMapping(value="ForgotPassword.html")
	public String forgotPassWordForm(Model model,@ModelAttribute("LoginDto") LoginDto LoginDto) {
		setModel(model, "forgot.password.page", "meta.key.desc.forgot.title", "meta.key.forgot.title");
		String view = "General/ForgotPassword";
		return view;
	}
	
	@PostMapping(value="ForgotPassword.html")
	public String forgotPassWord(Model model, @ModelAttribute("LoginDto") LoginDto LoginDto, BindingResult bindingResult) throws Exception {
		String view ;
		loginValidation.validateUser(LoginDto, bindingResult);
		if(bindingResult.hasErrors()) {
			setModel(model, "forgot.password.page", "meta.key.desc.forgot.title", "meta.key.forgot.title");
			view = "General/ForgotPassword";
		}else {
			String newPassword = AESEncDecUtils.generateAESPassword(6);
			SecretKey secretKey = AESEncDecUtils.generateSecretKey();
			IvParameterSpec secretIv = AESEncDecUtils.generateIv();
			String encryptedPassword = AESEncDecUtils.encrypt(newPassword,secretKey,secretIv);
            String encodedSecretKey = AESEncDecUtils.encodeSecretKey(secretKey);
            String encodedIv = AESEncDecUtils.encodeIv(secretIv);
			UserMaster userMaster = userManagementService.updateUserPassword(LoginDto, encryptedPassword, encodedSecretKey,encodedIv);
			String to = userMaster.getPersonalEmail();
			MailUtils.sendEmail(to, userMaster.getEmpCode(),newPassword);
			model.addAttribute("status", true);
			setModel(model, "login.page", "meta.key.desc.login.title", "meta.key.login.title");
			view = "redirect:Login.html";
		}
		return view;
	}
	
	@GetMapping("ChangePassword.html")
	public String changePassWordForm(Model model, @ModelAttribute("changePassword") ChangePasswordDto changePasswordDto) {
		String view;
		view = WebUtils.setModel(model, "ChangePassword.url", "ChangePassword.page","meta.key.desc.dashboard.title", "meta.key.dashboard.title");
		return view;
	}
	
	@PostMapping("ChangePassword.html")
	public String changePassword(Model model, @ModelAttribute("changePassword") ChangePasswordDto changePasswordDto, BindingResult bindingResult) throws Exception {
		loginValidation.validateUser(changePasswordDto, bindingResult);
		String view = null;
		if(bindingResult.hasErrors()) {
			view = WebUtils.setModel(model, "ChangePassword.url", "ChangePassword.page","meta.key.desc.changepassword.title", "meta.key.changepassword.title");
		}else {
			String newPassword=changePasswordDto.getNewPassword();
			String confPassword=changePasswordDto.getConfirmPassword();
			if(confPassword.equalsIgnoreCase(newPassword)) {
				SecretKey secretKey = AESEncDecUtils.generateSecretKey();
				IvParameterSpec secretIv = AESEncDecUtils.generateIv();
				String encryptedPassword = AESEncDecUtils.encrypt(confPassword,secretKey,secretIv);
	            String encodedSecretKey = AESEncDecUtils.encodeSecretKey(secretKey);
	            String encodedIv = AESEncDecUtils.encodeIv(secretIv);

				UserMaster userMaster = userManagementService.updateUserPassword(changePasswordDto, encryptedPassword, encodedSecretKey, encodedIv);
				String sub = "!...Login Password...!";
				String message = String.format(BundleUtils.message("new.password.sms"), confPassword);
				String to = userMaster.getPersonalEmail();
				String from = BundleUtils.message("company.private.mail");
				MailUtils.sendMail(message, sub, to, from);
				model.addAttribute("status", true);
				setModel(model, "login.page", "meta.key.desc.login.title", "meta.key.login.title");
				view = "redirect:Login.html";
			}else {
				model.addAttribute("notMatched", "Password Not Matched");
				view = WebUtils.setModel(model, "ChangePassword.url", "ChangePassword.page","meta.key.desc.changepassword.title", "meta.key.changepassword.title");
			}
		}
		return view;
	}
}
