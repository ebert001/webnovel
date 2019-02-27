<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<div>
	<%@include file="../frame/header.jsp"%>
	<%@include file="../frame/search.jsp"%>
	
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
			<div id="__setup_content" class="setup_content">
			</div>
 		</div>
	</div>
	
	<%@include file="../frame/footer.jsp"%>
	
	<script type="text/javascript">
	popLoginDiv();
	</script>
</div>
