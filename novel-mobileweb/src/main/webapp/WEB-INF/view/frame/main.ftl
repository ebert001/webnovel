<#include "/taglibs.ftl">

<!DOCTYPE HTML>
<html>
  <head>
    <title>${website_name!"Novel"}</title>
    
	<meta charset="UTF-8">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="format-detection" content="telephone=no" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="shortcut icon" type="image/x-icon" href="${ctx}/static/imgs/logo/favicon.ico" >
	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css" />
	
	<script type="text/javascript" src="${ctx}/static/lib/jquery/1.12.3/jquery.min.js" ></script>
	<script type="text/javascript" src="${ctx}/static/js/common.js" ></script>
	
  </head>
  
  <body class="body">
  	<#include "/frame/header.ftl">
  	
  	<div class="container">
	  	<div class="content-wrapper" id="--setup-content">
	
		</div>
  	</div>
  	
  	<#include "/frame/footer.ftl">
  </body>
  
  <script type="text/javascript" src="${ctx}/static/js/auto-load.js" ></script>
</html>
