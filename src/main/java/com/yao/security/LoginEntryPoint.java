package com.yao.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class LoginEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		String targetUrl = null;
        String url = request.getRequestURI();
   
        if(url.indexOf("admin") != -1){
            //未登录而访问后台受控资源时，跳转到后台登录页面
            targetUrl = "/admin/login.html";
        }else{
            //未登录而访问前台受控资源时，跳转到前台登录页面
            targetUrl = "/web/index.html";
        }
   
        targetUrl = request.getContextPath() + targetUrl;
        response.sendRedirect(targetUrl);
		
	}

}
