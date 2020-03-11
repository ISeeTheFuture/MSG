package com.kh.mybatis.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LoggerFilter
 */
//@WebFilter("/*")
public class LoggerFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoggerFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//servlet처리이전
		HttpServletRequest httpRequest 
			= (HttpServletRequest)request;
		//사용자 요청주소: /mvc/member/memberLogin
		String uri = httpRequest.getRequestURI();
		System.out.println("====================================");
		System.out.println(uri);
		System.out.println("------------------------------------");
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		//servlet처리 이후
		System.out.println("_____________________________________");
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
