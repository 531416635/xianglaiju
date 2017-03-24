var toolbar = [ {
	text : '增加',
	iconCls : 'icon-add',
	handler : function() {
		newData();
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
}, '-', {
	text : '分配权限',
	iconCls : 'icon-lock',
	handler : function() {
		newRoleMenu();
	}
}];
/**
 * 菜单管理初始化
 */
$(function(){
	$('#dg').datagrid({
	    url:'getRoleList.do',
	    idField:'id',
	    treeField:'rolename',
	    fit:true,
	    rownumbers :true,
		singleSelect : true,
		fitColumns : true,
	    columns:[[
			{title:'角色名称',field:'rolename'},
			{title:'角色编码',field:'rolecode'},
			{title:'角色状态',field:'status'},
			{title:'创建时间',field:'createtime'}
	    ]],
	    toolbar:toolbar,
	    loadFilter : function(data,parentId) {
			var returnData;
			if (data.errorID == 0) {
				returnData = {
					rows : data.result,
					errorID:data.errorID 
				};
				$.each(returnData.rows, function(i) { 
					returnData.rows[i].createtime = new Date(returnData.rows[i].createtime).Format("yyyy-MM-dd hh:mm:ss");
					if(0==returnData.rows[i].status){//1:已启用 0:未启用 2:已禁用
						returnData.rows[i].status = "未启用";
					}else if(1==returnData.rows[i].status){
						returnData.rows[i].status = "已启用";
					}else if(2==returnData.rows[i].status){
						returnData.rows[i].status = "已禁用";
					}
					
				}); 
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

function newRoleMenu(){
	var row = $('#dg').datagrid('getSelected');
	
	if (row) {
		$("#role_menu_id").val(row.id);
		$('#tt').html("");
		$('#tt').tree({
			url : 'getRoleMenu.do',
			checkbox : true,
			animate : true,
			lines :true,
			queryParams : {
				roleid : row.id
			},
			method : 'POST',
			loadFilter : function(data,parent) {
				var result = data.result;
				if (data.errorID == 0) {
					data = result;
				}
				return data;
			},
		});
		$('#insertRoleMenu').window({
		    width:600,
		    height:400,
		    modal:true
		});
		$('#insertRoleMenu').window('open');
	} else {
		$.messager.alert('警告','请先选择修改项');
	}
}
/**
 * 弹出新增菜单窗口
 */
function newData(){
	$('#insertMenu').window({
	    width:600,
	    height:400,
	    modal:true
	});
	$('#cc').combotree({
	    url: 'getCombotreeMenu.do',
		method : 'POST',
		width : 250,
		panelHeight : 250,
		panelWidth : 250,
		loadFilter : function(data) {
			if (data.errorID == 0) {
				data = data.result;
			}
			return data;
		}
	});
	$('#insertMenu').window('open'); 
}
/**
 * 保存菜单信息
 */
function saveMenuButton(){
	//var  menuname= $("#savemenuname").val().trim();
	//var  menupath= $("#savemenupath").val().trim();
	var  meunList= $("#tt").tree('getChecked');
	$.each(meunList, function(idx, obj) {
	    alert(obj.tagName);
	});
	if( pid == null || pid == '' ){
		pid = 0;
	}
	$.ajax({
		url : 'saveMenu.do',
		data : {
			menuname : menuname,
			menupath : menupath,
			pid : pid
		},
		method : 'POST',
		success : function(data) {
			if (data.errorID == 0 && data.result > 0) {
				$('#tt').treegrid('reload');
				$('#insertMenu').window('close');
				$.messager.alert('通知', '添加菜单成功！');
			} else {
				$.messager.alert('通知', '添加菜单失败！');
			}
		}
	});
}
