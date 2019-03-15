<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>待审核章节</title>
		<%@include file="/css-js.jsp"%>
	</head>
	
	<body class="body">
		<%@include file="/page/config/setup_common.jsp"%>
		<div id="embed_area">
			<div class="content_title">待审核章节</div>
			<form id="form_unaudit_chapters" action="${ctx}/spider/toUnauditChapters" method="post">
				<input type="hidden" name="bookId" value="${book.id}">
				<table>
					<tr>
						<td>名称</td>
						<td>
							<input type="text" name="Q-name">
						</td>
						<td></td>
						<td>
							<button>查找</button>
						</td>
					</tr>
				</table>
			
				<div class="table_area">
					<div class="table_header">
						<div class="title">
							${book.name}-章节列表
						</div>
					</div>
					<div class="table_body">
						<table class="table_list">
							<thead>
								<tr class="title">
									<td width="10%">章节名称</td>
									<td width="10%">创建时间</td>
									<td width="3%">状态</td>
									<td width="10%">操作</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.pageResult}" varStatus="i" var="bean">
									<tr>
										<td>
											<a href="${ctx}/spider/toUnauditChapter?chapterId=${bean.id}">${bean.subject}</a>
										</td>
										<td>
											<fmt:formatDate value="${bean.inputTime}" pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td>
											${bean.state}
										</td>
										<td>
											<a href="${ctx}/spider/toUnauditChapter?chapterId=${bean.id}">发布</a>
											<c:if test="${bean.state == 1}">
												<a href="${ctx}/spider/closeWebsite?id=${bean.id}">关闭</a>
											</c:if>
											<a href="${ctx}/spider/deleteWebsite?id=${bean.id}">删除</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="table_footer" load-page="true"
						page-no="${page.pageNo}" page-size="${page.pageSize}" 
						total-page="${page.pageCount}" total-count="${page.totalCount}">
						<div class="action">
							<button type="button" onclick="javascript:location.href='${ctx}/spider/toAddWebsite';">发布</button>
						</div>
					</div>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="${ctx}/static/js/auto_load.js"></script>
	</body>
</html>
