<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>修改基本信息</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css"/>
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/calendar/WdatePicker.js"></script>
  </head>
  
  <body>
  	<jsp:include page="/page/config/user_setup.jsp"></jsp:include>
  	<div id="embed_area">
  	<form name="userForm" action="${ctx}/User.action?addUser" method="post">
  		<table class="table_form">
    		<tr>
    			<td class="table_form_label">帐号：</td>
    			<td class="table_form_field">
    				<input class="input_wrapper input_required" type="text" name="username" value="">
    			</td>
    			<td class="table_form_label">邮箱：</td>
    			<td class="table_form_field">
    				<input class="input_wrapper" type="text" name="email" value="">
    			</td>
    		</tr>
    		<tr>
    			<td class="table_form_label">手机：</td>
    			<td class="table_form_field">
    				<input class="input_wrapper" type="text" name="phone" value="">
    			</td>
    			<td class="table_form_label">生日：</td>
    			<td class="table_form_field">
    				<input class="input_wrapper" type="text" name="birthday" value="" onClick="WdatePicker()">
    			</td>
    		</tr>
    		<tr>
    			<td class="table_form_label">角色：</td>
    			<td class="table_form_field">
    				<select name="role" class="input_wrapper">
    					<option value="1">管理员</option>
    					<option value="2">后台监控</option>
    					<option value="3">文章审阅</option>
    					<option value="4">读者</option>
    					<option value="5">作者</option>
    				</select>
    			</td>
    			<td class="table_form_label">性别：</td>
    			<td class="table_form_field">
    				<select name="sex" class="input_wrapper">
    					<option value="0">男</option>
    					<option value="1">女</option>
    				</select>
    			</td>
    		</tr>
    		<tr>
    			<td class="table_form_label">简介：</td>
    			<td class="table_form_field" colspan="3">
    				<textarea name="remark" style="width: 380px; height: 68px; margin-top: 18px;"></textarea>
    			</td>
    		</tr>
    	</table>
  	</form>
    
    <div style="width: 100%; text-align: center; margin: 26px auto; padding-bottom: 16px;">
    	<a class="a_btn medium" href="javascript: doSubmit('userForm');">保存信息</a>
    </div>
    </div>
    <script type="text/javascript">
    loadEmbedPage("setup_content", "embed_area");
    </script>
  </body>
</html>
