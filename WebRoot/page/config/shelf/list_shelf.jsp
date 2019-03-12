<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>我的书架</title>
		<%@include file="/css-js.jsp"%>
	</head>
	
	<body class="body">
		<%@include file="/page/config/setup_common.jsp"%>
		<div id="embed_area">
			<div class="content_title">我的书架</div>
			
			<div class="table_area">
				<div class="table_header">
					<div class="title">
						收藏列表
					</div>
				</div>
				<div class="table_body">
					<table class="table_list">
						<thead>
							<tr class="title">
								<td>书名</td>
								<td>作者</td>
								<td>添加时间</td>
								<td>操作</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.pageResult}" varStatus="i" var="bean">
								<tr>
									<td>
										<a href="${ctx}/bookshelf/toRead?id=${bean.id}">${bean.bookName}</a>
									</td>
									<td>
										${bean.bookAuthor}
									</td>
									<td>
										<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td>
										<a href="${ctx}/bookshelf/delete?id=${bean.id}">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="table_footer" load-page="true" 
					page-no="${page.pageNo}" page-size="${page.pageSize}" 
					total-page="${page.pageCount}" total-count="${page.totalCount}">
				</div>
			</div>
		</div>
		<script type="text/javascript" src="${ctx}/static/js/auto_load.js"></script>
	</body>
</html>
