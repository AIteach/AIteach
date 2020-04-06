<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="search"
	style="background-color: #111114; text-align: center; padding: 2px;">
	<p>
		<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbspweb
		图谱搜索
	</p>
	<div class="row" style="margin-bottom: 5px;">
		<div class="col-md-10 col-md-offset-1">
			<form action="/search" method="POST">
				<div class="input-group">
					<input type="text" name="searchContent" class="form-control"
						placeholder="Search for..."> <span class="input-group-btn">
						<input class="btn btn-default" type="submit" value="搜索"><span
						class="glyphicon glyphicon-search" aria-hidden="true"></span>
					</span>
				</div>
			</form>
		</div>
	</div>
</div>
