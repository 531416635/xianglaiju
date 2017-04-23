package com.yao.dao;

import java.util.List;

import com.yao.model.PictureModel;

public interface PictureDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PictureModel record);

    int insertSelective(PictureModel record);

    PictureModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PictureModel record);

    int updateByPrimaryKey(PictureModel record);
    
    int insertList(List<PictureModel> record);
}