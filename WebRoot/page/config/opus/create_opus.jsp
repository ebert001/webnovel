<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix= "fmt" uri= "http://java.sun.com/jsp/jstl/fmt"%> 

<!DOCTYPE HTML>
<html>
  <head>
    <title>我的作品</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar/WdatePicker.js"></script>
	<style type="text/css">
	</style>
	<script type="text/javascript">
	
	</script>
  </head>
  
  <body>
  	<jsp:include page="/config/user_setup.jsp"></jsp:include>
  	<div id="embed_area">
	<div id="input_area">
    	<form name="bookForm" action="${pageContext.request.contextPath}/Book.action?addBook" method="post">
  		<table class="table_form has_border">
    		<tr class="bottom_border">
    			<td class="table_form_label">书名：</td>
    			<td class="table_form_field">
    				<input class="input_wrapper input_required" style="width: 520px;" title="书名" type="text" name="bookName" value="">
    			</td>
    		</tr>
    		<tr class="bottom_border">
    			<td class="table_form_label">简介：</td>
    			<td class="table_form_field">
    				<textarea name="desc" style="width: 90%; height: 120px; margin-top: 8px; margin-bottom: 6px;" title="简介"></textarea>
    			</td>
    		</tr>
    	</table>
    	
    	<div style="width: 100%; text-align: center; margin: 26px auto 16px auto;">
	    	<a class="a_btn medium" href="javascript:checkAndSubmit('bookForm');">创建新作品</a>
	    </div>
  		</form>
    </div>
    
    <div>
   	 共有 ${bookList.size()} 部作品
    </div>
    
  	<table class="table_list" >
  		<thead>
   		<tr class="title">
   			<td width="10%">作品名称</td>
   			<td width="10%">创建时间</td>
   			<td width="8%">最近更新时间</td>
   			<td width="8%">状态</td>
   			<td width="3%">操作</td>
   		</tr>
   		</thead>
   		<tbody>
   		<c:forEach items="${bookList}" varStatus="i" var="book">
   		<tr <c:if test="${i.index % 2 == 1}">class="swap"</c:if>>
   			<td>
   				<a href="${pageContext.request.contextPath}/Book.action?listChapter&bookId=${book.id}">${book.bookName}</a>
   			</td>
   			<td><fmt:formatDate value="${book.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
   			<td><fmt:formatDate value="${book.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
   			<td>${book.status}</td>
   			<td>
   				<a href="${pageContext.request.contextPath}/Book.action?deleteBook&id=${book.id}">删除</a>
   			</td>
   		</tr>
   		</c:forEach>
   		</tbody>
   	</table>
   	</div>
    <script type="text/javascript">
    loadEmbedPage("setup_content", "embed_area");
    </script>
  </body>
</html>
