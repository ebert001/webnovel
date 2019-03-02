
/** 自动装载页面 */
$(document).ready(function() {
	var contentArea = $("#__setup_content");
	var embedArea = $("#embed_area");
	contentArea.html(embedArea.html());
	embedArea.remove();
	
	//自动选中下拉框默认值
	autoSelectOption();
	
	// 加载分页栏
	var pageDiv = $("div[load-page='true']");
	if (pageDiv) {
		var pageUrl = pageDiv.attr("page-url");
		var pageFunc = pageDiv.attr("page-func");
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
		var bar = laodPageBar(pageUrl, pageFunc, pageNo, pageSize, totalPage, totalCount);
		pageDiv.append(bar);
	}
});
