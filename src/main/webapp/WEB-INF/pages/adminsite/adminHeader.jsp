<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 这里就是装饰页面 -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><decorator:title default="翔来居" /></title>
<link rel="stylesheet" href="<%=path%>/css/plugin/easyui.css" />
<link rel="stylesheet" href="<%=path%>/css/plugin/icon.css" />
<script src="<%=path%>/js/plugin/jquery.min.js"></script>
<script src="<%=path%>/js/plugin/jquery.easyui.min.js"></script>
<script src="<%=path%>/js/plugin/easyui-lang-zh_CN.js"></script>
<script src="<%=path%>/js/plugin/datagrid-detailview.js"></script>
<script src="<%=path%>/js/plugin/dateFormat.js"></script>
<!-- head装饰页面 -->
<decorator:head />
</head>
<body style="margin: 0px;">
	<!-- 中间装饰内容页面 -->
	<decorator:body />

</body>
</html>