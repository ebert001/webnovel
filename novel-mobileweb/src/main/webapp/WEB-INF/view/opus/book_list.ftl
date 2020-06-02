<#include "/taglibs.ftl">

<div id="embed_area">
	<div load-page="true" class="border-box">
		<form id="bookListForm">
		<#list page.result as book>
			<div style="width: 100%; margin: 0; padding: 4px; border-bottom: 1px solid gray;"> 
				<span><a href="${ctx}/chapter/toList?bookId=${book.id}"> ${book.name} </a> </span>
				<span style="float: right;">${book.updateTime}</span> 
			</div>
		</#list>
		</form>
	</div>
</div>

<#include "/frame/main.ftl">