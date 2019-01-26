<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>乐在论坛-发表新帖</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/floatPage.js"></script>
	
	<style type="text/css">
	.block_width {
		margin: 60px 10%;
		width: 80%;
		padding: 5px;
	}
	</style>
  </head>
  
  <body class="body">
    <jsp:include page="/page/frame/header.jsp"></jsp:include>
    
    <div class="container">
  		<div class="content_wrapper none_space upper_border_radius">
  			<div class="block_width">
	  			<form action="${pageContext.request.contextPath}/Forum.action?addForumSubject" name="forumSubject" method="post">
	  			<table class="table_form has_border">
	  				<tr class="bottom_border">
	  					<td class="table_form_label">标题</td>
	  					<td class="table_form_field">
	  						<input class="input_wrapper input_required" style="width: 100%;" name="subject" type="text"/>
	  					</td>
	  				</tr>
	  				<tr class="bottom_border">
	  					<td class="table_form_label">内容</td>
	  					<td class="table_form_field">
	  						<script type="text/javascript">
				  			document.write(createDivEditor('content', 'content'));
				  			</script>
	  					</td>
	  				</tr>
	  				<tr class="bottom_border">
	  					<td class="text_center padding_top padding_bottom" colspan="2">
	  						<a class="a_btn medium" href="javascript: doSubmit('forumSubject')">发表新帖</a>
	  					</td>
	  				</tr>
	  			</table>
	  			</form>
  			</div>
  		</div>
  	</div>
  </body>
</html>
