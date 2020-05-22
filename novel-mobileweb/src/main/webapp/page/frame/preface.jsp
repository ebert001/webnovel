<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>乐在文学-简介</title>
    
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="shortcut icon" href="${ctx}/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css"/>
	<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/floatPage.js"></script>
  </head>
  
  <body class="body">
  	<%@include file="header.jsp"%>
  	<%@include file="search.jsp"%>
  	
  	<div class="container">
  		<%@include file="menu.jsp" %>
	  	<%@include file="/page/surface/opus/about_book.jsp"%>
  	</div>
  	
  	<%@include file="footer.jsp"%>
  	<script type="text/javascript">
  	popLoginDiv();
  	</script>
  </body>
</html>
