<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>${psupport.get("app.name")}</title>
		
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">		
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<%@include file="/surface-head.jsp"%>
	</head>
	
	<body class="body">
		<%@include file="/page/frame/header.jsp"%>
		<%@include file="/page/frame/search.jsp"%>
		
		<div class="container">
			<div class="content_wrapper upper_border_radius">
				<div class="book_title">${book.bookName}</div>
				<div style="margin-left: 30%; margin-top: 6px; width: 40%;">
					作者: <a href="">${book.authorId}</a>&nbsp;&nbsp;&nbsp;&nbsp;
					更新时间: <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${book.updateTime}"/>
					状态: 
				</div>
				<div style="width: 90%; margin-left: 5%;">
					<c:forEach items="${volumeList}" var="volume">
					<div class="volume" style="margin-top: 4px;">
						${volume.volumeName}
					</div>
					</c:forEach>
					<div class="catalog">
						<ul>
							<c:forEach items="${chapterList}" var="chapter">
								<c:if test="${chapter.volumeId == volume.id}">
									<li title="${chapter.subject}">
										<a href="${ctx}/book/readChapter?chapterId=${chapter.id}" target="_blank">${chapter.subject}</a>
									</li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
		
		<%@include file="/page/frame/footer.jsp"%>
	</body>
</html>
