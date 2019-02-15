<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<div class="search_wraper">
	<div class="search_content">
		<label style="font-size: 18px;">关键字</label>
		<input type="text" id="s_k" name="s_k" 
			value="书名/作者"
			onblur="javascript: if (this.value == '') {this.value='书名/作者'};"
			onfocus="javascript: if (this.value == '书名/作者') {this.value=''};"
			class="search_input">
		<a class="a_btn small" href="#"  onclick="javascript: window.open('${ctx}/frame/search_list.jsp')"> 搜 索 </a>
	</div>
	<div class="search_hot">
		热搜文章：<a href="${ctx}/book/view">莎翁全集</a>
	</div>
</div>