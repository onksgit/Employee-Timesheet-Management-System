package com.tsms.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tsms.web.session.SessionDetails;

public class FilterRequest implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String servletPath = httpServletRequest.getServletPath();

		httpServletResponse.setHeader("Pragma", "no-cache");
		httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		httpServletResponse.setHeader("X-Frame-Options", "SAMEORIGIN");
		httpServletResponse.setHeader("Expires", "0");
		httpServletResponse.setDateHeader("Expires", -1);
		httpServletResponse.setBufferSize(0);

		if (servletPath.startsWith("/Login.html") || servletPath.startsWith("/ChangePassword.html")
				|| servletPath.startsWith("/ForgotPassword.html")) {
			chain.doFilter(request, response);
		} else {
			SessionDetails sessionDetails = SessionDetails.getSessionDetails(httpServletRequest);
			if (sessionDetails != null) {
				chain.doFilter(request, response);
			} else {
				String url = "Login.html";
				if ("XMLHttpRequest".equals(httpServletRequest.getHeader("X-Requested-With"))) {
					String scr = "<script>window.location=\"" + httpServletRequest.getContextPath()
							+ "/index.jsp\"</script>";
					httpServletResponse.getWriter().write(scr);
					httpServletResponse.sendRedirect(url);
				} else {
					httpServletResponse.sendRedirect(url);
				}
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}
