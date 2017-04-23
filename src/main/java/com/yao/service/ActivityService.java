package com.yao.service;

import java.util.List;
import java.util.Map;

import com.yao.model.ActivityModel;
import com.yao.model.PictureModel;

public interface ActivityService {

	int deleteByPrimaryKey(Integer id);

	int insert(ActivityModel record, List<PictureModel> pictureList);

	int insertSelective(ActivityModel record);

	ActivityModel selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ActivityModel record);

	int updateByPrimaryKeyWithBLOBs(ActivityModel record);

	int updateByPrimaryKey(ActivityModel record);

	List<ActivityModel> selectList(Map<String, Object> map);
	int selectCount(Map<String, Object> map);
}
