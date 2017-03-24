<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script src="<%=path%>/js/admin/rolemanager.js"></script>
</head>
<body>
	<!-- 角色列表展示 --- 数据网格 -->
	<table id="dg" style=""></table>
	<!-- 分配权限 -->
	<div id="insertRoleMenu" style="display: none">
		<div><input type="hidden" id="role_menu_id" /></div>
		<div><ul id="tt"></ul></div>
		<input type="button" value="保存" onclick="saveMenuButton();"/>
		<input type="button" value="取消" onclick="$('#insertRoleMenu').window('close')"/>
	</div>
	<!-- 修改菜单 -->
	<div id="editMenu" style="display: none"></div>

</body>
</html>