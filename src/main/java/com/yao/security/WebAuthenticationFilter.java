package com.yao.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class WebAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	public Authentication attemptAuthentication(HttpServletRequest request,    
            HttpServletResponse response) throws AuthenticationException {        
        //这里可以抛出继承自AuthenticationException的exception  
        //然后会转到MyAuthenticationFailureHandler。        
        //比如说验证码什么的可以在这里验证，然后抛出异常。  
        //然后让MyAuthenticationFailureHandler去处理，并输出返回  
          
        //下面的代码段是具体的示例  
        //当用户输入的用户名为“123”抛出自定义的AuthenticationException异常。  
        String username = request.getParameter("username");  
        if(username.equals("123"))  
        {             
            throw new MyAuthenticationException("测试异常被MyAuthenticationFailureHandler处理");  
              
        }  
            
        return super.attemptAuthentication(request, response);    
    }  
}
