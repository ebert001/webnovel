
<div id="embed_area">
	<div class="book_title">${chapter.subject}</div>
	<div class="book_content">
		${chapter.content}
	</div>
	<div style="text-align: center; margin: 16px auto 10px;;">
		<a class="a_btn" href="">上一页</a>
		<a class="a_btn" href="${ctx}/book/listChapter?bookId=${chapter.bookId}&a=r">回目录</a>
		<a class="a_btn" href="${ctx}/book/readChapter?chapterId=${chapter.id}">下一页</a>
	</div>
</div>
<#include "/frame/main.ftl">
