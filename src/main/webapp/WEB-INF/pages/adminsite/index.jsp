<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>翔来居</title>
<link rel="stylesheet" href="<%=path%>/css/admin/index.css" />
</head>
<body>
	<div id="indexlayout" class="easyui-layout" data-options="fit:true"
		style="width: 100%;">
		<div data-options="region:'north'" style="height:75px">
                <table id="part1">头部页面</table>
            </div>
		<div data-options="region:'west',title:'West',split:true,"
			style="width: 10%; overflow: hidden">
			<div id="aa" class="easyui-accordion"
				style="width: 100%; height: 100%;">
				<div title="一级管理" data-options="iconCls:'icon-reload',selected:true"
					style="overflow: auto; width: 100%;">
					<div id="usermanager" class="menudiv">用户管理</div>
					<div id="articlemanager" class="menudiv">文章管理</div>
					<div id="menumanager" class="menudiv">菜单管理</div>
					<div id="rolemanager" class="menudiv">角色管理</div>
				</div>
				<div title="二级管理" data-options="iconCls:'icon-reload'"
					style="overflow: auto; width: 100%;">
					<div id="activitymanager" class="menudiv">活动管理</div>
				</div>
				<div title="三级管理" data-options="iconCls:'icon-reload'"
					style="overflow: auto; width: 100%;">content3</div>
			</div>
		</div>
		
		<div data-options="region:'center',title:'center title'"
			style="padding: 5px; background: #eee;">
			<!-- tab标签页 -->
			<div id="tt" class="easyui-tabs" style="width: 100%; height: 100%;">
				<div title="首页" style="padding:10px">
					<p><h3>欢迎访问翔来居管理后台.</h3></p>
				</div>
			</div>
		</div>
	</div>
	<script src="<%=path%>/js/admin/index.js"></script>
</body>
</html>
