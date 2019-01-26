<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
	<meta http-equiv= "Content-Type" content= "text/html; charset=UTF-8">
  </head>
  <body>
  	<jsp:include page="about.jsp"></jsp:include>
  	<div id="embed_area">
  		<jsp:include page="author_princple.jsp"></jsp:include>
	</div>
    <script type="text/javascript">
    loadEmbedPage("setup_content", "embed_area");
    </script>
  </body>
</html>
