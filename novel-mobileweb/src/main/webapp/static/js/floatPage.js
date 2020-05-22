
// ------------------------------------------------------------------------
// 弹出和关闭锁屏页面 -- begin
function openDiv() {
	// 锁定正文部分高度	 
	document.getElementById('_fade').style.height = document.body.scrollHeight + "px";
	document.getElementById('_fade').style.display = 'block';
	document.getElementById('_main').style.display = 'block';
}

// 关闭层
function closeDiv() {
	document.getElementById('_fade').style.display = 'none';
	document.getElementById('_main').style.display = 'none';
}

// 捕获键盘ESC事件，关闭层
function addEscEvent(evt) {
	if (evt.keyCode == 27) {
		closeDiv();
	}
}

// 增加层的拖动事件
function oevent(e) {
	if (!e) {
		e = window.event;
	}
	return e;
}

var _drag = false;
function moveDiv(cid) {
	var x, y;
	document.getElementById("_main").onmousedown = function(e) {
		_drag = true;
		with (this) {
			document.getElementById("_main").style.position = "absolute";
			var temp1 = offsetLeft;
			var temp2 = offsetTop;
			x = oevent(e).clientX;
			y = oevent(e).clientY;
			document.getElementById("_header").onmousemove = function(e) {
				if (!_drag) {
					return false;
				}
				with (this) {
					document.getElementById("_main").style.left = temp1 + oevent(e).clientX - x + "px";
					document.getElementById("_main").style.top = temp2 + oevent(e).clientY - y + "px";
				}
			}
		}
		document.onmouseup=new Function("_drag=false");
	};
}

// 弹出层
function popDiv(title, cid) {
	var tDiv = "";
	tDiv += '<div id="_fade" class="_overlay"></div>';
	tDiv += '<div id="_main" class="_main">';
	tDiv += '<div id="_header" class="_header" onmousemove="moveDiv()">';
	tDiv += '<span>' + title + '</span>';
	tDiv += '<span style="cursor: pointer; float: right;" onclick="closeDiv()"> X </span>';
	tDiv += '</div>';
	tDiv += '<div id="_body" class="_body">';
	tDiv += '<script type="text/javascript">';
	tDiv += 'document.write(document.getElementById("' + cid + '").innerHTML);';
	tDiv += '<\/script>';
	tDiv += '</div>';
	tDiv += '</div>';
	
	document.write(tDiv);
}

// 弹出登录层
function popLoginDiv() {
	var tDiv = "";
	tDiv += '<div id="_fade" class="_overlay" onclick="closeDiv()"></div>';
	tDiv += '<div id="_main" class="_main">';
	tDiv += '<div id="_header" class="_header" onmousedown="moveDiv(\'_main\')">';
	tDiv += '<span> 登 录 </span>';
	tDiv += '<span style="cursor: pointer; float: right;" onclick="closeDiv()" alt="关闭"> X </span>';
	tDiv += '</div>';
	tDiv += '<div id="_body" class="_body">';
	tDiv += '<form name="popLoginForm" method="post" action="">';
	tDiv += '<ul style="list-style: none;">';
	tDiv += '<li>请输入登录信息</li>';
	tDiv += '<li><label style="width: 60px;">帐  号 </label><input type="text" name="username" class="input_wrapper input_required" value=""><span class="required_color">(必须输入)</span></li>';
	tDiv += '<li><label style="width: 60px;">密  码 </label><input type="password" name="password" class="input_wrapper input_required" value=""><span class="required_color">(必须输入)</span></li>';
	tDiv += '<li><input style="margin-left: 64px;" type="checkbox" name="rem_pwd"> 记住密码</li>';
	tDiv += '<li style="text-align: center;"><input type="button" value=" 登  录 "/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value=" 重  置 "></li>';
	tDiv += '<li style="margin-top: 10px;"><hr/></li>';
	tDiv += '<li style="margin-top: 10px; text-align: center; line-height: 30px;">还没有帐号？现在就 <a href="/webnovel/p/register.jsp">注册</a></li>';
	tDiv += '</ul>';
	tDiv += '</form>';
	tDiv += '</div>';
	tDiv += '</div>';
	
	document.write(tDiv);
}
