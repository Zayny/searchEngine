<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="resource/img/1.ico" />
<title>TAILING</title>
<style type="text/css">
#kw {
	margin: 0;
	width: 521px;
	height: 40px;
	padding: 9px 7px;
	padding: 11px 7px 7px\9;
	font: 16px arial;
	border: 1px solid #d8d8d8;
	border-bottom: 1px solid #ccc;
	vertical-align: top;
	outline: none;
	position: relative;
	float: left;
}

#h_kw {
	margin: 0;
	width: 421px;
	height: 30px;
	padding: 9px 7px;
	padding: 11px 7px 7px\9;
	font: 16px arial;
	border: 1px solid #d8d8d8;
	border-bottom: 1px solid #ccc;
	vertical-align: top;
	outline: none;
	position: relative;
	float: left;
}

.s_btn {
	cursor: pointer;
	width: 102px;
	height: 30px;
	line-height: 30px;
	padding: 0;
	border: 0;
	background-color: #38f;
	font-size: 16px;
	color: white;
	position: relative;
	float: left;
}

#kw_box {
	margin-left: 10px;
	margin-top:-8px;
	position: relative;
	float: left;
}

#key_word {
	width: 500px;
	height: 150px;
	margin: 0 auto;
	position: relative;
	float: left;
}

a {
	text-decoration: none;
	color: -webkit-link;
}

li {
	list-style: none;
}

.box {
	margin-top: 10%;
	margin-left: 25%;
}

#nv a {
	color: #00C;
	text-decoration: underline;
	font-size: 14px;
	margin-left: 19px;
}

#nv b {
	font-size: 14px;
	margin-left: 19px;
}

.head {
	display: none;
	margin-top: -18px;
	margin-left: -8px;
	width: 105%;
	height: 42px;
	border: 1px solid #d8d8d8;
	padding-top: 10px;
}

#resmsg {
	margin-left: 30px;
	margin-top: 30px;
}

#imagebox {
	margin-left: 10px;
	position: relative;
	float: left;
}

.per {
	margin-top: 10px;
}

.name {
	font-size: 17px;
	font: oblique;
}

.contents {
	font-size: 13px;
	width: 700px;
	letter-spacing: 0.5px;
	line-height: 1.54;
}

.st {
	height: 40px;
}
</style>
<link
	href="<%=request.getContextPath()%>/resource/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/resource/img/1.ico" />
<script
	src="<%=request.getContextPath()%>/resource/jquery/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resource/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
// 		拓展jquery插件
		$.extend({
			  getUrlVars: function(){
			    var vars = [], hash;
			    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
			    for(var i = 0; i < hashes.length; i++)
			    {
			      hash = hashes[i].split('=');
			      vars.push(hash[0]);
			      vars[hash[0]] = hash[1];
			    }
			    return vars;
			  },
			  getUrlVar: function(name){
			    return $.getUrlVars()[name];
			  }
			});
// 		拓展jquery插件 end

		var code = $.getUrlVar('code');
			if(code==8421){
				console.log(code)
				$("#acc_login").click();
			}
		var login = $.getUrlVar('login');
		if(login==1){
			alert($.getUrlVar('email'))
		}
		$("#kw").focus();
		$("#kw").css("border-color","#38f")
		
		$("#h_kw").focus();
		$("#h_kw").css("border-color","#38f")
		
		$("#kw").on('focus',function(){
			$("#kw").css("border-color","#38f")
		});
		
		$("#kw").on('blur',function(){
			$("#kw").css("border-color","")
		});
		$("#h_kw").on('focus',function(){
			$("#h_kw").css("border-color","#38f")
		});
		
		$("#h_kw").on('blur',function(){
			$("#h_kw").css("border-color","")
		});
		
		function search(event){
			var code = event.keyCode;
			if(typeof(code) =='undefined'){
				code = 13;
			}
			if (code==13&& ($("#kw").val().trim()||$("#h_kw").val().trim())) {
				if(!$("#h_kw").val()){
					$("#h_kw").val($("#kw").val());
					$("#kw").val("");
				}
				$("#bd").css("display", "none");
				$("#hd").css("display", "block"); 
// 				$("#hd").css("position","fixed");
				$("#h_kw").focus();
				var data={};
				data.keyWord = $("#kw").val()|| $("#h_kw").val()
				console.log(data.keyWord)
				$.get("<%=request.getContextPath()%>/search/getmsg",data,function(rdata){
					console.log(rdata.data)
					var data  = rdata.data
					var ht=''
					for(var i=0 ;i<data.length;i++){
						ht+='<li><div class="per name"><a href='+data[i].uri+' target="_blank">'+data[i].name+'</a></div>'+'<div class="per contents">'+data[i].shortContent+'</div>'
						+'</div></li>'
					}
					$("#resmsg").text('');
					$("#resmsg").append(ht);
				})
			}
			
			
		}
	
		$("#kw").keyup(function(event) {
				search(event)
		})
		
		$("#h_kw").keyup(function(event) {
				search(event)
		})
		
		$('#su').on('click',function(event){
			search(event)
		})

		
// 			$("#kw").keyup(function(event) {
// 				var code = event.keyCode;
// 				var ischar = code<90&&code>65;
// 				if (ischar) {
// 					console.log($("#kw").val());
// 					$("#h_kw").val($("#kw").val());
// 					$("#bd").css("display", "none");
// 					$("#hd").css("display", "block"); 
// 					$("#h_kw").focus();
// 				}
// 			})
			
		$("#login_real").click(function(){
			var email = $("#ac_email").val()
			var password = $("#ac_password").val()
			var data={}
			data.email=email
			data.password = password
			$.post("<%=request.getContextPath()%>/accounts/login",data,function(rdata){
				if(rdata.status=='200'&&rdata.data=='1'){
					console.log(rdata)
					var ht='<a href="#">'+data.email+'</a>'
						$("#before_login").html(ht)
						$("#close_from").click()
				}else{
					
				}
			})
		})
	})
</script>
</head>
<body>

	<div class="modal fade bs-example-modal-sm" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="close_from">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">登陆TAIL</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="recipient-name" class="control-label">email:</label>
							<input type="text" class="form-control" id="ac_email">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">password:</label>
							<input type="password" class="form-control password" id="ac_password">
						</div>
						<div class="form-group">
							<a for="message-text" style="cursor: pointer">忘记密码？</a>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="login_real">登陆</button>
				</div>
			</div>
		</div>
	</div>

	<div style="width: 90px; margin-left: 92%; cursor: pointer" id="before_login">
		<span><a id="regist" href="regist.jsp" target="_blank">注册</a>|<a data-toggle="modal"
			data-target="#exampleModal" id="acc_login">登陆</a></span>
	</div>
	<div class="head" id="hd">
		<div>
			<div id="imagebox">
				<a href="<%=request.getContextPath()%>/search"><img alt="index"
					src="<%=request.getContextPath()%>/resource/img/logo_01.png"></a>
			</div>
			<div id="kw_box">
				<input type="text" class="s_ipt" name="wd" id="h_kw" maxlength="100"
					autocomplete="off"> <input type="submit" value="TAILING"
					id="h_su" class="s_btn">
			</div>
		</div>
	</div>
	<div id="resmsg"></div>
	<div class="box" id="bd">
		<div style="margin-left: 18%">
			<img alt="index"
				src="<%=request.getContextPath()%>/resource/img/logo.png">
		</div>
		<p id="nv" class="s-word-top">
			<a href="http://news.baidu.com" target="_blank">新闻</a>
			<b>网页</b>
			<a
				href="http://tieba.baidu.com" target="_blank">贴吧</a><a
				href="http://zhidao.baidu.com" target="_blank">知道</a><a
				href="http://music.baidu.com" target="_blank">音乐</a><a
				href="http://image.baidu.com" target="_blank">图片</a><a
				href="http://v.baidu.com" target="_blank">视频</a><a
				href="http://map.baidu.com" target="_blank">地图</a><a
				href="http://baike.baidu.com" target="_blank">百科</a><a
				href="http://wenku.baidu.com" target="_blank">文库</a><a
				href="http://www.baidu.com/more/" target="_blank">更多&gt;&gt;</a>
		</p>
		<div>
			<input type="text" class="s_ipt" name="wd" id="kw" maxlength="100"
				autocomplete="off" value=""> <input type="submit" value="TAILING"
				id="su" class="s_btn st">
		</div>
	</div>
</body>
</html>