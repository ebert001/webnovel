<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>我的备忘</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css"/>
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
	
	<style type="text/css">
	#memo_list ul {
		list-style: none;
		width: 80%;
		margin: 0 auto 4px;
		background: #A4E2C6;
	}
	#memo_list ul li {
		display: inline;
		line-height: 28px;
	}
	</style>
	
  </head>
  
  <body>
  	<%@include file="/page/config/user_setup.jsp"%>
  	<div id="embed_area">
    <div id="input_area">
    	<form name="memoForm" action="${ctx}/Memo.action?updateMemo" method="post">
  		<table class="table_form has_border">
    		<tr class="bottom_border">
    			<td class="table_form_label">标题：</td>
    			<td class="table_form_field">
    				<input class="input_wrapper" style="width: 520px;" type="text" name="title" value="${memo.title}">
    			</td>
    		</tr>
    		<tr class="bottom_border">
    			<td class="table_form_label">内容：</td>
    			<td class="table_form_field">
    				<textarea name="content" style="width: 90%; height: 400px; margin-top: 8px; margin-bottom: 6px;">${memo.content}</textarea>
    			</td>
    		</tr>
    	</table>
    	<input type="hidden" name="id" value="${memo.id}">
    	
    	<div style="width: 100%; text-align: center; margin: 26px auto 16px auto;">
	    	<a class="a_btn medium" href="javascript:doSubmit('memoForm');">保存备忘</a>
	    	<a class="a_btn medium" href="javascript:history.go(-1);">返回</a>
	    </div>
  		</form>
    </div>
    </div>
    <script type="text/javascript">
    loadEmbedPage("setup_content", "embed_area");
    </script>
  </body>
</html>
