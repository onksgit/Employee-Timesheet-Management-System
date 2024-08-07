package com.tsms.web.controller;
import static com.tsms.web.utils.WebUtils.setModel;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(Model model) {
    	String view = "ErrorPages/ErrorPage500";
		setModel(model, "login.page", "meta.key.desc.login.title", "meta.key.login.title");
		return view;	
        
    }

    public String getErrorPath() {
        return "/error";
    }
}
