<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>我的书架</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
  </head>
  
  <body>
  	<jsp:include page="/config/user_setup.jsp"></jsp:include>
  	<div id="embed_area">
  	<div style="margin: 20px 6px; ">
  		我的书架
  	</div>
  	
  	<div id="no1" style="display: none;">
	    <table class="table_list">
	    	<tr class="title">
	    		<td>书名</td>
	    		<td>作者</td>
	    		<td>类型</td>
	    		<td>状态</td>
	    	</tr>
	    	<tr class="swap">
	    		<td>
	    			<a href="#">三国演义</a>
	    		</td>
	    		<td>
	    			<a href="#">罗贯中</a>
	    		</td>
	    		<td>
	    			<a href="#">武侠小说</a>
	    		</td>
	    		<td>
	    			<a href="#">完本</a>
	    		</td>
	    	</tr>
	    </table>
    </div>
  	
  	<script type="text/javascript">
		var arrMenu = ["我的1号书架", "我的2号书架"]; 
		var arrContent = [document.getElementById("no1").innerHTML, "苏轼"];
		createTab('0', 2, arrMenu, arrContent, '');
	</script>
	</div>
    <script type="text/javascript">
    loadEmbedPage("setup_content", "embed_area");
    </script>
  </body>
</html>
