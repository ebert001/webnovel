<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>我的作品</title>
		<%@include file="/css-js.jsp"%>
	</head>
	
	<body class="body">
		<%@include file="/page/config/setup_common.jsp"%>
		<div id="embed_area">
			<div class="content_title">我的作品</div>
			
			<form action="${ctx}/user/list" method="post">
				<table class="table_form has_border">
					<tr class="bottom_border">
						<td class="table_form_label">名称</td>
						<td class="table_form_field">
							<input type="text" name="name" value="">
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
							作品列表
						</div>
						<div class="action">
							<button type="button" onclick="javascript:location.href='${ctx}/book/toAdd';">添加作品</button>
						</div>
					</div>
					<div class="table_body">
						<table class="table_list">
							<thead>
						 		<tr class="title">
						 			<td width="10%">作品名称</td>
						 			<td width="10%">创建时间</td>
						 			<td width="8%">最近更新时间</td>
						 			<td width="8%">状态</td>
						 			<td width="3%">操作</td>
						 		</tr>
					 		</thead>
					 		<tbody>
						 		<c:forEach items="${bookList}" varStatus="i" var="book">
							 		<tr>
							 			<td>
							 				<a href="${ctx}/book/listChapter?bookId=${book.id}&a=r">${book.bookName}</a>
							 			</td>
							 			<td><fmt:formatDate value="${book.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							 			<td><fmt:formatDate value="${book.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							 			<td>${book.status}</td>
							 			<td>
							 				<a href="${ctx}/Book.action?deleteBook&id=${book.id}">删除</a>
							 			</td>
							 		</tr>
						 		</c:forEach>
					 		</tbody>
					 	</table>
					 </div>
				</div>
			</form>
	 	</div>
		<script type="text/javascript" src="${ctx}/static/js/auto_load.js"></script>
	</body>
</html>
