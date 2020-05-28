// 设置首页
function setHomePage(url) {
	this.setHomePage(url);
}

// 添加收藏夹
function addFavorite(url) {
	window.external.addFavorite(url, "个人网站");
}

// 用class样式标识的必须输入项检查
function inputCheck(fid) {
	var tmpForm = document.forms[fid];
	
	var elements = tmpForm.elements;
	var flag = true;
	for (index in elements) {
		var input = elements[index];
		var className = input.className;
		var inputValue = input.value;
		if (className && className.indexOf("input_required") != -1) {
			if (inputValue == null || inputValue.length < 1) {
				alert(input.title + " 必须输入!");
				input.focus();
				flag = false;
				break;
			}
		}
	}
	return flag;
}

function loadEmbedPage(srcId, embedId) {
	document.getElementById(srcId).innerHTML = document.getElementById(embedId).innerHTML;
}

// 用于表单提交
function doSubmit(formName) {
	document.forms[formName].submit();
}

// 检查并提交表单
function checkAndSubmit(formName) {
	if (inputCheck(formName)) {
		doSubmit(formName);
	}
}

// 取字节长度，此处取汉字都是2个字节
function getBinaryLength(obj) {
	var length = obj.value.length;
	// 判断中文
	var cn = obj.value.match(/[\u4E00-\u9FA5\uF900-\uFA2D]/g);
	if (cn != null) {
		length = (length - cn.length) + cn.length * 2;
	}
	document.getElementById("re").innerHTML = length;
	return length;
}

// 替换字符串中的回车换行为<br>
function replaceCRLF(str) {
	return str.replace(/\<br\/\>/ig, "&lt;br/&gt;").replace(/\<br\>/ig, "&lt;br&gt;").replace(/\r\n/ig, "<br/>").replace(/\r/ig, "<br/>").replace(/\n/ig, "<br/>");
}

// 替换字符串中的<br>为换行符
function replaceBR(str) {
	return str.replace(/\<br\>/ig, "\n").replace(/\<br\/\>/ig, "\n").replace("&lt;br&gt;", "<br>").replace("&lt;br/&gt;", "<br/>");
}

var EventUtil = {
	addHandle : function(element, type, handle) {
		if (element.addEventListener) {
			element.addEventListener(type, handle, false);
		} else if (element.attachEvent) {
			element.attachEvent("on" + type, handle);
		} else {
			element["on" + type] = handle;
		}
	},
	removeHandle : function(element, type, handle) {
		if (element.removeEventListener) {
			element.removeEventListener(type, handle, false);
		} else if (element.detachEvent) {
			element.detachEvent("on" + type, handle);
		} else {
			element["on" + type] = null;
		}
	},
	getEvent : function(evt) {
		return evt ? evt : window.event;
	},
	getTarget : function(evt) {
		return evt.target || evt.srcElement;
	},
	getRelatedTarget : function(evt) {
		if (evt.relatedTarget) {
			return evt.relatedTarget;
		} else if (evt.toElement) {
			return evt.toElement;
		} else if (evt.fromElement) {
			return evt.fromElement;
		} else {
			return null;
		}
	},
	preventDefault : function(evt) {
		if (evt.preventDefault) {
			evt.preventDefault();
		} else {
			evt.returnValue = false;
		}
	},
	stopPropagation : function(evt) {
		if (evt.stopPropagation) {
			evt.stopPropagation();
		} else {
			evt.cancelBubble = true;
		}
	},
	getKeyCode : function(evt) {
		return evt.keyCode | evt.which;
	},
	getCharCode : function(evt) {
		if (typeof evt.charCode == "number") {
			return evt.charCode;
		} else {
			return evt.keyCode;
		}
	},
	getASCIICharCode : function(evt) {
		return String.fromCharCode(evt.keyCode | evt.which);
	}
}

// 捕获键盘ENTER事件
function addEnterEvent(evt, fun) {
	if (evt.keyCode == 13 || evt.keyCode ==108) {
		fun();
	}
}

// 注册键盘事件
function addKeyboardEvent(handle) {
	if (document.addEventListener) { // firefox
		document.addEventListener("keypress", handle, true);
	} else if (document.attachEvent) { // 其它浏览器
		document.attachEvent("onkeypress", handle);
	} else {
		document["onkeypress"] = handle;
	}
}

// 选项卡切换事件
function tabChange(prefix, num, currentSelected) {
	for (i = 1; i <= num; i++) {
		var menu = document.getElementById(prefix + "_tab_menu_" + i);
		var con = document.getElementById(prefix + "_tab_content_" + i);
		menu.className = ((i == currentSelected) ? "hover" : "");
		con.style.display = ((i == currentSelected) ? "block" : "none");
	}
}

// 创建选项卡
function createTab(prefix, num, arrMenu, arrContent, style, arrFunc) {
	var tDiv = '';
	tDiv += '<div class="tab" style="' + style + '">';
	tDiv += '<div class="tab_menu_box">';
	tDiv += '<ul>';
	for (i = 1; i <= num; i++) {
		if (i > 1) {
			tDiv += '<li id="' + prefix + '_tab_menu_' + i + '" onmouseover="tabChange(' + prefix + ', ' + num + ', ' + i + ');">';
		} else {
			tDiv += '<li id="' + prefix + '_tab_menu_' + i + '" onmouseover="tabChange(' + prefix + ', ' + num + ', ' + i + ');" class="hover">';
		}
		tDiv += arrMenu[i - 1];
		tDiv += '</li>';
	}
	tDiv += '</ul>';
	tDiv += '</div>';
	tDiv += '<div class="tab_content_box">';
	for (i = 1; i <= num; i++) {
		if (i > 1) {
			tDiv += '<div id="' + prefix + '_tab_content_' + i + '" style="display: none;">';
		} else {
			tDiv += '<div id="' + prefix + '_tab_content_' + i + '" class="hover">';	
		}
		tDiv += arrContent[i - 1];
		tDiv += '</div>';
	}
	tDiv += '</div>';
	tDiv += '</div>';
	
	document.write(tDiv);
}

/********************************/
function createDivEditor(id, name) {
	var str = '';
	str += createDivEditorHeader(id);
	str += '<div id="' + id + '_area" class="editor" contenteditable="true" onkeyup="javascript: setValue(\'' + id + '\');"></div>';
	str += '<input type="hidden" id="' + id + '" name="' + name + '" value="">';
	return str;
}

function createDivEditorHeader(id) {
	var str = '';
	var rootPath = getRootPath();
	str += '<div class="editor_header">';
	str += '<img src="' + rootPath + '/static/imgs/text_indent.png" title="增加缩进" onclick="javascript: decorate(\'Indent\'); setValue(\'' + id + '\');"/>';
	str += '<img src="' + rootPath + '/static/imgs/text_indent_remove.png" title="减少缩进" onclick="javascript: decorate(\'Outdent\'); setValue(\'' + id + '\');"/>';
	str += '<img src="' + rootPath + '/static/imgs/text_align_left.png" title="居左" onclick="javascript: decorate(\'JustifyLeft\'); setValue(\'' + id + '\');"/>';
	str += '<img src="' + rootPath + '/static/imgs/text_align_center.png" title="居中" onclick="javascript: decorate(\'JustifyCenter\'); setValue(\'' + id + '\');"/>';
	str += '<img src="' + rootPath + '/static/imgs/text_align_right.png" title="居右" onclick="javascript: decorate(\'JustifyRight\'); setValue(\'' + id + '\');"/>';
	str += '<img src="' + rootPath + '/static/imgs/bold.png" title="加粗" onclick="javascript: decorate(\'Bold\'); setValue(\'' + id + '\');"/>';
	str += '<img src="' + rootPath + '/static/imgs/italic.png" title="倾斜" onclick="javascript: decorate(\'Italic\'); setValue(\'' + id + '\');"/>';
	str += '<img src="' + rootPath + '/static/imgs/underline.png" title="下划线" onclick="javascript: decorate(\'UnderLine\'); setValue(\'' + id + '\');"/>';
	str += '<img src="' + rootPath + '/static/imgs/link.png" title="链接" onclick="javascript: decorate(\'CreateLink\'); setValue(\'' + id + '\');"/>';
	//str += '<img src="imgs/picture.png" title="图片" onclick="javascript: decorate(\'insertimage\'); setValue(\'' + id + '\');"/>';
	str += '</div>';
	return str;
}

function setValue(id) {
	document.getElementById(id).value = document.getElementById(id + "_area").innerHTML;
}

function decorate(command) {
	if (command == 'CreateLink') {
		var url = selectText();
		document.execCommand(command, false, url);
	} else {
		document.execCommand(command, false, false);
	}
}

function selectText() {
	var userSelection = '';
	if (window.getSelection) { //现代浏览器
	    userSelection = window.getSelection();
	} else if (document.selection) { //IE浏览器 考虑到Opera，应该放在后面
	    userSelection = document.selection.createRange();
	}
	return userSelection;
}



function getRootPath() { 
    var strFullPath = window.document.location.href; 
    var strPath = window.document.location.pathname; 
    var pos = strFullPath.indexOf(strPath); 
    var prePath = strFullPath.substring(0, pos); 
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1); 
    return (prePath + postPath); 
} 

/********************************************/
function paginationBar(pageNo, pageCount, url) {
	url += "&page=";
	var bar = '<div class="pagination_bar">';
	bar += '<ul>';
	if (pageCount == 1) {
		bar += pageNode('class="no_operation"', '1');
	} else {
		if (pageNo == 1) {
			bar += pageNode('class="no_operation"', '上页');
		} else {
			bar += pageNode('href="' + url + (pageNo - 1) + '"', '上页');
		}
		for (i = 1; i <= pageCount; i++) {
			if (pageNo == i) {
				bar += pageNode('class="no_operation"', i);
			} else {
				bar += pageNode('href="' + url + i + '"', i);
			}
		}
		if (pageNo == pageCount) {
			bar += pageNode('class="no_operation"', '下页');
		} else {
			bar += pageNode('href="' + url + (pageNo + 1) + '"', '下页');
		}
	}
	bar += '</ul>';
	bar += '</div>';
	return bar;
}

function pageNode(property, text) {
	var node = '<li>';
	node += '<a ' + property + '>' + text + '</a>';
	node += '</li>';
	return node;
}

/**
 * 分页栏
 * @param pageUrl 分页地址，表单提交可以为空
 * @param formId 分页表单函数， 没有表单可以为空
 * @param pageNo 当前页数
 * @param pageSize 每页数量
 * @param totalPage 总页数
 * @param totalCount 总记录数
 * @returns 分页栏
 */
function loadPageBar(pageUrl, formId, pageNo, pageSize, totalPage, totalCount) {
	// 有表单查询条件
	if (formId != null) {
		return pageWithForm(formId, pageNo, pageSize, totalPage, totalCount);
	}
	return pageWithUrl(pageUrl, pageNo, pageSize, totalPage, totalCount);
}

/** 私有方法，外部不要调用 */
function pageWithForm(formId, pageNo, pageSize, totalPage, totalCount) {
	var bar = "";
	bar += '<div class="page">';
	bar += '<input type="hidden" name="pageSize" value="' + pageSize + '">';
	bar += '<input type="hidden" name="pageNo" value="1">';
	
	bar += pageBlockWithForm("首页", formId, 1, (totalPage > 1));
	bar += pageBlockWithForm("上一页", formId, (pageNo - 1), (pageNo > 1));
	bar += pageBlockWithForm("下一页", formId, (pageNo + 1), (pageNo < totalPage));
	bar += pageBlockWithForm("尾页", formId, totalPage, (pageNo < totalPage));
	
	bar += ' ' + pageNo + '/' + totalPage;
	if (totalCount != undefined && totalCount != null) {
		bar += '(' + totalCount + ')';
	}
	bar += '</div>';
	return bar;
}

/** 私有方法，外部不要调用 */
function pageBlockWithForm(text, formId, pageNo, shown) {
	if (shown) {
		return '<a href="#" onclick="doPage(' + pageNo + ', \'' + formId + '\')">' + text + '</a>';
	}
	return '<a>' + text + '</a>';
}

/** 私有方法，外部不要调用 */
function doPage(pageNo, formId) {
	$("#" + formId + " input[name=pageNo]").val(pageNo);
	$("#" + formId).submit();
}

/** 私有方法，外部不要调用 */
function pageWithUrl(pageUrl, pageNo, pageSize, totalPage, totalCount) {
	if (pageUrl == undefined || pageUrl == null) {
		return "";
	}
	pageUrl += (pageUrl.indexOf("?") == -1 ? "?" : "&") + "pageSize=" + pageSize;
	var bar = "";
	bar += '<div class="page">';
	
	bar += pageBlockWithUrl("首页", pageUrl, 1, (totalPage > 1));
	bar += pageBlockWithUrl("上一页", pageUrl, (pageNo - 1), (pageNo > 1));
	bar += pageBlockWithUrl("下一页", pageUrl, (pageNo + 1), (pageNo < totalPage));
	bar += pageBlockWithUrl("尾页", pageUrl, totalPage, (pageNo < totalPage));
	
	bar += ' ' + pageNo + '/' + totalPage;
	if (totalCount != undefined && totalCount != null) {
		bar += '(' + totalCount + ')';
	}
	bar += '</div>';
	return bar;
}

/** 私有方法，外部不要调用 */
function pageBlockWithUrl(text, pageUrl, pageNo, shown) {
	pageUrl += '&pageNo=' + pageNo;
	if (shown) {
		return '<a href="' + pageUrl + '">' + text + '</a>';
	}
	return '<a>' + text + '</a>';
}

function selectOption(selectId) {
	var value = $("#" + selectId).attr("default-value");
	if (value == undefined) {
		return;
	}
	var arr = value.split(",");
	if (arr.length < 1) {
		return;
	}
	$("#" + selectId + " option").each(function() {
		var val = $(this).val();
		for (var i = 0; i < arr.length; i++) {
			if (val == arr[i]) {
				$(this).attr("selected", "selected");
				break;
			}
		}
	});
}

function autoSelectOption() {
	$("select").each(function() {
		var select = $(this);
		var value = select.attr("default-value");
		if (value == undefined) {
			return;
		}
		var arr = value.split(",");
		if (arr.length < 1) {
			return;
		}
		$("option", select).each(function() {
			var option = $(this);
			var val = option.val();
			for (var i = 0; i < arr.length; i++) {
				if (val == arr[i]) {
					option.attr("selected", "selected");
					break;
				}
			}
		});
	});
}

