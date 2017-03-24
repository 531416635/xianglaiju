//$("#midiv").css("margin-top",$("#webheadernavbar").height());bottom:-390%;
//alert($("img").height());
$("img + span").css("bottom",-($("#imghety").height()/2-8));
$("img + span").css("width",$("#imghety").width()+10);
/***
 * 相册图片浏览
 */
$('.images').viewer();

/**
 * 图片上传插件
 */
$("#input-700").fileinput({
	language:"zh",
	uploadUrl : "../web/toalbumajax.html", // server upload action
	uploadAsync: true,
    minFileCount: 1,
    maxFileCount: 10,
    deleturl : "../web/toalbumajax.html",
    //allowedFileTypes: ['image']
    //dropZoneEnabled:false,
   // resizeImage: false,
    //showCaption: false,
    //showPreview: false,
}).on("filebatchselected", function(event, files) {
   //	$(this).fileinput("upload");
}).on('fileuploaded', function(event, data, previewId, index) {
	var form = data.form, files = data.files, extra = data.extra,
	response = data.response, reader = data.reader;
});
/**
 * 图片上传按钮点击效果
 */
$("#imagebtn").click(function(){
	var str=$(".input-sele").find("option:selected").text().trim();
	if(str==''||str==null||str=='undefined'){
		alert("所选相册名不能为空！");
		return false;
	}
	//用来在点击关闭弹出模态框之后，清空其中的值
	$(".fileinput-remove").click();
});
/**
 * 新建相册名确认按钮
 */
$("#surenewalbum").click(function(){
	var str=$(".editnewalbum").val().trim();
	if(str==''||str==null||str=='undefined'){
		alert("请输入正确的相册名！");
		$(".editnewalbum").val('')
		return false;
	}else{
		var str="<option value=\"\" selected>"+str+"</option>";
		$(".input-sele").append(str);
	}
	$("#spanclose").click();
});


