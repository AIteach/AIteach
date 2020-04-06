<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="/H-ui.admin/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="/H-ui.admin/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="/H-ui.admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="/H-ui.admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/H-ui.admin/static/h-ui.admin/css/style.css" />
</head>
<body>
<%@ include file="../header.jsp" %>


<div class="page-container">
	<form action="/searchGraphByAllField" method="post">
	<div class="text-c"> 日期范围：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;">
		<input type="text" class="input-text" style="width:250px" placeholder="输入关键词" id="" name="content">
		<button type="submit" onlick="/searchGraphByAllField" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜图谱</button>
	</form>
	</div>
	<a href="javascript:;" onclick="window.location.href='/createIndex'" class="btn btn-primary radius"><i class="Hui-iconfont"></i> 添加图谱</a>
	<div class="mt-20">
		<table class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
				
					<th width="60">ID</th>
					<th width="60">用户名</th>
					<th width="60">图谱名</th>
					<th>图谱目录</th>
					<th>图谱结点</th>
					<th>图谱链接</th>
					<th>图谱创作时间</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="graph" items="${creatGraph}" varStatus="id">
				<tr class="text-c">
					<td>${id.count}</td>
					<td>${graph.member.name}</td>
					<td>${graph.content}</td>
					<td class="text-l"><div>${graph.categories}</div></td>
					<td class="text-l"><div>${graph.nodes}</div></td>
					<td class="text-l"><div>${graph.links}</div></td>
					<td>${graph.creatTime}</td>
					<td class="td-manage">
						<a title="编辑" href="javascript:;" onclick="window.location.href='/createGraphEdit?createGraphId=${graph.id}'" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> 
						<a title="删除" onclick="return confirm('确定删除当前数据？')" href="/createGrapgDelete?createGraphId=${graph.id}&currentControllId=${param.memberId}"  class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
					</td>
				</tr>	
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>