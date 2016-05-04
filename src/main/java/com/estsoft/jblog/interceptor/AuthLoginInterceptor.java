package com.estsoft.jblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.estsoft.jblog.service.UserService;
import com.estsoft.jblog.vo.UserVO;


public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		String ret = request.getParameter("ret");			// 1: 해당 블로그로 가도록 처리
		
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
	
		UserVO userVo = new UserVO(user_id, password);
		
		// login 서비스 호출(로그인 작업)
		UserVO authUser = userService.login(userVo);
		
		if(authUser == null){
			response.sendRedirect(request.getContextPath()+"/user/loginform");		
			return false;
		}
		
		// 로그인 처리
		HttpSession session = request.getSession(true);		// 없으면 새로 만들어라
		session.setAttribute("authUser", authUser);
		
		
		// 블로그 주소 타고 로그인 한 경우엔 해당 블로그로 가게 처리!!!!!
		if(ret.equals("1")){
			response.sendRedirect(request.getContextPath()+"/"+authUser.getUser_id());
		}else{
			response.sendRedirect(request.getContextPath()+"/main");
		}
		

		// 처리하고 끝내기 위해 false;
		return false;
	}

}
