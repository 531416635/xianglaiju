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
	$('#tt').treegrid({
	    url:'getMenuList.do',
	    idField:'id',
	    treeField:'menuname',
	    fit:true,
	    columns:[[
			{title:'菜单名称',field:'menuname'},
			{title:'菜单路径',field:'menupath'},
			{title:'创建时间',field:'createtime'},
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
	var  menuname= $("#savemenuname").val().trim();
	var  menupath= $("#savemenupath").val().trim();
	var  pid= $("#cc").combotree('getValue').trim();
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
