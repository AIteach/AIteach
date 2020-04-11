<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="right-drawer drawer">
    <div class="container" id="schmsg-tab">
        <div class="row">
           
            <div id="schmsg" style="width: 100%;height: 750px;background-color: #333333;">
                <div style="padding-left: 20px;width: auto;font-size: 18px;">
                    <span><img src="img/water.png"></span><span style="color: #66CCFF;font-family: '微软雅黑';">学习者报表</span>
                </div>
                <div style="height: 202px;width: 100%;margin-top: 3px;border-top: 2px solid #66CCFF;border-bottom: 2px solid #66CCFF;">
                    <div class="img-introduce" style="height: 196px;width: 39%;float: left;overflow: auto;word-wrap: break-word;padding: 5px;">
                    	<h3>${kgNode[0].kgName}</h3>
                    	<p style="font-size: 14px;font-family: '微软雅黑';">
                    		描述：${kgNode[0].kgDesc}
                    	</p>
                    </div>

                    <div class="text-introduce" style="height: 200px;width: 60%;float: right;border-left: 1px dotted #66CCFF;">
                        <div style="width: 95%;height: 160px;margin: 20px auto;background-color: #666;" id="entitymsg">
	                        	<div style="height: 140px;width:auto;text-align: right;float: left;margin:20px 0px;">
	                        		<table style="text-align: right;height: 120px;">
	                        			<tr>
	                        				<td style="padding-left: 10px;">学生名</td>
	                        				<td style="width: 10px;"></td>
	                        				<td style="border-bottom: 1px solid #ccc;height: 20px;margin-left: 10px;width: 50px;text-align: center;">
	                        					${sessionScope.currentUser.name}
	                        				</td>
	                        			</tr>
	                        			<tr>
                                            <td style="padding-left: 10px;">子知识数</td>
                                            <td style="width: 10px;"></td>
                                            <td style="border-bottom: 1px solid #ccc;height: 20px;margin-left: 10px;width: 50px;text-align: center;">
                                                ${kgNode[0].linkNum}
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
                                                <fmt:formatDate value="${kgNode[0].creatTime}" />  
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
                                    <c:forEach items="${kgSonNodes}" var="kgSon">
                                    <tr>
                                        <td>${kgSon.kgName}</td>
                                        <td>${kgSon.createrId}</td>
                                        <td width="100px"><a href="/kgDownLoad?kgId=${kgSon.kgId}"><img src="img/下载.png"></a>
	                                        <a href="/sourcePreview?kgId=${kgSon.kgId}"><img src="img/查看.png"></a>
	                                        <a href="/kgDelete?kgId=${kgSon.kgId}"><img src="img/删除.png"></a>
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
	                    		<button onclick="javascript:window.location.href='/kgAddKg?kgId=${kgNode[0].kgId}&courseId=${kgNode[0].courseId}'" class="btn btn-info" style="margin: 5px 0px;" >添加知识元</button>
	                    		<button onclick="javascript:window.location.href='/kgEdit?kgId=${kgNode[0].kgId}&kgName=${kgNode[0].kgName}'" class="btn btn-info" style="margin: 5px 0px;">编辑知识元</button>
	                    		<button  class="btn btn-info" > <a onclick="return confirm('确定删除当前数据？')" href="/kgDelete?kgId=${kgNode[0].kgId}&kgName=${kgNode[0].kgName}">删除该知识</a></button>
	                    		<button onclick="javascript:window.location.href='/kgLinkAdd?kgId=${kgNode[0].kgId}&kgName=${kgNode[0].kgName}'" class="btn btn-info" style="margin: 5px 0px;">添加链接</button>
	                    		<button onclick="javascript:window.location.href='/sourceAdd?kgId=${kgNode[0].kgId}&kgName=${kgNode[0].kgName}'" class="btn btn-info" style="margin: 5px 0px;">添加资源元</button>
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