<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<div>
	<%@include file="../frame/header.jsp"%>
	<%@include file="../frame/search.jsp"%>
	
	<div class="container">
		<div class="content_wrapper upper_border_radius none_space">
			<div class="setup_tree padding_top">
				<ul id="__setup_menu">
					<li class="tree_group">用户设置</li>
					<li url="${ctx}/user/info">基本信息</li>
					<li url="${ctx}/user/toUpdatePwd">修改密码</li>
					<li url="${ctx}/user/list">用户管理</li>
					<li url="${ctx}/user/toUploadAvatar">上传头像</li>
					<!--  
					<li url="${ctx}/config/history/list_history.jsp">访问历史</li>
					-->
					<li class="tree_group">我的乐文</li>
					<!--  
					<li url="">我的信箱</li>
					<li url="">我的书评</li>
					<li url="">我的帖子</li>
					<li url="${ctx}/memo/list">我的备忘</li>
					-->
					<li url="${ctx}/bookshelf/toList">我的书架</li>
					
					<li class="tree_group">作家专区</li>
					<li url="${ctx}/user/toCreateAuthor">信息注册</li>
					<li url="${ctx}/book/list">我的作品</li>
					<li url="${ctx}/book/toWritePage">我要写作</li>
					<li url="${ctx}/user/toAuthorPrincple" target="_blank">作者协议</li>
					
					<li class="tree_group">运维监控</li>
					<!--  
					<li>日志监控</li>
					<li>人员维护</li>
					-->
					<li url="${ctx}/spider/toSpiderWebsite">蜘蛛</li>
					<li url="${ctx}/spider/toUnauditBooks">文章审核</li>
					
					<!--  
					<li class="tree_group">意见反馈</li>
					<li url="${ctx}/config/feedback/create_feedback.jsp">我有反馈</li>
					<li url="${ctx}/config/feedback/create_feedback.jsp">我有建议</li>
					<li url="${ctx}/feedback/list">所有反馈</li>
					<li url="${ctx}/feedback/listByUser">我的反馈</li>
					-->
				</ul>
			</div>
			
			<!-- 动态内容加载区域 -->
			<div id="__setup_content" class="setup_content">
			</div>
 		</div>
	</div>
	
	<%@include file="../frame/footer.jsp"%>
	
</div>
