<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.btbox {
	border: solid 1px;
	width:90px;
	
}
.title {
	border: solid 1px #ddd;
	width:100%;
	heigth:30%;
}
</style>
<link href="<%=request.getContextPath()%>/resource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Home</title>
</head>
<body>
	<div class="title">Home</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-1">
				<div class="btbox">
					<div>
						<button class="btn btn-default">用户管理</button>
					</div>
					<div>
						<button class="btn btn-default">爬虫管理</button>
					</div>
					<div>
						<button class="btn btn-default">用户管理</button>
					</div>
					<div>
						<button class="btn btn-default">爬虫管理</button>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<table class="table">
					<tr>
						<td>id</td>
						<td>姓名</td>
						<td>性别</td>
						<td>爱好</td>
						<td>作为</td>
						<td>操作</td>
					</tr>
					<tr>
						<td>a</td>
						<td>a</td>
						<td>a</td>
						<td>a</td>
						<td>a</td>
						<td>a</td>
					</tr>
				</table>
			</div>
		</div>
	</div>

	<script src="<%=request.getContextPath()%>/resource/jquery/jquery.min.js"></script>
	<script	src="<%=request.getContextPath()%>/resource/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>