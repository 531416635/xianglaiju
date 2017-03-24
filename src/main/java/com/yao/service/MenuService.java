package com.yao.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yao.model.Menu;
import com.yao.model.MenuExample;
import com.yao.vo.TreeNode;

public interface MenuService {

	long countByExample(MenuExample example);

    int deleteByExample(MenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> selectByExample(MenuExample example);

    Menu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    
    List<TreeNode> selectTreeNode(MenuExample example);
}
