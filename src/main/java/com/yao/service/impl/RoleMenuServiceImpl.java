package com.yao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yao.dao.RoleMenuMapper;
import com.yao.model.RoleMenu;
import com.yao.model.RoleMenuExample;
import com.yao.service.RoleMenuService;

@Service
public class RoleMenuServiceImpl implements RoleMenuService{

	@Autowired
	private RoleMenuMapper dao;

	@Override
	public long countByExample(RoleMenuExample example) {
		// TODO Auto-generated method stub
		return dao.countByExample(example);
	}

	@Override
	public int deleteByExample(RoleMenuExample example) {
		// TODO Auto-generated method stub
		return dao.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RoleMenu record) {
		// TODO Auto-generated method stub
		return dao.insert(record);
	}

	@Override
	public int insertSelective(RoleMenu record) {
		// TODO Auto-generated method stub
		return dao.insertSelective(record);
	}

	@Override
	public List<RoleMenu> selectByExample(RoleMenuExample example) {
		// TODO Auto-generated method stub
		return dao.selectByExample(example);
	}

	@Override
	public RoleMenu selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(RoleMenu record, RoleMenuExample example) {
		// TODO Auto-generated method stub
		return dao.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(RoleMenu record, RoleMenuExample example) {
		// TODO Auto-generated method stub
		return dao.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(RoleMenu record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RoleMenu record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKey(record);
	}
	
}
