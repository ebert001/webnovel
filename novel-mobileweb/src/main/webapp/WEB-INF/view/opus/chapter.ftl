<#include "/taglibs.ftl">

<div id="embed-area">
	<div class="book-title">${chapter.subject}</div>
	<div class="book-content">
		${chapter.content}
	</div>
	<div style="text-align: center; width: 100%; margin: 8px auto; border: 1px solid red; box-sizing: border-box;">
		<div class="">
		<a class="a-btn" style="width: 30%;" href="${ctx}/chapter/toChapter?chapterId=${chapter.id}">上一页</a>
		</div>
		<a class="a-btn" style="width: 30%;" href="${ctx}/chapter/toList?bookId=${chapter.bookId}&a=r">目录</a>
		<a class="a-btn" style="width: 30%;" href="${ctx}/chapter/toChapter?chapterId=${chapter.id}">下一页</a>
	</div>
</div>
<#include "/frame/main.ftl">
