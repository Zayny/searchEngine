<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登陆</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="resource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="shortcut icon" href="resource/img/1.ico" />

<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->


<style type="text/css">
.ihide {
	display: none;
	color: red;
}
</style>

</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-6 column"></div>
			<div class="col-md-6 column">
				<div class="row clearfix">
					<div class="col-md-6 column" style="border: border:1px solid #000">
						<br> <br> <br> <br> <br> <br> <br>
						<br> <br> <br> <br> <br> <br> <br>
						<form action="<%=request.getContextPath()%>/sys" id="loginForm" method="POST">
						<div
							style="border: 1px solid #8B98A9; width: 350px; height: 300px; margin-top: -20px;">
							<div style="margin-top:10%; margin-left:10%; width: 260px; height: 200px; z-index:2px">
								<div class="form-group">
									<label for="exampleInputEmail1">Email address</label><input
										class="form-control" id="loginemail" type="email" name="name"  />
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Password</label><input
										class="form-control" type="password" id="loginpwd" name="pwd" />
									<div id="capslock" class="ihide">
										<i></i><s></s>键盘大写锁定已打开，请注意大小写!
									</div>
									<div id="loginerror" class="ihide">
										<i></i><s></s>用户名密码错误!
									</div>
								</div>
								<input type="submit" class="btn btn-lg btn-primary btn-block"
									onclick="login()" value="login" />
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="resource/jquery/jquery.min.js"></script>
	<script src="resource/bootstrap/js/bootstrap.min.js"></script>

	<script type="text/javascript">
	  
	  function login(){
		  var data={};
		  var pwd  = $("#loginpwd").val();
		  var name = $("#loginemail").val();
		  data['name']=name;
		  data['pwd']=pwd;
<%-- 		  $.post("<%=request.getContextPath()%>/sys",data,function(data){ --%>
// 			  if(data=='false'){
// 				  $('#loginerror').show();
// 			  }else{
<%-- 				  window.location='<%=request.getContextPath()%>/sys/login' --%>
// 			  }
// 		  })
			$("loginForm").submit();
		}
		$('#loginpwd')[0].onkeypress = function(event) {
			var e = event || window.event, $tip = $('#capslock'), kc = e.keyCode
					|| e.which, // 按键的keyCode
			isShift = e.shiftKey || (kc == 16) || false; // shift键是否按住
			if (((kc >= 65 && kc <= 90) && !isShift)
					|| ((kc >= 97 && kc <= 122) && isShift)) {
				$tip.show();
			} else {
				$tip.hide();
			}
		};
	</script>
</body>
</html>