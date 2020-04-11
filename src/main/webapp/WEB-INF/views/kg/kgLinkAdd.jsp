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

<title>添加链接</title>
</head>
<body>
<article class="page-container">
	<span style="text-align:center;color:blue"><h1>编辑链接</h1></span>
	</br></br></br></br>
	<form action="/doKgLinkAdd" method="post" class="form form-horizontal" id="form-member-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>链接元名：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" disabled="disabled" class="input-text" value="${param.kgName}" placeholder="" id="kgName" name="kgName">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>链接关系描述：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text"  class="input-text"  placeholder="" id="value" name="value">
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>链接结点类型：</label>
			<div class="formControls col-xs-8 col-sm-9">
				 <select name="type"  class="input-text">	 
		            	<option value="2">知识元类型</option>
		            	<option value="3">资源类型</option>
	         	 </select>
      		</div>
      	</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>链接结点选择：</label>
			<div class="formControls col-xs-8 col-sm-9">	
				 <select name="preId"  class="input-text">	
				 	<option selected value="0">- 请选择结点 -</option>
		           	 <c:forEach var="node" items="${nodes}">
		            	<option value="${node.id}">${node.name}</option>
		           	 </c:forEach>
	         	 </select>
      		</div>
      	</div>
   
		<input type="hidden" value="${rearId}" name="rearId">
		<input type="hidden" value="${param.kgId}" name="kgId">
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
		
	</form>
</article>
</body>
</html>