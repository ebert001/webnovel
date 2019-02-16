<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>上传头像</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<jsp:include page="/page/config/user_setup.jsp"></jsp:include>
  	<div id="embed_area">
	  	<form action="${ctx}/user/uploadAvatar" method="post">
		    <table>
		    	<tr>
		    		<td colspan="2">
		    			<div style="border: 2px solid red; width: 200px; height: 200px;">
		    			<img src="${ctx}/imgs/d_avatar.png">
		    			</div>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>个性化头像：</td>
		    		<td><input type="file" name="avatar"></td>
		    	</tr>
		    	<tr>
		    		<td></td>
		    		<td>
		    			<button type="submit">上传</button>
		    		</td>
		    	</tr>
		    </table>
	    </form>
    </div>
    <script type="text/javascript">
    loadEmbedPage("setup_content", "embed_area");
    </script>
  </body>
</html>
