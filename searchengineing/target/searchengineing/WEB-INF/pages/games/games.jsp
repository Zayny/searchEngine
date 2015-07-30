<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta content="text/html" http-equiv="Content-type" charset="utf-8">
<title>game</title>
<script	src="<%=request.getContextPath()%>/resource/jquery/jquery.min.js"></script>
<style type="text/css">
#bgdiv {
	top:0px;
	height: 600px; 
	width:1000px;
	background-color: green;
}
.char {
	width: 20px;
	height: 20px;
	position: absolute;
	font: 40px;
	text-align:center;
}

</style>
<script type="text/javascript">
	var rightCount = 0;
	var errorCount = 0;
	var speed = 5000;
	var score = 0;
	var div_x;

	var charArray = new Array("A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"X", "Y", "Z");
	var game = {
		createChar : function() {
			var ps = Math.random()*970+10;
			var temp = Math.random() * 25;
			var c = charArray[parseInt(temp)];
			var charHtml = charHtml = " <div class='char' id='" + c + "' style='left: "
			+ ps + "px;border-radius: 50%;background-color:"+game.rdColor()+"'>" + c + "</div>";
			$("#bgdiv").append(charHtml);
		},
		
		rdColor:function(){
			var colorArray = [];
			colorArray[0] = "#ff2211";
			colorArray[1] = "#ff3311";
			colorArray[2] = "#ff5511";
			colorArray[3] = "#ff8811";
			colorArray[4] = "#ffBB99";
			colorArray[5] = "#1ff4f1";
			colorArray[6] = "#ff5566";
			colorArray[7] = "#668899";
			colorArray[8] = "#99BBfA";
			colorArray[9] = "#fECECC";
			colorArray[10] = "#CD2626";
			colorArray[11] = "#B452CD";
			colorArray[12] = "#9A32CD";
			return colorArray[parseInt(Math.random()*(colorArray.length-1))];
		},
		
		move: function(){
			$("#bgdiv").find("div").animate({top:'+=470px'},speed,function(){
				$(this).remove();
				score -=10;				
				$("#score").html("<span>分数："+score+"<span/>");
			});
		},
	   start:function(){
		   game.createChar();
		   game.move();
	   }
	}
	$(document).ready(function(){		
		window.setInterval("game.start()", 2000);
		$("#circle").click(function(){
			game.createChar();
		})
		$("#move").click(function(){
			game.move();
		})
		
		$("#bd").keypress(function(event){
			var key = event.keyCode;
			var key_char = charArray[key-97]||charArray[key-65];
			$("#"+key_char+"").stop(1000).remove();
			score+=10;
		})
	})
</script>
</head>
<!-- onkeypress 和 onkeydown 是有区别，下面将讲解 onkeypress 与 onkeydown 事件的区别。  -->
<!-- onkeypress 事件在用户按下并放开任何字母数字键时发生。但是系统按钮（例如：箭头键、功能键）无法得到识别。  -->
<body id="bd">
	<div id="bgdiv" style="float:left"></div>
<!-- 	<div ><button id="circle" style="float:left">circle</button></div> -->
<!-- 	<div ><button id="move" style="float:left">move</button></div> -->
	<div id="score"></div>
</body>
</html>