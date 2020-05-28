
<div id="embed_area">
	<div class="book_title">${book.bookName}</div>
	<div style="margin-left: 30%; margin-top: 6px; width: 40%;">
		作者: <a href="">${book.authorId}</a>&nbsp;&nbsp;&nbsp;&nbsp;
		更新时间: <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${book.updateTime}"/>
		状态: 
	</div>
	<div style="width: 90%; margin-left: 5%;">
		<c:forEach items="${volumeList}" var="volume">
		<div class="volume" style="margin-top: 4px;">
			${volume.volumeName}
		</div>
		</c:forEach>
		<div class="catalog">
			<ul>
				<c:forEach items="${chapterList}" var="chapter">
					<c:if test="${chapter.volumeId == volume.id}">
						<li title="${chapter.subject}">
							<a href="${ctx}/book/readChapter?chapterId=${chapter.id}" target="_blank">${chapter.subject}</a>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
<#include "/frame/main.ftl">
