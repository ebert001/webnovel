<#include "/taglibs.ftl">

<!DOCTYPE HTML>
<html>
  <head>
	<title>通行帐号</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">	
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="shortcut icon" href="${ctx}/static/imgs/logo/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css">
	<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/floatPage.js"></script>

	<style type="text/css">
	.block-width {
		margin: 60px 10%;
		width: 80%;
		padding: 5px;
	}
	</style>
  </head>
  
  <body class="body">
	<#include file="/frame/header.jsp">
	<div class="container">
		<div class="content-wrapper none-space upper-border-radius">
			<div class="block-width">
				<form name="register-form" method="post" action="">
					<table class="table-form">
						<tr class="bottom-border">
							<td class="table-form-label">名称：</td>
							<td class="table-form-field">
								<input type="text" name="username" class="input-required"/>
							</td>
						</tr>
						<tr class="bottom-border">
							<td class="table-form-label">邮箱：</td>
							<td class="table-form-field">
								<input type="text" name="email" class="input-required"/>
							</td>
						</tr>
						<tr class="bottom-border">
							<td class="table-form-label">密码：</td>
							<td class="table-form-field">
								<input type="password" name="password" class="input-required"/>
							</td>
						</tr>
						<tr class="bottom-border">
							<td class="table-form-label">确认密码：</td>
							<td class="table-form-field">
								<input type="password" name="confirm-password" class="input-required"/>
							</td>
						</tr>
						
					</table>
				</form>
			</div>
			
			
			<ul style="width: 80%; margin: 20px 10%; list-style: none; line-height: 58px;">
				<li>
					<input type="checkbox" name="pro-check" value="true" checked="checked"> 我接受<a>网络小说用户服务协议</a>
				</li>
				<li style="margin-left: 20%;">
					<a class="a-btn medium" href="#" onclick="inputCheck('register-form')">注册帐号</a>
				</li>
				<li style="text-align: right;">
					<span class="required-color">输入框左侧有红线，表明必须输入</span>
				</li>
			</ul>
		
		</div>
	</div>
  </body>
</html>
