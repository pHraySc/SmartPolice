$().ready(function(){
	KE.show({
		 id : "editor",
	     width : "100%",
	     height : "500px",		    
	     resizeMode : 1,
	     allowFileManager : true,
	     /*图片上传的SERVLET路径*/
	       imageUploadJson : "/JSPKindEditor/uploadImage.html", 
	       /*图片管理的SERVLET路径*/     
	       fileManagerJson : "/JSPKindEditor/uploadImgManager.html",
	       /*允许上传的附件类型*/
	       accessoryTypes : "doc|xls|pdf|txt|ppt|rar|zip",
	       /*附件上传的SERVLET路径*/
	       accessoryUploadJson : "/JSPKindEditor/uploadAccessory.html"
	});
});