<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>用户列表</title>
		<%@include file="/css-js.jsp"%>
	</head>
	
	<body class="body">
		<%@include file="/page/config/setup_common.jsp"%>
		<div id="embed_area">
			<div class="content_title">用户列表</div>
			
			<form action="${ctx}/user/list" method="post">
				<table class="table_form has_border">
					<tr class="bottom_border">
						<td class="table_form_label">帐号：</td>
						<td class="table_form_field">
							<input type="text" name="username" value="">
						</td>
						<td class="table_form_label">注册时间：</td>
						<td class="table_form_field">
							<input type="text" name="register_time" value="" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_ele" colspan="4" align="center">
							<button >查找</button>
						</td>
					</tr>
				</table>
			
				<div class="table_area">
					<div class="table_header">
						<div class="title">
							用户列表
						</div>
						<div class="action">
							<button type="button" onclick="javascript:location.href='${ctx}/user/toAddUser';">添加用户</button>
						</div>
					</div>
					<div class="table_body">
						<table class="table_list">
							<thead>
								<tr class="title">
									<td width="10%">名称</td>
									<td width="10%">昵称</td>
									<td width="8%">角色</td>
									<td width="8%">状态</td>
									<td width="12%">邮件</td>
									<td width="12%">手机</td>
									<td width="12%">生日</td>
									<td width="20%">注册时间</td>
									<td width="8%">操作</td>
								</tr>
							</thead>
							<c:forEach items="${page.pageResult}" varStatus="i" var="user">
								<tr>
									<td>${user.name}</td>
									<td>${user.alias}</td>
									<td>${user.role}</td>
									<td>${user.state}</td>
									<td>${user.email}</td>
									<td>${user.phone}</td>
									<td><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" /></td>
									<td><fmt:formatDate value="${user.regTime}" pattern="yyyy-MM-dd" /></td>
									<td class="text_center">
										<a href="${ctx}/user/toEdit?userId=${user.id}">编辑</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div class="table_footer" load-page="true" 
						page-no="${page.pageNo}" page-size="${page.pageSize}" 
						total-page="${page.pageCount}" total-count="${page.totalCount}">
					</div>
				</div>
			</form>
		</div>
	
		<script type="text/javascript" src="${ctx}/static/js/auto_load.js"></script>
	</body>
</html>
