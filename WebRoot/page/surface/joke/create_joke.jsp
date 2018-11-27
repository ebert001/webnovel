<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>写段子</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
  </head>
  
  <body class="body">
  	<jsp:include page="/frame/header.jsp"></jsp:include>
  	
    <div class="container">
    	<jsp:include page="joke_menu.jsp"></jsp:include>
    
  		<div style="background-color: #FFFFFF; height: 300px;">
  			<div style="background-color: #FFFDDD; font: 24px bolder;">写段子</div>
  			<form action="" method="post">
  				<table class="table_form full_border">
		    		<tr class="bottom_border">
		    			<td class="table_form_label">标题：</td>
		    			<td class="table_form_field">
		    				<input class="input_wrapper" style="width: 520px;" type="text" name="title" value="">
		    			</td>
		    		</tr>
		    		<tr class="bottom_border">
		    			<td class="table_form_label">内容：</td>
		    			<td class="table_form_field">
		    				<textarea id="content" name="content" style="height: 200px; margin-top: 8px; margin-bottom: 6px;"></textarea>
		    			</td>
		    		</tr>
		    		
		    		<tr>
		    			<td></td>
		    			<td>
		    				<button type="sbumit" value="发布">发布</button>
		    			</td>
		    		</tr>
		    	</table>
  			</form>
  		</div>
  	</div>
  </body>
</html>
