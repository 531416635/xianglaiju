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
<title>菜单</title>
<script src="<%=path%>/js/admin/menumanager.js"></script>
</head>
<body>
	<!-- 菜单列表展示 --- 树形数据网格 -->
	<table id="tt" style=""></table>
	<!-- 新增菜单 -->
	<div id="insertMenu" style="display: none">
		<div>
			<label>菜单名称</label> 
			<input type="text" name="menuname" id="savemenuname" /> 
		</div>
		<div>
			<label>菜单路径</label>
			<input type="text" name="menupath" id="savemenupath"/> 
		</div>
		<div>
			<label>父菜单</label>
			<input id="cc" name="pid"/>
		</div>
		<input type="button" value="保存" onclick="saveMenuButton();"/>
		<input type="button" value="取消" onclick="$('#insertMenu').window('close')"/>
	</div>
	<!-- 修改菜单 -->
	<div id="editMenu" style="display: none"></div>

</body>
</html>