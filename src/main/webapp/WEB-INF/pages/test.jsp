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
<title>test json</title>
</head>
<body>
	<h2>测试json数据</h2>
	<input type="button" value="click" id="click" />
	<script src="<%=path%>/js/jquery.js"></script>
	<script type="text/javascript">
	$(function(){
        $('#click').click(function(){
        	var name="yaoyuxiao";
            $.ajax({
                type : 'POST', 
                url  : 'tojsontest.do',
                dataType:"text",
              	contentType : "text/html;charset=UTF-8",
                data : {
                	name:name
                },
              //  contentType:"application/json;charset=utf-8",
                success : function(obj) {
                    console.log("测试成功！！！！！");
                    console.log(obj);
                    console.log(obj.data);
                },
                error:function(e){
                	console.log("测试失败！！！！！");
                    console.log(e) ;  
                }
            }); 
        });
    });
	</script><br/><br/><br/>
	<input type="button" value="click2" id="click1" />
	<script type="text/javascript">
	$(function(){
        $('#click1').click(function(){
        	var name="yaoyuxiao";
            $.ajax({
                type : 'POST', 
                url  : 'tojsontest1.do',
                dataType:"json",
              	contentType : "application/json;charset=UTF-8",
                data : {
                	name:name
                },
                success : function(obj) {
                    console.log("测试成功！！！！！");
                    console.log(obj);
                    console.log(obj.data);
                },
                error:function(e){
                	console.log("测试失败！！！！！");
                    console.log(e) ;  
                }
            }); 
        });
    });
	</script>
</body>
</html>