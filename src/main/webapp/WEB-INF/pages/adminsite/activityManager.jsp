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
<link rel="stylesheet" href="<%=path%>/css/plugin/bootstrap.css" />

<link href="<%=path%>/css/plugin/summernote.css" rel="stylesheet"/>
<script src="<%=path%>/js/plugin/summernote.js"></script>
<script src="<%=path%>/js/admin/activitymanager.js"></script>
</head>
<body>
	<!-- 菜单列表展示 --- 树形数据网格 -->
	<table id="dg" style=""></table>
	<!-- 新增菜单 -->
	<div id="insert" style="display: none">
		<form action="" id="activityForm" method="post" enctype="multipart/form-data">
			<div>
				<label>活动名称</label> 
				<input type="text" name="activityName" id="activityName" /> 
			</div>
			<div>
				<label>活动时间</label>
				<input type="text" name="activityTime" id="activityTime"/> 
			</div>
			<div>
				<label>活动发布人</label>
				<input type="text" name="userId" id="userId"/> 
			</div>
			<div>
				<label>是否首页显示</label>
			 	<input type="radio" name="isIndex" >是
	   			<input type="radio" name="isIndex">否
			</div>
			<textarea id="itemcontent" name="activityContent" style="display:none;"></textarea>
			<div id="summer1">请输入内容...</div>
			<script type="text/javascript">
				$(document).ready(function() {
					$('#summer1').summernote({
						lang : 'zh-CN', 
						minHeight : 250,
						focus : true
					});
				});
			</script>
			<input type="button" value="保存" onclick="saveButton();"/>
			<input type="button" value="取消" onclick="$('#insert').window('close')"/>
		</form>
	</div>
	<!-- 修改菜单 -->
	<div id="editMenu" style="display: none"></div>
	<script src="<%=path%>/js/plugin/bootstrap.js"></script>
</body>
</html>