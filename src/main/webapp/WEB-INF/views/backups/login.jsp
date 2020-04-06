<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>登陆</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>

<style>
* {
	padding: 0;
	margin: 0;
}

.container {
	position: relative;
	width: 100%;
	overflow: hidden;
}

.left-drawer {
	position: absolute;
	top: 92px;
	left: 0;
	height: 700px;
	background-color: #333333;
	color: #fff;
	transition: all 0.15s ease-in;
	z-index: 250;
}

.right-drawer {
	position: absolute;
	top: 0;
	right: 0;
	height: auto;
	background-color: #333333;
	color: #fff;
	transition: all 0.15s ease-in;
	z-index: 250;
	margin-top: 39px;
}

.left-drawer--hide {
	transform: translateX(-100%);
}

.right-drawer--hide {
	transform: translateX(100%);
}

.toggle-btn {
	position: fixed;
	top: 50%;
	width: 25px;
	height: 80px;
	text-align: center;
	line-height: 30px;
	transform: translateY(50%);
	background-color: #333;
	font-size: 18px;
	color: #fff;
	cursor: pointer;
	border: none;
	outline: none;
	z-index: 250;
}

.toggle-btn-left {
	position: fixed;
	top: 50%;
	left: 15%;
	background-image: url(img/left.png);
	border-top-right-radius: 15px;
    border-bottom-right-radius: 15px;
}

.btn-left-temp {
	position: fixed;
	top: 50%;
	left: 0;
}

.toggle-btn-right {
    position: fixed;
    top: 50%;
    right: 30%;       
    background-image: url(img/right.png);
    border-top-left-radius: 15px;
   border-bottom-left-radius: 15px;
}

.btn-right-temp {
	position: fixed;
	top: 50%;
	right: 0;
}

.left-drawer {
	width: 15%;
	left: 0;
	overflow: scroll;
}

.right-drawer {
	width: 30%;
	right: 0;
}
</style>
</head>
<body>
	<div id="nav">
		<div id="img" style="float: left;">
			<img src="img/logo.png" style="margin-left: 10px; margin-top: 10px;">
		</div>
		<div id="navli">
		   <ul>
            <li id="li-mylesson"><a href="">我的图谱</a></li>
            <li id="li-mylesson"><a href="/createrGraph">课程图谱</a></li>
            <li id="li-mylesson"><a href="/index">首页</a></li>
            <li id="li-login">
            <c:if var="result" test="${empty sessionScope.currentUser}"><a href="/login">登陆</a></c:if>
            <c:if test="${!result}"><a href="/exit">退出登陆</a></c:if>
            </li>
        </ul>
		</div>
	</div>

	<%-- <div>
		<div class="left-drawer drawer">
			<!-- 收起/展开按钮 -->
			<div id="search"
				style="background-color: #111114; text-align: center; padding: 2px;">
				<p>
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbspweb
					图谱搜索
				</p>
				<div class="row" style="margin-bottom: 5px;">
					<div class="col-md-10 col-md-offset-1">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="Search for..."> <span
								class="input-group-btn">
								<button class="btn btn-default" type="button">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
								</button>
							</span>
						</div>
					</div>
				</div>
			</div>

			<div id="leftli">
            <ul class="firstul">
                <li class="firstli" id="sch"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp&nbsp学校                   
                </li>
                <div class="secmenu" id="sch-secmenu">   
                       <ul>
                            <li><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp&nbsp${schoolNode.schoolName}</li>
                        </ul>                    
                </div> 
                <li class="firstli" id="lesson"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp&nbsp课程                    
                </li>
                <div class="secmenu" id="les-secmenu">
                        <ul>
                        <c:forEach var="course" items="${courseNode}">
                            <li><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span><a style="font-color:white" href="${course.courseId}">&nbsp&nbsp${course.courseName}</a></li>             
                        	</c:forEach>
                        </ul>
                </div>
                <li class="firstli" id="teacher"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp&nbsp知识点                   
                </li>
                <div class="secmenu" id="tea-secmenu">
                       <ul>
                            <li><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp&nbspteacher1</li>
                            <li><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp&nbspteacher2</li>
                            <li><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp&nbspteacher3</li>
                        </ul> 
                </div> 
            </ul>
        </div> 
		</div>
		<div>
			<button class="toggle-btn toggle-btn-left"></button>
		</div>
	</div> --%>


	<div class="container">
		<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
		<div id="main" style="width: 100%; height: 400px;"></div>
		
	</div>
<div style="position: fixed;top: 300px;left:30%;right:50%;width:20%;text-align: center;display: none;z-index: 999;" id="login-tip">
    <img src="img/logintip.gif">
    <h1>Hi,Welcome to</h1>
    <h3>Education technology Datebase</h3> 
</div>
<script type="text/javascript">
    $('#login-tip').fadeIn();
  
</script>
	<div class="right-drawer drawer">
		<div id="topimg">
			<img src="img/edc.gif" style="width: 100%;">
		</div>

		<div id="user" style="width: 100%; height: 330px; text-align: center;">
			<div class="container">
				<div class="row">
					<div id="login-in">
						<div id="loginimg" style="width: 30%; float: left;">
							<img src="img/loginimg.png"
								style="padding: 15px; margin-top: 80px;">
						</div>
						<form  action="/doLogin" method="post"
							style="padding: 15px; width: 70%; float: right; margin-top: 30px;">
							<div class="input-group input-group-lg">
								<span class="input-group-addon" id="sizing-addon2">账&nbsp&nbsp号</span>
								<input type="text" name="username" class="form-control"
									aria-describedby="sizing-addon2">
							</div>
							<div class="input-group input-group-lg">
								<span class="input-group-addon" id="sizing-addon2">密&nbsp&nbsp码</span>
								<input type="password" name="password" class="form-control"
									aria-describedby="sizing-addon2">
							</div>

							<span style="float: left; padding: 0px 35px; margin-top: 20px"><a
								href="/register">注册账号</a></span>
							<span style="float: right; padding: 0px 35px; margin-top: 20px"><a
								href="#">忘记密码</a></span>
							<div style="text-align: center;">
								<input type="submit" value="登陆"  class="btn btn-info"
									name="loginbtn" style="margin-top: 10px; width: 180px;"></button>
							</div>
							<p style="margin-top: 20px; color: #999;">
								其他方式登陆&nbsp <a href="#"> <img src="img/qq.png"
									style="width: 18px; height: 18px;">
								</a>&nbsp <a href="#"> <img src="img/weixin.png"
									style="width: 18px; height: 18px;">
								</a>&nbsp <a href="#"> <img src="img/xinlang.png"
									style="width: 18px; height: 18px;">
								</a>
							</p>
						</form>
					</div>
				</div>
				<p style="margin-top: 5px;">
					<a href="#" style="color: #000; font-size: 12px;">遇到技术问题,请点击这里</a>
				</p>
			</div>


			<div id="bottomimg">
				<p style="padding-top: 170px;">Copyright © 2018-2030 Nagin &
					Black All Right Reserved</p>
			</div>
		</div>


	</div>


	</div>
	<button class="toggle-btn toggle-btn-right"></button>



	<script src="js/echarts.min.js"></script>
	<!-- 注册tab -->
	<script type="text/javascript">
    $('#self').click(function(){
        $('#selfregister').css("display","block");
        $('#comregister').css('display','none');
    });
    $('#school').click(function(){
        $('#selfregister').css("display","none");
        $('#comregister').css('display','block');
    });
    if(${!empty registerError}){
        alert("${registerError}");
        }
    if(${!empty message}){
        alert("${message}");
        }
   /*  if(${loginType}){
		alert("1");
		var msg="${msg}";
		console.log(msg.nodes);
		var myChart = echarts.init(document.getElementById('main'));
	    myChart.showLoading();  
	    //var webkitDep=msg;	        	        
	    myChart.hideLoading();  
	    
	    option = {  
	            legend: {  
	                data: msg.categories//此处的数据必须和关系网类别中name相对应  
	            },  
	            series: [{  
	                type: 'graph',  
	                layout: 'force',  
	                animation: true,  
	                label: {  
	                    normal: {  
	                        show: true,  
	                        position: 'right'  
	                    }  
	                },  
	                draggable: true,  
	                data: msg.nodes,  
	                categories: msg.categories,  
	                force: {  
	                    edgeLength: 105,//连线的长度  
	                    repulsion: 100  //子节点之间的间距  
	                },  
	                edges: msg.links  
	            }]  
	        };  
	        myChart.setOption(option);  
	        myChart.on("click", function (param){               
	        	var option = myChart.getOption();
	        	var data = param.data; //判断节点的相关数据是否正确     
	        	console.log(data);
	        	if (data!= null && data!= undefined) {
	        		if (data.url!= null && data.url!= undefined) {
	        			//根据节点的扩展属性url打开新页面
	        			console.log(data.url);
	        			window.open(data.url);
	        		}
	        	}
	        	// alert(param.dataIndex+':'+option.series[0].data[param.dataIndex].name);
	          //  alert(option.series[0].data[0].name);
	          //  console.log(param.data);
	        });
			 myChart.setOption(option); 
	}
	else { alert(${loginType});} */
  
</script>
	<!-- 侧栏显示，隐藏 -->
	<script>
$(document).ready(function(){ 
	
//	show();  ///加载下面的show
	
    $('.toggle-btn-left').click(function () {
        // body...        
        $('.left-drawer').toggle();
        $('.toggle-btn-left').toggleClass('btn-left-temp');
    });

    $('.toggle-btn-right').click(function () {
        // body...
        $('.right-drawer').toggle();
        $('.toggle-btn-right').toggleClass('btn-right-temp');
    });

});
</script>

<script type="text/javascript">
    $('#sch').click(function () {
        // body...
        $('#sch-secmenu').toggle();
    });
    $('#lesson').click(function () {
        // body...
        $('#les-secmenu').toggle();
    })
    $('#teacher').click(function () {
        // body...
        $('#tea-secmenu').toggle();
    })

</script>
<script type="text/javascript">
    
 function show(){
$.ajax({
    type : "POST",  //请求方式
    url : "/resource",  //请求路径
    data : {  
    },
    success : function(msg) {  //异步请求成功执行的回调函数
        alert("成功了: " + msg);
        var myChart1 = echarts.init(document.getElementById('main'));
        myChart1.showLoading();  
        var webkitDep=msg;
        myChart1.hideLoading();  
        
        //myChart1.setOption(option);  
        //$("#usernameinfo").html(msg);
    },//ajax引擎一般用不到；状态信息；抛出的异常信息
    dataType: "json",
    error : function(XMLHttpRequest, textStatus, errorThrown) {
        alert(textStatus);
        alert("失败了"+errorThrown)
    }
});
} 

    /*
    * Echarts代码
    */

    /* var myChart = echarts.init(document.getElementById('main'));

    myChart.showLoading();  
    var webkitDep = 
     {  
    	    type: "force",  
    	    categories: [
    	        {  
    	            name: '学校'
    	        },{
    	            name: '课程'
    	        }  
    	    ],  
    	    nodes: [	   
    	    ],  
    	    links: []  
    	};   
    myChart.hideLoading();  */
  
   
 

</script>
</body>
</html>