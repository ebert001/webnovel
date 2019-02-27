<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>访问历史</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css"/>
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
  </head>
  
  <body>
  	<%@include file="/page/config/user_setup.jsp"%>
  	<div id="embed_area">
    <table class="table_list">
    	<caption style="margin-top: 30px; font-weight: bold; font-size: 28px;">我的浏览历史</caption>
    	<thead>
    	<tr class="title">
    		<td>书名</td>
    		<td>作者</td>
    		<td>类型</td>
    	</tr>
    	</thead>
    	<tbody>
    	<tr class="swap">
    		<td>
    			<a href="">三国演义</a>
    		</td>
    		<td>
    			<a href="">罗贯中</a>
    		</td>
    		<td>
    			<a href="">武侠小说</a>
    		</td>
    	</tr>
    	</tbody>
    </table>
    </div>
    <script type="text/javascript">
    loadEmbedPage("setup_content", "embed_area");
    </script>
  </body>
</html>
