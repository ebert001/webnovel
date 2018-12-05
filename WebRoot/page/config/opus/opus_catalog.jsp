<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>作品章节目录</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css"/>
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/calendar/WdatePicker.js"></script>
	<style type="text/css">
	.edit_catalog {
		background-color: #A4E2C6;
		float: left;
		border: 1px solid #8BB8F2;
		padding-bottom: 6px;
		width: 100%;
	}
	
	.edit_catalog ul {
		list-style: none;
		width: 100%;
	}
	
	.edit_catalog ul li {
		float: left;
		line-height: 28px;
		text-indent: 6px;
		overflow: hidden;
		width: 100%;
		border-bottom: 1px dashed #556B2F;
	}
	
	.edit_catalog ul li:hover {
		background-color: #AFDFE4;
	}
	.text_wrapper {
		width: 480px;
		height: 24px;
		border: 1px solid #8BB8F2; 
		font-size: 18px;
	}
	</style>
	<script type="text/javascript">
	function addVolume() {
		document.getElementById('volume_view').style.display = 'none'; 
		document.getElementById('volume_edit').style.display = 'block';
	}
	
	function deleteChapter(chapterId) {
		var rs = confirm("确认删除此章节吗？删除后，此章节会放入回收站中。");
		if (rs == true) {
			document.location.href = "${ctx}/Book.action?deleteChapter&chapterId=" + chapterId;
		} else {
			return;
		}
	}
	
	function swapDisplay(aid, bid) {
		$$(aid).style.display = "block"; 
		$$(bid).style.display = "none";
	}
	
	function backfill(formName, eleName, value) {
		$$(eleName).value = value;
	}
	
	function volumeDiv(volumeId, volumeName) {
		var editId = 'volume_edit_' + volumeId;
		var viewId = 'volume_view_' + volumeId;
		var formName = 'volumeForm' + volumeId;
		var volumeNameId = 'volume_name_' + volumeId;
		
		var str = '';
		str += '<div class="volume">';
		str += '<div id="' + editId + '" style="display: none;">';
		str += '<form name="' + formName + '" action="${ctx}/Book.action?updateVolume&volumeId="' + volumeId + ' method="post">';
		str += '<span style="width: 80%;">';
		str += '卷名称：<input class="text_wrapper" type="text" name="volumeName" id="' + volumeNameId + '" value="">';
		str += '<input type="hidden" name="bookId" value="${book.id}">';
		str += '</span>';
		str += '<span style="width: 20%; float: right;">';
		str += '<a href="#" onclick="javascript:doSubmit(\'' + formName + '\');">保存</a>';
		str += '&nbsp;&nbsp;&nbsp;&nbsp;';
		str += '<a href="#" onclick="swapDisplay(\'' + viewId + '\', \'' + editId + '\');">取消</a>';
		str += '</span>';
		str += '</form>';
		str += '</div>';
			
		str += '<div id="' + viewId + '">';
		str += '<span style="width: 80%;">';
		str += volumeName;
		str += '</span>';
		str += '<span style="width: 20%; float: right;">';
		str += '<a href="#" onclick="swapDisplay(\'' + editId + '\', \'' + viewId + '\');backfill(\'' + formName + '\', \'' + volumeNameId + '\', \'' + volumeName + '\')">编辑</a>';
		str += '&nbsp;&nbsp;&nbsp;&nbsp;';
		str += '<a href="#">删除</a>';
		str += '</span>';
		str += '</div>';
		str += '</div>';
		return str;
	}
	
	function chapterDiv(chapterId, chapterName) {
		var editId = 'chapter_edit_' + chapterId;
		var viewId = 'chapter_view_' + chapterId;
		var formName = 'chapterForm' + chapterId;
		var chapterNameId = 'chapter_name_' + chapterId;
		
		var str = '';
		str += '<div>';
		str += '<div id="' + editId + '" style="display: none;">';
		str += '<form name="' + formName + '" action="${ctx}/Book.action?updateVolume&volumeId="' + chapterId + ' method="post">';
		str += '<span style="width: 80%;">';
		str += '章节名称：<input class="text_wrapper" type="text" name="chapterName" id="' + chapterNameId + '" value="">';
		str += '<input type="hidden" name="bookId" value="${book.id}">';
		str += '</span>';
		str += '<span style="width: 20%; float: right;">';
		str += '<a href="#" onclick="javascript:doSubmit(\'' + formName + '\');">保存</a>';
		str += '&nbsp;&nbsp;&nbsp;&nbsp;';
		str += '<a href="#" onclick="swapDisplay(\'' + viewId + '\', \'' + editId + '\');">取消</a>';
		str += '</span>';
		str += '</form>';
		str += '</div>';
			
		str += '<div id="' + viewId + '">';
		str += '<span style="width: 80%; float: left; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">';
		str += '<a href="${ctx}/Book.action?queryChapter&chapterId=' + chapterId + '"><span>' + chapterName + '</span></a>';
		str += '</span>';
		str += '<span style="width: 20%; float: right;">';
		str += '<a href="#" onclick="swapDisplay(\'' + editId + '\', \'' + viewId + '\');backfill(\'' + formName + '\', \'' + chapterNameId + '\', \'' + chapterName + '\')">编辑</a>';
		str += '&nbsp;&nbsp;&nbsp;&nbsp;';
		str += '<a href="#" onclick="${ctx}/Book.action?deleteChapter&chapterId=' + chapterId + '">删除</a>';
		str += '</span>';
		str += '</div>';
		str += '</div>';
		return str;
	}
	</script>
  </head>
  
  <body>
  	<jsp:include page="/config/user_setup.jsp"></jsp:include>
  	<div id="embed_area">
    	<div class="content_wrapper upper_border_radius" style="overflow: hidden;">
 			<div class="book_title">${book.bookName}</div>
 			<div style="margin-top: 6px; text-align: center;">
 				作者：<a href="">${book.authorId}</a>&nbsp;&nbsp;&nbsp;&nbsp;
 				更新时间：<fmt:formatDate value="${book.updateTime}" pattern="yyyy-MM-dd HH:mm"/>
 			</div>
	 			
 			<div>
	 			<div class="volume">
	 				<div id="volume_edit" style="display: none;">
	 					<form name="volumeForm" action="${ctx}/Book.action?addVolume" method="post">
							卷名称：<input class="input_wrapper" type="text" name="volumeName" value="">
							<input type="hidden" name="bookId" value="${book.id}">
							<a href="#" onclick="javascript:doSubmit('volumeForm');">保存</a>
							<a href="#" onclick="javascript:$$('volume_view').style.display = 'block'; $$('volume_edit').style.display = 'none';">取消</a>
						</form>
	 				</div>
	 				<div id="volume_view" style="display: block;">
	 					<a href="#" onclick="javascript:$$('volume_view').style.display = 'none'; $$('volume_edit').style.display = 'block';">添加卷</a>
	 				</div>
	 			</div>
 			</div>
 			
 			<div>
	 			<c:forEach items="${volumeList}" var="volume">
		 			<script type="text/javascript">
		 			document.write(volumeDiv('${volume.id}', '${volume.volumeName}'));
		 			</script>
		 			
		 			<div class="edit_catalog">
			  			<ul>
			  				<li style="height: 42px; line-height: 42px;">
			  					<c:set var="chapter_edit" value="chapter_edit_v_${volume.id}"></c:set>
			  					<c:set var="chapter_view" value="chapter_view_v_${volume.id}"></c:set>
			  					<c:set var="chapter_form" value="chapter_form_v_${volume.id}"></c:set>
			  					<div id="${chapter_edit}" style="display: none;">
				 					<form name="${chapter_form}" action="${ctx}/Book.action?addChapterSubject" method="post">
					 					章节名称：<input class="input_wrapper" type="text" name="chapterName" value="">
					 					<input type="hidden" name="volumeId" value="${volume.id}">
					 					<input type="hidden" name="bookId" value="${book.id}">
					 					<a href="#" onclick="javascript:doSubmit('${chapter_form}')">保存</a>
					 					<a href="#" onclick="javascript:$$('${chapter_view}').style.display = 'block'; $$('${chapter_edit}').style.display = 'none';">取消</a>
				 					</form>
				 				</div>
				 				<div id="${chapter_view}" style="display: block;">
				 					<span style="width: 80%; float: left; text-align: center;">
					 					<a href="#" onclick="javascript:$$('${chapter_view}').style.display = 'none'; $$('${chapter_edit}').style.display = 'block';">添加新章节标题</a>
					  					&nbsp;&nbsp;&nbsp;&nbsp;
					  					<a href="${ctx}/Book.action?goWritePage&bookId=${book.id}">添加新章节</a>
				  					</span>
			  					</div>
			  				</li>
			  				<c:forEach items="${chapterList}" var="chapter">
			  				<c:if test="${chapter.volumeId == volume.id}">
				  				<li title="${chapter.subject}">
				  					<script type="text/javascript">
						 			document.write(chapterDiv('${chapter.id}', '${chapter.subject}'));
						 			</script>
				  				</li>
			  				</c:if>
			  				</c:forEach>
			  			</ul>
		 			</div>
	 			</c:forEach>
 			<div>
  		</div>
	</div>
	
    <script type="text/javascript">
    loadEmbedPage("setup_content", "embed_area");
    </script>
  </body>
</html>
