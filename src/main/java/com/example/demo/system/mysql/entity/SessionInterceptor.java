/**
	时间：2018年4月13日
	地点：
	包名：com.example.demo
	项目名：grap
 * 
 */
package com.example.demo.system.mysql.entity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Administrator
 *
 */
public class SessionInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object o)throws Exception{
		if(request.getRequestURL().equals("/login")||request.getRequestURL().equals("/register"))
		{
			return true;
		}
		Object obj = request.getSession().getAttribute("currentUser");
		if(obj==null) {
			response.sendRedirect("/login");
			return false;
		}
		return true;
	}
	
	  @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	    }

	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	    }
}
