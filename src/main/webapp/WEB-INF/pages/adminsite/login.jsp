<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='security ' uri='http://www.springframework.org/security/tags' %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 对移动设备的支持更友好-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>登录</title>
<link rel="stylesheet" href="<%=path%>/css/plugin/bootstrap.css" />
<script src="<%=path%>/js/plugin/jquery.min.js"></script>
<script src="<%=path%>/js/plugin/bootstrap.js"></script>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-body">
			<form class="bs-example bs-example-form  center-block" action="<%=path%>/admin/tologin.html" method="post">
				<div class="form-group">
					<label>用户名：</label>
					<div class="form-trname">
						<input id="toregisterusername" type="text" name="username" oninput="if(this.value.length>15){this.value=this.value.slice(0,15)}"
							class="form-control" placeholder="请输入用户名"> 
					</div>
				</div>
				<div class="form-group">
					<label>密码：</label>
					<div class="form-tremail">
						<input id="toregisteremail" type="password" name="password"
							oninput="if(this.value.length>25){this.value=this.value.slice(2,25)}"
							class="form-control" placeholder="请输入密码"> 
					</div>
				</div>
				<div class="form-group">
					<div class="form-tremail">
						${sessionScope.loginException} 
					</div>
				</div>
				<button type="submit" id="toLogin" class="btn btn-default disabled">登录</button>
			</form>
		</div>
	</div>



	<div class="col-lg-5"></div>


	<script type="text/javascript">
		$("#toregisterusername").focus(function() {
			$("#toregisterusername").removeClass("input-danger");
		});
		$("#toregisteremail").focus(function() {
			$("#toregisteremail").removeClass("input-danger");
		});
	</script>
</body>
</html>