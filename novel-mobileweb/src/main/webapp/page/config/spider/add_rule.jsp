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
					<tr>
						<td class="table_form_label">名称：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="name" value="${rule.name}"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="group">书籍列表页</td>	
					</tr>
					<tr>
						<td class="table_form_label">书籍列表地址格式：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookListUrlFormat" value="${rule.bookListUrlFormat}"/>
						</td>
					</tr>
					<tr>
						<td class="table_form_label">书籍列表起始页号：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookListStartPageNo" value="${rule.bookListStartPageNo}" />
						</td>
					</tr>
					<tr>
						<td class="table_form_label">书籍列表页面编码：</td>
						<td class="table_form_field">
							<select name="bookListCharset" default-value="${rule.bookListCharset}">
								<option>UTF-8</option>
								<option>GBK</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="table_form_label">书籍列表总页数XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookListTotalPagePath" value="${fn:escapeXml(rule.bookListTotalPagePath)}" />
						</td>
					</tr>
					<tr>
						<td class="table_form_label">书籍列表总页数正则：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookListTotalPageRegular" value="${fn:escapeXml(rule.bookListTotalPageRegular)}" />
						</td>
					</tr>
					<tr>
						<td class="table_form_label">书籍节点XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookNodePath" value="${fn:escapeXml(rule.bookNodePath)}" />
						</td>
					</tr>
					<tr>
						<td class="table_form_label">书籍名称XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookNodeNamePath" value="${fn:escapeXml(rule.bookNodeNamePath)}" />
						</td>
					</tr>
					<tr>
						<td class="table_form_label">书籍地址XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookNodeUrlPath" value="${fn:escapeXml(rule.bookNodeUrlPath)}" />
						</td>
					</tr>
					<tr>
						<td class="table_form_label">书籍作者XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookNodeAuthorPath" value="${fn:escapeXml(rule.bookNodeAuthorPath)}" />
						</td>
					</tr>
					<tr>
						<td class="table_form_label">书籍图片XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookNodeImgPath" value="${fn:escapeXml(rule.bookNodeImgPath)}" />
						</td>
					</tr>
					<tr>
						<td class="table_form_label">书籍简介XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookNodeIntroductionPath" value="${fn:escapeXml(rule.bookNodeIntroductionPath)}" />
						</td>
					</tr>
					<tr>
						<td class="table_form_label">书籍最近更新时间XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="bookNodeLastUpdateTimePath" value="${fn:escapeXml(rule.bookNodeLastUpdateTimePath)}" />
						</td>
					</tr>
					<tr>
						<td colspan="2" class="group">书籍目录页</td>	
					</tr>
					<tr>
						<td class="table_form_label">目录页编码：</td>
						<td class="table_form_field">
							<select name="catalogCharset" default-value="${rule.catalogCharset}">
								<option>UTF-8</option>
								<option>GBK</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="table_form_label">目录章节XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="catalogChapterNodePath" value="${fn:escapeXml(rule.catalogChapterNodePath)}" />
						</td>
					</tr>
					<tr>
						<td class="table_form_label">目录章节地址XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="catalogChapterUrlPath" value="${fn:escapeXml(rule.catalogChapterUrlPath)}" />
						</td>
					</tr>
					<tr>
						<td colspan="2" class="group">章节页</td>	
					</tr>
					<tr>
						<td class="table_form_label">章节页编码：</td>
						<td class="table_form_field">
							<select name="chapterCharset" default-value="${rule.chapterCharset}">
								<option>UTF-8</option>
								<option>GBK</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="table_form_label">章节XPATH：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="chapterNodePath" value="${fn:escapeXml(rule.chapterNodePath)}" />
						</td>
					</tr>
					<tr>
						<td class="table_form_label">干扰词：</td>
						<td class="table_form_field">
							<input class="input_required" type="text" name="chapterWeed" value="${fn:escapeXml(rule.chapterWeed)}" />
						</td>
					</tr>
				</table>
				
				<div class="button_area">
					<button type="submit">保存</button>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="${ctx}/static/js/auto_load.js"></script>
	</body>
</html>
