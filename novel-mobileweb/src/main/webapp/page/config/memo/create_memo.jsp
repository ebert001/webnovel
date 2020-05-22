<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>我的备忘</title>
		<%@include file="/css-js.jsp"%>
		
		<style type="text/css">
		#memo_list .item {
			margin: 0 auto 4px;
			background: #A4E2C6;
			line-height: 28px;
		}
		</style>
		
		<script type="text/javascript">
		function show(i) {
			var id = "memo_content_" + i;
			var sdisplay = document.getElementById(id).style.display;
			if (sdisplay == "none") {
				document.getElementById(id).style.display = "block";
			} else {
				document.getElementById(id).style.display = "none";
			}
		}
		
		function submitMemo() {
			var content = document.forms["memoForm"].elements["content"].value; 
			document.forms["memoForm"].elements["content"].value = replaceCRLF(content);
			doSubmit("memoForm");
		}
		</script>
	</head>
	
	<body class="body">
		<%@include file="/page/config/setup_common.jsp"%>
		<div id="embed_area">
			<div class="content_title">添加备忘</div>
			<div id="input_area">
				<form name="memoForm" action="${ctx}/Memo.action?addMemo" method="post">
				<table class="table_form full_border">
					<tr class="bottom_border">
						<td class="table_form_label">标题：</td>
						<td class="table_form_field">
							<input style="width: 520px;" type="text" name="title" value="">
						</td>
					</tr>
					<tr class="bottom_border">
						<td class="table_form_label">内容：</td>
						<td class="table_form_field">
							<textarea id="content" name="content" style="height: 200px; margin-top: 8px; margin-bottom: 6px;"></textarea>
						</td>
					</tr>
				</table>
				
				<div style="width: 100%; text-align: center; margin: 26px auto 16px;">
					<a class="a_btn medium" href="javascript:submitMemo();">保存备忘</a>
				</div>
				</form>
			</div>
			
			<div id="memo_list">
				<c:forEach items="${memoList}" varStatus="i" var="memo">
					<div class="item">
						<span style="width: 59%; overflow: hidden; word-wrap: break-word; white-space: nowrap; margin-right: 6px; border: 1px solid red; padding: 4px;" onclick="show(${i.index})">${memo.title}</span>
						<span style="width: 15%; float: right; border: 1px solid red;">
							<a href="${ctx}/Memo.action?queryOne&id=${memo.id}">编辑</a>
							<a href="${ctx}/Memo.action?deleteMemo&id=${memo.id}">删除</a>
						</span>
						<span style="width: 24%; float: right; border: 1px solid red;"><fmt:formatDate value="${memo.updateTime}" pattern="yyyy-MM-dd HH:mm"/></span>
					</div>
					
					<div id="memo_content_${i.index}" style="border: 1px solid gray; width: 80%; margin: 0 auto 4px; display: none; word-wrap: break-word; word-break: break-all;">
						${memo.content}
					</div>
				</c:forEach>
			</div>
		</div>
		
		<script type="text/javascript" src="${ctx}/static/js/auto_load.js"></script>
	</body>
</html>
