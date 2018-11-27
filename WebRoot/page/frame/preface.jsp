<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>乐在文学-简介</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/floatPage.js"></script>
  </head>
  
  <body class="body">
  	<jsp:include page="header.jsp"></jsp:include>
  	<jsp:include page="search.jsp"></jsp:include>
  	
  	<div class="container">
  		<%@include file="menu.jsp" %>
	  	<jsp:include page="/surface/opus/about_book.jsp"></jsp:include>
  	</div>
  	
  	<jsp:include page="footer.jsp"></jsp:include>
  	<script type="text/javascript">
  	popLoginDiv();
  	</script>
  </body>
</html>
