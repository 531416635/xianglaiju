package com.yao.admin.controller;

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
import com.yao.service.MenuService;
import com.yao.utils.EhcacheUtils;
import com.yao.vo.TreeNode;

@Controller
@RequestMapping(value = "/admin/menu")
public class MenuController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MenuService service;

	/**
	 * iframe跳转到菜单管理页面
	 * @param response
	 * @return
	 */
	@RequestMapping("/menuManager.html")
	public String initMenu(HttpServletResponse response) {
		logger.debug("进入菜单管理页面");
		response.setHeader("X-Frame-Options", "SAMEORIGIN");
		return "adminsite/menuManager";
	}

	/**
	 * 获取菜单列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getMenuList", method = RequestMethod.POST)
	public JSONObject getMenuList() {
		JSONObject json = new JSONObject();
		json.put("errorID", 0);
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>) EhcacheUtils.getCache("menuList");
		if (CollectionUtils.isEmpty(menuList)) {
			menuList = service.selectByExample(new MenuExample());
		} 
		json.put("result", menuList);
		logger.debug(json.toJSONString());
		return json;
	}
	
	/**
	 * 获取Combotree菜单列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCombotreeMenu", method = RequestMethod.POST)
	public JSONObject getCombotreeMenu() {
		JSONObject json = new JSONObject();
		json.put("errorID", 0);
		List<TreeNode> menuList = service.selectTreeNode(new MenuExample());
		json.put("result", menuList);
		logger.debug(json.toJSONString());
		return json;
	}
	
	/**
	 * 保存新增的菜单信息
	 * @param menu
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveMenu", method = RequestMethod.POST)
	public JSONObject saveMenu(Menu menu) {
		JSONObject json = new JSONObject();
		json.put("errorID", 0);
		menu.setCreatetime(new Date());
		int result = service.insert(menu);
		json.put("result", result);
		logger.debug(json.toJSONString());
		return json;
	}
}
