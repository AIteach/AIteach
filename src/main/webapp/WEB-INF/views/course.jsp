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
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/public.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/echarts.min.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ include file="left_include.jsp"%>


	<div class="container">
		<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
		<div id="main" style="width: 100%; height: 700px;"></div>

	</div>

	<%@ include file="right_include.jsp"%>

	


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



	
	<!-- 侧栏显示，隐藏 -->
	<script>
		$(document).ready(function() {
			var msg = ${msg};
			console.log(msg);
			var myChart = echarts.init(document.getElementById('main'));
			myChart.showLoading();
			//var webkitDep=msg;	        	        
			myChart.hideLoading();

			option = {
				title : {
					text : ''
				},
				tooltip : {},
				animationDurationUpdate : 1500,
				animationEasingUpdate : 'quinticInOut',
				label : {
					normal : {
						show : true,
						textStyle : {
							fontSize : 20
						},
					}
				},
				legend : {
					data : msg.categories
				//此处的数据必须和关系网类别中name相对应  
				},
				series : [ {
					type : 'graph',
					layout : 'force',
					animation : true,
					label : {
						normal : {
							show : true,
							position : 'right',
							fontSize : 20
						}
					},
					edgeLabel : {
						normal : {
							show : true,
							textStyle : {
								fontSize : 20
							},
							formatter : "{c}"
						}
					},
					draggable : true,
					data : msg.nodes,
					categories : msg.categories,
					force : {
						edgeLength : 250,//连线的长度  
						repulsion : 500
					//子节点之间的间距  
					},
					edges : msg.links
				} ]
			};
			myChart.setOption(option);
			myChart.on("click", function(param) {
				var option = myChart.getOption();
				var data = param.data; //判断节点的相关数据是否正确     
				console.log(data);
				if (data != null && data != undefined) {
					if (data.url != null && data.url != undefined) {
						window.location.href = data.url;
					}
				}
				// alert(param.dataIndex+':'+option.series[0].data[param.dataIndex].name);
				//  alert(option.series[0].data[0].name);
				//  console.log(param.data);
			});
			myChart.setOption(option);
			var i = 1;

		});
	</script>

	<script>
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
</body>
</html>