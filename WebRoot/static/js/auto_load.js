
/** 自动装载页面 */
$(document).ready(function() {
	var contentArea = $("#__setup_content");
	var embedArea = $("#embed_area");
	contentArea.html(embedArea.html());
	embedArea.remove();
	
	//自动选中下拉框默认值
	autoSelectOption();
});
