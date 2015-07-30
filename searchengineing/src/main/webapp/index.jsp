<%@ page language="java" contentType="text/html"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>login</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 引入 Bootstrap -->
<link href="resource/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->
<style type="text/css">
div.panel, p.flip {
	margin: 0px;
	padding: 5px;
	text-align: center;
	background: #e5eecc;
	border: solid 1px #c3c3c3;
}

div.panel {
	height: 120px;
	display: none;
}
</style>
</head>
<body>
	<p>
		鼠标指针位于： <span></span>
	</p>
	<table class="table table-bordered">
		<tr>
			<td>a</td>
			<td>a</td>
			<td>a</td>
			<td>a</td>
			<td>a</td>
			<td>a</td>
		</tr>
		<td>b</td>
		<td>b</td>
		<td>b</td>
		<td>b</td>
		<td>b</td>
		<td>b</td>
		<tr>
			<td>c</td>
			<td>c</td>
			<td>c</td>
			<td>c</td>
			<td>c</td>
			<td>c</td>
		</tr>
		<tr>
			<td>d</td>
			<td>d</td>
			<td>d</td>
			<td>d</td>
			<td>d</td>
			<td>d</td>
		</tr>
		<tr>
			<td>e</td>
			<td>e</td>
			<td>e</td>
			<td>e</td>
			<td>e</td>
			<td>e</td>
		</tr>
		<br>
	</table>
	<div class="panel">
		<p>W3School web</p>
		<p>W3School,you are right</p>
	</div>
	<p class="flip">click</p>

	<hr>
	<button id="bt01">动</button>
	<button id="bt02">大</button>
	<button id="bt03">小</button>
	<button id="bt04">变</button>
	<button id="bt05">联动</button>	
	<button id="bt06">浏览器尺寸</button>	
	<button id="bt07">设置尺寸</button>	
	<!-- 	默认情况下，所有 HTML 元素的位置都是静态的，并且无法移动。如需对位置进行操作，记得首先把元素的 CSS position 属性设置为 relative、fixed 或 absolute。 -->
	<div id="auto"
		style="background: #98bf21; height: 100px; width: 100px; position: absolute;"></div>


	<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	<script src="resource/jquery/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$(".flip").click(function() {
				$(".panel").slideToggle(2000, function() {
					alert("i am collback")
				}); //【slide滑动】   功能： 藏显示切换    /*slideup 向上滑动  slidedown 向下  */
					//$(".panel").fadeToggle("slow"); //【fade 褪色】   功能：淡入淡出切换   /* fadein 淡入 fadeout 淡出   */

				/*fadeIn等函数(speed,callback);					
					可选的 speed 参数规定效果的时长。它可以取以下值："slow"、"fast" 或毫秒.
					可选的 callback 参数是 fading 完成后所执行的函数名称。	 
					###Callback 函数在当前动画 100% 完成之后执行。
					【animate 使活泼  使有生气；使活泼；鼓舞；推动】
				 */
			});

			$(document).mousemove(function(e) {
				$("span").text("X: " + e.pageX + "    " + "Y:  " + e.pageY);
			});
			$("#bt01").click(function() {
				//$(selector).animate({params},speed,callback);
				//必需的 params 参数定义形成动画的 CSS 属性。
				//可选的 speed 参数规定效果的时长。它可以取以下值："slow"、"fast" 或毫秒。
				//可选的 callback 参数是动画完成后所执行的函数名称。
				$("#auto").animate({
					left : '+=250px'
				});
			});
			$("#bt02").click(function() {
				$("#auto").animate({
					//left:'250px',
					//height:'+=150px',
					width : '+=150px'
				});
			});
			$("#bt03").click(function() {
				$("#auto").animate({
					//left:'250px',
					//height:'+=150px',
					width : '-=150px'
				});
			});
			$("#bt04").click(function() {
				var div = $("#auto");
				div.animate({
					height : '300px',
					opacity : '0.4'
				}, "slow");
				div.animate({
					width : '300px',
					opacity : '0.8'
				}, "slow");
				div.animate({
					height : '100px',
					opacity : '0.4'
				}, "slow");
				div.animate({
					width : '100px',
					opacity : '0.8'
				}, "slow");
			});
			$("#bt05").click(function() {
				$("#auto").css("background","red").slideUp(2000).slideDown(2000);
			});
			$("#bt06").click(function(){ 
				  var txt="";
				  txt+=$(document).width();
				  txt+="x" + $(document).height();
				  alert(txt);
				});
			$("#bt07").click(function(){ 
				 $("#auto").width(500).height(300);
				});			
		});
		
		
		// 		$(selector).stop(stopAll,goToEnd);
		// 		可选的 stopAll 参数规定是否应该清除动画队列。默认是 false，即仅停止活动的动画，允许任何排入队列的动画向后执行。
		// 		可选的 goToEnd 参数规定是否立即完成当前动画。默认是 false。
	</script>
	<!-- 包括所有已编译的插件 -->
	<script src="resource/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>