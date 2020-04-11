<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 

 
 
 <div>
        <div class="left-drawer drawer">
            <!-- 收起/展开按钮 -->
            <div id="search"
                style="background-color: #111114; text-align: center; padding:5px;font-size:16px;">
              		 该成员的图谱
                <div class="row" style="margin-bottom: 5px;">
                    <div class="col-md-10 col-md-offset-1">
                    </div>
                </div>
            </div>

            <div id="leftli">
           
           		<div id="myCreatCourse" >
           				<a  target="right"  style="font-size:25px;" href="/OtherCreatGraph?memberId=${param.memberId}" >他的课程图谱</a>
           		</div>
           		<div id="myJoinCourse">
           				<a target="right"  style="font-size:25px;color:white;" href="/otherJoinGraph?memberId=${param.memberId}">他参与的课程图谱</a>
           		</div>
           		<div id="myLearnerCourse" >
           			<a target="right"  style="font-size:25px;color:white;" href="/searchGraph?memberId=${param.memberId}">他创作的图谱</a>
           		</div>
       		 </div> 
        </div>
        <div>
            <button class="toggle-btn toggle-btn-left"></button>
        </div>
    </div>