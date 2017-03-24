package com.yao.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yao.model.Menu;
import com.yao.model.MenuExample;
import com.yao.model.RoleMenu;
import com.yao.model.RoleMenuExample;
import com.yao.model.Roles;
import com.yao.model.RolesExample;
import com.yao.service.MenuService;
import com.yao.service.RoleMenuService;
import com.yao.service.RolesService;
import com.yao.utils.EhcacheUtils;
import com.yao.vo.TreeNode;

@Controller
@RequestMapping(value = "/admin/role")
public class RoleController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RolesService service;
	
	@Autowired
	private RoleMenuService rolemenuservice;

	@Autowired
	private MenuService menuservice;
	/**
	 * iframe跳转到菜单管理页面
	 * @param response
	 * @return
	 */
	@RequestMapping("/roleManager.html")
	public String initRole(HttpServletResponse response) {
		logger.debug("进入角色管理页面");
		response.setHeader("X-Frame-Options", "SAMEORIGIN");
		return "adminsite/roleManager";
	}

	/**
	 * 获取菜单列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getRoleList", method = RequestMethod.POST)
	public JSONObject getRoleList() {
		JSONObject json = new JSONObject();
		json.put("errorID", 0);
		@SuppressWarnings("unchecked")
		List<Roles> roleList = (List<Roles>) EhcacheUtils.getCache("roleList");
		if (CollectionUtils.isEmpty(roleList)) {
			roleList = service.selectByExample(new RolesExample());
		} 
		json.put("result", roleList);
		logger.debug(json.toJSONString());
		return json;
	}
	
	/**
	 * 获取tree角色--菜单权限
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getRoleMenu", method = RequestMethod.POST)
	public JSONObject getCombotreeMenu(Integer roleid) {
		JSONObject json = new JSONObject();
		json.put("errorID", 0);
		//获取角色和菜单的对应关系
		RoleMenuExample example = new RoleMenuExample();
		com.yao.model.RoleMenuExample.Criteria criteria = example.or();
		criteria.andRoleidEqualTo(roleid);
		List<RoleMenu> roleMenuList = rolemenuservice.selectByExample(example);
		//获取菜单的列表
		List<TreeNode> menuTreeList = menuservice.selectTreeNode(new MenuExample());
		//将菜单和角色之间的关系映射出来
		if(!CollectionUtils.isEmpty(roleMenuList)){
			for(RoleMenu roleMenu : roleMenuList){
				for(TreeNode menu : menuTreeList){
					if(roleMenu.getMenuid().toString().equals(menu.getId())){
						menu.setState("open");
						menu.setChecked(true);
					}
					if(!CollectionUtils.isEmpty(menu.getChildren())){
						menu.setChildren(getChildrenNode(roleMenu,menu.getChildren()));
					}
				}
			}
		}
		json.put("result", menuTreeList);
		logger.debug(json.toJSONString());
		return json;
	}
	
	public List<TreeNode> getChildrenNode(RoleMenu roleMenu ,List<TreeNode> menuTreeList){
		for(TreeNode menu : menuTreeList){
			if(roleMenu.getMenuid().toString().equals(menu.getId())){
				menu.setState("open");
				menu.setChecked(true);
			}
			if(!CollectionUtils.isEmpty(menu.getChildren())){
				menu.setChildren(getChildrenNode(roleMenu,menu.getChildren()));
			}
		}
		return menuTreeList;
	}
	
	/**
	 * 保存新增的菜单信息
	 * @param menu
	 * @return
	 */
	//@ResponseBody
	//@RequestMapping(value = "/saveMenu", method = RequestMethod.POST)
	/*public JSONObject saveMenu(Menu menu) {
		JSONObject json = new JSONObject();
		json.put("errorID", 0);
		menu.setCreatetime(new Date());
		int result = service.insert(menu);
		json.put("result", result);
		logger.debug(json.toJSONString());
		return json;
	}*/
}
