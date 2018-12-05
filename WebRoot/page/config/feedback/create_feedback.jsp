<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>意见反馈</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css"/>
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
  </head>
  
  <body>
  	<jsp:include page="/config/user_setup.jsp"></jsp:include>
  	<div id="embed_area">
  	<form name="feedbackForm" action="${ctx}/Feedback.action?addFeedback" method="post">
  		<table class="table_form has_border">
    		<tr class="bottom_border">
    			<td class="table_form_label">标题：</td>
    			<td class="table_form_field">
    				<input class="input_wrapper" type="text" name="title" style="width: 100%;" value="">
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
    <script type="text/javascript">
    loadEmbedPage("setup_content", "embed_area");
    </script>
  </body>
</html>
