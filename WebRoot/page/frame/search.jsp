<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<div class="search_wraper">
	<div class="search_content">
		<input type="text" class="search_input" id="s_k" name="s_k" placeholder="书名/作者">
		<a class="btn btn-success" href="#"  onclick="javascript: window.open('${ctx}/frame/search_list.jsp')"> 搜 索 </a>
	</div>
	<div class="search_hot">
		热搜文章：<a href="${ctx}/book/view?id=1">莎翁全集</a>
	</div>
</div>