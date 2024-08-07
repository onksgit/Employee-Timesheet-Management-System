package com.tsms.web.controller;

import static com.tsms.web.utils.WebUtils.setModel;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tsms.web.dto.LoginDto;
import com.tsms.web.session.SessionDetails;
import com.tsms.web.validation.LoginValidation;

@Controller
public class LoginController {
 
	@Autowired
	LoginValidation loginValidation;

	@GetMapping(value = { "Login.html", "/" })
	public String getLoginForm(Model model, @ModelAttribute("LoginDto") LoginDto LoginDto) {
		setModel(model, "login.page", "meta.key.desc.login.title", "meta.key.login.title");
		String view = "General/LoginForm";
		return view;
	}

	@PostMapping(value = "Login.html")
	public String postLoginForm(Model model, HttpServletResponse response, HttpServletRequest request,
			@ModelAttribute("LoginDto") LoginDto LoginDto, BindingResult bindingResult) {
		loginValidation.validate(LoginDto, bindingResult);
		String view = "";
		try {
			if (bindingResult.hasErrors()) {
				invalidateSession(request, response);
				setModel(model, "login.page", "meta.key.desc.login.title", "meta.key.login.title");
				view = "General/LoginForm";
			} else {
				activateSession(LoginDto, request);
				SessionDetails sessionDetails = LoginDto.getSessionDetails();
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
			}
		} catch (Exception e) {
			e.printStackTrace();
			view = "redirect:Login.html";
		}
		return view;
	}

	@GetMapping("Logout.html")
	public String getLogout(Model model, HttpServletRequest request, HttpServletResponse response) {
		String view;
		invalidateSession(request, response);
		view = "redirect:Login.html";
		return view;
	}

	private void activateSession(LoginDto loginForm, HttpServletRequest request) {
		SessionDetails sessionDetails = loginForm.getSessionDetails();
		HttpSession httpSession = request.getSession(true);
		httpSession.setMaxInactiveInterval(10 * 60 * 2);
		httpSession.setAttribute("sessionDetails", sessionDetails);
	}

	public static void invalidateSession(HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession(false);
		if (httpSession != null) {
			Enumeration<?> attributeList = httpSession.getAttributeNames();
			while (attributeList.hasMoreElements()) {
				String attributeName = (String) attributeList.nextElement();
				httpSession.removeAttribute(attributeName);
				// Removes the object bound with the specified name from this session. If the
				// session does not have an object bound with the specified name, this method
				// does nothing.
			}
			httpSession.invalidate();// Invalidates this session then unbinds any objects bound to it.
		}
		// response headers to prevent caching
		response.setHeader("Pragma", "no-cache");// Specifies that no caching should be done by the browser.
		response.setHeader("Cache-Control", "no-store");// Indicates that the browser should not store any cached copies
														// of the response.
		response.setHeader("Expires", "0");// ensuring that the browser treats the response as expired
		response.setDateHeader("Expires", -1);// ensuring that the browser treats the response as expired
		response.setBufferSize(0);// indicating that the response should not be buffered.
	}
}
