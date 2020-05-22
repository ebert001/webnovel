<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
	<meta http-equiv= "Content-Type" content= "text/html; charset=UTF-8">
  </head>
  <body>
  	<%@include file="about.jsp"%>
  	<div id="embed_area">
  		<%@include file="author_princple.jsp"%>
	</div>
    <script type="text/javascript">
    loadEmbedPage("setup_content", "embed_area");
    </script>
  </body>
</html>
