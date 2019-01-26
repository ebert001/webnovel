<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
	<title>通行帐号</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">	
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/floatPage.js"></script>

	<style type="text/css">
	.block_width {
		margin: 60px 10%;
		width: 80%;
		padding: 5px;
	}
	</style>
  </head>
  
  <body class="body">
	<jsp:include page="/page/frame/header.jsp"></jsp:include>
	<div class="container">
		<div class="content_wrapper none_space upper_border_radius">
			<div class="block_width">
				<form name="register_form" method="post" action="">
				<table class="table_form">
					<tr class="bottom_border">
						<td class="table_form_label">名称：</td>
						<td class="table_form_field">
							<input type="text" name="username" style="width: 60%;" class="input_wrapper input_required"/>
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">密码：</td>
						<td class="table_form_field">
							<input type="password" name="password" style="width: 60%;" class="input_wrapper input_required"/>
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">确认密码：</td>
						<td class="table_form_field">
							<input type="password" name="confirm_password" style="width: 60%;" class="input_wrapper input_required"/>
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">邮箱：</td>
						<td class="table_form_field">
							<input type="text" name="email" style="width: 60%;" class="input_wrapper input_required"/>
						</td>
					</tr>
				</table>
				</form>
			</div>
			
			
			<ul style="width: 80%; margin: 20px 10%; list-style: none; line-height: 58px;">
				<li>
					<input type="checkbox" name="pro_check" value="true" checked="checked"> 我接受<a>网络小说用户服务协议</a>
				</li>
				<li style="margin-left: 20%;">
					<a class="a_btn medium" href="#" onclick="inputCheck('register_form')">注册帐号</a>
				</li>
				<li style="text-align: right;">
					<span class="required_color">输入框左侧有红线，表明必须输入</span>
				</li>
			</ul>
		
		</div>
	</div>
  </body>
</html>
