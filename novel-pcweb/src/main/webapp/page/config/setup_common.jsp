<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>

<div>
	<%@include file="../frame/header.jsp"%>
	<%@include file="../frame/search.jsp"%>
	
	<div class="container">
		<div class="content_wrapper upper_border_radius none_space">
			<div class="setup_tree padding_top">
				<dl id="__setup_menu">
					<dt>用户设置</dt>
					<dd url="${ctx}/user/info">基本信息</li>
					<dd url="${ctx}/user/toUpdatePwd">修改密码</dd>
					<dd url="${ctx}/user/toUploadAvatar">上传头像</dd>
					<dd url="${ctx}/bookshelf/toList">我的书架</dd>
					<!--  
					<dd url="${ctx}/config/history/list_history.jsp">访问历史</dd>
					-->
					<!--  
					<dd url="">我的信箱</dd>
					<dd url="">我的书评</dd>
					<dd url="">我的帖子</dd>
					<dd url="${ctx}/memo/list">我的备忘</dd>
					-->
					
					<dt>作家专区</dt>
					<dd url="${ctx}/user/toCreateAuthor">信息注册</dd>
					<dd url="${ctx}/book/list">我的作品</dd>
					<dd url="${ctx}/book/toWritePage">我要写作</dd>
					<dd url="${ctx}/user/toAuthorPrincple" target="_blank">作者协议</dd>
					
					<dt>运维监控</dt>
					<dd url="${ctx}/user/list">用户管理</dd>
					<!--  
					<dd>日志监控</dd>
					<dd>人员维护</dd>
					-->
					<dd url="${ctx}/spider/toSpiderWebsite">蜘蛛</dd>
					<dd url="${ctx}/spider/toUnauditBooks">文章审核</dd>
					
					<!--  
					<dt>意见反馈</dt>
					<dd url="${ctx}/config/feedback/create_feedback.jsp">我有反馈</dd>
					<dd url="${ctx}/config/feedback/create_feedback.jsp">我有建议</dd>
					<dd url="${ctx}/feedback/list">所有反馈</dd>
					<dd url="${ctx}/feedback/listByUser">我的反馈</dd>
					-->
				</dl>
			</div>
			
			<!-- 动态内容加载区域 -->
			<div id="__setup_content" class="setup_content">
			</div>
 		</div>
	</div>
	
	<%@include file="../frame/footer.jsp"%>
	
</div>
