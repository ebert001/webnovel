<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>作家专区</title>
		<%@include file="/css-js.jsp"%>
		
		<style type="text/css">
		.de {
			margin-top: 28px;
			border: 1px solid gray;
			height: 240px;
			overflow-y: scroll;
			word-break: break-all;
		}
		
		.ok {
			margin-top: 28px;
			margin-left: 10%;
		}
		</style>
	</head>
	
	<body class="body">
		<%@include file="/page/config/setup_common.jsp"%>
		<div id="embed_area">
			<div class="content_title">添加作者</div>
			<form action="" method="post">
				<table class="table_form has_border">
					<tr class="bottom_border">
						<td class="table_form_label">笔名：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="penname" />
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">简介：</td>
						<td class="table_form_field">
							<textarea class="input_required" name="intro" style="width: 400px; height: 120px; margin-top: 8px;"></textarea>
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">姓名：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="penname" />
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">性别：</td>
						<td class="table_form_field">
							<select name="sex" style="width: 48px; height: 24px;">
								<option value="0">男</option>
								<option value="1">女</option>
							</select>
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">证件号码：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="cardno" />
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">联系电话：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="phone" />
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">电子邮件：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="email" />
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">QQ或MSN：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="im" />
						</td>
					</tr>
				</table>
			</form>
	
			<div class="de">
				<%@include file="/page/surface/self/author_princple.jsp"%>
			</div>
	
			<div class="ok">
				<input type="checkbox" name="ok" checked /> 我已阅读并同意接受 乐文作者注册协议
			</div>
	
			<div style="width: 100%; text-align: center; margin: 26px auto 16px auto;">
				<a class="a_btn medium" href="#">下一步</a>
			</div>
		</div>
		<script type="text/javascript" src="${ctx}/static/js/auto_load.js"></script>
	</body>
</html>
