<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="css/right_include.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
<script src="js/vue.js"></script>
<script src="js/bingtu.js"></script>
<script src="js/lidaotu.js"></script>
<script src="js/jquery.min.js"></script>
<script src="layer/layer.js"></script>
<!-- 保存当前节点 -->
<input type="hidden" id="hidden" value="${nodeid}"/>
<input type="hidden" id="classhidden" value="${course.courseName}"/>

<div class="right-drawer drawer" id="schmsg_tab">
    <div v-show="show">
        <!-- <div class="Row" style="width: 100%">-->
        <div>
            <div :class="{show_orange:show_color[0],show_black:!show_color[0]}"
                 class="col-md-6 schmsg-tab" @click.stop="show_div(1)">基本信息
            </div>
            <div :class="{show_orange:show_color[1],show_black:!show_color[1]}"
                 class="col-md-6 schmsg-tab" @click.stop="show_div(2)">发帖评论区
            </div>
            <div :class="{show_orange:show_color[2],show_black:!show_color[2]}"
                 class="col-md-6 schmsg-tab" @click.stop="show_div(3)">数据可视化
            </div>
        </div>
        <!--基本信息区html开始-->
        <div v-show="show_color[0]" id="schmsg"
             style="width: 100%; height: 750px; background-color: #333333;">
            <div class="right-div">
                <span><img src="img/water.png"></span> <span
                    style="color: #66CCFF; font-family: '微软雅黑';">课程信息</span>
            </div>
            <div class="texta">
                <div class="img-introduce"
                     style="height: 296px; width: 60%; float: left; border-right: 1px dotted #66CCFF; overflow: auto;">
                    <img src="img/c.gif"
                         style="width: 80px; height: 80px; padding-left: 25px; padding-top: 25px; float: left;">
                    <div style="float: right; padding-right: 10px; padding-top: 25px;">
                        <p style="font-size: 18px; height: 18px;">${courseNode[0].courseName}</p>
                        <p style="font-size: 15px; height: 15px;">
                            教师：<span> <a href="#">${courseNode[0].createrName}</a></span>
                        </p>
                        <p style="font-size: 12px; height: 12px;">
                            创建时间：<span>${courseNode[0].creatTime}</span>
                        </p>
                        <button type="button" class="btn btn-default"
                                style="margin-top: 10px; width: 100px; background-color: #FFCC00; color: #fff;">数据申请
                        </button>
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
                                href="/kgAdd?courseId=${courseNode[0].courseId}&courseName=${courseNode[0].courseName}">+</a>
                        </li>
                        <li><a onclick="return confirm('确定删除当前数据？')"
                               href="/courseDelete?courseId=${courseNode[0].courseId}&courseName=${courseNode[0].courseName}">-</a>
                        </li>
                        <li><a
                                href="/courseEdit?courseId=${courseNode[0].courseId}&courseName=${courseNode[0].courseName}">edit</a>
                        </li>
                    </ul>
                    <div
                            style="background-color: #66CCFF; height: 2px; width: 100%; clear: both;"></div>
                    <div style="padding: 15px; overflow: auto; height: 360px;">
                        <p>课程推荐</p>

                        <ul v-if="classList.code==200&&classList.count>0"  class="record">
                            <li v-for="val in classList.classList">
                                <a class="classList"  :href="val.courseUrl" >{{val.courseName}}</a></li>
                        </ul>
                        <div v-else>暂时没有数据！</div>
                    </div>
                </div>
                <!--力导图和饼图-->
                <div id="pic1">
                    <div id="main1" style="width: 250px; height: 250px;"></div>

                    <div id="main2"
                         style="width: 250px; height: 280px; display:; float: right; top: 10px"></div>

                </div>

            </div>
        </div>
        <!--基本信息区html结束-->

        <!--评论区html开始-->
        <div v-show="show_color[1]" id="comment_table" class="comment-div">
            <div class="right-div">
                <span><img src="img/water.png"></span><span
                    style="color: #66CCFF; font-family: '微软雅黑';">所有评论</span>
            </div>
            <div class="textArea" style="position: relative">
                <button @click.stop="setCommentData(1,-1,-1)" type="button"
                        class="layui-btn layui-btn-lg layui-btn-fluid">发表评论
                </button>
                <div class="commentArea" id="textArea">
                    <ul class="jieda" id="jieda">
                        <!-- 此处为单条评论代码-->
                        <li v-for="val in allComments" data-id="111" class="jieda-daan">
                            <div class="detail-about detail-about-reply"
                                 style="height: 45px;">
                                <a class="fly-avatar"
                                   @click.stop="submit(val.comment.id)"><img src="pic/1.jpg"
                                                                             alt=" "> </a>
                                <div class="fly-detail-user" style="height: 65px">
                                    <a class="fly-link"> <cite>{{val.username}}</cite></a>
                                </div>
                                <div class="detail-hits" style="margin-top: -45px">
                                    <span> {{val.comment.commentContent}}</span><br>
                                    <ul>
                                        <li><span style="margin-right: 20px">{{val.comment.ctime|dateFormat}}</span>
                                            <span class="jieda-zan" type="zan" style="margin-right: 5px">
												<a :class="{'icon-afterzan':comment_click[val.comment.id]}"
                                                   class="iconfont icon-zan "
                                                   @click.stop="clickZan(1,comment_click[val.comment.id],login_sign,[val.comment.id])"></a>
												<em> {{val.comment.likenum}}</em>

										</span> <span type="reply"> <a
                                                    class="iconfont icon-svgmoban53"
                                                    @click.stop="setCommentData(2,[val.comment.id],[val.comment.userid])"></a>
										</span></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="comment">
                                <ul>
                                    <li v-for="newval in val.commentresponse"><span> <i
                                            style="color: aqua;">{{newval.username}}</i><i
                                            style="color: rgb(218, 102, 7)">回复</i><i style="color: aqua">{{newval.rusername}}</i>
											<i style="color: rgb(218, 102, 7)">:{{newval.commentResponse.content}}</i></span>
                                        <span class="jieda-zan" type="zan" style="margin-right: 5px">
											<a
                                                    :class="{'icon-afterzan':response_click[newval.commentResponse.id]}"
                                                    class="iconfont icon-zan"
                                                    @click.stop="clickZan(2,response_click[newval.commentResponse.id],login_sign,[newval.commentResponse.id])"></a><em>{{newval.commentResponse.likenum}}
										</em>
									</span> <span type="reply"><a
                                                @click.stop="setCommentData(2,[val.comment.id],[newval.commentResponse.userId])"
                                                class="iconfont icon-svgmoban53"></a> </span></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                    <!-- 回复评论部分  -->
                    <div :class="{'comment-show':!comment_show}"
                         class="layui-from from-relative">
                        <div class="layui-form-item layui-form-text">
                            <div class="layui-input-block comment-text">
								<textarea name="desc" placeholder="请输入内容"
                                          v-model:value="comment_data.comment_data"
                                          class="layui-textarea text-color"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block comment-text">
                                <button @click.stop="sendComment" class="layui-btn" lay-submit
                                        lay-filter="formDemo">立即提交
                                </button>
                                <button @click.stop="resetComment" type="reset"
                                        class="layui-btn layui-btn-primary comment-reset">重置
                                </button>
                                <button @click.stop="cancelComment" type="cancel"
                                        class="layui-btn layui-btn-primary comment-cancel">取消
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- 回复评论部分  -->
                </div>
            </div>
        </div>

        <!--评论区html结束-->

        <!--数据可视化区html开始-->
        <div v-show="show_color[2]" id="datamsg"
             style="width: 100%; height: 750px; background-color: #333333; display: none;">

            <!--数据化及其下面的一条线-->

            <div class="right-div">
                <span><img src="img/water.png"></span><span
                    style="color: #66CCFF; font-family: '微软雅黑';">数据可视化</span>
            </div>
            <!-- 	<div
                style="height: 38px; width: 100%; margin-top: 3px; border-top: 2px solid #66CCFF;">
            </div>
 -->

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

                    <div
                            style="height: 400px; background-color: blueviolet; width: 118px; display: inline-block; margin-left: 10px; float:; margin-left: 0px">

                        <div
                                style="float: left; background-color:; margin-left: 1px; width: 20px; height: 20px">
                            <form>
                                <fieldset style="width: 80px; height: 10px">
                                    <legend
                                            style="font-size: 10px; color: aliceblue; border-color: aliceblue">该课程在线资源
                                    </legend>
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

                    </div>
                </div>
            </div>
        </div>
        <!--数据可视化区html结束-->
    </div>
    <button :class="{'btn-right-temp':!show}"
            class="toggle-btn toggle-btn-right" @click.stop="changeShow"></button>
</div>
<script src="js/right_include.js"></script>
<script src="js/shujukeshihua-shangxingtu.js"></script>
<script src="js/shujukeshihua-zhextu.js"></script>

<script>
    $("i.iconfont.icon-zan").click(function () {
        $(this).css("color", "red");
    });

    $(document).ready(function () {

        $('body').on("click", 'i.iconfont.icon-zan', function () {

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

<script>
    $("span[type='reply']").click(function () {
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

<!-- 注册tab -->
<!-- <script>
$('#self').click(function() {
$('#selfregister').css("display", "block");
$('#comregister').css('display', 'none');
});
$('#school').click(function() {
$('#selfregister').css("display", "none");
$('#comregister').css('display', 'block');
});
</script> -->

<!-- 侧栏显示，隐藏 -->


<script>
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
</script>

