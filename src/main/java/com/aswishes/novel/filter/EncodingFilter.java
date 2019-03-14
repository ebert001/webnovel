package com.aswishes.novel.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.aswishes.novel.common.Codes;

public class EncodingFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		
		// TODO 此处后面将被删去，暂时做测试使用
		((HttpServletRequest) req).getSession().setAttribute(Codes.SESSION_USER, "test_1");
		
		chain.doFilter(req, res);
	}

	public void init(FilterConfig fconfig) throws ServletException {
	}
}
