

<div id="embed_area">
	<div load-page='true'>
	<#list page.result as book>
		<div> 
			<span><a href="${ctx}/chapter/toList?bookId=${book.id}"> ${book.name} </a> </span>
			<span>${book.updateTime!""}</span> 
		</div>
	</#list>
	</div>
</div>

<#include "/frame/main.ftl">