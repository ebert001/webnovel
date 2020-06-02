<#include "/taglibs.ftl">

<div id="embed-area">
	<form id="bookListForm" action="${ctx}/book/toList">
	<div load-page="true" class="border-box" 
		page-no="${page.pageNo}" page-size="${page.pageSize}" total-page="${page.pageCount}" total-count="${page.totalCount}">
		<#list page.result as book>
			<div class="book-item"> 
				<span class="subject"><a href="${ctx}/chapter/toList?bookId=${book.id}"> ${book.name} </a> </span>
				<span style="float: right;">${book.updateTime}</span> 
			</div>
		</#list>
	</div>
	</form>
</div>

<#include "/frame/main.ftl">