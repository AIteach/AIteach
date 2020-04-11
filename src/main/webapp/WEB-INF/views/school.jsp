<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>教育技术知识图谱</title>
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
        background-color:#333333;
        color: #fff;
        transition: all 0.15s ease-in;
        z-index: 250;
    }
    .right-drawer{
        position: absolute;
        top:0;
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
        transform:translateY(50%);
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
        left: 13%;
        background-image: url(img/left.png);
        border-top-right-radius: 15px;
        border-bottom-right-radius: 15px;
    }
    .btn-left-temp{        
        position: fixed;
        top: 50%;
        left: 0;
    }

    .toggle-btn-right {
        position: fixed;
        top: 50%;
        right: 35%;
        background-image: url(img/right.png);
        border-top-left-radius: 15px;
        border-bottom-left-radius: 15px;
    }    
    .btn-right-temp{        
        position: fixed;
        top: 50%;
        right:0;
    }

    .left-drawer {
        width: 13%;
        left: 0;
        overflow: auto;
    }

    .right-drawer {
        width: 35%;
        right: 0;
    }

</style>
</head>
<body>
<%@ include file="header.jsp" %>

<div>
   <div class="left-drawer drawer">
        <!-- 收起/展开按钮 -->
        <%@ include file="search.jsp" %>
        
        <div id="leftli">
            <ul class="firstul">
                <li class="firstli" id="sch"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp;&nbsp;学校                   
                </li>
                <div class="secmenu" id="sch-secmenu">   
                       <ul>
                            <li><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp;&nbsp;${schoolNode.schoolName}</li>
                       </ul>                    
                </div> 
                <li class="firstli" id="lesson"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp;&nbsp;课程                    
                </li>
                <div class="secmenu" id="les-secmenu">
                        <ul>
                        <c:forEach var="course" items="${courseNode}">
                            <li><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span><a style="font-color:white" href="${course.courseId}">&nbsp;&nbsp;${course.courseName}</a></li>             
                        </c:forEach>
                        </ul>
                </div>
                  <li class="firstli" id="kg"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp;&nbsp;知识点                  
                </li>
                <div class="secmenu" id="kg-secmenu">
                       <c:forEach var="kgSonNode" items="${kgNode}">
                            <li><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span><a style="font-color:white" href="${kgSonNode.kgId}">&nbsp;&nbsp;${kgSonNode.kgName}</a></li>             
                        </c:forEach>
                </div> 
                <li class="firstli" id="source"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp;&nbsp;资源                  
                </li>
                <div class="secmenu" id="tea-secmenu">
                       <c:forEach var="kgSonNode" items="${kgSonNodes}">
                            <li><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span><a style="font-color:white" href="${kgSonNode.id}">&nbsp;&nbsp;${kgSonNode.sourceName}</a></li>             
                        </c:forEach>
                </div> 
            </ul>
        </div>      
    </div> 
    <div>
        <button class="toggle-btn toggle-btn-left"></button>
    </div> 
</div>


<div class="container">   
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="margin: 0 0 0 20% ;width:1000px; height: 700px;"></div>
    
</div>
<div class="right-drawer drawer">
    <div class="container" id="schmsg-tab">
        <div class="row">
            <div class="col-md-6 schmsg-tab" style="background-color:#FFCC00;" id="basemsg">基本信息</div>
            <div id="dataview" class="col-md-6 schmsg-tab" style="background-color: #000;">数据可视化</div>
            <div id="schmsg" style="width: 100%;height: 750px;background-color: #333333;">
                <div style="padding-left: 20px;width: auto;font-size: 18px;">
                    <span><img src="img/water.png"></span><span style="color: #66CCFF;font-family: '微软雅黑';">学校信息</span>
                </div>
                <div style="height: 300px;width: 100%;margin-top: 3px;border-top: 2px solid #66CCFF;border-bottom: 2px solid #66CCFF;">
                    <div class="img-introduce" style="height: 296px;width: 50%;float: left;border-right: 1px dotted #66CCFF;overflow: auto;">
                        <div>
                            <img src="${schoolNode.imgUrl}" style="width: 50px;height: 50px;margin-left: 20px;margin-top: 20px;float: left;vertical-align: middle;">
                            <div style="float: right;margin-right: 5px;margin-top: 20px;vertical-align: middle;">
                                <p id="school" style="font-size: 16px;height:16px;">学校: ${schoolNode.schoolName}</p>
                                <p style="font-size: 15px;height:15px;">省份: ${schoolNode.province}</p>
                                <p style="font-size: 12px;height:12px;">城市:${schoolNode.city}</p>

                                <button type="button" class="btn btn-default" style="margin-top: 10px;width: 100px;background-color:#66CCFF;color: #000;">数据完善</button>
                            </div> 
                        </div>
                        
                        <div style="height:auto;width: 100%;clear: both;margin: 0 auto;padding-top: 25px;">
                            <table style="background-image: url(img/456.png);width: 100%;height: 100px;background-size: cover;background-repeat: no-repeat;font-family: '微软雅黑';">
                                <tr style="text-align: center;font-size: 14px;">
                                    <th style="padding-left:40px;color: red;">教育技术学</th>
                                </tr>
                                <tr style="text-align: center;font-size: 28px;color: white;">
                                    <td>${schoolNode.score}%</td>
                                </tr>
                                <tr style="text-align: center;font-size: 16px;color: black;">
                                    <td>好评率</td>
                                </tr>
                            </table>
                        </div>
                    </div>

                    <div class="text-introduce" style="height: 280px;width: 50%;float: right;"><pre style="padding:2px;margin:0px;background-color: #333333;width: 100%;height:50%;overflow: auto;color: #fff;font-size: 18px;font-family: '微软雅黑';">
							<p style="margin: 0px;color:#66CCFF" align="center">学校简介</p>${schoolNode.schoolDesc}
						</pre>
                        <!-- pre -->
						<p style="margin: 0px;color:#66CCFF" align="center" font-size: 18px;">基本操作</p>
                    	<ul class="edit"> 									 <!--添加功能-->
                            <li><a href="/courseAdd?schoolId=${schoolNode.id}&schoolName=${schoolNode.schoolName}">+</a></li>
                            <li><a onclick="return confirm('确定删除当前数据？')" href="/schoolDelete?schoolId=${schoolNode.id}&schoolName=${schoolNode.schoolName}">-</a></li>
                            <li><a href="/schoolEdit?schoolId=${schoolNode.id}&schoolName=${schoolNode.schoolName}">edit</a></li>
                        </ul>
                    </div>

                </div>
                <div id="data">
                    <div style="width: 100%;overflow-x:scroll;height:125px;width: 100% ;overflow-y: hidden;white-space: nowrap;">
                            <ul style="padding:5px;width: auto;float: none;" class="imgul">
                                <li><img src="img/Index_03.png"><p>课程 <span>${schoolNode.courseNum}</span></p></li>
                                <li><img src="img/课程_11.gif"><p>教师 <span>${schoolNode.teacherNum}</span></p></li>
                                <li><img src="img/课程_13.gif"><p>学生 <span>${schoolNode.stuNum}</span></p></li>
                                <li><img src="img/课程_16.gif"><p>实体知识 <span>${schoolNode.linkNum}</span></p></li>
                            </ul>
                        </div>
                    <div style="float: left;border-right: 1px dotted #66CCFF;padding-right: 3px;width: 65%; overflow: auto;">                        
                        <div style="padding: 15px;">
                            <p><u>课程属性</u></p>
                            <div style="width:anto;height: 200px;background-color: #666666;width: auto;">
                                <table class="datatab" style="background-color: rgb(102, 102, 102);">
                                    <tr>
                                        <th>课程名</th>
                                        <th width="30%">教师</th>
                                        <th>学生总数</th>
                                        <th>实体知识</th>
                                    </tr>
                                    <c:forEach var="course" items="${courseNode}">
                                    <tr>
                                        <td>${course.courseName}</td>
                                        <td>${course.createrName}</td>
                                        <td>${course.stuNum}</td>
                                        <td>${course.nodeNum}</td>
                                    </tr>   
                                     </c:forEach>         
                                </table>
                            </div>
                        </div>
                    </div>
                    <div style="width: 35%;height:260px;float: right;">
                        <div style="padding: 15px;overflow: auto;height: 360px;">
                            <p>最近一周记录</p>
                            <ul class="record">
                                <li>教师<a href="">xxx</a>创建了<a href="">实体知识</a>xxxxxxxxx</li>
                                <li>教师<a href="">xxx</a>创建了<a href="">实体知识</a>xxxxxxxxx</li>
                                <li>教师<a href="">xxx</a>创建了<a href="">实体知识</a>xxxxxxxxx</li>
                                <li>教师<a href="">xxx</a>创建了<a href="">实体知识</a>xxxxxxxxx</li>
                                <li>教师<a href="">xxx</a>创建了<a href="">实体知识</a>xxxxxxxxx</li>
                                <li>教师<a href="">xxx</a>创建了<a href="">实体知识</a>xxxxxxxxx</li>
                                <li>教师<a href="">xxx</a>创建了<a href="">实体知识</a>xxxxxxxxx</li>
                                <li>教师<a href="">xxx</a>创建了<a href="">实体知识</a>xxxxxxxxx</li>
                                <li>教师<a href="">xxx</a>创建了<a href="">实体知识</a>xxxxxxxxx</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div id="datamsg" style="width: 100%;height: 750px;background-color: #999999;display: none;">
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
    if(${loginType}){
    	//alert("有用户:"+${loginType});
		var msg=${msg};
		console.log(msg);
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
	                    edgeLength: 285,//连线的长度  
	                    repulsion: 200  //子节点之间的间距  
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
	        			 window.location.href =data.url;
	        		}
	        	}
	        	// alert(param.dataIndex+':'+option.series[0].data[param.dataIndex].name);
	          //  alert(option.series[0].data[0].name);
	          //  console.log(param.data);
	        });
			 myChart.setOption(option); 
	}
	else { alert("登录失败"+${loginType});
	}
</script>
<!-- 侧栏显示，隐藏 -->
<script>
$(document).ready(function(){
   
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
<!-- schmsgtab -->
<script type="text/javascript">
    $('#basemsg').click(function () {        
        $('#schmsg').css("display","block");
        $('#datamsg').css('display','none');
    });
    $('#dataview').click(function () {        
        $('#datamsg').css("display","block");
        $('#schmsg').css('display','none');
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
    $('#kg').click(function () {
        // body...
        $('#kg-secmenu').toggle();
    })
    $('#source').click(function () {
        // body...
        $('#tea-secmenu').toggle();
    })
    		
</script>
</body>
</html>