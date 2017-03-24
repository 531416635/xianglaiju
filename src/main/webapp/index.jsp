<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
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
<!-- 对移动设备的支持更友好-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>翔来居</title>
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css"  media="screen" />
<script src="<%=path%>/js/jquery.js"></script>
<script src="<%=path%>/js/bootstrap.js"></script>

<script language="javascript" type="text/javascript">
	function bodyload(){
	window.location="<%=path%>/web/index.html";
	}
  </script>

</head>
<body onload="bodyload()">
</body>
</html>
