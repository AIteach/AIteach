<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="./H-ui.admin/favicon.ico" >
<link rel="Shortcut Icon" href="./H-ui.admin/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="./H-ui.admin/lib/html5shiv.js"></script>
<script type="text/javascript" src="./H-ui.admin/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="./H-ui.admin/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="./H-ui.admin/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="./H-ui.admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="./H-ui.admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="./H-ui.admin/static/h-ui.admin/css/H-ui.login.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>编辑课程</title>
<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
	<span style="text-align:center;color:blue"><h1>编辑课程</h1></span>
	</br></br></br></br>
	<form action="/doCourseEdit" method="post" class="form form-horizontal" id="form-member-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>学校名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" disabled="disabled" class="input-text" value="${school.schoolName}" placeholder="" id="schoolName" name="schoolName">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>课程名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${course.courseName}" placeholder="请输入新的知识点名" id="courseName" name="courseName">
			</div>
		</div>
		<input type="hidden" class="input-text" value="${course.courseId}"  id="courseId" name="courseId">
		<input type="hidden" class="input-text" value="${course.nodeId}"  id="nodeId" name="nodeId">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>课程描述</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" placeholder="请简单描述一下你创建的课程" value="${course.courseDesc}" name="courseDesc" id="courseDesc">
			</div>
		</div>
	<!-- 	<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">附件：</label>
			<div class="formControls col-xs-8 col-sm-9"> <span class="btn-upload form-group">
				<input class="input-text upload-url" type="text" name="kgUrl" id="uploadfile" readonly nullmsg="请添加附件！" style="width:200px">
				<a href="javascript:void();" class="btn btn-primary radius upload-btn"><i class="Hui-iconfont">&#xe642;</i> 浏览文件</a>
				<input type="file" multiple name="file-2" class="input-file">
				</span> 
			</div>
		</div> -->
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="./H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="./H-ui.admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="./H-ui.admin/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="./H-ui.admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本--> 
<script type="text/javascript" src="./H-ui.admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="./H-ui.admin/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="./H-ui.admin/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="./H-ui.admin/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	/* $("#form-member-add").validate({
		rules:{
			username:{
				required:true,
				minlength:2,
				maxlength:16
			},
			sex:{
				required:true,
			},
			mobile:{
				required:true,
				isMobile:true,
			},
			email:{
				required:true,
				email:true,
			},
			uploadfile:{
				required:true,
			},
			
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			//$(form).ajaxSubmit();
			var index = parent.layer.getFrameIndex(window.name);
			//parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	}); */
});
</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>