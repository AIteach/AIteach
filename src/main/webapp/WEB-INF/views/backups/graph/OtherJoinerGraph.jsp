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

   <%@ include file="../header.jsp" %>
   <%@ include file="otherGraphLeftIndex.jsp" %>
   <%@ include file="../container.jsp" %>


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
        left: 20%;
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
        width: 20%;
        left: 0;
        overflow: auto;
    }

    .right-drawer {
        width: 35%;
        right: 0;
    }
	 #leftli #myCreatCourse a{
		font-size:25px;display: block;width: 90%;text-decoration: none;text-align: center;margin: 30px auto;background-color:#f47983;padding-top:50px;padding-bottom: 50px;border-radius: 10px;color: #fff;
    }
    #myJoinCourse a{
    	font-size:25px;display: block;width: 90%;text-decoration: none;text-align: center;margin: 30px auto;background-color:#66CCFF;padding-top:50px;padding-bottom: 50px;border-radius: 10px;color: #fff;	
    }
    #myLearnerCourse a{
    	font-size:25px;display: block;width: 90%;text-decoration: none;text-align: center;margin: 30px auto;background-color:#00CC33;padding-top:50px;padding-bottom: 50px;border-radius: 10px;color: #fff;	
    }
    #leftli #myCreatCourse a:hover{
    	background-color: #c32136;
    }
    #myJoinCourse a:hover{
    	background-color: #177cb0;
    }
    #leftli #myLearnerCourse a:hover{
    	background-color:green;
    }
    #myLearnerCourse a:hover{
    	background-color:#00CC33 ;
    }
</style>
</head>
<body>

<script src="js/echarts.min.js"></script>
<script>
$(document).ready(function(){  
    var i=1;
    $('.toggle-btn-left').click(function () {
        // body...        
        $('.left-drawer').toggle();
        $('.toggle-btn-left').toggleClass('btn-left-temp');
    });
});
	
var msg=${msg};
var myChart = echarts.init(document.getElementById('main'));
myChart.showLoading();  
//var webkitDep=msg;                        
myChart.hideLoading();  

option = {  
		title: {
            text: ''
        },
        tooltip: {},
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
            data:  msg.nodes,/*.map(function (node, idx) {  
            node.id = idx;  
            return node;  
        })*/
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
                 window.location.href =data.url;
            }
        }
        // alert(param.dataIndex+':'+option.series[0].data[param.dataIndex].name);
      //  alert(option.series[0].data[0].name);
      //  console.log(param.data);
    });
     myChart.setOption(option); 
</script>
</body>
</html>