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
	
	<link rel="shortcut icon" href="${ctx}/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css"/>
	<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/floatPage.js"></script>
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
	  				<li>第二节</li>
	  				<li>第三节</li>
	  				<li>第四节</li>
	  				<li>第五节</li>
	  			</ul>
	  			<div>
	  				<script type="text/javascript">
	  				document.write(paginationBar(2, 15, "${ctx}/Forum.action?queryOne&id=${forumSubject.id}"));
	  				</script>
	  			</div>
  			</div>
	  	</div>
  	</div>
  	
  	<%@include file="/page/frame/footer.jsp"%>
  	<script type="text/javascript">
  	popLoginDiv();
  	</script>
  </body>
</html>
