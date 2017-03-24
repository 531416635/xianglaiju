<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>用户注册</title>
<style type="text/css">

.bs-example-form{
	max-width: 250px;
}
.input-danger{
	border-color: #FC4343 !important;
    outline: 0 !important;
    background-color: #F2DEDE !important;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6) !important;
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6) !important;
}
.form-trname,.form-tremail{
	position: relative;
}
.from-success{
	color: #3c763d;
}
.from-error{
	color: #a94442;
}
.form-control-error {
	border-color: #ff0000 !important;
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 6px #ce8483 !important;
    -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 6px #ce8483 !important;
}
.form-control-success {
	border-color: #2b542c !important;
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 6px #67b168 !important;
    -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 6px #67b168 !important;
}
.error-for-name,.error-for-email {
	display: block;
	margin-top:0.6rem;
	color: #843534;
	line-height: 1.6rem;
}
</style>
<link rel="stylesheet" href="<%=path%>/css/plugin/bootstrap.css"  />
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-body">
			<form class="bs-example bs-example-form  center-block" data-example-id="simple-input-groups">
				<div class="form-group">
					<label>用户名：</label>
					<div class="form-trname">
						<input id="toregisterusername" type="text" name="username"  oninput="if(this.value.length>15){this.value=this.value.slice(0,15)}" class="form-control" placeholder="请输入用户名">
						<i class="from-success form-control-feedback glyphicon glyphicon-ok" style="display: none;"></i>
						<i class="from-error form-control-feedback glyphicon glyphicon-remove" style="display: none;"></i>
						<small class="error-for-name"></small>
					</div>
				</div>
				<div class="form-group">
					<label>邮箱：</label>
					<div class="form-tremail">
						<input id="toregisteremail" type="email" name="email"   oninput="if(this.value.length>25){this.value=this.value.slice(0,25)}" class="form-control"  placeholder="请输入邮箱">
						<i class="from-success form-control-feedback glyphicon glyphicon-ok" style="display: none;"></i>
						<i class="from-error form-control-feedback glyphicon glyphicon-remove" style="display: none;"></i>
						<small class="error-for-email"></small>
					</div>
				</div>
				<div class="form-group" id="msgDIV"></div>
				<button type="button" id="toregisterbtn"  class="btn btn-default disabled">注册</button>
			</form>
		</div>
	</div>

	<div class="col-lg-5">
	</div>

	<script src="<%=path%>/js/toRegister.js"></script>
</body>
</html>