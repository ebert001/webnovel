<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>修改密码</title>
		<%@include file="/css-js.jsp"%>
		<script type="text/javascript">
		function check() {
			var newPwd = $$("new_password").value;
			var confirmPwd = $$("confirm_password").value;
			if (newPwd != confirmPwd) {
				alert("新密码和确认密码不相等");
				return false;
			}
		}
		</script>
	</head>
	
	<body class="body">
		<%@include file="/page/config/setup_common.jsp"%>
		<div id="embed_area">
			<div class="content_title">修改密码</div>
			
			<div style="width: 80%; margin-left: 10%; padding-top: 68px;">
				<form action="${ctx}/user/updatePassword" method="post">
					<table class="table_form full_border">
						<tr class="bottom_border">
							<td class="table_form_label">原始密码：</td>
							<td class="table_form_field">
								<input class="input_required" type="password" name="oldPassword"/>
							</td>
						</tr>
						<tr class="bottom_border">
							<td class="table_form_label">新密码：</td>
							<td class="table_form_field">
								<input class="input_required" type="password" id="new_password" name="newPassword"/>
							</td>
						</tr>
						<tr class="bottom_border">
							<td class="table_form_label">确认密码：</td>
							<td class="table_form_field">
								<input class="input_required" type="password" id="confirm_password" name="confirmPassword"/>
							</td>
						</tr>
					</table>
				</form>
				
				
			 	<ul style="width: 80%; margin: 20px 10%; list-style: none; line-height: 58px;">
					<li style="margin-left: 20%;">
						<a class="a_btn medium" href="#" onclick="check()">修改密码</a>
					</li>
					<li style="text-align: right;">
						<span class="required_color">输入框左侧有红线，表明必须输入</span>
					</li>
				</ul>
			</div>
		</div>
		<script type="text/javascript" src="${ctx}/static/js/auto_load.js"></script>
	</body>
</html>
