package com.tsms.web.global.exception;

import static com.tsms.web.utils.WebUtils.setModel;

import java.net.UnknownHostException;
import java.sql.SQLException;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.servlet.ModelAndView;

//Advice all controllers that this is the global exception handler class
@ControllerAdvice
public class GlobalExceptions implements ErrorController {
	
	@ExceptionHandler({InternalServerError.class})
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionInternalServerError(Model model) {
		String view = "/WEB-INF/views/ErrorPages/ErrorPage500";
		setModel(model, "login.page", "meta.key.desc.login.title", "meta.key.login.title");
		return view;
	}

	@ExceptionHandler({BadRequest.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String exceptionBadRequest(Model model) {
		String view = "/WEB-INF/views/ErrorPages/ErrorPage400";
		setModel(model, "login.page", "meta.key.desc.login.title", "meta.key.login.title");
		return view;
	}
	
	@ExceptionHandler({NotFound.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String exceptionPageNotFound(Model model) {
		String view = "/WEB-INF/views/ErrorPages/ErrorPage404";
		setModel(model, "login.page", "meta.key.desc.login.title", "meta.key.login.title");
		return view;
	}
	
	@ExceptionHandler({Forbidden.class})
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public String exceptionForbidden(Model model) {
		String view = "/WEB-INF/views/ErrorPages/ErrorPage404";
		setModel(model, "login.page", "meta.key.desc.login.title", "meta.key.login.title");
		return view;
	}
	
    @ExceptionHandler({SQLException.class, UnknownHostException.class})
    public String handleNetworkError(Exception ex,Model model) {
    	String view = "/WEB-INF/views/ErrorPages/ErrorPage500";
		setModel(model, "login.page", "meta.key.desc.login.title", "meta.key.login.title");
		return view;
    }
	
	
}
