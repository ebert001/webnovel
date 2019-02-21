<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>乐在文学-用户设置</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    
	<link rel="shortcut icon" href="${ctx}/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css"/>
	<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/floatPage.js"></script>
  </head>
  
  <body class="body">
  	<jsp:include page="../frame/header.jsp"></jsp:include>
  	<jsp:include page="../frame/search.jsp"></jsp:include>
  	
  	<div class="container">
  		<div class="content_wrapper upper_border_radius none_space">
  			<div class="setup_tree padding_top">
  				<ul>
  					<li class="tree_group">用户设置</li>
  					<li><a href="${ctx}/user/info">基本信息</a></li>
  					<li><a href="${ctx}/user/toUpdatePwd">修改密码</a></li>
  					<li><a href="${ctx}/user/list">用户管理</a></li>
  					<li><a href="${ctx}/user/toUploadAvatar">上传头像</a></li>
  					<li><a href="${ctx}/config/history/list_history.jsp">访问历史</a></li>
  					
  					<li class="tree_group">我的乐文</li>
  					<li><a href="">我的信箱</a></li>
  					<li><a href="">我的书评</a></li>
  					<li><a href="">我的帖子</a></li>
  					<li><a href="${ctx}/memo/list">我的备忘</a></li>
  					<li><a href="${ctx}/bookshelf/toList">我的书架</a></li>
  					<li><a href="#">回收站</a></li>
  					
  					<li class="tree_group">作家专区</li>
  					<li><a href="${ctx}/user/toCreateAuthor">信息注册</a></li>
  					<li><a href="${ctx}/book/list">我的作品</a></li>
  					<li><a href="${ctx}/book/toWritePage">我要写作</a></li>
  					<li><a href="${ctx}/user/toAuthorPrincple" target="_blank">作者协议</a></li>
  					
  					<li class="tree_group">我的彩票</li>
  					
  					<li class="tree_group">运维监控</li>
  					<li>日志监控</li>
  					<li>人员维护</li>
  					<li>文章审核</li>
  					<li><a href="${ctx}/spider/toSpiderWebsite">蜘蛛</a></li>
  					
  					<li class="tree_group">意见反馈</li>
  					<li><a href="${ctx}/config/feedback/create_feedback.jsp">我有反馈</a></li>
  					<li><a href="${ctx}/config/feedback/create_feedback.jsp">我有建议</a></li>
  					<li><a href="${ctx}/feedback/list">所有反馈</a></li>
  					<li><a href="${ctx}/feedback/listByUser">我的反馈</a></li>
  				</ul>
  			</div>
  			
			<!-- 动态内容加载区域 -->
			<div class="setup_content">
				<div style="border-bottom: 2px solid #8BB8F2;margin-bottom: 18px; text-indent: 8px;">
			  		<span style="line-height: 28px; font-weight: bold;">用户设置</span>
			  	</div>
	  			<div id="setup_content" >
	  			</div>
  			</div>
	  	</div>
  	</div>
  	
  	<jsp:include page="../frame/footer.jsp"></jsp:include>
  	<script type="text/javascript">
  	popLoginDiv();
  	</script>
  </body>
</html>
