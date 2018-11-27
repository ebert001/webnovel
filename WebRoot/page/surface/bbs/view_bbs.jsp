<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix= "fmt" uri= "http://java.sun.com/jsp/jstl/fmt"%> 

<!DOCTYPE HTML>
<html>
  <head>
    <title>乐在论坛</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/floatPage.js"></script>
	
	<style type="text/css">
	.title {
		margin-top: 16px;
		margin-bottom: 6px;
		text-align: center;
		font-size: 28px;
		line-height: 36px;
		font-weight: bold;
		border-bottom: 1px solid gray;
	}
	.mini_msg_list {
		margin-top: 8px;
	}
	.mini_msg_list ul li {
		margin-bottom: 22px;
	}
	.mini_msg_top {
		padding-left: 6px;
		display: block;
		line-height: 32px;
		background-color: #f2eada;
	}
	.mini_msg_top_left {
		display: inline-block; 
	}
	.mini_msg_top_right {
		display: inline-block; 
		float: right;
	}
	.mini_msg_top_right a {
		float: none;
	}
	.mini_msg_middle {
		padding-left: 6px;
		padding-top: 8px;
		padding-bottom: 8px;
		border-bottom: 1px solid blue;
		background-color: #afdfe4;
		
		word-wrap:break-word; 
	}
	.mini_msg_bottom {
		padding-right: 6px;
		text-align: right;
		background-color: #f6f5ec;
		line-height: 22px;
	}
	.mini_msg_width {
		width: 80%;
		margin-left: 10%;
	}
	.new_bbs {
		margin-top: 8px;
		float: right;
		position: fixed;
	}
	.bbs_reply {
		float: left;
	}
	</style>
  </head>
  
  <body class="body">
    <jsp:include page="/frame/header.jsp"></jsp:include>
    
    <div class="container">
  		<div class="content_wrapper none_space upper_border_radius">
  			<div class="mini_msg_width">
  				<div class="title">这是帖子标题</div>
  				<div style="text-align: center;">
  				查看：${forumSubject.readTimes} 回复：${forumSubject.replyTimes}
  				</div>
  			</div>
  			<div class="new_bbs">
  				<a class="a_btn medium" href="${pageContext.request.contextPath}/frame/bbs_make.jsp">发表新帖</a><br/><br/>
  				<a class="a_btn medium" href="${pageContext.request.contextPath}/Forum.action?list">查看新帖</a>
  			</div>
	  		<div class="mini_msg_list mini_msg_width">
				<ul>
					<c:if test="${forumSubject != null}">
					<li>
						<div class="mini_msg_top">
							<div class="mini_msg_top_left">
								<b>楼主</b> 【${forumSubject.userId}】发表时间:<fmt:formatDate value="${forumSubject.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</div>
						</div>
						<div class="mini_msg_middle">
						${forumSubject.content}
						</div>
						<div class="mini_msg_bottom">
						赞 拍砖 
						<a href="#">回复此帖</a>
						</div>
					</li>
					</c:if>
					<c:forEach items="${forumList}" var="fl" varStatus="item">
					<li>
						<div class="mini_msg_top">
							<div class="mini_msg_top_left">
								<span style="font-weight: bold;">${(pageNo - 1) * 20 + item.count}</span> 楼
								 【<a href="#">${fl.userId}</a>】
								 发表时间:<fmt:formatDate value="${fl.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</div>
						</div>
						<div class="mini_msg_middle">
						${fl.content}
						</div>
						<div class="mini_msg_bottom">
						赞 拍砖 
						<a href="#">回复此帖</a>
						</div>
					</li>
					</c:forEach>
				</ul>	  		
	  		</div>
	  		
	  		<div class="mini_msg_width">
  				<script type="text/javascript">
  				document.write(paginationBar(${pageNo}, ${pageCount}, "${pageContext.request.contextPath}/Forum.action?queryOne&id=${forumSubject.id}"));
  				</script>
  			</div>
	  		
	  		<div class="bbs_reply mini_msg_width">
	  			<form action="${pageContext.request.contextPath}/Forum.action?replyForum" method="post" name="reply">
	  			<input type="hidden" name="id" value="${forumSubject.id}">
	  			<input type="hidden" name="page" value="${pageCount}">
	  			<script type="text/javascript">
	  			document.write(createDivEditor('content', 'content'));
	  			</script>
	  			<br>
	  			<a class="a_btn medium" href="javascript:doSubmit('reply')">回复</a>
	  			</form>
	  		</div>
  		</div>
  	</div>
  </body>
</html>
