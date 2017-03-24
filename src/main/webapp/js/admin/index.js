//请求URL的路径
var path = "/xianglaiju/admin/";
//加载JS静态文件的路径
var JSpath = "/xianglaiju/js/admin/";
//图片显示的路径
var IMGpath = "/xianglaiju";
// 页面加载js时，初始化方法
jQuery(function($){ 
	initlayout();
});

/**
 * 初始化页面布局
 */
function initlayout() {
	// $("#indexlayout").layout('collapse','west');
	// $("#indexlayout").layout('remove','east');

}
//用户管理
$('#usermanager').click(function() {
	$.getScript(JSpath + 'usermanager.js')
});
//文章管理
$('#articlemanager').click(function() {
	$.getScript(JSpath + 'articlemanager.js')
});
$('#menumanager').click(function() {
	var title = '菜单管理';
	if ($('#tt').tabs('exists', title)) {
		$('#tt').tabs('select', title);
	} else {
		$('#tt').tabs('add', {
			title : title,
			selected : true,
			content : '<iframe id="B" frameborder="0" src="menu/menuManager.html" style="width: 100%; height: 100%;"></iframe>',
			closable : true
		});
	}
});
$('#rolemanager').click(function() {
	var title = '角色管理';
	if ($('#tt').tabs('exists', title)) {
		$('#tt').tabs('select', title);
	} else {
		$('#tt').tabs('add', {
			title : title,
			selected : true,
			content : '<iframe id="B" frameborder="0" src="role/roleManager.html" style="width: 100%; height: 100%;"></iframe>',
			closable : true
		});
	}
});