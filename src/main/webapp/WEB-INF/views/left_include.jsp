
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div>
	<div class="left-drawer drawer">
		<!-- 收起/展开按钮 -->
		<%@ include file="search.jsp"%>
		<div id="leftli">
			<ul class="firstul">
				<li class="firstli" id="sch"><span
					class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp<span
					class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp&nbsp图谱类型
				</li>
				<div class="secmenu" id="sch-secmenu">
					<ul>
						<c:forEach var="school" items="${schoolNode}">
							<li><span class="glyphicon glyphicon-plus"
								aria-hidden="true"></span>&nbsp<span
								class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp&nbsp${school.schoolName}</li>
						</c:forEach>
					</ul>
				</div>
				<li class="firstli" id="lesson"><span
					class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp<span
					class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp&nbsp朝代
				</li>
				<div class="secmenu" id="les-secmenu">
					<ul>
						<c:forEach var="course" items="${courseNode}">
							<li><span class="glyphicon glyphicon-plus"
								aria-hidden="true"></span>&nbsp<span
								class="glyphicon glyphicon-folder-open" aria-hidden="true"></span><a
								style="font-color: white" href="${course.courseId}">&nbsp&nbsp${course.courseName}</a></li>
						</c:forEach>
					</ul>
				</div>
			</ul>
		</div>
	</div>
	<div>
		<button class="toggle-btn toggle-btn-left"></button>
	</div>
	<script>
		$('.toggle-btn-left').click(function() {
			// body...        
			$('.left-drawer').toggle();
			$('.toggle-btn-left').toggleClass('btn-left-temp');
		});
	</script>
</div>

