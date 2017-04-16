package com.yao.dao;

import java.util.List;
import java.util.Map;

import com.yao.model.ActivityModel;

public interface ActivityDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityModel record);

    int insertSelective(ActivityModel record);

    ActivityModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityModel record);

    int updateByPrimaryKeyWithBLOBs(ActivityModel record);

    int updateByPrimaryKey(ActivityModel record);
    
    List<ActivityModel> selectList(Map<String, Object> map);
    int selectCount(Map<String, Object> map);
}