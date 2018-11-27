<%@ page language="java" pageEncoding="UTF-8"%>

<script>
function doSearch() {
	var sk = document.getElementById("s_k").value;
	
}

</script>

<div class="content_wrapper">
	
	<script type="text/javascript">
		var arrMenu = ["唐", "宋", "元", "明", "清"]; 
		var arrContent = ["李白", "苏轼", "元", "王安石", "和申"];
		createTab('0', 5, arrMenu, arrContent, 'float: left; width: 49%;');
		
		var tscript = '<script type="text/javascript">';
		tscript += '//alert("hello");';
		tscript += '<\/script>';
		arrMenu = ["唐-1", "宋-1", "元", "明", "清"]; 
		arrContent = ["李白-1", "苏轼", "元", "王安石", "和申" + tscript];
		createTab('1', 5, arrMenu, arrContent, 'float: right; width: 49%;');
		
		arrMenu = ["唐-2", "宋-2", "元", "明", "清"]; 
		arrContent = ["李白-2", "苏轼", "元", "王安石", "和申"];
		createTab('2', 5, arrMenu, arrContent, 'float: left; width: 99%; margin-top: 6px;');
	</script>
</div>
