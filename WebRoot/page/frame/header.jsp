<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<div class="header_wrapper">
	<div class="header">
		<a href="${ctx}/frame/homepage.jsp"><img src="${ctx}/static/imgs/logo/logo.png" style="border: none;" alt="乐在文学"/></a>
		<div class="header_weather">
			<iframe scrolling="no" frameborder="0" src=""></iframe>
		</div>
		<div class="account_wrapper">
			<ul class="account_wrapper_nav"
				onmouseover="javascript:document.getElementById('account_nav_sub').style.display='block';"
				onmouseout="javascript:document.getElementById('account_nav_sub').style.display='none';">
				<li onclick="javascript: addKeyboardEvent(addEscEvent); openDiv();">
					帐号
				</li>
				<div id="account_nav_sub">
				<li onclick="javascript:window.open('${ctx}/surface/user/register.jsp')">
					注册
				</li>
				<li onclick="javascript:window.open('${ctx}/User.action?queryOne')">设置</li>
				<li>退出</li>
				</div>
			</ul>
		</div>
	</div>
</div>