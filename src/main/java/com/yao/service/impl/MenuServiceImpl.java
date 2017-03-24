package com.yao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.yao.dao.MenuMapper;
import com.yao.model.Menu;
import com.yao.model.MenuExample;
import com.yao.service.MenuService;
import com.yao.utils.EhcacheUtils;
import com.yao.vo.TreeNode;

@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuMapper dao;
	
	@Override
	public long countByExample(MenuExample example) {
		// TODO Auto-generated method stub
		return dao.countByExample(example);
	}

	@Override
	public int deleteByExample(MenuExample example) {
		// TODO Auto-generated method stub
		return dao.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Menu record) {
		// TODO Auto-generated method stub
		int t = dao.insert(record);
		if(t>0){
			List<Menu> menuList = dao.selectByExample(new MenuExample());
			EhcacheUtils.putCache("menuList", menuList);
		}
		return t;
	}

	@Override
	public int insertSelective(Menu record) {
		// TODO Auto-generated method stub
		return dao.insertSelective(record);
	}

	@Override
	public List<Menu> selectByExample(MenuExample example) {
		// TODO Auto-generated method stub
		return dao.selectByExample(example);
	}

	@Override
	public Menu selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(Menu record, MenuExample example) {
		// TODO Auto-generated method stub
		return dao.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Menu record, MenuExample example) {
		// TODO Auto-generated method stub
		return dao.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Menu record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Menu record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TreeNode> selectTreeNode(MenuExample example) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>) EhcacheUtils.getCache("menuList");
		if (CollectionUtils.isEmpty(menuList)) {
			menuList = dao.selectByExample(example);
		} 
		Map<Integer,List<Menu>> map = trunkMap(menuList);
		List<Menu> list2 = map.get(0); //此处的0不是只map中的第一个元素，而是指key为0，表示取出树中的一级菜单元素。
		List<TreeNode> treeNodeList = trunkChildren(list2,map);
		return treeNodeList;
	}
	private Map<Integer, List<Menu>> trunkMap(List<Menu> list) {
		Map<Integer,List<Menu>> map = new HashMap<Integer, List<Menu>>();
		for(Menu o : list){
			if(map.containsKey(o.getPid())){
				map.get(o.getPid()).add(o);
			}else{
				map.put(o.getPid(), new ArrayList<Menu>());
				map.get(o.getPid()).add(o);
			}
		}
		return map;
	}

	private List<TreeNode> trunkChildren(List<Menu> list2,
			Map<Integer, List<Menu>> map) {
		List<TreeNode> rst = new ArrayList<TreeNode>();
		TreeNode node = null ;
		for(Menu o : list2){
			node = new TreeNode();
			node.setId(String.valueOf(o.getId()));
			node.setText(o.getMenuname());
			node.setPid(o.getPid().toString());
			rst.add(node);
			if(!CollectionUtils.isEmpty(map.get(o.getId()))){
				node.setChildren(trunkChildren(map.get(o.getId()),map));
			}else{
				node.setState("open");
			}
		}
		return rst ;
	}

}
