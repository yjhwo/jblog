package com.estsoft.jblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthLogoutInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String user_id = request.getParameter("user_id");
		HttpSession httpSession = request.getSession();

		if (httpSession != null) {
			httpSession.removeAttribute("authUser");
			httpSession.invalidate();
		}

		// 블로그 주소 타고 로그인 한 경우엔 해당 블로그로 가게 처리!!!!!
		if (user_id == null) {
			response.sendRedirect(request.getContextPath() + "/main");
		} else {
			response.sendRedirect(request.getContextPath() + "/" + user_id);
		}

		return false;
	}

}
