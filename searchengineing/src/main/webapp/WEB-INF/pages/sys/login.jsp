<%@ page language="java" contentType="text/html"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<title>登陆</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/resource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/resource/img/1.ico" />

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
<script src="<%=request.getContextPath()%>/resource/jquery/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-6 column"></div>
			<div class="col-md-6 column">
				<div class="row clearfix">
					<div class="col-md-6 column">
						<br> <br> <br> <br> <br> <br> <br>
						<br> <br> <br> <br> <br> <br> <br>
						<div class="form-group">
							<label for="exampleInputEmail1">Email address</label><input
								class="form-control" id="exampleInputEmail1" type="email" />
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Password</label><input
								class="form-control" type="password" id="loginpwd" />
							<div id="capslock" class="ihide">
								<i></i><s></s>键盘大写锁定已打开，请注意大小写!
							</div>
						</div>
						<button type="submit" class="btn btn-lg btn-primary btn-block"
							onclick="login()">login</button>
					</div>
					<div class="col-md-6 column"></div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	  
	  function login(){
		  var a  = $("#loginpwd").val();
			alert(a);
			var url = "<%=request.getContextPath()%>/sys/login"
		  $.ajax({ 
			  url:url, 
			  type :"POST", 
			  success: function(data){
		        if(data==="yes"){
		        	location.href ="<%=request.getContextPath()%>/index.jsp";
		        }
		      }});
	  }
		$('#loginpwd')[0].onkeypress = function(event){
			var e = event||window.event,
			$tip = $('#capslock'),
			kc  =  e.keyCode||e.which, // 按键的keyCode
			isShift  =  e.shiftKey ||(kc  ==   16 ) || false ; // shift键是否按住
			if (((kc >=65&&kc<=90)&&!isShift)|| ((kc >=97&&kc<=122)&&isShift)){
				$tip.show();
			}
			else{
				$tip.hide();
			}
		};
    </script>
</body>
</html>