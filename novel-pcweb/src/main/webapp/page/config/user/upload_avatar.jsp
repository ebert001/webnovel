<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>上传头像</title>
		<%@include file="/css-js.jsp"%>
	</head>
	
	<body class="body">
		<%@include file="/page/config/setup_common.jsp"%>
		<div id="embed_area">
			<div class="content_title">上传头像</div>
			
			<form action="${ctx}/user/uploadAvatar" method="post">
				<table>
					<tr>
						<td colspan="2">
							<div style="border: 2px solid red; width: 200px; height: 200px;">
							<img src="${ctx}/static/imgs/d_avatar.png">
							</div>
						</td>
					</tr>
					<tr>
						<td>个性化头像：</td>
						<td><input type="file" name="avatar"></td>
					</tr>
					<tr>
						<td></td>
						<td>
							<button type="submit">上传</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<script type="text/javascript" src="${ctx}/static/js/auto_load.js"></script>
	</body>
</html>
