<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>用户信息</title>
		<%@include file="/css-js.jsp"%>
	</head>
	
	<body class="body">
		<%@include file="/page/config/setup_common.jsp"%>
		<div id="embed_area">
			<div class="content_title">用户信息</div>
			<table class="table_form has_border">
		 		<tr class="bottom_border">
		 			<td class="table_form_label">帐号：</td>
		 			<td class="table_form_field">ebert</td>
		 		</tr>
		 		<tr class="bottom_border">
		 			<td class="table_form_label">注册时间：</td>
		 			<td class="table_form_field">2012-02-15 15:52:43</td>
		 		</tr>
		 		<tr class="bottom_border">
		 			<td class="table_form_label">所在地址：</td>
		 			<td class="table_form_field">上海市 黄浦区 宁海东路200号 申鑫大厦 2609</td>
		 		</tr>
		 		<tr class="bottom_border">
		 			<td class="table_form_label">邮箱：</td>
		 			<td class="table_form_field">ebert_li@163.com</td>
		 		</tr>
		 		<tr class="bottom_border">
		 			<td class="table_form_label">性别：</td>
		 			<td class="table_form_field">男</td>
		 		</tr>
		 		<tr class="bottom_border">
		 			<td class="table_form_label">简介：</td>
		 			<td class="table_form_field">帅哥</td>
		 		</tr>
		 	</table>
			
			<div style="width: 100%; text-align: center; margin: 26px auto 16px auto;">
				<a class="a_btn medium" href="${ctx}/User.action?queryOne&uName=">修改基本信息</a>
			</div>
		</div>
		<script type="text/javascript" src="${ctx}/static/js/auto_load.js"></script>
	</body>
</html>
