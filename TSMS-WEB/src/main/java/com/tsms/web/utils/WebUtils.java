package com.tsms.web.utils;

import org.springframework.ui.Model;

public class WebUtils {
	
	public static String setModel(Model model, String urlLink, String title, String metadescription,
			String metakeywords) {
		String view;
		model.addAttribute("title", title);
		model.addAttribute("metadescription", metadescription);
		model.addAttribute("metakeywords", metakeywords);
		model.addAttribute("urlLink", urlLink);
		view = "General/Main";
		return view;
	}
	
	public static void setModel(Model model, String title, String metadescription,
			String metakeywords) {
		model.addAttribute("title", title);
		model.addAttribute("metadescription", metadescription);
		model.addAttribute("metakeywords", metakeywords);
	}
	
}
