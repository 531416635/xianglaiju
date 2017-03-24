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
<link href="<%=path%>/css/plugin/fileinput.css" media="all" rel="stylesheet" />
<link href="<%=path%>/css/album.css" rel="stylesheet" />
</head>
<body>
	<div class="container" id="box">
		<!-- 上传图片的按钮 -->
		<button type="button" class="btn btn-primary" id="imagebtn" data-toggle="modal" data-target="#imagefile">上传照片</button>
		<!-- 相册名字组 -->
		<select class="input-sele input-sm">
			<c:forEach items="${nameMap }" var="namestr">
				<option value="${namestr.key }">${namestr.value }</option>
			</c:forEach>
		</select>
		<!-- 新建相册按钮 -->
		<button class="btn btn-link" data-toggle="modal" data-target="#newalbum">新建相册</button>
		<!-- 新建相册弹框 -->
		<div class="modal fade bs-example-modal-sm" id="newalbum" tabindex="-1" role="dialog">
			<div class="modal-dialog  modal-sm" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true" id="spanclose">×</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">新建相册</h4>
					</div>
					<div class="modal-body">
						<div>
							<input type="text" class="editnewalbum" placeholder="请输入相册名称">
							<button type="button" class="btn btn-info" id="surenewalbum">确认</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 上传图片的模态框 bootstrap的Modal -->
		<div class="modal fade bs-example-modal-lg" id="imagefile"
			tabindex="-1" role="dialog">
			<div class="modal-dialog  modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">文件上传</h4>
					</div>
					<div class="modal-body">
						<input id="input-700" name="filename" type="file" multiple="true"
							class="file-loading">
					</div>
				</div>
			</div>
		</div>
		<!-- 处理图片二次弹出模态框的转换层 -->
		<div id="othermodal"></div>
		<!-- 图册集层-->
		<div class="container center-black">
			<c:forEach var="photoAlbum" items="${photoAlbums }" varStatus="status">
				<div class="albums">
					<a href="toalbumdetail.html?albumid=${photoAlbum.id}" class="b-link-stripe b-animate-go thickbox">
					<img src="<%=path%>${photoAlbum.albumpath}" class="img-thumbnail" />
					</a>
				</div>
			</c:forEach>
		</div>
		<!-- 下载测试 -->
		<div>
			<c:forEach var="fileImage" items="${fileInfos }" varStatus="status">
				<a
					href="../web/downloadfile.html?path=${fileImage.filepath}&filename=${fileImage.filename}${fileImage.filetype}">${fileImage.filename}</a>
				<%-- <img src="<%=path%>${fileImage.filepath}" class="img-thumbnail" /> --%>
			</c:forEach>
		</div>
	</div>
	<script src="<%=path%>/js/plugin/viewer.js"></script>
	<script src="<%=path%>/js/plugin/fileinput.js"></script>
	<script src="<%=path%>/js/album.js"></script>
</body>
</html>