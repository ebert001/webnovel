<#include "/taglibs.ftl">

<!DOCTYPE HTML>
<html>
  <head>
    <title>${website_name}--登录</title>

    <meta http-equiv="content-type" content="text/html;charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="乐文,乐在文学">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css">
	<style type="text/css">
	table {
		border: 1px solid #8BB8F2;
		background-color: #FFFFFF;
	}
	.left {
		
	}
	.right {
		
	}
	</style>
  </head>
  
  <body class="">
  	<div class="container">
  		<div class="left">
  		
  		</div>
	  	<div class="right">
		    <form action="${ctx}/user/login" method="post">
			   	<table>
			   		<tr>
			   			<td>用户名：</td>
			   			<td>
			   				<input type="text" name="wnUsername">
			   			</td>
			   		</tr>
			   		<tr>
			   			<td>密&nbsp;&nbsp;码：</td>
			   			<td>
			   				<input type="password" name="wnPassword">
			   			</td>
			   		</tr>
			   		<tr>
			   			<td>验证码：</td>
			   			<td>
			   				<input style="width: 80px;" type="text" name="validcode">
			   			</td>
			   		</tr>
			   		<tr>
			   			<td colspan="2">
			   				<div style="width: 100%; text-align: center; margin: 26px auto 16px auto;">
						    	<button class="a_btn medium" href="${ctx}/User.action?queryOne&uName=">登  录</button>
						    </div>
			   			</td>
			   		</tr>
			   	</table>
		    </form>
	    </div>
    </div>
  </body>
</html>
