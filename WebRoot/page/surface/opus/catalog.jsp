<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>乐在文学-简介</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/floatPage.js"></script>
  </head>
  
  <body class="body">
  	<jsp:include page="/page/frame/header.jsp"></jsp:include>
  	<jsp:include page="/page/frame/search.jsp"></jsp:include>
  	
  	<div class="container">
  		<div class="content_wrapper upper_border_radius">
  			<div class="book_title">${book.bookName}</div>
  			<div style="margin-left: 30%; margin-top: 6px; width: 40%;">
  				作者：<a href="">${book.authorId}</a>&nbsp;&nbsp;&nbsp;&nbsp;
  				更新时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${book.updateTime}"/>
  			</div>
  			<div style="width: 90%; margin-left: 5%;">
  				<c:forEach items="${volumeList}" var="volume">
	  			<div class="volume" style="margin-top: 4px;">
	  				${volume.volumeName}
	  			</div>
	  			<div class="catalog">
		  			<ul>
		  				<c:forEach items="${chapterList}" var="chapter">
		  					<c:if test="${chapter.volumeId == volume.id}">
				  				<li title="${chapter.subject}">
				  					<a href="${pageContext.request.contextPath}/Book.action?readChapter&chapterId=${chapter.id}" target="_blank">${chapter.subject}</a>
				  				</li>
			  				</c:if>
		  				</c:forEach>
		  			</ul>
	  			</div>
	  			</c:forEach>
  			</div>
	  	</div>
  	</div>
  	
  	<jsp:include page="/page/frame/footer.jsp"></jsp:include>
  	<script type="text/javascript">
  	popLoginDiv();
  	</script>
  </body>
</html>
