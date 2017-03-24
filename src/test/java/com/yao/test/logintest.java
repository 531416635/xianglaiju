package com.yao.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yao.model.UserModel;
import com.yao.service.LoginService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml",
		"classpath:spring-mvc.xml", 
		"classpath:spring-mybatis.xml"}) 
public class logintest {
	private static final Logger logger=LoggerFactory.getLogger(logintest.class);
	
	@Autowired
	private LoginService loginService;
	
	@Test
	public void testRegister(){
		UserModel user =new UserModel();
		user.setEmail("5314126263@qq.com");
		user.setUsername("eee444");
		user.setRegtime(new Date());
		user.setUserstatus(3);
		try {
			String  str=loginService.saveRegUser(user);
			logger.info(str+"");
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
}