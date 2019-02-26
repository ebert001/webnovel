<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>蜘蛛网站</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css"/>
	<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
  </head>
  
  <body>
  	<jsp:include page="/page/config/setup_common.jsp"></jsp:include>
  	<div id="embed_area">
  	<div style="margin: 20px 6px; ">
  		蜘蛛网站
  	</div>
  	
  	<div id="no1">
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
	   		<tr>
	   			<td>
	   				<a href="${ctx}/book/listChapter?bookId=${book.id}&a=r">${book.bookName}</a>
	   			</td>
	   			<td><fmt:formatDate value="${book.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	   			<td><fmt:formatDate value="${book.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	   			<td>${book.status}</td>
	   			<td>
	   				<a href="${ctx}/Book.action?deleteBook&id=${book.id}">删除</a>
	   			</td>
	   		</tr>
	   		</c:forEach>
	   		</tbody>
	   	</table>
    </div>
  </body>
</html>
