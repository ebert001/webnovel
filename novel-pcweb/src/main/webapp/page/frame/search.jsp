<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<div class="search_wraper">
	<div class="search_content">
		<form action="${ctx}/book/search" method="get">
			<input type="text" class="search_input" id="q" name="q" autocomplete="off" autofocus="true" placeholder="书名/作者">
			<button> 搜 索 </button>
		</form>
	</div>
	<div class="search_hot">
		
	</div>
</div>