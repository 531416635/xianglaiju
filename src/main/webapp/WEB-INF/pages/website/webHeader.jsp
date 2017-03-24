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
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 对移动设备的支持更友好-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title><decorator:title default="翔来居" /></title>
<link rel="stylesheet" href="<%=path%>/css/plugin/bootstrap.css" />
<decorator:head />
<style type="text/css">
.modal-footer {
	text-align: center;
}
</style>
</head>
<body>
	<nav id="webheadernavbar" class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<!-- 屏幕缩小之后，导航条通过此按钮召唤出 -->
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!-- LOGO处添加<img> 元素即可展示自己的品牌图标 -->
			<a class="navbar-brand" href="javascript:void();">LOGO</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="<%=path%>/web/index.html">首页<span
						class="sr-only">(current)</span></a></li>
				<li><a href="<%=path%>/web/toRegister.html">注册</a></li>
				<li><a href="<%=path%>/admin/index.html">后台</a></li>
				<li><a href="<%=path%>/web/toArticle.html">文章</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">班级<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="toalbum.html">相册</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">视频</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">心情</a></li>
					</ul></li>
			</ul>
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
			<ul class="nav navbar-nav navbar-right">

				<!-- <button type="button" class="" >Launch demo modal </button> -->
				<li><a href="#">Link</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Dropdown<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Separated link</a></li>
					</ul></li>
				<c:if test="${sessionScope.user!=null }">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">${sessionScope.user.email}<span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="javascript:void(0);">${sessionScope.user.username}</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="javascript:void(0);">个人中心</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="javascript:void(0);">设置</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="../web/loginout.html">退出</a></li>
						</ul></li>
				</c:if>
				<c:if test="${sessionScope.user==null }">
					<li>
						<button type="button" class="btn btn-primary" id="loginbtn"
							data-toggle="modal" data-target="#tologin">登录</button>
					</li>
				</c:if>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	</nav>
	<div id="midiv"></div>

	<!-- Modal -->
	<div class="modal fade" id="tologin" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Sign in to 翔来居</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label>邮箱：</label> <input type="email" class="form-control"
								id="webheaderInputEmail1" placeholder="请输入邮箱地址">
						</div>
						<div class="form-group">
							<label>密码：</label> <input type="password" class="form-control"
								id="webheaderInputPassword1" placeholder="请输入密码">
						</div>
					</form>
				</div>
				<div class="text-center modal-footer">
					<div id="warninglogin"></div>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="signin">登录</button>
				</div>
			</div>
		</div>
	</div>

	<script src="<%=path%>/js/plugin/jquery.min.js"></script>
	<script src="<%=path%>/js/plugin/jquery.md5.js"></script>
	<script src="<%=path%>/js/plugin/bootstrap.js"></script>
	<script src="<%=path%>/js/webHeader.js"></script>
	<!-- 中间装饰内容页面 -->
	<decorator:body />
	<p class="text-center">
		<small>Copyright©2016 yaoyuxiao All Rights Reserved.</small>
	</p>
	<p class="text-center">
		<small>版权所有©yaoyuxiao | 地址：中国·上海·青浦 | QQ：531416635</small>
	</p>

</body>
</html>
