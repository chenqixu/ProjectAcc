package com.cqx.acc.servlet;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccMainFilter implements javax.servlet.Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
//		ServletContext application=session.getServletContext();
//		System.out.println("[application.getRealPath]"+application.getRealPath("/"));
		String path = req.getContextPath();
//		String uri = req.getRequestURI();
//		System.out.println("[uri]"+uri);
		if (session.getAttribute("user") == null) {
			res.sendRedirect(path+"/Login.jsp");
			return;
		} else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void destroy() {
	}

}
