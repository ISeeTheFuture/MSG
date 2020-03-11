package com.kh.mybatis.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 
 * init - doFilter - destroy
 * 
 * filterChain : 여러필터를 모아서 연속처리할수 있도록 한 객체
 *
 */
@WebFilter("*")
public class EncodingFilter implements Filter{

	private FilterConfig filterConfig;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
//		System.out.println("EncodingFilter.init()");
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, 
						 ServletResponse response, 
						 FilterChain chain)
			throws IOException, ServletException {
//		System.out.println("EncodingFilter.doFilter()");
		
		String encodingType = "utf-8";		
		
		//공통 인코딩 처리
		request.setCharacterEncoding(encodingType);
		System.out.println("request : ["+encodingType+"]으로 인코딩처리함.");
		
		//필터체인에 연결된 다음 Filter의 doFilter를 호출
		//다음 필터가 없다면, Servlet을 호출함.
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
//		System.out.println("EncodingFilter.destroy()");
		
	}

}
