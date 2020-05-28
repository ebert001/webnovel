
/** 自动装载页面 */
$(document).ready(function() {
	// 加载基本布局
	function loadLayout() {
		console.log("layout...");
		var contentArea = $("#__setup_content");
		var embedArea = $("#embed_area");
		contentArea.html(embedArea.html());
		embedArea.remove();
	}
	// 加载分页栏
	function loadPageLayout() {
		var pageDiv = $("div[load-page='true']");
		if (pageDiv) {
			// 查找父元素中是否有form，返回0或1个元素
			var form = pageDiv.closest('form');
			var pageUrl = pageDiv.attr("page-url");
			var formId = null;
			if ((form == undefined || form == null) && (pageUrl == undefined || pageUrl == null)) {
				alert("请为分页节点增加 page-url 属性.");
				return;
			} else {
				formId = form.attr("id");
			}
			var pageNo = pageDiv.attr("page-no");
			var pageSize = pageDiv.attr("page-size");
			var totalPage = pageDiv.attr("total-page");
			var totalCount = pageDiv.attr("total-count");
			if (pageNo == undefined) {
				pageNo = 1;
			}
			if (pageSize == undefined) {
				pageSize = 20;
			}
			if (totalPage == undefined) {
				totalPage = 1
			}
			var bar = loadPageBar(pageUrl, formId, parseInt(pageNo), parseInt(pageSize), parseInt(totalPage), parseInt(totalCount));
			pageDiv.append(bar);
		}
	}
	function loadConfigMenu() {
		$("#__setup_menu dd").click(function() {
			var dd = $(this);
			var url = dd.attr("url");
			if (url == undefined || url == null) {
				return;
			}
			location.href = url;
		});
	}
	
	loadLayout();
	loadConfigMenu();
	//自动选中下拉框默认值
	autoSelectOption();
	loadPageLayout();
});
