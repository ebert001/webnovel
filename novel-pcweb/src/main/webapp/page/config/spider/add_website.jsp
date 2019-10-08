<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>添加网站</title>
		<%@include file="/css-js.jsp"%>
	</head>
	
	<body class="body">
		<%@include file="/page/config/setup_common.jsp"%>
		<div id="embed_area">
			<div class="content_title">添加网站</div>
			<form action="${ctx}/spider/addWebsite" method="post">
				<table class="table_form has_border">
					<tr class="bottom_border">
						<td class="table_form_label">名称：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="name" />
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">地址：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="url" />
						</td>
					</tr>
				</table>
				
				<div style="width: 100%; text-align: center; margin: 26px auto 16px auto;">
					<button type="submit">保存</button>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="${ctx}/static/js/auto_load.js"></script>
	</body>
</html>
