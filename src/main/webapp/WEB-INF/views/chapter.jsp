<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
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
        <div id="search" style="background-color: #111114;text-align: center;padding: 2px;">
            <p><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbspweb 图谱搜索</p>
            <div class="row" style="margin-bottom: 5px;">
                <div class="col-md-10 col-md-offset-1">
                    <div class="input-group">
                      <input type="text" class="form-control" placeholder="Search for...">
                      <span class="input-group-btn">
                        <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
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
                          <li><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp&nbsp广州大学</li>
                     </ul> 
                </div>

                <li class="firstli" id="lesson"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span><a style="color:white" href="${course.courseurl}">&nbsp&nbsp课程</a>                    
                </li>
                <div class="secmenu" id="les-secmenu">
                   <ul>    
                         <c:forEach var="course" items="${courseNode}">
                        	 <li><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp<span class="glyphicon glyphicon-folder-open" aria-hidden="true"><a style="font-color:white" href="${course.courseId}">&nbsp&nbsp${course.courseName}</a></span></li>
                         </c:forEach>
                   </ul>
                </div>

                <li class="firstli" id="chapter" value="记得去后面设置点击张开"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp&nbsp章节
                <div class="secmenu" id="tea-secmenu">
                       <ul>
                       		 <c:forEach var="chapter" items="${chapterNode}">
                            <li><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp&nbsp${chapter.chapterName}</li>
                             </c:forEach>
                        </ul> 
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
    <div id="main" style="width:100%;height:600px;"></div>
    
</div>
<div class="right-drawer drawer">
    <div class="container" id="schmsg-tab">
        <div class="row">
            <div class="col-md-6 schmsg-tab" style="background-color:#FFCC00;" id="basemsg">基本信息</div>
            <div id="dataview" class="col-md-6 schmsg-tab" style="background-color: #000;">数据可视化</div>
            <div id="schmsg" style="width: 100%;height: 750px;background-color: #333333;">
                <div style="padding-left: 20px;width: auto;font-size: 18px;">
                    <span><img src="img/water.png"></span><span style="color: #66CCFF;font-family: '微软雅黑';">实体信息</span>
                </div>
                <div style="height: 202px;width: 100%;margin-top: 3px;border-top: 2px solid #66CCFF;border-bottom: 2px solid #66CCFF;">
                    <div class="img-introduce" style="height: 196px;width: 39%;float: left;overflow: auto;word-wrap: break-word;padding: 5px;">
                    	<h3>${chapterNode[0].chapterName}</h3>
                    	<p style="font-size: 14px;font-family: '微软雅黑';">
                    		描述：${chapterNode[0].chapterDesc}
                    	</p>
                    </div>

                    <div class="text-introduce" style="height: 200px;width: 60%;float: right;border-left: 1px dotted #66CCFF;">
                        <div style="width: 95%;height: 160px;margin: 20px auto;background-color: #666;" id="entitymsg">
	                        	<div style="height: 140px;width:auto;text-align: right;float: left;margin:20px 0px;">
	                        		<table style="text-align: right;height: 120px;">
	                        			<tr>
	                        				<td style="padding-left: 10px;">创建者</td>
	                        				<td style="width: 10px;"></td>
	                        				<td style="border-bottom: 1px solid #ccc;height: 20px;margin-left: 10px;width: 50px;text-align: center;">
	                        					${sessionScope.currentUser.name}
	                        				</td>
	                        			</tr>
	                        			<tr>
                                            <td style="padding-left: 10px;">子知识数</td>
                                            <td style="width: 10px;"></td>
                                            <td style="border-bottom: 1px solid #ccc;height: 20px;margin-left: 10px;width: 50px;text-align: center;">
                                                ${chapterNode[0].linkNum}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="padding-left: 10px;">所属课程</td>
                                            <td style="width: 10px;"></td>
                                            <td style="border-bottom: 1px solid #ccc;height: 20px;margin-left: 10px;width: 50px;text-align: center;">
                                                ${courseName}
                                            </td>
                                        </tr>
                                        <tr>
	                        				<td style="padding-left: 10px;">创建时间</td>
	                        				<td style="width: 10px;"></td>
	                        				<td style="border-bottom: 1px solid #ccc;height: 20px;margin-left: 10px;width: 120px;text-align: center;">
                                                <fmt:formatDate value="${chapterNode[0].creatTime}" />  
	                        				</td>
	                        			</tr>
	                        			
	                        		</table>
	                        	</div>
                        	
                        	<div style="width:auto;float: right;margin: 20px 5px;">
                        		是否拥有题库&nbsp;<input type="checkbox" name="" style="color: blue;">
                        	</div>                       	                        	
                        </div>
                    </div>

                </div>
                <div id="data">
                    <div style="width: 50%;height: 480px;float: left;">
                    	<div style="height: 460px;background-color: #666666;width: auto;overflow-y: scroll;margin-top: 10px;margin-left: 8px;">
                                <table class="datatab" style="margin:0 auto; ">
                                    <tr style="color: #FFCC00;">
                                        <th>资源名</th>
                                        <th width="30%">创建者</th>
                                        <th width="30%x">操作</th>
                                    </tr>
                                    <c:forEach items="${chapterSonNodes}" var="chapterSon">
                                    <tr>
                                        <td>${chapterSon.kgName}</td>
                                        <td>${chapterSon.createrId}</td>
                                        <td width="100px"><a href="/chapterDownLoad?chapterId=${chapterSon.kgId}"><img src="img/下载.png"></a>
	                                        <a href="/sourcePreview?chapterId=${chapterSon.kgId}"><img src="img/查看.png"></a>
	                                        <a href="/chapterDelete?chapterId=${chapterSon.kgId}"><img src="img/删除.png"></a>
                                        </td>
                                    </tr>                        
                                    </c:forEach>  
                                    <c:forEach  items="${sourceNodes}" var="source">              
                                    <tr>
                                        <td>${source.sourceName}</td>
                                        <td >${source.createrName}</td>
                                        <td ><a href="/sourceDownLoad?sourceId=${source.id}"><img src="img/下载.png"></a>
                                            <a href="/sourcePreview?sourceId=${source.id}"><img src="img/查看.png"></a>
                                            <a href="/sourceDelete?sourceId=${source.id}"><img src="img/删除.png"></a>
                                        </td>
                                    </tr>  
                                    </c:forEach>           
                                </table>
                        </div>
                    </div>           
                    
	                    <div style="width: 48%;height: 480px;float: right;border-left: 2px dotted #66CCFF;">
	                    	<div style="padding:5px;height: 200px;border-bottom: 2px dotted #66CCFF">
	                    		<h4>功能模块</h4>
	                    		<button onclick="javascript:window.location.href='/chapterAddKg?chapterId=${chapterNode[0].chapterId}&chapterId=${chapterNode[0].chapterId}'" class="btn btn-info" style="margin: 5px 0px;" >添加章节</button>
	                    		<button onclick="javascript:window.location.href='/chapterEdit?chapterId=${chapterNode[0].chapterId}&chapterName=${chapterNode[0].chapterName}'" class="btn btn-info" style="margin: 5px 0px;">编辑章节</button>
	                    		<button  class="btn btn-info" > <a onclick="return confirm('确定删除当前数据？')" href="/chapterDelete?chapterId=${chapterNode[0].chapterId}&chapterName=${chapterNode[0].chapterName}">删除该章节</a></button>
	                    		<button onclick="javascript:window.location.href='/chapterLinkAdd?chapterId=${chapterNode[0].chapterId}&chapterName=${chapterNode[0].chapterName}'" class="btn btn-info" style="margin: 5px 0px;">添加链接</button>
	                    		<button onclick="javascript:window.location.href='/sourceAdd?chapterId=${chapterNode[0].chapterId}&chapterName=${chapterNode[0].chapterName}'" class="btn btn-info" style="margin: 5px 0px;">添加资源元</button>
	                    		<button class="btn btn-info" style="margin: 5px 0px;">进入学习</button>
	                    	</div>
	                    	<div style="height:280px;padding:10px;overflow-y: scroll;">
	                    		<h5>操作记录</h5>
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
	    </div>              
	</div>
</div>
<button class="toggle-btn toggle-btn-right"></button>
<script src="js/echarts.min.js"></script>
<script>
$(document).ready(function(){ 
	var msg=${msg};
	console.log(msg);
   // console.log();
	var myChart = echarts.init(document.getElementById('main'));
    myChart.showLoading();  
    //var webkitDep=msg;	        	        
    myChart.hideLoading();  
    
    option = {  
    		title: {
    	        text: ''
    	    },
    	    tooltip: {},
    	    animationDurationUpdate: 1500,
    	    animationEasingUpdate: 'quinticInOut',
    	    label: {
    	        normal: {
    	            show: true,
    	            textStyle: {
    	                fontSize: 12
    	            },
    	        }
    	    },
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
                edgeLabel: {
	                normal: {
	                    show: true,
	                    textStyle: {
	                        fontSize: 10
	                    },
	                    formatter: "{c}"
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
	
	var i=1;
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
    $('#kg').click(function () {
        // body...
        $('#tea-secmenu').toggle();
    })

</script><!-- 注册tab -->
<script type="text/javascript">
    $('#self').click(function(){
        $('#selfregister').css("display","block");
        $('#comregister').css('display','none');
    });
    $('#school').click(function(){
        $('#selfregister').css("display","none");
        $('#comregister').css('display','block');
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
    
   
/* <!-- 侧栏显示，隐藏 --> */
 function msg(){
    	alert("success");
    	var msg=${msg};
		console.log(msg);
		var myChart = echarts.init(document.getElementById('main'));
	    myChart.showLoading();  
	    //var webkitDep=msg;	        	        
	    myChart.hideLoading();  
	    
	    option = {  
	    		title: {
	    	        text: ''
	    	    },
	    	    tooltip: {},
	    	    animationDurationUpdate: 1500,
	    	    animationEasingUpdate: 'quinticInOut',
	    	    label: {
	    	        normal: {
	    	            show: true,
	    	            textStyle: {
	    	                fontSize: 20
	    	            },
	    	        }
	    	    },
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
	                        position: 'right',
	                        fontSize: 20
	                    }
	                }, 
	                edgeLabel: {
		                normal: {
		                    show: true,
		                    textStyle: {
		                        fontSize: 20
		                    },
		                    formatter: "{c}"
		                }
		            },
	                draggable: true,  
	                data: msg.nodes,  
	                categories: msg.categories,  
	                force: {  
	                    edgeLength: 250,//连线的长度  
	                    repulsion: 500  //子节点之间的间距  
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
	        });
			 myChart.setOption(option); 	
    }
    
</script>


</body>
</html>