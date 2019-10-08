<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>待审核章节</title>
		<%@include file="/css-js.jsp"%>
	</head>
	
	<body class="body">
		<%@include file="/page/config/setup_common.jsp"%>
		<div id="embed_area">
			<div class="content_title">待审核章节-${chapter.subject}</div>
			<form id="form_unaudit_chapters" action="${ctx}/spider/toUnauditChapters" method="post">
				<input type="hidden" name="bookId" value="${chapter.id}">
				<div class="content_wrapper upper_border_radius">
		  			<div class="book_title">${chapter.subject}</div>
		  			<div class="book_content">
						${chapter.content}
		  			</div>
		  			<div style="text-align: center; margin: 16px auto 10px;;">
						<a class="a_btn" href="">上一页</a>
						<a class="a_btn" href="${ctx}/book/listChapter?bookId=${chapter.bookId}&a=r">回目录</a>
						<a class="a_btn" href="${ctx}/book/readChapter?chapterId=${chapter.id}">下一页</a>
					</div>
			  	</div>		
			</form>
		</div>
		<script type="text/javascript" src="${ctx}/static/js/auto_load.js"></script>
	</body>
</html>
