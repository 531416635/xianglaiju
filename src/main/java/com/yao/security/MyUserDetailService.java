package com.yao.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yao.model.Roles;
import com.yao.model.UserModel;
import com.yao.model.UserModelExample;
import com.yao.model.UserModelExample.Criteria;
import com.yao.service.UserService;
import com.yao.utils.EhcacheUtils;

public class MyUserDetailService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;

	// 登陆验证时，通过username获取用户的所有权限信息，
	// 并返回User放到spring的全局缓存SecurityContextHolder中，以供授权器使用
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		UserModel domainUser =  (UserModel) EhcacheUtils.getCache(username);
		logger.debug("从缓存中取出username为{},对应的domainUser值{}", username, domainUser);
		if (domainUser == null) {
			System.out.println(username + "============UserDetailsService");
			UserModelExample example = new UserModelExample();
			Criteria criteria = example.createCriteria();
			criteria.andUsernameEqualTo(username);
			 domainUser = userService.selectByExample(example).get(0);
			EhcacheUtils.putCache(username, domainUser);
			logger.debug("从数据库中取出username为{},对应的domainUser值{}", username, domainUser);
		}
		@SuppressWarnings({ "unchecked" })
		List<Roles> rolesList = (List<Roles>) EhcacheUtils.getCache("rolesList");
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		for (int i = 0; i < rolesList.size(); i++) {
			if (rolesList.get(i).getId().equals(domainUser.getRoleid())) {
				GrantedAuthority auth = new SimpleGrantedAuthority(rolesList.get(i).getRolecode());
				auths.add(auth);
			}
		}
		User user = new User(username, domainUser.getPassword().toString(), true, true,true, true, auths);
		return user;
	}

}
