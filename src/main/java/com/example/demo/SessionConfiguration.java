/**
	时间：2018年4月13日
	地点：
	包名：com.example.demo
	项目名：grap
 * 
 */
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.example.demo.system.mysql.entity.SessionInterceptor;

/**
 * @author Administrator
 *
 */
public class SessionConfiguration extends WebMvcConfigurationSupport {
	@Autowired
	private SessionInterceptor securityInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(securityInterceptor).addPathPatterns("/**");
	}
}
