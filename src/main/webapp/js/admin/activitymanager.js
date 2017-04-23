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
 * 初始化
 */
$(function(){
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
	        {title:'id',field:'id',hidden: true},
			{title:'活动名称',field:'activityName'},
			{title:'活动内容',field:'activityContent',hidden: true},
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
		},
		//双击查看详情
		onDblClickRow:function(rowIndex, rowData){
			$("#activityName2").text(rowData.activityName);
			$("#activityTime2").text(rowData.activityTime);
			$("#userId2").text(rowData.userId);
			$("#itemcontent2").html(rowData.activityContent);
			$("input[type=radio][name=isIndex2][value="+rowData.isIndex+"]").attr("checked",true)
			$("#isIndex2").text(rowData.activityName);
			$('#dblClick').window({
				title:"查看详情",
			    width:850,
			    height:450,
			    modal:true
			});
			$('#dblClick').window('open'); 
		}
	});
	
	
});

/**
 * 弹出新增窗口
 */
function newData(){
	$('#insert').window({
		title:"新增活动",
	    width:850,
	    height:450,
	    modal:true
	});
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
					if (data.errorID == 1) {
						$('#dg').datagrid('reload');
						$.messager.alert('通知','添加资讯成功！');
						// 关闭窗口
						$('#insert').window('close')
					} else {
						$.messager.alert('通知','添加资讯失败'+data.msg);
						// 关闭窗口
						//$('#insert').window('close')
					}
				}
		};
	
	$('#activityForm').ajaxSubmit(ajax_option);
	return false;
}
/**
 * 修改
 */
function editData(){
	
	var row = $('#dg').datagrid('getSelected');
	if(row){
		$('#summer3').summernote('code', row.activityContent);
		$("#id3").val(rowData.id);
		$("#activityName3").val(rowData.activityName);
		$("#activityTime3").val(rowData.activityTime);
		$("#userId3").val(rowData.userId);
		$("#update input[type=radio][name=isIndex][value="+rowData.isIndex+"]").attr("checked",true)
		
		$('#update').window({
			title:"修改",
			width:850,
			height:450,
			modal:true
		});
		$('#update').window('open'); 
	} else {
		$.messager.alert('警告','请先选择修改项！');
	}
}
