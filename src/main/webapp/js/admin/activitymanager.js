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
}];
/**
 * 菜单管理初始化
 */
$(function(){
	console.log("=====");
	$('#dg').datagrid({
	    url:'getActivityList.do',
	    idField:'id',
	    treeField:'menuname',
	    rownumbers:true,
		singleSelect:true,
		autoRowHeight:false,
	    fit:true,
	    pagination:true,
		pageSize:10,
	    columns:[[
			{title:'活动名称',field:'activityName'},
			{title:'活动内容',field:'activityContent'},
			{title:'活动时间',field:'activityTime'},
			{title:'活动发布人',field:'userId'},
			{title:'是否首页显示',field:'isIndex'},
			{title:'创建时间',field:'createtime'},
			{title:'创建者ID',field:'createuserid'},
			{title:'demo',field:'demo'},
	    ]],
	    toolbar:toolbar,
	    loadFilter : function(data,parentId) {
			var returnData;
			if (data.errorID == 1) {
				returnData = {
					rows : data.result,
					errorID:data.errorID 
				};
				$.each(returnData.rows, function(i) { 
					returnData.rows[i].createtime = new Date(returnData.rows[i].createtime).Format("yyyy-MM-dd hh:mm:ss");
                    var parentId = returnData.rows[i].pid; 
                    if(parentId != "0"){  
                    	returnData.rows[i]._parentId = parentId;  
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

/**
 * 弹出新增菜单窗口
 */
function newData(){
	$('#insert').window({
	    width:850,
	    height:450,
	    modal:true
	});
	/*$('#cc').combotree({
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
	});*/
	$('#insert').window('open'); 
}
/**
 * 保存信息
 */
function saveButton(){
	//获取资讯内容的值
	var markupStr = $('#summer1').summernote('code');
	$("#itemcontent").val(markupStr);
	var ajax_option = {
				url : path+'activity/saveActivity.do',// 默认是form action
				method : 'POST',
				success : function(data) {
					if (data.errorID == 0) {
						$('#dg').datagrid('reload');
						$.messager.alert('通知','添加资讯成功！');
					} else {
						$.messager.alert('通知','添加资讯失败'+data.message);
					}
				}
		};
	
	$('#activityForm').ajaxSubmit(ajax_option);
	// 关闭窗口
	//$('#insert').window('close')
	return false;
}
