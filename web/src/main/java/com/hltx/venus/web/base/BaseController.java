package com.hltx.venus.web.base;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.support.RequestContextUtils;

public abstract class BaseController {
	//protected HttpServletRequest request;
	//protected HttpServletResponse response;
	/**
	 * value = "success"
	 */
	protected final static String USER_KEY = "user";
	protected final static String SUCCESS = "success";
	protected final static String ERROR = "error";
	
	protected ServletContext getServletContext(HttpServletRequest request) {
		return RequestContextUtils.getWebApplicationContext(request).getServletContext();
	}
	
	/*protected User getSessionUser(HttpServletRequest request) {
		return getSessionUser(request.getSession());
	}
	
	protected User getSessionUser(HttpSession session) {
		return (User) session.getAttribute(USER_KEY);
	}
	
	protected void setSessionUser(HttpServletRequest request, User user) {
		request.getSession().setAttribute(USER_KEY, user);
	}*/
	
}
