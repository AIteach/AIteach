<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>教育技术知识图谱</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/public.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
</head>
<body>
			<%
				int g = 0;
			%>
		<c:forEach items="${comment }" var="u">
	<c:forEach items="${response }" begin="<%=g %>" end="<%=g %>" var="rr">
		<c:forEach items="${rr }" var="responsed">
		${responsed.username}<br/>
	${responsed.username}<br/>
		</c:forEach>
			<%
				g++;
			%>
	</c:forEach>
	</c:forEach>
		






	<div class="textArea" id="scroll-2">
		<ul class="jieda" id="jieda">
			<%
				int i = 0;
			%>
			<c:forEach items="${comment }" var="u">
				<li data-id="111" class="jieda-daan"><a name="item-1111111111"></a>
					<div class="detail-about detail-about-reply" style="height: 45px;">
						<!-- <a class="fly-avatar" href=""> <img src="pic/1.jpg" alt=" ">
				</a> -->
						<div class="fly-detail-user" style="height: 65px">
							<a href="" class="fly-link"> <cite>${u.username }</cite></a>
						</div>

						<div class="detail-hits" style="margin-top: -45px">
							<span>${u.comment.commentContent}</span><br>
							<ul>
								<li><span style="margin-right: 20px">${u.comment.ctime }</span>
									<span class="jieda-zan" type="zan" style="margin-right: 5px">
										<i class="iconfont icon-zan"></i> <em>${u.comment.likenum }</em>
								</span> <span type="reply"> <i class="iconfont icon-svgmoban53"></i>
										回复
								</span></li>
							</ul>
						</div>
					</div>
					<div style="height: 30px"></div>
					<div class="detail-body jieda-body photos">
						<div class="comment">
							<ul>
								<c:forEach items="${response }" begin="<%=i %>" end="<%=i %>"
									var="rr">
									<c:forEach items="${rr }" var="response">
										<li><span> <vi style="color:aqua ;">${response.username}</vi>
												<vi style="color:rgb(218, 102, 7)">回复</vi> <vi
													style="color: aqua">${response.rusername }</vi> <vi
													style="color:rgb(218, 102, 7)">:${response.commentResponse.content }</vi>
										</span> <span class="jieda-zan" type="zan" style="margin-right: 5px">
												<i class="iconfont icon-zan"></i> <em>${response.commentResponse.likenum }</em>
										</span> <span type="reply"> <i
												class="iconfont icon-svgmoban53"></i> 回复
										</span></li>
									</c:forEach>
								</c:forEach>
								<%
									i++;
								%>
							</ul>
						</div>
					</div></li>

				<div style="height: 30px"></div>

			</c:forEach>

			<!-- 无数据时 -->
			<!-- <li class="fly-none">消灭零回复</li> -->
		</ul>
	</div>
</body>