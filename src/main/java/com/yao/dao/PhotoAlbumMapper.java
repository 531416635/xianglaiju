package com.yao.dao;

import com.yao.model.PhotoAlbum;
import com.yao.model.PhotoAlbumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhotoAlbumMapper {
    long countByExample(PhotoAlbumExample example);

    int deleteByExample(PhotoAlbumExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PhotoAlbum record);

    int insertSelective(PhotoAlbum record);

    List<PhotoAlbum> selectByExample(PhotoAlbumExample example);

    PhotoAlbum selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PhotoAlbum record, @Param("example") PhotoAlbumExample example);

    int updateByExample(@Param("record") PhotoAlbum record, @Param("example") PhotoAlbumExample example);

    int updateByPrimaryKeySelective(PhotoAlbum record);

    int updateByPrimaryKey(PhotoAlbum record);
}