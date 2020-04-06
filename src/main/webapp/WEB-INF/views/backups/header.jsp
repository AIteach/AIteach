<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="nav">
    <div id="img" style="float: left;">
        <img src="img/logo.png" style="margin-left: 10px;margin-top: 10px;">
    </div>
    <div id="navli">
        <ul>
            <li id="li-mylesson"><a href="/graphList">我的图谱</a></li>
            <li id="li-mylesson"><a href="/createrGraph?memberId=${sessionScope.currentUser.id}">课程图谱</a></li>
            <li id="li-mylesson"><a href="/index">首页</a></li>
            <li id="li-login">
            <c:if var="result" test="${empty sessionScope.currentUser}"><a href="/login">登陆</a></c:if>
            <c:if test="${!result}"><a href="/exit">退出登陆</a></c:if>
            </li>
        </ul>
    </div>
</div> 