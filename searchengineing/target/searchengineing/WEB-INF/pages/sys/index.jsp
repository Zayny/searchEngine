<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.layout {
	width:100%;
	height:auto;
	margin:0;
	padding:0;
}
.layout-up{
	width:100%;
	height:40px;
	border:solid 1px #ccc;
	background-color: #11728E;
}
.nav {
	border: solid 1px #ddd;
	width:10%;
	margin-left:5px;
	float: left;
	background-color: #FFFFFF;
}
.main{
	width:80%;
	float:left;
	margin-left: 45px;
}
.layout-down{
	width:100%;
	heigth:auto;
	margin-top: 10px;
}
.navbox{
	margin-left: 15%;
}
.sysInfo{
	margin-left: 95%;
	margin-top:1%;
	font-family: fantasy;
}
</style>
<link href="<%=request.getContextPath()%>/resource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Home</title>
</head>
<body>
	<div class="layout">
		<div class="layout-up">
			<a class="sysInfo" id="goback">退出</a>
		</div>
		<div class="layout-down">
			<div class="nav">
				<div class="navbox">
					<button class="btn btn-default" id="sys">系统管理</button>
					<button class="btn btn-default" id="spider">爬虫管理</button>
					<button class="btn btn-default" id="user">用户管理</button>
<!-- 					<button class="btn btn-default">系统管理</button> -->
<!-- 					<button class="btn btn-default">系统管理</button> -->
<!-- 					<button class="btn btn-default">系统管理</button> -->
<!-- 					<button class="btn btn-default">系统管理</button> -->
<!-- 					<button class="btn btn-default">系统管理</button> -->
				</div>
			</div>
			<div class="main" id="main">
				<table class="table table table-bordered">
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
						<td><a>删除</a>|<a>详情</a></td>
					</tr>
					<tr>
						<td>a</td>
						<td>a</td>
						<td>a</td>
						<td>a</td>
						<td>a</td>
						<td><a>删除</a>|<a>详情</a></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	
	<script src="<%=request.getContextPath()%>/resource/jquery/jquery.min.js"></script>
	<script	src="<%=request.getContextPath()%>/resource/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$("#spider").click(function(){
			var $t = $(this);
			$t.attr("class", "btn btn-primary")
			$.get("<%=request.getContextPath()%>/climbs",function(table){
				$("#main").html(table);
				$('#suri').focus();
			})
		})
		$("#goback").click(function(){
			if(confirm("确认退出?"))
			window.location="/tail/login.jsp";
		})
	</script>
</body>
</html>