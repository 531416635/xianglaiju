package com.yao.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class AdminAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	

	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
	public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

	private boolean postOnly = true;
	
	public Authentication attemptAuthentication(HttpServletRequest request,    
            HttpServletResponse response) throws AuthenticationException {        
        //这里可以抛出继承自AuthenticationException的exception  
        //然后会转到MyAuthenticationFailureHandler。        
        //比如说验证码什么的可以在这里验证，然后抛出异常。  
        //然后让MyAuthenticationFailureHandler去处理，并输出返回  
          
        //下面的代码段是具体的示例  
        //当用户输入的用户名为“123”抛出自定义的AuthenticationException异常。  
        //String username = request.getParameter("username");  
        //if(username.equals("123"))  
       // {             
       ///     throw new MyAuthenticationException("测试异常被MyAuthenticationFailureHandler处理");  
              
      //  }  
            
       // return super.attemptAuthentication(request, response);    
		
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (username == null) {
			username = "";
		}
		if (password == null) {
			password = "";
		}
		username = username.trim();
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		
		AuthenticationManager authen = this.getAuthenticationManager();
		Authentication auth =authen.authenticate(authRequest);
		return auth;
    }  
}
