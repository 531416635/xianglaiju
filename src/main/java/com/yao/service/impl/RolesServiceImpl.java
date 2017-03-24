package com.yao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yao.dao.RolesMapper;
import com.yao.model.Roles;
import com.yao.model.RolesExample;
import com.yao.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService{

	@Autowired
	private RolesMapper dao;
	
	@Override
	public long countByExample(RolesExample example) {
		// TODO Auto-generated method stub
		return dao.countByExample(example);
	}

	@Override
	public int deleteByExample(RolesExample example) {
		// TODO Auto-generated method stub
		return dao.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Roles record) {
		// TODO Auto-generated method stub
		return dao.insert(record);
	}

	@Override
	public int insertSelective(Roles record) {
		// TODO Auto-generated method stub
		return dao.insertSelective(record);
	}

	@Override
	public List<Roles> selectByExample(RolesExample example) {
		// TODO Auto-generated method stub
		return dao.selectByExample(example);
	}

	@Override
	public Roles selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(Roles record, RolesExample example) {
		// TODO Auto-generated method stub
		return dao.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Roles record, RolesExample example) {
		// TODO Auto-generated method stub
		return dao.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Roles record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Roles record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKey(record);
	}

}
