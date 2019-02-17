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
	
	<link rel="shortcut icon" href="${ctx}/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css"/>
	<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/floatPage.js"></script>
	<script type="text/javascript">
	function doNext() {
	}
	
	function doPrevious() {
	}
	
	function doBackCatalog() {
	}
	
	function addReadEvent(evt) {
		evt = EventUtil.getEvent(evt);
		var keyCode = EventUtil.getKeyCode(evt);
		// alert(evt.keyCode + "," + EventUtil.getASCIICharCode(evt) + "," + evt.which);
		// 39 右键 78 n键 捕获键盘事件 下一页
		if (keyCode == 39 || keyCode == 78 || keyCode == 110) {
			doNext();
		}
		// 37 左键 80 p键 捕获键盘事件 上一页
		if (keyCode == 37 || keyCode == 80 || keyCode == 112) {
			doPrevious();
		}
		// 67 c键(catalog) 捕获键盘事件 回目录
		if (keyCode == 67 || keyCode == 99) {
			doBackCatalog();
		}
	}
	// 浏览器兼容性：
	EventUtil.addHandle(document, "keyup", addReadEvent);
	</script>
  </head>
  
  <body class="body">
  	<jsp:include page="/page/frame/header.jsp"></jsp:include>
  	
  	<div class="container">
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
  	</div>
  	
  	<jsp:include page="/page/frame/footer.jsp"></jsp:include>
  	<script type="text/javascript">
  	popLoginDiv();
  	</script>
  </body>
</html>
