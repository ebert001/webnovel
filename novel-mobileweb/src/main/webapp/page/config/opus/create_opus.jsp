<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>我的作品</title>
		<%@include file="/css-js.jsp"%>
	</head>
	
	<body class="body">
		<%@include file="/page/config/setup_common.jsp"%>
		<div id="embed_area">
			<div class="content_title">添加作品</div>
			
			<div id="input_area">
				<form name="bookForm" action="${ctx}/book/add" method="post">
				<table class="table_form has_border">
					<tr class="bottom_border">
						<td class="table_form_label">书名：</td>
						<td class="table_form_field">
							<input class="input_required" title="书名" type="text" name="bookName" value="">
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">简介：</td>
						<td class="table_form_field">
							<textarea name="desc" title="简介"></textarea>
						</td>
					</tr>
				</table>
				
				<div>
					<button onclick="javascript:checkAndSubmit('bookForm');">提交</button>
				</div>
				</form>
			</div>
	 	</div>
		<script type="text/javascript" src="${ctx}/static/js/auto_load.js"></script>
	</body>
</html>
