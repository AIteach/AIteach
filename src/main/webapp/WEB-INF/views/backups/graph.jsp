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

</style>
</head>
<body>
<div id="nav">
    <div id="img" style="float: left;">
        <img src="img/logo.png" style="margin-left: 10px;margin-top: 10px;">
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


    <div>
        <div class="left-drawer drawer">
            <!-- 收起/展开按钮 -->
            <div id="search"
                style="background-color: #111114; text-align: center; padding:5px;font-size:16px;">
              		   我的课程图谱
                <div class="row" style="margin-bottom: 5px;">
                    <div class="col-md-10 col-md-offset-1">
                    </div>
                </div>
            </div>

            <div id="leftli">
           		<div id="myCreatCourse"     style="background-color:pink; height:200px;text-align: center; margin:0 5px;font-size:16px;">
           			<p style="padding:70px;">
           				<a  target="right"  style="font-size:30px;" href="/OtherCreatGraph" >我创建的</a>
           			</p>
           		</div>
           		<div id="myJoinCourse" style="background-color:#66CCFF; height:200px;text-align: center; margin:.0 5px;font-size:16px;">
           			<p style="padding:70px;">
           				<a target="right"  style="font-size:30px;color:white;" href="/login">我参与的</a>
           			</p>
           		</div>
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
        alert("1");
        var msg=${msg};
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
                       window.location.href =data.url;
                    }
                }
                // alert(param.dataIndex+':'+option.series[0].data[param.dataIndex].name);
              //  alert(option.series[0].data[0].name);
              //  console.log(param.data);
            });
             myChart.setOption(option); 
    }
    else { alert(${loginType});}
</script>
    <!-- 侧栏显示，隐藏 -->
    <script>
$(document).ready(function(){  
    var i=1;
    $('.toggle-btn-left').click(function () {
        // body...        
        $('.left-drawer').toggle();
        $('.toggle-btn-left').toggleClass('btn-left-temp');
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
    
/* function show(){
$.ajax({
    type : "POST",  //请求方式
    url : "/resource",  //请求路径
    data : {  //请求参数
        schoolId : 1
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
} */

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