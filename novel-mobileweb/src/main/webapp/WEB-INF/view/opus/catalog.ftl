<#include "/taglibs.ftl">

<div id="embed-area">
	<form id="chapterListForm" action="${ctx}/chapter/toList">
	<input type="hidden" name="bookId" value="${bookId}">
	<div load-page="true" class="border-box" 
		page-no="${page.pageNo}" page-size="${page.pageSize}" total-page="${page.pageCount}" total-count="${page.totalCount}">
		<#list page.result as chapter>
			<div class="chapter-item"> 
				<span><a href="${ctx}/chapter/toChapter?chapterId=${chapter.id}"> ${chapter.subject} </a> </span>
				<span style="float: right;">${chapter.inputTime}</span> 
			</div>
		</#list>
	</div>
	</form>
</div>
<#include "/frame/main.ftl">
