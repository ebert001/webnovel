
<div id="embed-area">
	<div class="about-book-left">
		<div class="nav-book about-book-border">
			<span style="padding-left: 3px;">乐在文学 > 唐 > 李白 > 静夜思 (书号：NO.1)</span>
			<span style="float: right; padding-right: 3px;">放入书架</span>
		</div>
		
		<div class="book-detail about-book-border">
			<div class="left">
				<div class="img">
					<img style="width: 160px; height: 200px;" src="${ctx}/static/imgs/face.png"/>
				</div>
				<div class="nav">
					<ul>
						<li>
							<a class="nav-button" href="${ctx}/book/listChapter?a=r&bookId=4">点击阅读</a>
						</li>
						<li>
							<a class="nav-button" href="catalog.jsp">加入书架</a>
						</li>
						<li>
							<a class="nav-button" href="catalog.jsp">投票推荐</a>
						</li>
						<li>
							<a class="nav-button" href="catalog.jsp">打赏作品</a>
						</li>					
					</ul>
				</div>
			</div>
			<div class="right">
				<div class="part-top">
					<div class="title text-center">
					<b>莎翁全集</b>
					</div>
					<div class="count-bar">
						<ul class="ul-inline-table text-center">
							<li>点击：134567</li>
							<li>推荐：23456</li>
							<li>总字数：12345678</li>
							<li>总章节：1234</li>
						</ul>
					</div>
				</div>
				<div class="part-middle">
					<div style="margin: 8px;">
						<b>作品简介</b><br/>
						ccc<br/>
						ccc<br/>
						ccc<br/>
						ccc<br/>
						ccc<br/>
						ccc<br/>
						ccc<br/>
						ccc<br/>
					</div>
				</div>
				<div class="part-bottom">
					<div style="margin: 8px;">
						<b>作者：</b><a href="#">ebert</a> &nbsp;&nbsp;&nbsp;&nbsp;
						<b>入驻时间：</b>2012-08-07
						<div><b>标签：</b>历史 军事</div>
					</div>
					
				</div>
			</div>
		</div>
		
		<div class="nav-book">
			<span style="padding-left: 3px;">书评区</span>
		</div>
		
		<div class="input-comment">
			<form name="commentForm" action="" method="post">
			<ul>
				<li>
					<div style="vertical-align: top;">内&nbsp;&nbsp;容:</div>
					<textarea style="width: 70%; height: 100px;"></textarea>
				</li>
				<li>
					<div>验证码:</div>
					<input name="checkCode"/>
				</li>
				<li>
					<div><input type="button" value="确认提交"/></div>
				</li>
			</ul>
			</form>
		</div>
		
		<div class="book-comment">
			
			<div class="about-book-border">
				<ul class="book-comment-ul">
					<c:forEach begin="1" end="5" step="1">
					<li>
						<div class="book-comment-header">
							发表人：【ebert】最后回复时间：2012-02-14 13:40:32
						</div>
						<div class="book-comment-content">
							这是是书评内容的咯
						</div>
						<div class="toolbar">
							<a href="#">赞</a>
							<a href="#">拍砖</a>
							<a href="#">回复</a>
						</div>
					</li>
					</c:forEach>
				</ul>
			</div>
			<div>
  				<script type="text/javascript">
  				document.write(paginationBar(2, 10, "${ctx}/forum/queryOne?id=${forumSubject.id}"));
  				</script>
  			</div>
		</div>
	</div>
	
	<div class="about-book-right">
		本书最新消息<br/>
		
		作品推荐
	</div>
</div>
<#include "/frame/main.ftl">
