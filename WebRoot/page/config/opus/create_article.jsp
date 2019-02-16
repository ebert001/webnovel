<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>> 

<!DOCTYPE HTML>
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css"/>
	<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
	
	<style type="text/css">
	#article {
		border: none; 
		height: 320px; 
		width: 100%;
	}
	.web_editor {
		border: 1px solid gray;
		margin-bottom: 20px;
	}
	.tool_bar {
		border-bottom: 1px solid gray;
		height: 32px;
	}
	.title {
		border-bottom: 1px solid gray;
		width: 99%;
		margin: 0 auto;
		height: 36px;
	}
	.title input {
		border: none;
		width: 100%;
		text-align: center;
		height: 34px;
		line-height: 30px;
		font-size: 28px;
		font-weight: bold;
	}
	.div_line {
		height: 36px;
	}
	.div_label {
		background-color: #A4E2C6;
		line-height: 36px;
		width: 120px;
		display: inline-block;
	}
	.div_ele {
		display: inline-block;
	}
	</style>
  </head>
  
  <body>
  	<jsp:include page="/page/config/user_setup.jsp"></jsp:include>
  	<div id="embed_area">
  	<form name="chapterForm" action="${ctx}/book/addChapter" method="post">
  	<input type="hidden" name="volumeId" value="${chapter.volumeId}">
  	<input type="hidden" name="chapterId" value="${chapter.id}">
  	<div class="div_line">
  		<div class="div_label">章节所属书籍</div>
  		<div class="div_ele">
  			<select class="input_wrapper input_required" name="bookId">
  				<c:forEach items="${bookList}" var="book">
				<option value="${book.id}" <c:if test="${book.id == bookId}">selected</c:if>>${book.bookName}</option>
				</c:forEach>
			</select>
  		</div>
	</div>
  	<div class="web_editor">
  		<div class="title">
  			<input type="text" name="subject" value="${chapter.subject}"
			onblur="javascript: if (this.value == '') {this.value='文章标题'};"
			onfocus="javascript: if (this.value == '文章标题') {this.value=''};"/>
  		</div>
  		<div class="content input_required">
  			<script type="text/javascript">
	  		document.write(createDivEditor('content', 'content'));
	  		</script>
  		</div>
  		
  	</div>
  	
	<div style="text-align: center;">
		<a class="a_btn medium" href="#" onclick="doSubmit('chapterForm');">保存文章</a>
		<a class="a_btn medium" href="#">保存草稿</a>
	</div>
  	</form>
  	</div>
    <script type="text/javascript">
    loadEmbedPage("setup_content", "embed_area");
    </script>
  </body>
</html>
