<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>修改基本信息</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css"/>
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
	<style type="text/css">
	</style>
  </head>
  
  <body>
  	<jsp:include page="../user_setup.jsp"></jsp:include>
  	<div id="embed_area">
  	<div style="border-bottom: 2px dashed #8BB8F2;margin-bottom: 18px; text-indent: 8px;">
  		<span style="line-height: 28px; font-weight: bold;">用户设置</span>
  	</div>
  	<form name="userForm" action="${ctx}/User.action?updateUser" method="post">
  		<table class="table_form has_border">
    		<tr class="bottom_border">
    			<td class="table_form_label">帐号：</td>
    			<td class="table_form_field">
    				<input class="input_wrapper" type="text" name="username" readonly value="${user.name}">
    			</td>
    			<td class="table_form_label">注册时间：</td>
    			<td class="table_form_field">
    				<input class="input_wrapper" type="text" name="register_time" readonly value="<fmt:formatDate value="${user.regTime}" pattern="yyyy-MM-dd"/>">
    			</td>
    		</tr>
    		<tr class="bottom_border">
    			<td class="table_form_label">邮箱：</td>
    			<td class="table_form_field">
    				<input class="input_wrapper" type="text" name="email" value="${user.email}">
    			</td>
    			<td class="table_form_label">手机：</td>
    			<td class="table_form_field">
    				<input class="input_wrapper" type="text" name="phone" value="${user.phone}">
    			</td>
    		</tr>
    		<tr class="bottom_border">
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
    					<option value="1" <c:if test="${user.sex == 1}">selected</c:if>>男</option>
    					<option value="0" <c:if test="${user.sex == 0}">selected</c:if>>女</option>
    				</select>
    			</td>
    		</tr>
    		<tr class="bottom_border">
    			<td class="table_form_label">生日：</td>
    			<td class="table_form_field" colspan="3">
    				<input class="input_wrapper" type="text" name="birthday" value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/>" onClick="WdatePicker()">
    			</td>
    		</tr>
    		<tr class="bottom_border">
    			<td class="table_form_label">所在地址：</td>
    			<td class="table_form_field" colspan="3">
    				<input class="input_wrapper" style="width: 380px;" type="text" name="address" value="${user.name}">
    			</td>
    		</tr>
    		<tr class="bottom_border">
    			<td class="table_form_label">简介：</td>
    			<td class="table_form_field" colspan="3">
    				<textarea name="remark" style="width: 80%; height: 128px; margin-top: 18px;">${user.remark}</textarea>
    			</td>
    		</tr>
    	</table>
  	</form>
    
    <div style="width: 100%; text-align: center; margin: 26px auto 16px auto;">
    	<a class="a_btn medium" href="javascript:doSubmit('userForm');">保存基本信息</a>
    </div>
    </div>
    <script type="text/javascript">
    loadEmbedPage("setup_content", "embed_area");
    </script>
  </body>
</html>
