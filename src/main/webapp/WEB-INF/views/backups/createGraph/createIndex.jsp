<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  <%@ include file="../header.jsp" %>
<!-- 提交确定框 -->
    <div id="postdiv"  style="width: 26%;height: auto;border: 1px solid #ccc;position: relative;top: 37px;left: 37%;background-color: #88ada6;border-radius: 10px;display:none">
        <div style="height: 50px;border-bottom: 2px solid #e9f1f6;border-top-right-radius: 10px;border-top-left-radius: 10px;">
            
        </div>
        <form style="margin: 0 auto;width: 70%;height: auto;">
            <table style="width: 100%;border-collapse:separate; border-spacing:0px 10px;color: #fff;font-size: 16px;">
                <tr>
                    <td>图谱名称:</td>
                    <td><input type="text" name="graphname" style="width: 180px;color: #000;" id="graphname"></td>
                </tr>
                <tr>
                    <td>图谱描述:</td>
                    <td><textarea style="max-width: 180px;min-width: 180px;color: #000;" id="desc"></textarea></td>
                </tr>
            </table>
        </form>
        <div style="margin: 20px auto;width: 60%;">
            <button class="btn" id="postensurebtn" style="background-color: #e9bb1d;color: #fff;width: 25%;margin-right: 24%;">提交</button>
            <button class="btn" id="postunensurebtn" style="background-color: #9d2933;color: #fff;width: 25%;margin-left: 24%;">取消</button>
        </div>
    </div>
   
</div> 
<div class="container">  
    <p id="lastwarming" style="color: red;text-align: center;"></p>
    <div id="control" style="width: auto;margin-left: 15%;">
        <button class="btn btn-info" style="width: 60px;height: 60px;border-radius: 50%;font-size: 24px;background-color: #666;overflow: hidden;" id="add"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
        <button class="btn btn-info" id="newbtn" style="display: none;">添加</button>
        <button class="btn" id="delbtn" style="background-color: #9d2933;color: #fff;width: auto;display: none;">删除当前节点</button>
        <button class="btn" id="changebtn" style="background-color: #e9bb1d;color: #fff;display: none;">改</button>
        <button class="btn" id="postbtn" style="background-color: #e9bb1d;color: #fff;">提交</button>
    </div>   
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width:100%;height:600px;"></div>    
</div>


<!-- 新建目录及单节点 -->
<div id="rootdiv" style="position: fixed;right: 50px;top: 80px;width:300px;height: auto;background-color:#999;z-index: 999;border-radius: 10px;display:none;">
    <div class="container" style="width: 100%;">
        <div class="row">
            <div class="col-md-6" style="text-align: center;font-size: 18px;color: #fff;background-color:#3399CC;height: 36px;line-height: 36px;border-top-left-radius: 10px;cursor: pointer;" id="newnodebtn">
                新建节点
            </div>
            <div class="col-md-6" style="text-align: center;font-size: 18px;color: #fff;background-color:#545454;height: 36px;line-height: 36px;border-top-right-radius: 10px;cursor: pointer;" id="newcategorybtn">      
                新建目录
            </div>
        </div>
        <div id="newnodediv" style="width: 100%;height:auto;padding-top: 10px;">
            <form id="form1">
                <label for="chosecate">选择目录:</label>
                <select id="chosecate">
                </select><br>
                <b>节点名称:</b><input type="text" name="" placeholder="新建节点名称" style="height: 30px;border-radius: 5px;" id="newnode">
                <p id="nodewarming"></p>
            </form>
        </div>
        <div style="margin: 20px auto;text-align: center;" id="ensure3">
            <button id="addbtn3" class="btn btn-info" style="width: 60px;margin-right: 10px;">确定</button>
            <button class="btn btn-default" style="width: 60px;" onclick="rootdivhide();">关闭</button>
        </div>
        <div id="newcategorydiv" style="width: 100%;height:auto;text-align: center;padding-top: 30px;display: none;">
            <form id="form2">
                <input type="text" name="" placeholder="新建目录名称" style="height: 30px;border-radius: 5px;width: 80%;" id="newcategory">
                <p id="categorywarming"></p>
            </form>
        </div>
        <div style="margin: 20px auto;text-align: center;display: none;" id="ensure4">
            <button id="addbtn4" class="btn btn-info" style="width: 60px;margin-right: 10px;">确定</button>
            <button class="btn btn-default" style="width: 60px;" onclick="rootdivhide();">关闭</button>
        </div>
    </div>
</div>
<!-- 新建操作框 -->
<div id="selectdiv" style="position: fixed;right: 50px;top: 80px;width:300px;height: auto;background-color:#999;z-index: 999;border-radius: 10px;display: none;">
    <div class="container" style="width: 100%;">
        <div class="row">
            <div class="col-md-6" style="text-align: center;font-size: 18px;color: #fff;background-color:#3399CC;height: 36px;line-height: 36px;border-top-left-radius: 10px;cursor: pointer;" id="addnodebtn">
                添加关联节点
            </div>
            <div class="col-md-6" style="text-align: center;font-size: 18px;color: #fff;background-color:#545454;height: 36px;line-height: 36px;border-top-right-radius: 10px;cursor: pointer;" id="addlinksbtn">      
                添加次级连接
            </div>
        </div>
        <div id="newnodesdiv">
            <form id="form3">
                <p id="categories_"></p>
                <label for="categories">选择目录：</label>
                <select id="categories">
                </select><br>
                <p class="currnode_"></p>
                <b>节点名:</b>&nbsp<input type="text" name="nodename" id="newnodename"><br>
                <b>连接值:</b>&nbsp<input type="text" name="linkvalue1" id="linkvalue1">
                <p id="newnodenamewarming" style="text-align: center;color: red;"></p>
            </form>
        </div> 
        <div style="margin: 20px auto;text-align: center;" id="ensure1">
            <button id="addbtn1" class="btn btn-info" style="width: 60px;margin-right: 10px;">确定</button>
            <button class="btn btn-default" style="width: 60px;" onclick="selectdivhide();">关闭</button>
        </div>
        <div id="newlinkdiv" style="width: 100%;height:auto;padding-top: 20px;display: none;">
            <form id="form4">
                <p class="currnode_"></p>
                <label for="nodes">连接节点：</label>
                <select id="nodes">
                </select><br>
                <b>连接值:</b>&nbsp<input type="text" name="linkvalue2" id="linkvalue2" onfocus="newlinkwarminghide();">
                <p id="newlinkwarming" style="text-align: center;color: red;"></p>
            </form>
        </div>
        <div style="margin: 20px auto;text-align: center;display: none;" id="ensure2">
            <button id="addbtn2" class="btn btn-info" style="width: 60px;margin-right: 10px;">确定</button>
            <button class="btn btn-default" style="width: 60px;" onclick="selectdivhide();">关闭</button>
        </div>
        
    </div>    
</div>
<!-- 更改操作框 -->
<div id="changediv"  style="position: fixed;right: 50px;top: 80px;width:300px;height: auto;background-color:#999;z-index: 999;border-radius: 10px;display:none; ;">
    <div class="container" style="width: 100%;">
        <div class="row">
            <div class="col-md-6" style="text-align: center;font-size: 18px;color: #fff;background-color:#3399CC;height: 36px;line-height: 36px;border-top-left-radius: 10px;cursor: pointer;" id="changenodebtn">      修改节点
            </div>
            <div class="col-md-6" style="text-align: center;font-size: 18px;color: #fff;background-color:#545454;height: 36px;line-height: 36px;border-top-right-radius: 10px;cursor: pointer;" id="changelinksbtn">      
                新增连接
            </div>
        </div>
        <div style="width: 90%;height: auto;margin: 10px auto;font-size: 16px;font-family: '微软雅黑';" id="chnodediv">
            <form id="form5">
                <p class="currnode"></p>
                更改名称:<input type="text" name="changename" id="changename" style="height: 30px;border-radius: 5px;width: 60%;"><br><br>
                <p  id="currlinks"></p>
                删除子连接:<select id="dellinks">
                            </select><br>
                
            </form>
        </div>
        <div style="margin: 20px auto;text-align: center;" id="changebtndiv1">
            <button class="btn btn-info" style="width: 60px;margin-right: 10px;" id="change-ensure1">确定</button>
            <button class="btn btn-default" style="width: 60px;" onclick="changedivhide();">关闭</button>
        </div>
        <div style="width: 90%;height: auto;margin: 10px auto;font-size: 16px;font-family: '微软雅黑';display: none;" id="chlinkdiv">
            <form id="form6">
                <p class="currnode"></p>
                新连接:&nbsp<select id="newlinks" style="margin-top: 10px;margin-bottom: 10px;">
                            </select><br>
                连接值:&nbsp<input type="text" name="linkvalue3" id="linkvalue3">
            </form>
        </div>
        <div style="margin: 20px auto;text-align: center;display: none;" id="changebtndiv2">
            <button class="btn btn-info" style="width: 60px;margin-right: 10px;" id="change-ensure2">确定</button>
            <button class="btn btn-default" style="width: 60px;" onclick="changedivhide();">关闭</button>
        </div>
    </div>
    
</div>


<div class="right-drawer drawer">
    <div class="container" id="schmsg-tab">
        <div class="row">
            <div id="schmsg" style=" overflow:auto;" style="width:200px;height: 500px;background-color: #333333;">
               <%@ include  file="operation.jsp"%>
                   
	        </div>
	    </div>              
	</div>
</div>
<button class="toggle-btn toggle-btn-right"></button>

<!-- 导入js文件，echarts.min.js要先于control.js文件导入 ，且只能在后面导入，在header导入无效-->
<script src="js/echarts.min.js"></script>
<script type="text/javascript" src="js/example.js"></script>
<script type="text/javascript" src="js/add.js"></script>
<script type="text/javascript" src="js/control.js"></script>


<!-- 注册tab-->
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
    $('#postbtn').click(function () {
        // body...
        $('#postdiv').fadeIn(600);
        $('#desc').text(" ");
        $('#graphname').val("");   
        });
        
        $('#postunensurebtn').unbind("click").bind("click",function(){
            // body...
            $('#postdiv').fadeOut(600);
            $('#desc').text(" ");
            $('#graphname').val("");
        });


         $('#postensurebtn').unbind("click").bind("click",function(){
                // body...
                      var option = myChart.getOption();
                	  var categories = JSON.stringify(option.series[0].categories);
                	  var nodesOption = JSON.stringify(option.series[0].nodes);
                	  var linksOption = JSON.stringify(option.series[0].links);
                	    $.ajax({
                	            type : "POST",  //请求方式
                	            url : "/createGrapgSave",  //请求路径
                	            data : {  
                	                "categories":categories,
                	                "nodesOption":nodesOption,
                	                "linksOption":linksOption,
                	                "title":$('#graphname').val(),
                	                "content":$('#desc').val(),
                	       			"id":0
                	            },
                	            success : function(msg) {  //异步请求成功执行的回调函数
                	                if(msg){
                	                	 alert('提交成功!');
                	                    window.location.href="/graphList";
                	                }
                	            },//ajax引擎一般用不到；状态信息；抛出的异常信息
                	            dataType: "json",
                	            error : function(XMLHttpRequest, textStatus, errorThrown) {
                	                alert(textStatus);
                	                alert("失败了"+errorThrown)
                	            }
                	        }); 
                	$('#postdiv').fadeOut(600);             
            });     
 

    </script>
    
</body>
</html>