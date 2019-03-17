<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>待审核书籍</title>
		<%@include file="/css-js.jsp"%>
	</head>
	
	<body class="body">
		<%@include file="/page/config/setup_common.jsp"%>
		<div id="embed_area">
			<div class="content_title">待审核书籍</div>
			<form id="form_unaudit_books" action="${ctx}/spider/toUnauditBooks" method="post">
				<table class="table_form has_border">
					<tr>
						<td>名称</td>
						<td>
							<input type="text" name="Q-S-LIKE-name">
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
							书籍列表
						</div>
						<div class="action">
							<button type="button" onclick="javascript:location.href='${ctx}/spider/toAddWebsite';">增加网站</button>
						</div>
					</div>
					<div class="table_body">
						<table class="table_list">
							<thead>
								<tr class="title">
									<td width="10%">书籍名称</td>
									<td width="10%">创建时间</td>
									<td width="8%">最近更新时间</td>
									<td width="3%">状态</td>
									<td width="10%">操作</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.pageResult}" varStatus="i" var="bean">
									<tr>
										<td>
											<a href="${ctx}/spider/toUnauditChapters?bookId=${bean.id}">${bean.name}</a>
										</td>
										<td>
											<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td>
											<fmt:formatDate value="${bean.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td>
											${bean.state}
										</td>
										<td>
											<a href="${ctx}/spider/toAddRule?id=${bean.id}">发布</a>
											<c:if test="${bean.state == 1}">
												<a href="${ctx}/spider/closeWebsite?id=${bean.id}">关闭</a>
											</c:if>
											<c:if test="${website.state == 2 }">
												<a href="${ctx}/spider/openWebsite?id=${bean.id}">打开</a>
											</c:if>
											<a href="${ctx}/spider/deleteWebsite?id=${bean.id}">删除</a>
											<a href="${ctx}/spider/loopChapters?bookId=${bean.id}">抓取</a>
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
