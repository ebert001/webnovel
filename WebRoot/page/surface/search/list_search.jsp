<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>乐在文学-简介</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/surface-head.jsp"%>
  </head>
  
  <body class="body">
  	<%@include file="/page/frame/header.jsp"%>
  	<%@include file="/page/frame/search.jsp"%>
  	
  	<div class="container">
  		<div class="content_wrapper upper_border_radius">
  			<div class="search_list_left">
  				查询历史
  			</div>
  			<div class="search_list_right">
  				<div class="search_result_title">查询结果：共123条记录，用时0.0123秒</div>
	  			<ul>
	  				<li>
	  					<div>书名：水浒传</div>
	  					<div>作者：施耐庵</div>
	  					<div>内容简介：水浒传</div>
	  					<div>最后更新时间：</div>
	  				</li>
	  			</ul>
	  			<div>
	  				<script type="text/javascript">
	  				document.write(paginationBar(2, 15, "${ctx}/book/search?queryOne&id=${forumSubject.id}"));
	  				</script>
	  			</div>
  			</div>
	  	</div>
  	</div>
  	
  	<%@include file="/page/frame/footer.jsp"%>
  </body>
</html>
