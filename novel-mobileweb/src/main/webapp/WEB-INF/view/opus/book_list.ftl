<#include "/frame/main.ftl">

<div class="embed_area">
	<div>
	<#list page.result as book>
		<div> 
			<span>${book.name}</span>
			<span>${book.updateTime}</span> 
		</div>
	</#list>
	
	</div>
</div>
