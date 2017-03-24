package com.yao.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yao.model.UserModel;
import com.yao.model.UserModelExample;

public interface UserService {

	long countByExample(UserModelExample example);

	int deleteByExample(UserModelExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(UserModel record);

	int insertSelective(UserModel record);

	List<UserModel> selectByExample(UserModelExample example);

	UserModel selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") UserModel record,
			@Param("example") UserModelExample example);

	int updateByExample(@Param("record") UserModel record,
			@Param("example") UserModelExample example);

	int updateByPrimaryKeySelective(UserModel record);

	int updateByPrimaryKey(UserModel record);
}
