package com.yao.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * web页面的主控制器
 * 
 * @author yaoyuxiao
 * @date 2016年9月2日 上午9:35:44
 */
@Controller
@RequestMapping(value = "/web")
public class WebController {

	private static final Logger logger = LoggerFactory.getLogger(WebController.class);

	/**
	 * 跳转到网站首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index() {
		logger.info("跳转到首页");
		return "website/index";
	}
	
	@RequestMapping(value="/login")
	public String login(){
		logger.info("跳转到首页");
		return "website/login";
	}
}
