<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/right_include.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script src="js/echarts.min.js"></script>
<script src="js/echarts.js"></script>
<div class="right-drawer drawer">
	<div class="container" id="schmsg-tab">
		<div class="row">
			<div id="basemsg" class="col-md-6 schmsg-tab"
				style="background-color: black;">基本信息</div>
			<div id="comment1" class="col-md-6 schmsg-tab"
				style="background-color: black;">发帖评论区</div>
			<div id="dataview" class="col-md-6 schmsg-tab"
				style="background-color: black;">数据可视化</div>



			<div hidden id="comment-table"
				style="width: 100%; height: 750px; background-color: #333333;">
				<div style="padding-left: 35px; width: auto; font-size: 18px;">
					<span><img src="img/water.png"></span><span
						style="color: #66CCFF; font-family: '微软雅黑';">所有评论</span>
				</div>
				<div
					style="height: 30px; width: 100%; margin-top: 3px; margin-left: 15px; border-top: 2px solid #66CCFF;"></div>
				<div class="main">
					<div class="textArea" id="scroll-2">
						<ul class="jieda" id="jieda">

							<%
								int i = 0;
							%>
							<c:forEach items="${comment }" var="u">

								<li data-id="111" class="jieda-daan"><a
									name="item-1111111111"></a>
									<div class="detail-about detail-about-reply"
										style="height: 45px;">
										<a class="fly-avatar" href=""> <img src="pic/1.jpg"
											alt=" ">
										</a>
										<div class="fly-detail-user" style="height: 65px">
											<a href="" class="fly-link"> <cite>${u.username }</cite>

											</a>
										</div>

										<div class="detail-hits" style="margin-top: -45px">
											<span>${u.comment.commentContent}</span><br>

											<ul>
												<li><span style="margin-right: 20px">${u.comment.ctime }</span>
													<span class="jieda-zan" type="zan"
													style="margin-right: 5px"> <i
														class="iconfont icon-zan"></i> <em>${u.comment.likenum }</em>
												</span> <span type="reply"> <i
														class="iconfont icon-svgmoban53"></i> 回复
												</span></li>
											</ul>
										</div>


									</div>
									 <div class="detail-body jieda-body photos">
										<div class="comment">
											<ul>
											 	<c:forEach items="${response}"  begin="<%=i %>" end="<%=i %>" var="rr">
													<c:forEach items="${rr}" var="response">
													<li><span> <vi style="color:aqua ;">${response.username}</vi>
														<vi style="color:rgb(218, 102, 7)">回复</vi> <vi
															style="color: aqua">${response.rusername }</vi> <vi
															style="color:rgb(218, 102, 7)">:${response.commentResponse.content }</vi>
												</span> <span class="jieda-zan" type="zan"
													style="margin-right: 5px"> <i
														class="iconfont icon-zan"></i> <em>${response.commentResponse.likenum }</em>
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
									</div>
								</li>
							</c:forEach>


							<!-- 无数据时 -->
							<!-- <li class="fly-none">消灭零回复</li> -->
						</ul>
					</div>

					<!--力导图和饼图-->
					<div id="pic1">
						<div id="main1" style="width: 250px; height: 250px;"></div>
						<div id="ss"></div>
						<script src="js/lidaotu.js"></script>
						<div id="main2"
							style="width: 250px; height: 280px; display:; float: right; top: 10px"></div>
						<div id="ss"></div>
						<script src="js/bingtu.js"></script>
					</div>

					<div hidden class="layui-form-item layui-form-text" id="com1">
						<form action="/jie/reply/" method="post">
							<div class="layui-form-item layui-form-text">
								<a name="comment"></a>
								<div class="layui-input-block"
									style="margin-left: 57px; margin-right: 45px;">
									<div class="layui-unselect fly-edit"
										style="background-color: black; border-color: #886363;">
										<span type="face" title="插入表情"
											style="background-color: black;"><i
											class="iconfont icon-yxj-expression"
											style="top: 1px; color: red;"></i></span> <span type="picture"
											title="插入图片：img[src]"><i class="iconfont icon-tupian"
											style="color: red;"></i></span> <span type="href"
											title="超链接格式：a(href)[text]"><i
											class="iconfont icon-lianjie" style="color: red;"></i></span> <span
											type="code" title="插入代码或引用"><i
											class="iconfont icon-emwdaima" style="top: 1px; color: red;"></i></span>
										<span type="hr" title="插入水平线" style="color: red;">hr</span><span
											type="yulan" title="预览"><i
											class="iconfont icon-yulan1" style="color: red;"></i></span>
									</div>
									<textarea id="L_content" name="content" required
										lay-verify="required" placeholder="请输入内容"
										class="layui-textarea fly-editor"
										style="height: 150px; color: white; background-color: black; border-color: #886363;"></textarea>
								</div>
							</div>
							<div class="layui-form-item">
								<input type="hidden" name="jid" value="123">
								<button class="layui-btn" lay-filter="*" lay-submit=""
									style="background: black">
									<img src="pic/sub.png"
										style="margin-top: 0px; width: 100px; margin-right: 0px; margin-left: 10‒; margin-left: 37px;">
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div style="text-align: center;"></div>
		</div>
		<div id="schmsg"
			style="width: 100%; height: 750px; background-color: #333333;">
			<div style="padding-left: 20px; width: auto; font-size: 18px;">
				<span><img src="img/water.png"></span><span
					style="color: #66CCFF; font-family: '微软雅黑';">课程信息</span>
			</div>
			<div
				style="height: 300px; width: 100%; margin-top: 3px; border-top: 2px solid #66CCFF; border-bottom: 2px solid #66CCFF;">
				<div class="img-introduce"
					style="height: 296px; width: 60%; float: left; border-right: 1px dotted #66CCFF; overflow: auto;">
					<img src="img/c.gif"
						style="width: 80px; height: 80px; padding-left: 25px; padding-top: 25px; float: left;">
					<div style="float: right; padding-right: 10px; padding-top: 25px;">
						<p style="font-size: 18px; height: 18px;">${courseNode[0].courseName}</p>
						<p style="font-size: 15px; height: 15px;">
							教师：<span><a href="#">${courseNode[0].createrName}</a></span>
						</p>
						<p style="font-size: 12px; height: 12px;">
							创建时间：<span>${courseNode[0].creatTime}</span>
						</p>

						<button type="button" class="btn btn-default"
							style="margin-top: 10px; width: 100px; background-color: #FFCC00; color: #fff;">数据申请</button>
					</div>
					<div
						style="height: auto; width: 90%; clear: both; margin: 0 auto; padding-top: 5PX;">
						<table
							style="background-image: url(img/456.png); width: 100%; height: 100px; margin-top: 25px; background-size: cover; background-repeat: no-repeat; font-family: '微软雅黑';">
							<tr style="text-align: center; font-size: 14px;">
								<th style="padding-left: 50px; color: red;">课程积分</th>
							</tr>
							<tr style="text-align: center; font-size: 28px; color: white;">
								<td>${courseNode[0].courseScore}</td>
							</tr>
							<tr style="text-align: center; font-size: 16px; color: black;">
								<td></td>
							</tr>
						</table>
					</div>
				</div>

				<div class="text-introduce"
					style="height: 300px; width: 40%; float: right;">
					<!-- pre -->
					<pre
						style="padding: 8px; background-color: #333333; border: none; width: 100%; height: 282px; overflow: auto; color: #fff; font-size: 24px; font-family: '微软雅黑'; word-wrap: break-word; white-space: pre-wrap;">
${courseNode[0].courseDesc}
</pre>
				</div>

			</div>
			<div id="data">
				<div style="width: 60%; float: left; padding-right: 3px;">
					<div
						style="overflow-x: auto; height: 125px; width: 100%; overflow-y: hidden; white-space: nowrap;">
						<ul style="padding: 5px; width: auto; float: none;" class="imgul">
							<li><img src="img/课程_13.gif">
								<p>
									学生 <span>${courseNode[0].stuNum}</span>
								</p></li>
							<li><img src="img/课程_16.gif">
								<p>
									实体知识 <span>${courseNode[0].linkNum}</span>
								</p></li>
						</ul>
					</div>
					<div style="padding: 15px;">
						<p>知识属性</p>
						<div>
							<table class="datatab">
								<tr>
									<th>知识名称</th>
									<th>创建者</th>
									<th>知识连接</th>
									<th>操作</th>
								</tr>
								<c:forEach var="kg" items="${kgNode}">
									<tr>
										<td><a href="/kg?kgId=${kg.kgId}">${kg.kgName}</a></td>
										<td><a href="/member?memberId=${kg.createrId}">${kg.createrName}</td>
										<td>${kg.linkNum}</td>
										<td width="120px"><a href="/kgDownLoad?kgId=${kg.kgId}"><img
												src="img/下载.png"></a> <a href="/kgPreview?kgId=${kg.kgId}"><img
												src="img/查看.png"></a> <a href="/kgDelete?kgId=${kg.kgId}"><img
												src="img/删除.png"></a></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				<div
					style="width: 40%; height: 380px; float: right; border-left: 1px dotted #66CCFF;">
					<ul class="edit">
						<!--添加功能-->
						<li><a
							href="/kgAdd?courseId=${courseNode[0].courseId}&courseName=${courseNode[0].courseName}">+</a></li>
						<li><a onclick="return confirm('确定删除当前数据？')"
							href="/courseDelete?courseId=${courseNode[0].courseId}&courseName=${courseNode[0].courseName}">-</a></li>
						<li><a
							href="/courseEdit?courseId=${courseNode[0].courseId}&courseName=${courseNode[0].courseName}">edit</a></li>
					</ul>
					<div
						style="background-color: #66CCFF; height: 2px; width: 100%; clear: both;"></div>
					<div style="padding: 15px; overflow: auto; height: 360px;">
						<p>最近一周记录</p>
						<ul class="record">
							<li>教师<a href="">xxx</a>创建了<a href="">实体知识</a>xxxxxxxxx
							</li>
							<li>教师<a href="">xxx</a>创建了<a href="">实体知识</a>xxxxxxxxx
							</li>
							<li>教师<a href="">xxx</a>创建了<a href="">实体知识</a>xxxxxxxxx
							</li>
							<li>教师<a href="">xxx</a>创建了<a href="">实体知识</a>xxxxxxxxx
							</li>
							<li>教师<a href="">xxx</a>创建了<a href="">实体知识</a>xxxxxxxxx
							</li>
							<li>教师<a href="">xxx</a>创建了<a href="">实体知识</a>xxxxxxxxx
							</li>
							<li>教师<a href="">xxx</a>创建了<a href="">实体知识</a>xxxxxxxxx
							</li>
							<li>教师<a href="">xxx</a>创建了<a href="">实体知识</a>xxxxxxxxx
							</li>
							<li>教师<a href="">xxx</a>创建了<a href="">实体知识</a>xxxxxxxxx
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div id="datamsg"
			style="width: 100%; height: 750px; background-color: #333333; display: none;">

			<!--数据化及其下面的一条线-->

			<div style="padding-left: 20px; width: auto; font-size: 18px;">
				<span><img src="img/water.png"></span><span
					style="color: #66CCFF; font-family: '微软雅黑';">数据可视化</span>
			</div>
			<div
				style="height: 38px; width: 100%; margin-top: 3px; border-top: 2px solid #66CCFF;"></div>


			<!--正文内容-->

			<div class="textArea" id="scroll-2"
				style="margin-left: 3px; height: 664px">
				<!--扇形图和右边小部件-->
				<div
					style="height: 400px; background-color: dodgerblue; width: 498px">
					<div style="background-color: brown; width: 380px; float: left">
						<p>该课程中实体知识点击量情况</p>
						<div id="main3" style="width: 380px; height: 380px"></div>
					</div>
					<!--扇形图的script-->
					<script src="js/shujukeshihua-shangxingtu.js"></script>
					<div
						style="height: 400px; background-color: blueviolet; width: 118px; display: inline-block; margin-left: 10px; float:; margin-left: 0px">



						<div
							style="float: left; background-color:; margin-left: 1px; width: 20px; height: 20px">
							<form>
								<fieldset style="width: 80px; height: 10px">
									<legend
										style="font-size: 10px; color: aliceblue; border-color: aliceblue">该课程在线资源</legend>
									<br> <img src="img/课程_16.gif" height="31" width="25"><br>
									<p style="font-size: 10px">实体知识 1234</p>
								</fieldset>
							</form>
						</div>



					</div>
					<div
						style="background-color: burlywood; width: 497px; height: 400px">
						<p>2019年4月1日24小时中具体在线用户量</p>
						<div id="chart"
							style="margin-left: 10px; margin-top: 30px; width: 480px; height: 280px"></div>
						<!--折线图所在的script-->
						<script src="js/shujukeshihua-zhextu.js"></script>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>





<script>
	$("i.iconfont.icon-zan").click(function() {
		$(this).css("color", "red");
	});

	$(document).ready(function() {

		$('body').on("click", 'i.iconfont.icon-zan', function() {

			var A = $(this).attr("id");
			var B = A.split("like");
			var messageID = B[1];
			//var C=parseInt($("#likeCount"+messageID).html());
			$(this).css("background-position", "")
			var D = $(this).attr("rel");

			if (D === 'like') {
				$("#likeCount" + messageID).html(C + 1);
				$(this).addClass("heartAnimation").attr("rel", "unlike");
			} else {
				$("#likeCount" + messageID).html(C - 1);
				$(this).removeClass("heartAnimation").attr("rel", "like");
				$(this).css("background-position", "left");
			}
		});

	});
</script>


<script type="text/javascript">
	$("span[type='reply']").click(function() {
		var dis = $("#pic1").css("display");
		if (dis == 'none') {
			$('#pic1').css("display", "block");
			$('#com1').css('display', 'none');
		} else {
			$('#pic1').css("display", "none");
			$('#com1').css('display', 'block');
		}
	});
</script>


<script src="js/echarts.min.js"></script>
<!-- 注册tab -->
<script type="text/javascript">
	$('#self').click(function() {
		$('#selfregister').css("display", "block");
		$('#comregister').css('display', 'none');
	});
	$('#school').click(function() {
		$('#selfregister').css("display", "none");
		$('#comregister').css('display', 'block');
	});
</script>

<!-- schmsgtab -->
<script type="text/javascript">
	$('#basemsg').click(function() {
		$('#schmsg').css("display", "block");
		$('#datamsg').css('display', 'none');
		$('#comment-table').css('display', 'none');
	});
	$('#dataview').click(function() {
		$('#datamsg').css("display", "block");
		$('#schmsg').css('display', 'none');
		$('#comment-table').css('display', 'none');
	});
	$('#comment1').click(function() {
		$('#datamsg').css("display", 'none');
		$('#schmsg').css('display', 'none');
		$('#comment-table').css('display', 'block');
	});
</script>
<script>
	$('#basemsg').mousedown(function() {
		$('#basemsg').css("background-color", 'orange');
		$('#comment1').css("background-color", 'black');
		$('#dataview').css("background-color", 'black');

	})
	$('#comment1').mousedown(function() {
		$('#comment1').css("background-color", '#00FFFF');
		$('#basemsg').css("background-color", 'black');
		$('#dataview').css("background-color", 'black');

	})
	$('#dataview').mousedown(function() {
		$('#dataview').css("background-color", 'orange');
		$('#comment1').css("background-color", 'black');
		$('#basemsg').css("background-color", 'black');

	})
</script>
<!-- 侧栏显示，隐藏 -->
<script>
	$('.toggle-btn-left').click(function() {
		// body...        
		$('.left-drawer').toggle();
		$('.toggle-btn-left').toggleClass('btn-left-temp');
	});

	$('.toggle-btn-right').click(function() {
		// body...
		$('.right-drawer').toggle();
		$('.toggle-btn-right').toggleClass('btn-right-temp');
	});
</script>

<script type="text/javascript">
	$('#sch').click(function() {
		// body...
		$('#sch-secmenu').toggle();
	});
	$('#lesson').click(function() {
		// body...
		$('#les-secmenu').toggle();
	})
	$('#kg').click(function() {
		// body...
		$('#tea-secmenu').toggle();
	})
</script>

