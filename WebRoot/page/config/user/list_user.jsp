<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>用户列表</title>
		<%@include file="/css-js.jsp"%>
		<script type="text/javascript">
			var role = {"1":"管理员", "2":"后台监控", "3":"文章审阅", "4":"读者", "5":"作者 "};
		</script>
	</head>
	
	<body>
		<%@include file="/page/config/setup_common.jsp"%>
		<div id="embed_area">
			<div class="content_title">用户列表</div>
			<form action="${ctx}/user/list" method="post">
				<table class="table_form has_border">
					<tr class="bottom_border">
						<td class="table_form_label">帐号：</td>
						<td class="table_form_field"><input
							type="text" name="username" value="ebert"></td>
						<td class="table_form_label">注册时间：</td>
						<td class="table_form_field"><input
							type="text" name="register_time" value="2012-02-15 15:52:43"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">性别：</td>
						<td class="table_form_field" colspan="3"><select name="sex"
							class="input_wrapper">
								<option value="1">男</option>
								<option value="0">女</option>
						</select></td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_ele" colspan="4" align="center"><a
							class="a_btn medium" href="#"> 查 找 </a></td>
					</tr>
				</table>
			</form>
	
			<table class="table_list">
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
						<td class="text_center"><a
							href="${ctx}/user/toEdit?userId=${user.id}">编辑</a></td>
						<td>${user.name}</td>
						<td>${user.alias}</td>
						<td><script type="text/javascript">if (${user.role} != '') {document.write(role["${user.role}"]);}</script></td>
						<td>${user.state}</td>
						<td>${user.email}</td>
						<td>${user.phone}</td>
						<td><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate value="${user.regTime}" pattern="yyyy-MM-dd" /></td>
					</tr>
				</c:forEach>
			</table>
	
			<div style="width: 100%; text-align: center; margin: 26px auto 16px auto;">
				<a class="a_btn medium" href="${ctx}/user/user_add.jsp">添加用户</a>
			</div>
		</div>
	
		<script type="text/javascript" src="${ctx}/static/js/auto_load.js"></script>
	</body>
</html>
