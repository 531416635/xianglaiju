package com.yao.dao;


import com.yao.model.UserModel;

public interface LoginDao {

	/**
	 * 根据email查询对应的用户信息
	 * @param email
	 * @return
	 */
	UserModel findUserByEmail(String email);
	
	/**
	 * 将注册页面填写的用户信息（邮箱，用户名）存入数据库
	 * @param user
	 * @return
	 */
	String saveRegUser(UserModel user);

	/**
	 * 点击激活链接，激活用户
	 * @param user
	 * @return
	 */
	int regUser(UserModel user);
}
