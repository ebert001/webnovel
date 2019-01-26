<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>修改密码</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css"/>
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
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
  
  <body>
  	<jsp:include page="/page/config/user_setup.jsp"></jsp:include>
  	<div id="embed_area">
  	<div style="width: 80%; margin-left: 10%; padding-top: 68px;">
    <form action="${ctx}/User.action?updatePassword" method="post">
    	<table class="table_form full_border">
    		<tr class="bottom_border">
    			<td class="table_form_label">原始密码：</td>
    			<td class="table_form_field">
    				<input class="input_wrapper input_required" type="password" name="old_password"/>
    			</td>
    		</tr>
    		<tr class="bottom_border">
    			<td class="table_form_label">新&nbsp;密&nbsp;码：</td>
    			<td class="table_form_field">
    				<input class="input_wrapper input_required" type="password" id="new_password" name="new_password"/>
    			</td>
    		</tr>
    		<tr class="bottom_border">
    			<td class="table_form_label">确认密码：</td>
    			<td class="table_form_field">
    				<input class="input_wrapper input_required" type="password" id="confirm_password" name="confirm_password"/>
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
    <script type="text/javascript">
    loadEmbedPage("setup_content", "embed_area");
    </script>
  </body>
</html>
