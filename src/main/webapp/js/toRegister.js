/**
 * 注册前，填写注册邮箱界面
 */
$("#toregisterbtn").click(function(){
	var s = $("#toregisterbtn").hasClass("disabled");
	if(!s){
		var username=$.trim($("#toregisterusername").val());
		var email=$.trim($("#toregisteremail").val());
		$.ajax({
			type: "post",
			url: "sendRegisterMsg.do",
			data: {
				username:username, 
				email:email
			},
			dataType: "text",
			success: function(data){
				if(data=="1"){
					$("#msgDIV").html("");
					var html="<div class=\"alert alert-danger alert-dismissible text-left\" role=\"alert\">"
						+"<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">"
						+"<span aria-hidden=\"true\">&times;</span>"
						+"</button>"
						+"用户名已被注册,请重新输入"
						+"</div>";
					$("#toregisterusername").addClass("input-danger");
					$("#toregisteremail").removeClass("input-danger");
					$("#msgDIV").append(html);
				}else if(data=="2"){
					$("#msgDIV").html("");
					var html="<div class=\"alert alert-danger alert-dismissible text-left\" role=\"alert\">"
						+"<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">"
						+"<span aria-hidden=\"true\">&times;</span>"
						+"</button>"
						+"邮箱已被注册,请重新输入"
						+"</div>";
					$("#toregisteremail").addClass("input-danger");
					$("#toregisterusername").removeClass("input-danger");
					$("#msgDIV").append(html);
				}else if(data=="3"){
					$("#msgDIV").html("");
					var html="<div class=\"alert alert-success alert-dismissible text-left\" role=\"alert\">"
						+"<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">"
						+"<span aria-hidden=\"true\">&times;</span>"
						+"</button>"
						+"邮件已发送，请注意查收！"
						+"</div>";
					$("#msgDIV").append(html);
				}else{
					$("#msgDIV").html("");
					var html="<div class=\"alert alert-warning alert-dismissible text-left\" role=\"alert\">"
						+"<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">"
						+"<span aria-hidden=\"true\">&times;</span>"
						+"</button>"
						+"发生未知错误，请稍后再试！"
						+"</div>";
					$("#toregisterusername").addClass("input-danger");
					$("#toregisteremail").addClass("input-danger");
					$("#msgDIV").append(html);
				}
				
			}
		});
	}
});

/**
 * 用户名校验
 */
$('#toregisterusername').on('input propertychange', function () {
	var username=$('#toregisterusername').val().trim();
	var rename = new RegExp(/^[a-zA-Z_\u4E00-\u9FA5][a-zA-Z_0-9\u4E00-\u9FA5]{0,}$/);
	var isname= rename.test(username);
	if(isname==false){
		$(".form-trname .from-error").css("display","block");
		$(".form-trname .from-success").css("display","none");
		$('#toregisterusername').removeClass("form-control-success");
		$('#toregisterusername').addClass("form-control-error");
		$(".error-for-name").html("用户名只能填写中文、字母、数字或下划线，且首字母不能为数字、下划线");
		//处理注册按钮
		$("#toregisterbtn").addClass("disabled");
	}else{
		$(".form-trname .from-success").css("display","block");
		$(".form-trname .from-error").css("display","none");
		$('#toregisterusername').removeClass("form-control-error");
		$('#toregisterusername').addClass("form-control-success");
		$(".error-for-name").html("");
		//处理注册按钮
		var emaildisplay = $(".form-tremail .from-success").css("display");
		if("block" == emaildisplay){
			$("#toregisterbtn").removeClass("disabled");
		}
	}
});

/**
 * 邮箱校验
 */
$('#toregisteremail').on('input propertychange', function () {
	var email=$('#toregisteremail').val().trim();
	var reemail =new RegExp(/^(([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5}){1,25})$/);
	var isemail= reemail.test(email);
	if(isemail==false){
		$(".form-tremail .from-error").css("display","block");
		$(".form-tremail .from-success").css("display","none");
		$('#toregisteremail').removeClass("form-control-success");
		$('#toregisteremail').addClass("form-control-error");
		$(".error-for-email").html("邮箱格式错误");
		//处理注册按钮
		$("#toregisterbtn").addClass("disabled");
	}else{
		$(".form-tremail .from-success").css("display","block");
		$(".form-tremail .from-error").css("display","none");
		$('#toregisteremail').removeClass("form-control-error");
		$('#toregisteremail').addClass("form-control-success");
		$(".error-for-email").html("");
		//处理注册按钮
		var namedisplay = $(".form-trname .from-success").css("display");
		if("block" == namedisplay){
			$("#toregisterbtn").removeClass("disabled");
		}
	}
});

/**
 * 输入框获取焦点时，样式清除
 */
$("#toregisterusername").focus(function(){
	$("#toregisterusername").removeClass("input-danger");
});
$("#toregisteremail").focus(function(){
	$("#toregisteremail").removeClass("input-danger");
});
