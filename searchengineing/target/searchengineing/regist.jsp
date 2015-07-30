<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>注册用户</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 引入 Bootstrap -->
<link
	href="<%=request.getContextPath()%>/resource/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/resource/img/1.ico" />
<script
	src="<%=request.getContextPath()%>/resource/jquery/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resource/bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
.header {
	height: 40px;
	border: solid 1px;
	border-color: gray;
	margin-left: auto;
	margin-right: auto;
}

.inner_header_before {
	height: 20px;
	margin-left: 15%;
	margin-top: 14px;
}

.inner_header_after {
	height: 20px;
	margin-left: 64%;
	margin-top: 14px;
}
.regist_form {
	margin-top: 20px;
	margin-left: -124px;
}
.regist_bt {
	width: 320px;
	margin-left: 247px;
	height: 31px;
	background-color: #3f89ec;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="header">
		<span class="inner_header_before">注册用户</span><span
			class="inner_header_after"><a style="cursor: pointer;">已经注册</a></span>
	</div>
	<div class="regist_form" id="regist_form">
		<form class="form-horizontal">
		<div class="form-group form-group-sm">
			<label class="col-sm-2 control-label" for="formGroupInputSmall">邮箱</label>
			<div class="col-sm-3">
				<input class="form-control" type="text" id="mail"
					placeholder="email">
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-sm-2 control-label" for="formGroupInputSmall">密码</label>
			<div class="col-sm-3">
				<input class="form-control" type="password" id="password"
					placeholder="密码" >
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-sm-2 control-label" for="formGroupInputSmall"></label>
			<div class="regist_bt" align="center" id="regist_bt"><span style="margin-top: 20px">注册</span></div>
			
		</div>
	</form>
	
	</div>

	<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	<script src="resource/jquery/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#regist_bt").on("click",function(obj){
				var data = {}
				var email = $("#mail").val()
				var password = $("#password").val()
				data.email = email
				data.password = password
				var url = 'mail.'+email.split('.')[0].split('@')[1]+'.com'
				$.post("<%=request.getContextPath()%>/accounts/regist", data, function(rdata) {
					if(rdata.status=='200'){
						console.log(url)
						$("#regist_form").css("display","none")
						var ht = '<a href=http://'+url+'>'+url+'</a>'
						console.log(ht)
						$("#regist_form").after("注册成功,立即激活： "+ht)
					}
				})
			
			})
		});
	</script>
	<!-- 包括所有已编译的插件 -->
	<script src="resource/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>