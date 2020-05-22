<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>我的备忘</title>
		<%@include file="/css-js.jsp"%>
		
		<style type="text/css">
		#memo_list ul {
			list-style: none;
			width: 80%;
			margin: 0 auto 4px;
			background: #A4E2C6;
		}
		#memo_list ul li {
			display: inline;
			line-height: 28px;
		}
		</style>
	</head>
	
	<body class="body">
		<%@include file="/page/config/setup_common.jsp"%>
		<div id="embed_area">
			<div class="content_title">修改备忘</div>
			
			<div id="input_area">
				<form name="memoForm" action="${ctx}/Memo.action?updateMemo" method="post">
					<table class="table_form has_border">
						<tr class="bottom_border">
							<td class="table_form_label">标题：</td>
							<td class="table_form_field">
								<input style="width: 520px;" type="text" name="title" value="${memo.title}">
							</td>
						</tr>
						<tr class="bottom_border">
							<td class="table_form_label">内容：</td>
							<td class="table_form_field">
								<textarea name="content" style="width: 90%; height: 400px; margin-top: 8px; margin-bottom: 6px;">${memo.content}</textarea>
							</td>
						</tr>
					</table>
					<input type="hidden" name="id" value="${memo.id}">
					
					<div style="width: 100%; text-align: center; margin: 26px auto 16px auto;">
						<a class="a_btn medium" href="javascript:doSubmit('memoForm');">保存备忘</a>
						<a class="a_btn medium" href="javascript:history.go(-1);">返回</a>
					</div>
				</form>
			</div>
		</div>
		<script type="text/javascript" src="${ctx}/static/js/auto_load.js"></script>
	</body>
</html>
