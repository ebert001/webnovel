<#include "/taglibs.ftl">

<!DOCTYPE HTML>
<html>
  <head>
    <title>${website_name!"Novel"}</title>
    
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="shortcut icon" type="image/x-icon" href="${ctx}/static/imgs/logo/favicon.ico" >
	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css" />
	
	<script type="text/javascript" src="${ctx}/static/lib/jquery/1.12.3/jquery.min.js" ></script>
	<script type="text/javascript" src="${ctx}/static/js/common.js" ></script>
	<script type="text/javascript" src="${ctx}/static/js/floatPage.js" ></script>
	
  </head>
  
  <body class="body">
  	<#include "/frame/header.ftl">
  	
  	<div class="container">
  		<div class="menu_wrapper">
			<ul class="menu">
				<li><a href="#">网络文学</a></li>
			</ul>
		</div>
	  	<div class="content_wrapper" id="__setup_content">
	
		</div>
  	</div>
  	
  	<#include "/frame/footer.ftl">
  </body>
  
  <script type="text/javascript" src="${ctx}/static/js/auto_load.js" ></script>
</html>
