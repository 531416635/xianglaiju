// 用户管理页面初始化
$(function() {
	var title = '用户管理';
	if ($('#tt').tabs('exists', title)) {
		$('#tt').tabs('select', title);
	} else {
		$('#tt').tabs('add', {
			title : title,
			selected : true,
			content : '<table id="dgUser"  style="height:100%;"></table><div id="insertUser"></div><div id="editUser"></div>',
			closable : true
		});
	}
	// 加载网格数据
	$('#dgUser').datagrid(
			{
				url : path + '/getAllUser.do',
				method : 'POST',
				// 要显示的字段
				columns : [ [ {
					field : 'id',
					title : 'id',
					checkbox : true,
				}, {
					field : 'username',
					title : '用户名',
					sortable : true,
					order : 'asc',
					align : "center",
					width : 200
				}, {
					field : 'usertype',
					title : '用户类型',
					align : "center",
					width : 200/*,
					formatter: function(value,row,index){
						if(value==0){
							return "超级管理员";
						}else if(value==1){
							return "管理员";
						}else if(value==2){
							return "用户";
						}else{
							return "未知用户类型";
						}
						
					}*/
				}, {
					field : 'email',
					title : '邮箱',
					align : "center",
					width : 200
				}, {
					field : 'phone',
					title : '电话',
					align : "center",
					width : 200
				}, {
					field : 'roleid',
					title : '角色',
					align : "center",
					width : 200
				} ] ],
				// 标题菜单
				toolbar : [ {
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						insertUserData();
					}
				}, '-', {
					text : '修改',
					iconCls : 'icon-edit',
					handler : function() {
						editData();
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						delData();
					}
				} ],
				fitColumns : true,
				striped : true,
				pagination : true,
				pageNumber : 1,
				pageSize : 10,
				rownumbers : true,
				sortName : "username",
				view : detailview,
				detailFormatter : function(rowIndex, rowData) {
					return "<table class=\"datagridtable\">" + "<tr>"
							+ "<td style='width: 27px;'></td><td style='width:100px;'>用户ID</td><td>"
							+ rowData.id
							+ "</td><td>用户名</td><td>"
							+ rowData.username
							+ "</td><td>用户密码</td><td>"
							+ rowData.password
							+ "</td><td>用户类型</td><td>"
							+ rowData.usertype
							+ "</td>"
							+ "</tr>"
							+ "<tr><td style='width: 27px;'></td>"
							+ "<td>用户头像</td><td>"
							+ rowData.userphoto
							+ "</td><td>邮箱</td><td>"
							+ rowData.email
							+ "</td><td>电话</td><td>"
							+ rowData.phone
							+ "</td><td>用户角色</td><td>"
							+ rowData.roleid
							+ "</td>"
							+ "</tr>"
							+ "<tr><td style='width: 27px;'></td>"
							+ "<td>注册时间</td><td>"
							+ new Date(rowData.regtime).Format("yyyy-MM-dd hh:mm:ss")
							+ "</td><td>用户状态</td><td>"
							+ rowData.userstatus
							+ "</td><td>激活状态</td><td>"
							+ rowData.activestatus
							+ "</td><td>激活码</td><td>"
							+ rowData.activecode
							+ "</td>"
							+ "</tr>"
							+ "</table>";
				},
				loadFilter : function(data) {
					var returnData;
					if (data.errorID == 0) {
						returnData = {
							total : data.result.totalCount,
							rows : data.result.users,
						};
					} else {
						returnData = {
							total : 0,
							rows : [],
						};
					}
					return returnData;
				}
			});
});


function insertUserData(){
	$('#insertUser').window({
	    width:600,
	    height:400,
	    modal:true
	});
	$('#insertUser').window('open'); 
}
function editData(){
	$('#editUser').window({
		title : '修改用户',
	    width:800,
	    height:500,
	    modal:true,
	    closed : true
	});
	var selectArr=$('#dgUser').datagrid("getSelections")
	if(selectArr.length==0){
		$.messager.alert({
			title : '温馨提示',
			msg : '请选择要修改的数据',
			icon : 'warning'//要显示的图标图片。可用的值是：error、question、info、warning。
		});
	}else if(selectArr.length==1){
		var selectRow=selectArr[0];
		var strhtml="";
		
		strhtml += "<form id='editUserForm' enctype=\"multipart/form-data\" method='post'>"
			+ "<input name='id' type='hidden' value='" + selectRow.id + "' />"
			+ "<div>"
			+ "<label for='password'>用户名：</label>"
			+ "<input class='easyui-validatebox' type='text' name='username' data-options='required:true'  />"
			+ "</div><div>"
			+ "<label for='password'>密码：</label>"
			+ "<input class='easyui-validatebox' type='text' name='password' data-options='disabled:true'  />"
			+ "</div><div>"
			+ "<label for='usertype'>用户类型：</label>"
			+ "<input class='easyui-validatebox' type='text' name='usertype' data-options='required:true'  />"
			+ "</div><div>"
			+ "<label for='userphoto'>用户头像：</label>"
			/*+ "<input class='easyui-validatebox' type='text' name='userphoto' data-options='required:true'  />"*/
			+ "<input class=\"easyui-filebox\" id=\"file\" name=\"file\""
			+ "	style=\"width: 250px;display:none;\""
			+ "	data-options=\"onChange:function() {"
				+ "		var f = $(this).next().find('input[type=file]')[0];"
				+ "		if (f.files && f.files[0]) {"
				+ "		var reader = new FileReader();"
				+ "		reader.onload = function(e) {"
				+ "			$('#changeImg').attr('src', e.target.result);"
				+ "		};"
				+ "		reader.readAsDataURL(f.files[0]);"
				+ "	}"
				+ "},missingMessage:'请选择封面图片'\">"
			+ "</div><div>"
			+ "<label for='email'>邮箱：</label>"
			+ "<input class='easyui-validatebox' type='text' name='email' data-options=\"required:true,validType:'email'\"  />"
			+ "</div><div>"
			+ "<label for='phone'>手机：</label>"
			+ "<input class='easyui-validatebox' type='text' name='phone' data-options='required:true'  />"
			+ "</div><div>"
			+ "<label for='roleid'>角色：</label>"
			+ "<input class='easyui-validatebox' type='text' name='roleid' data-options='required:true'  />"
			+ "</div><div>"
			+ "<label for='regtime'>注册时间：</label>"
			+ "<input class='easyui-validatebox' type='text' name='regtime' data-options='disabled:true'  />"
			+ "</div><div>"
			+ "<label for='userstatus'>用户状态：</label>"
			+ "<input class='easyui-validatebox' type='text' name='userstatus' data-options='required:true'  />"
			+ "</div><div>"
			+ "<label for='activestatus'>激活状态：</label>"
			+ "<input class='easyui-validatebox' type='text' name='activestatus' data-options='disabled:true'  />"
			+ "</div><div>"
			+ "<label for='activecode'>激活码：</label>"
			+ "<input class='easyui-validatebox' type='text' name='activecode' data-options='disabled:true'  />"
			+ "</div>"
	    + "</form>";
		
	strhtml+="<div style='display:inline;'><img alt='图片丢失，请重新选择' title='头像预览' style='max-width:320px;margin: 20px 0 0 20px;max-height: 380px;' id='changeImg' src='"+IMGpath+selectRow.userphoto+"' ></div>"
	
	strhtml+="<div><button onclick='butttuiohjuio()'>789808797890789</button></div>";
	$('#editUser').html(strhtml);
	$('#editUserForm').form('load',selectRow);
	$.parser.parse($('#editUser'));
	$('#editUser').window('open');
	}else if(selectArr.length >1){
		$.messager.alert({
			title : '温馨提示',
			msg : '要修改的数据条数不能大于1',
			icon : 'warning'//要显示的图标图片。可用的值是：error、question、info、warning。
		});
	}
}

function butttuiohjuio(){
	/*$('#editUserForm').submit();*/
	$('#editUserForm').form('submit', {
		url:path+"updateUserInfo.do",
		onSubmit: function(){
			// do some check
			// return false to prevent submit;
		},
		success:function(data){
			alert(data)
		}
	});
	
}