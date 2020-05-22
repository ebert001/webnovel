<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<div class="content_wrapper">
	<!-- 默认加载阅读量最大的30本书籍 -->
	<div>
		<c:forEach items="${top30}" var="book">
		<div>
			<span><a href="${ctx}/book/listChapters?bookId=${book.id}&a=r">${book.name}</a></span>
			<span>${book.clickTimes}</span>
			<span>${book.updateTime}</span>
		</div>
		</c:forEach>
	</div>
</div>
