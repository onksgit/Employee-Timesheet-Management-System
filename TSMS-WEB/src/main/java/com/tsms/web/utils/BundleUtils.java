package com.tsms.web.utils;

import java.util.ResourceBundle;

public class BundleUtils {

	private static ResourceBundle MESSAGE_BUNDLE = ResourceBundle.getBundle("Messages");
	private static ResourceBundle URL_BUNDLE = ResourceBundle.getBundle("Views");


	public static String message(String key) {
		return MESSAGE_BUNDLE.getString(key);
	}

	public static String url(String key) {
		return URL_BUNDLE.getString(key);
	}

}
