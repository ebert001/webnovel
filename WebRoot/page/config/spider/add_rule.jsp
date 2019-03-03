<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>添加爬取规则</title>
		<%@include file="/css-js.jsp"%>
	</head>
	
	<body class="body">
		<%@include file="/page/config/setup_common.jsp"%>
		<div id="embed_area">
			<div class="content_title">添加爬取规则</div>
			<form action="${ctx}/spider/addRule" method="post">
				<input type="hidden" name="websiteId" value="${website.id}">
				<input type="hidden" name="id" value="${rule.id}">
				<table class="table_form has_border">
					<tr class="bottom_border">
						<td class="table_form_label">名称：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="name" value="${rule.name}"/>
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">书籍列表地址格式：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookListUrlFormat" value="${rule.bookListUrlFormat}"/>
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">书籍列表起始页号：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookListStartPageNo" value="${rule.bookListStartPageNo}" />
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">书籍列表页面编码：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookListCharset" value="${rule.bookListCharset}" />
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">书籍列表总页数XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookListTotalPagePath" value="${rule.bookListTotalPagePath}" />
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">书籍列表总页数正则：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookListTotalPageRegular" value="${rule.bookListTotalPageRegular}" />
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">书籍节点XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookNodePath" value="${rule.bookNodePath}" />
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">书籍名称XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookNodeNamePath" value="${rule.bookNodeNamePath}" />
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">书籍地址XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookNodeUrlPath" value="${rule.bookNodeUrlPath}" />
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">书籍作者XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookNodeAuthorPath" value="${rule.bookNodeAuthorPath}" />
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">书籍图片XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookNodeImgPath" value="${rule.bookNodeImgPath}" />
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">书籍简介XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookNodeIntroductionPath" value="${rule.bookNodeIntroductionPath}" />
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">书籍最近更新时间XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookNodeLastUpdateTimePath" value="${rule.bookNodeLastUpdateTimePath}" />
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">目录页编码：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="catalogCharset" value="${rule.catalogCharset}" />
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">目录章节XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="catalogChapterNodePath" value="${rule.catalogChapterNodePath}" />
						</td>
					</tr>
				</table>
				
				<div style="width: 100%; text-align: center; margin: 26px auto 16px auto;">
					<button type="submit">保存</button>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="${ctx}/static/js/auto_load.js"></script>
	</body>
</html>
