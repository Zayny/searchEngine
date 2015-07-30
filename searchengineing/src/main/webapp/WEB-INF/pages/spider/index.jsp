<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<table class="table">
		<tr>
			<td>单页抓取</td><td>URI:<input type="text" placeholder="URI" id="suri"/></td>
			<td>
			<td>
			<td><button type="button" class="btn btn-default  btn-sm" id="single" data-loading-text="正在抓取..." >开始</button></td>
		</tr>
		<tr>
			<td>深度抓取</td>
			<td>URI:<input type="text" placeholder="URI"/></td>
			<td>深度:<input type="text" placeholder="DEEP"/></td>
			<td>爬虫数量:<input type="text" placeholder="1"/></td>
			<td><button type="button" class="btn btn-default  btn-sm" id="deep" data-loading-text="正在抓取..." data-fail-text="抓取失败！">开始</button></td>
		</tr>	
	</table>
	<script type="text/javascript">
		$('#single').on('click', function () {
			var url = $('#suri').val();
			if(url != ''){
			var $btn = $(this).button('loading')
				$.get('<%=request.getContextPath()%>/climbs/jobs',{"url":url},function(res){
					if(res){
						alert('抓取成功')
						var url = $('#suri').val("");
						 $btn.button('reset')
					}else{
						  alert('抓取失败');
						  $btn.button('reset')
					}
				})
			}else{
				alert('请填写URL');
				$('#suri').focus()
			}
  		})
		$('#deep').on('click', function () {
			var $btn = $(this).button('loading')
// 			      $btn.button('reset')
  		})
	</script>
