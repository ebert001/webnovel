<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>意见反馈</title>
		<%@include file="/css-js.jsp"%>
	</head>
	
	<body>
		<%@include file="/page/config/user_setup.jsp"%>
		<div id="embed_area">
			<div class="content_title">反馈</div>
			
			<form name="feedbackForm" action="${ctx}/feedback/addFeedback" method="post">
				<table class="table_form has_border">
					<tr class="bottom_border">
						<td class="table_form_label">标题：</td>
						<td class="table_form_field">
							<input type="text" name="title" style="width: 100%;" value="">
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">反馈：</td>
						<td class="table_form_field">
							<script type="text/javascript">
							document.write(createDivEditor("advice", "advice"));
							</script>
						</td>
					</tr>
					<tr>
						<td class="table_form_ele" colspan="4" align="center">
							<a class="a_btn medium" href="javascript:doSubmit('feedbackForm')"> 提交 </a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<script type="text/javascript" src="${ctx}/static/js/auto_load.js"></script>
	</body>
</html>
