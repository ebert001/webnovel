<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix= "fmt" uri= "http://java.sun.com/jsp/jstl/fmt"%> 

<!DOCTYPE HTML>
<html>
  <head>
    <title>用户列表</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar/WdatePicker.js"></script>
	<style type="text/css">
	</style>
	<script type="text/javascript">
	var role = {"1":"管理员", "2":"后台监控", "3":"文章审阅", "4":"读者", "5":"作者 "};
	</script>
  </head>
  
  <body>
  	<jsp:include page="/config/user_setup.jsp"></jsp:include>
  	<div id="embed_area">
  	<form>
  		<table class="table_form has_border">
    		<tr class="bottom_border">
    			<td class="table_form_label">帐号：</td>
    			<td class="table_form_field">
    				<input class="input_wrapper" type="text" name="username" value="ebert">
    			</td>
    			<td class="table_form_label">注册时间：</td>
    			<td class="table_form_field">
    				<input class="input_wrapper" type="text" name="register_time" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="2012-02-15 15:52:43">
    			</td>
    		</tr>
    		<tr class="bottom_border">
    			<td class="table_form_label">性别：</td>
    			<td class="table_form_field" colspan="3">
    				<select name="sex" class="input_wrapper">
    					<option value="1">男</option>
    					<option value="0">女</option>
    				</select>
    			</td>
    		</tr>
    		<tr class="bottom_border">
    			<td class="table_form_ele" colspan="4" align="center">
    				<a class="a_btn medium" href="#"> 查 找 </a>
    			</td>
    		</tr>
    	</table>
    </form>
    
  	<table class="table_list" >
  		<thead>
   		<tr class="title">
   			<td width="8%">操作</td>
   			<td width="10%">名称</td>
   			<td width="10%">昵称</td>
   			<td width="8%">角色</td>
   			<td width="8%">状态</td>
   			<td width="12%">邮件</td>
   			<td width="12%">手机</td>
   			<td width="12%">生日</td>
   			<td width="20%">注册时间</td>
   		</tr>
   		</thead>
   		<c:forEach items="${userList}" varStatus="i" var="user">
   		<tr <c:if test="${i.index % 2 == 1}">class="swap"</c:if>>
   			<td class="text_center"><a href="${pageContext.request.contextPath}/User.action?queryOne&username=${user.name}">编辑</a></td>
   			<td>${user.name}</td>
   			<td>${user.alias}</td>
   			<td><script type="text/javascript">if (${user.role} != '') {document.write(role["${user.role}"]);}</script></td>
   			<td>${user.status}</td>
   			<td>${user.email}</td>
   			<td>${user.phone}</td>
   			<td><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/></td>
   			<td><fmt:formatDate value="${user.regTime}" pattern="yyyy-MM-dd"/></td>
   		</tr>
   		</c:forEach>
   	</table>
    
    <div style="width: 100%; text-align: center; margin: 26px auto 16px auto;">
    	<a class="a_btn medium" href="${pageContext.request.contextPath}/user/user_add.jsp">添加用户</a>
    </div>
    </div>
    <script type="text/javascript">
    loadEmbedPage("setup_content", "embed_area");
    </script>
  </body>
</html>
