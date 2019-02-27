<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>我的书架</title>
		<%@include file="/css-js.jsp"%>
	</head>
	
	<body class="body">
		<%@include file="/page/config/setup_common.jsp"%>
		<div id="embed_area">
			<div class="content_title">我的书架</div>
			
			<div id="no1">
				<table class="table_list">
					<tr class="title">
						<td>书名</td>
						<td>作者</td>
						<td>类型</td>
						<td>状态</td>
					</tr>
					<tr class="swap">
						<td>
							<a href="#">三国演义</a>
						</td>
						<td>
							<a href="#">罗贯中</a>
						</td>
						<td>
							<a href="#">武侠小说</a>
						</td>
						<td>
							<a href="#">完本</a>
						</td>
					</tr>
				</table>
			</div>
			
			<script type="text/javascript">
				var arrMenu = ["我的1号书架", "我的2号书架"]; 
				var arrContent = [document.getElementById("no1").innerHTML, "苏轼"];
				createTab('0', 2, arrMenu, arrContent, '');
			</script>
		</div>
		<script type="text/javascript" src="${ctx}/static/js/auto_load.js"></script>
	</body>
</html>
