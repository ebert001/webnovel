<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

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
  </head>
  
  <body class="body">
    <jsp:include page="/page/frame/header.jsp"></jsp:include>
    
    <div class="container">
  		<div class="content_wrapper none_space upper_border_radius">
  			<div class="bbs_left">
  				<div style="background-color: #FF8C00; line-height: 28px; text-indent: 22px;">论坛板块</div>
  				<ul>
  					<li>经济论坛</li>
  					<li>法律论坛</li>
  					<li>舞文弄墨</li>
  					<li>诗词歌赋</li>
  					<li>琴棋书画</li>
  					<li>视频专区</li>
  					<li>游戏地带</li>
  					<li>娱乐八卦</li>
  					<li>情感天地</li>
  					<li>饮食男女</li>
  					<li>家具装饰</li>
  					<li>旅游休闲</li>
  					<li>国际观察</li>
  					<li>心灵鸡汤</li>
  					<li>数码生活</li>
  					<li>IT视界</li>
  					<li>灌水专区</li>
  				</ul>
  			</div>
  			<div class="bbs_right">
  				<div style="background-color: #FF8C00; line-height: 28px;">
  					虚位以待
  				</div>
  				<div style="line-height: 32px; margin-top: 6px;">
  					搜索：<input class="input_wrapper" style="width: 320px;" type="text" name="bbs_subject"/>
  					<a class="a_btn small" href="#"  onclick="javascript: window.open('${pageContext.request.contextPath}/frame/search_list.jsp')"> 搜 索 </a>
  				</div>
  				<div style="margin-top: 6px; float: right;">
  					<a class="a_btn medium" href="${pageContext.request.contextPath}/frame/bbs_make.jsp">发表新帖</a>
  				</div>
	  			<table class="table_list">
	  				<thead>
	  				<tr class="title">
	  					<td style="width: 6%;">编号</td>
	  					<td style="width: 40%;">主题</td>
	  					<td style="width: 12%;">作者</td>
	  					<td style="width: 11%;">浏览</td>
	  					<td style="width: 11%;">回复</td>
	  					<td style="width: 20%;">最后跟帖时间</td>
	  				</tr>
	  				</thead>
	  				<tbody>
	  				<c:forEach items="${forumSubjectList}" var="fs" varStatus="item">
	  				<tr <c:if test="${item.index % 2 == 0}">class="swap"</c:if>>
	  					<td>${item.count}</td>
	  					<td>
	  						<a href="${pageContext.request.contextPath}/Forum.action?queryOne&id=${fs.id}">${fs.subject}</a>
	  					</td>
	  					<td>${fs.userId}</td>
	  					<td>${fs.readTimes}</td>
	  					<td>${fs.replyTimes}</td>
	  					<td><fmt:formatDate value="${fs.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	  				</tr>
	  				</c:forEach>
	  				</tbody>
	  			</table>
	  			<div>
	  				<script type="text/javascript">
	  				document.write(paginationBar(2, 100, "${pageContext.request.contextPath}/Forum.action?queryOne&id=${forumSubject.id}"));
	  				</script>
	  			</div>
  			</div>
  		</div>
  	</div>
  </body>
</html>
