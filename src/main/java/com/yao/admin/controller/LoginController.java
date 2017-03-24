package com.yao.admin.controller;

import javax.security.auth.login.CredentialExpiredException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.yao.model.UserModel;
import com.yao.service.LoginService;

/**
 * 与登录相关的用户操作
 * 
 * @author yaoyuxiao
 * @date 2016年9月2日 上午9:35:15
 */
@Controller("adminLoginController")
@RequestMapping(value = "/admin")
public class LoginController {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(String error, HttpServletRequest request) {
		if ("true".equals(error)) {

			HttpSession session = request.getSession();
			Object object = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
			
			if (object != null && object instanceof InternalAuthenticationServiceException) {
				session.setAttribute("loginException", "用户名或密码错误，请重新登录");
			}else if (object != null && object instanceof UsernameNotFoundException) {
				session.setAttribute("loginException", "用户名或密码错误，请重新登录");
			}else if (object != null && object instanceof BadCredentialsException) {
				session.setAttribute("loginException", "用户名或密码错误，请重新登录");
			}else if (object != null && object instanceof LockedException) {
				session.setAttribute("loginException", "帐户被锁定，请联系管理员");
			}else if (object != null && object instanceof DisabledException) {
				session.setAttribute("loginException", "帐户未启动，请联系管理员");
			}else if (object != null && object instanceof CredentialExpiredException) {
				session.setAttribute("loginException", "密码已过期，请联系管理员");
			}else if (object != null && object instanceof AccountExpiredException) {
				session.setAttribute("loginException", "用户已过期，请联系管理员");
			}else{
				session.setAttribute("loginException", "发生未知错误");
			}
			// 页面可直接使用  ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message} 获取登录异常信息
		}
		logger.info("跳转到登录页");
		return "adminsite/login";
	}

	/**
	 * 登录（根据注册邮箱验证密码）
	 * 
	 * @param email
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "tologin")
	public void login(String login_error, String email, String password,
			HttpServletRequest request) {
		JSONObject stri = new JSONObject();
		stri.put("data", "1");
	}

	/**
	 * 退出操作，清除session中的user值，防止创建session,重定向首页
	 * 
	 * @param request
	 */
	@RequestMapping("loginout")
	public String loginout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);// 防止创建Session
		UserModel user = (UserModel) session.getAttribute("user");
		logger.info("退出成功！邮箱:{}，密码:{}", user.getEmail(), user.getPassword());
		session.removeAttribute("user");
		return "redirect:index.html";
	}
}
