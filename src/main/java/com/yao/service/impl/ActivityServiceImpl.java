package com.yao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.yao.dao.ActivityDao;
import com.yao.dao.PictureDao;
import com.yao.model.ActivityModel;
import com.yao.model.PictureModel;
import com.yao.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService{

	@Autowired
	private ActivityDao dao;
	
	@Autowired
	private PictureDao pictureDao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ActivityModel record, List<PictureModel> pictureList) {
		// TODO Auto-generated method stub
		if(!CollectionUtils.isEmpty(pictureList)&&pictureList.size()>0){
			pictureDao.insertList(pictureList);
		}
		return dao.insert(record);
	}

	@Override
	public int insertSelective(ActivityModel record) {
		// TODO Auto-generated method stub
		return dao.insertSelective(record);
	}

	@Override
	public ActivityModel selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ActivityModel record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(ActivityModel record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(ActivityModel record) {
		
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public List<ActivityModel> selectList(Map<String, Object> map) {
		return dao.selectList(map);
	}

	@Override
	public int selectCount(Map<String, Object> map) {
		return dao.selectCount(map);
	}

}
