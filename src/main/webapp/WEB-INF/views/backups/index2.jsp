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
<link rel="stylesheet" type="text/css" href="css/page.css">
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
        width: 15%;
        left: 0;
        overflow: auto;
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
        width: 35%;
        right: 0;
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
        left: 15%;
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
                <li class="firstli" id="sch"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp&nbsp图谱类型                   
                </li>
                <div class="secmenu" id="sch-secmenu">   
                       <ul>
                       		<c:forEach var="school" items="${schoolNode}">
                           		 <li><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp&nbsp${school.schoolName}</li>
                       		 </c:forEach>
                        </ul>                    
                </div> 
                <li class="firstli" id="lesson"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp&nbsp朝代                  
                </li>
                <div class="secmenu" id="les-secmenu">
                        <ul>
	                        <c:forEach var="course" items="${courseNode}">
	                            <li><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span><a style="font-color:white" href="${course.courseId}">&nbsp&nbsp${course.courseName}</a></li>             
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
    <div id="main" style="margin: 0 0 0 13% ;width:1100px; height: 600px;background-color:#FFE4E1"></div>
    
</div>



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
    
</script>
<!-- 侧栏显示，隐藏 -->
<script>
$(document).ready(function(){
    $('.right-drawer').hide();
        $('.toggle-btn-right').toggleClass('btn-right-temp');
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
    if(${loginType}){
    	//alert("有用户:"+${loginType});
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
                tooltip: {
                    show: true
                },
                toolbox: {
                    show: true,
                    feature: {
                        dataView: {
                            show: true,
                            readOnly: true
                        },
                        restore: {
                            show: true
                        },
                        saveAsImage: {
                            show: true
                        }
                    }
                },
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
	                    edgeLength: 85,//连线的长度  
	                    repulsion: 255  //子节点之间的间距  
	                },  
	                edges: msg.links  
	            }]  
	        };  
	        myChart.setOption(option);  
	        myChart.on("click", function (param){               
	        	var option = myChart.getOption();
	        	var data = param.data; //判断节点的相关数据是否正确     
	        	//console.log(data);
	        	if (data!= null && data!= undefined) {
	        		if (data.url!= null && data.url!= undefined) {
	        			//根据节点的扩展属性url打开新页面
	        		//	console.log(data.url);
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
    $('#teacher').click(function () {
        // body...
        $('#tea-secmenu').toggle();
    })
    		 /*$(function(){
    			var model= new JavascriptSerializer().Serialize('${echartjson}');
    			
    		})
	        var myChart = echarts.init(document.getElementById('main'));
	        myChart.showLoading();  
	   
	       // var webkitDep=${echartjson};	        	        
	        myChart.hideLoading();  
	        
	        option = {  
	                legend: {  
	                    data: msg.categories      //此处的数据必须和关系网类别中name相对应  
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
	                    data: msg.nodes,     //结点
	                    categories: msg.categories,     //类别
	                    force: {  
	                        edgeLength: 105,//连线的长度  
	                        repulsion: 100  //子节点之间的间距  
	                    },  
	                    edges: msg.links   //连接  
	                }]  
	            };  
	            myChart.setOption(option);  
	            
	            myChart.on("click", function (param){               
	                alert(param.dataIndex+':'+option.series[0].data[param.dataIndex].name);
	                alert(option.series[0].data[0].name);
	                console.log(param.data);
	            });
	   		 myChart.setOption(option); 
	        //$("#usernameinfo").html(msg);
	    },//ajax引擎一般用不到；状态信息；抛出的异常信息
	    dataType: "json",
	    error : function(XMLHttpRequest, textStatus, errorThrown) {
	        alert(textStatus);
	        alert("失败了"+errorThrown)
	    }
	}); */
</script>
</body>
</html>