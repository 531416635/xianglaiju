<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>文章</title>
<link href="<%=path%>/css/plugin/summernote.css" rel="stylesheet"/>
</head>
<body>
	<div id="summer1">请输入内容...</div>
	<script src="<%=path%>/js/plugin/summernote.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#summer1').summernote({
				lang : 'zh-CN', 
				minHeight : 250,
				focus : true
			});
		});
	</script>
</body>
</html>