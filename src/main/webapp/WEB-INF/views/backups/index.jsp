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
<link rel="stylesheet" type="text/css" href="css/public.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="css/page.css">
</head>
<body>
<%@ include file="header.jsp" %>
<%@ include file="left_include.jsp" %>



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