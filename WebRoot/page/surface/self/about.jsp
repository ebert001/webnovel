<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>乐在相关</title>
	<link rel="shortcut icon" href="${ctx}/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css"/>
	<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/floatPage.js"></script>
  </head>
  
  <body class="body">
  	<jsp:include page="/page/frame/header.jsp"></jsp:include>
  	<jsp:include page="/page/frame/search.jsp"></jsp:include>
	  	
  	<div class="container">
  		<div class="content_wrapper upper_border_radius none_space">
  			<jsp:include page="/page/frame/menu.jsp"></jsp:include>
  			<div class="left_bar">
	  			<dl>
	  				<dt>广而告之</dt>
					<dd><a href="#">关于乐文</a></dd>
					<dd><a href="#">作者协议</a></dd>
					<dd><a href="#">联系方式</a></dd>
					<dd><a href="#">发布广告</a></dd>
					<dd><a href="#">网站招聘</a></dd>
					<dd><a href="#">用户指南</a></dd>
				</dl>
				<div style="height: 8px;"></div>
			</div>
  			
			<!-- 动态内容加载区域 -->
  			<div id="setup_content" class="setup_content" style="float: left;">
  			</div>
	  	</div>
  	</div>
  	
  	<jsp:include page="/page/frame/footer.jsp"></jsp:include>
  	<script type="text/javascript">
  	popLoginDiv();
  	</script>
  </body>
</html>
