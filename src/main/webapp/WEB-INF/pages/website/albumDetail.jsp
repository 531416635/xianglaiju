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
<title>我的相册</title>
<link href="<%=path%>/css/plugin/viewer.css" rel="stylesheet" />
<link href="<%=path%>/css/albumdetail.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
		<div id="wrap">
			<ul class="images">
				<c:forEach var="fileImage" items="${images }" varStatus="status">
					<li>
						<div class="box">
							<div class="info">
								<div class="pic">
									<img src="<%=path%>${fileImage.filepath}" alt="Picture"
										class="img-thumbnail" />
								</div>
								<div class="title">
									<a href="#">This is a title.</a>
								</div>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	<div style="padding-top: -100px">
		<c:forEach var="fileImage" items="${images }" varStatus="status">
			<a
				href="../web/downloadfile.html?path=${fileImage.filepath}&filename=${fileImage.filename}${fileImage.filetype}">${fileImage.filename}</a>
		</c:forEach>
	</div>
	</div>
	<script src="<%=path%>/js/plugin/viewer.js"></script>
	<script src="<%=path%>/js/albumdetail.js"></script>
</body>
</html>