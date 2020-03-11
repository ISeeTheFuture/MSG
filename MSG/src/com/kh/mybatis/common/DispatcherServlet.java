package com.kh.mybatis.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Properties prop = new Properties();
	private Map<String, AbstractController> cmdMap = new HashMap<>();
	
    /**
     * server에 요청된 url 중에 .do 로 끝나는 요청만 처리한다.
     * js/image/css 관련 파일은 DispatcherServlet 을 타지 않는다.
     */
    public DispatcherServlet() {
        //command.properties에 저장된 
    	//key(사용자 요청)-value(요청을 처리할 클래스명)
    	//prop필드에 저장함.
    	
    	String fileName = DispatcherServlet.class.getResource("/command.properties").getPath();
    	try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	Enumeration<?> keys = prop.keys();
    	while(keys.hasMoreElements()) {
    		String key = (String)keys.nextElement();// /studentEnroll.do
    		String className = prop.getProperty(key);// c.k.m.s.StudentEnrollController
    		if(className != null && !className.isEmpty()) {
    			Class cls = null;
    			AbstractController controller = null;
    			
    			try {
    				//클래스객체.newInstance()
					cls = Class.forName(className);
					//controller 객체 생성
					controller = (AbstractController)cls.newInstance();
					
					//cmdMap에 저장
					cmdMap.put(key, controller);
					
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
    		}
    	}
    	
    	System.out.println("cmdMap = " + cmdMap);
    	
    	
    }

	/**
	 * http://localhost:9090/mybatis/student/studentEnroll.do
	 * 
	 * 실제 사용자 요청이 있을때, 
	 * 사용자 요청 url과 같은 cmdMap key 값을 조회,
	 * 적절한 controller 객체의 excute메소드를 실행한다. 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();// /mybatis/student/studentEnroll.do
		String ctxName = request.getContextPath();
		String key = uri.substring(ctxName.length());
		
		System.out.printf("uri = %s , key = %s %n" , uri, key);
		
		AbstractController controller = cmdMap.get(key);
		
		//controller 처리
		if(controller == null) {
			System.out.println("[" + key + "] 에 매핑된 객체가 없습니다.");
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		try {
			controller.execute(request, response);
			boolean isRedirect = controller.isRedirect();
			String view = controller.getView();
			
			//redirect하는 경우
			if(isRedirect) {
				response.sendRedirect(view);
			}
			//jsp forwarding 하는 경우
			else if(view != null){
				request.getRequestDispatcher(view).forward(request, response);
			}
			else {
				//ajax 처리등과 같이 controller 에서 직접 응답쓰기 한경우
				//처리코드 없음.
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
