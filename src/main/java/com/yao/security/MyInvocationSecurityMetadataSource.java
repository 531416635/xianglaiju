package com.yao.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

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

public class MyInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	private RoleMenuService roleMenuService;
	
	private RolesService rolesService;
	
	private MenuService menuService;

	// tomcat启动时实例化一次
	public MyInvocationSecurityMetadataSource(RoleMenuService roleMenuService,
			RolesService rolesService,MenuService menuService) {
		this.roleMenuService = roleMenuService;
		this.rolesService = rolesService;
		this.menuService = menuService;
		loadResourceDefine();
	}

	// tomcat开启时加载一次，加载所有url和权限（或角色）的对应关系
	private void loadResourceDefine() {
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		// 获取全部菜单列表 ，并添加到缓存
		List<Menu> menuList = menuService.selectByExample(new MenuExample());
		EhcacheUtils.putCache("menuList", menuList);
		// 获取全部角色列表 ，并添加到缓存
		List<Roles> rolesList = rolesService.selectByExample(new RolesExample());
		EhcacheUtils.putCache("rolesList", rolesList);
		// 获取全部角色-菜单列表 ，并添加到缓存
		List<RoleMenu> roleMenuList = roleMenuService.selectByExample(new RoleMenuExample());
		EhcacheUtils.putCache("roleMenuList", roleMenuList);

		//
		for (Roles role : rolesList) {
			Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
			ConfigAttribute ca = new SecurityConfig(role.getRolecode());
			atts.add(ca);
			for (RoleMenu roleMenu : roleMenuList) {
				if (roleMenu.getRoleid().equals(role.getId())) {
					for (Menu menu : menuList) {
						if (roleMenu.getMenuid().equals(menu.getId())) {
							resourceMap.put(menu.getMenupath(), atts);
						}
					}
				}
			}
		}
	}

	// 参数是要访问的url，返回这个url对于的所有权限（或角色）
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		// 将参数转为url
		String url = ((FilterInvocation) object).getRequestUrl();
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (urlMatcher.pathMatchesUrl(resURL, url)) {
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
