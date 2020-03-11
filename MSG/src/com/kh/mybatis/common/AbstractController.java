package com.kh.mybatis.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 모든 controller 클래스를 관리하기 위한 추상클래스
 *
 * redirect 여부
 * view 단 주소
 * 
 * 공통적으로 실행할 메소드 (추상)
 */
public abstract class AbstractController {
	
	private boolean isRedirect;
	private String view;//jsp 주소(redirect 주소)
	/**
	 * 
	 * 자식 Controller 클래스에서 구현해야할 추상메소드
	 * 
	 * 
	 */
	public abstract void execute(HttpServletRequest request, HttpServletResponse responce) throws Exception;
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
}
