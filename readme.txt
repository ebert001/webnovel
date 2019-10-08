
系统发布部署使用名称：webnovel

说明：
	全局使用统一的编码格式：UTF-8，包括.java，.txt，.css，.jsp，.js，.xml等
	依赖第三方框架：spring2.5 mybatis3.0.5
	日志记录：log4j
	使用的数据库：mysql5.0+
	使用的jdk:1.6.0+
	避免使用struts等页面框架，减少内存损耗。
	
	学习资料和相关文档，放置在金山快盘上。
	
开发注意事项：
	因条件限制，开发时要尽量考虑到节省内存，优化系统吞吐量。尽量的让有限的资源，发挥最大的作用。
	文件提交时，请勿将工程的个性配置文件提交。如：.myeclipse .project等。
	
编码注意事项：
	注重编码规范，全局使用统一的编码方式，缩进方式(一个tab键)，合理的注释方式。
	工程中所有的类，方法，属性，最好都能指出其作用。力求编码的干净整洁。同时也可以提供虚拟机classloader的效率，变相的也就提高的内存的使用率。
	.java文件可以在三种模式下进行编译：运行模式 调试模式 安全模式。
	运行模式：正常部署时一般使用此模式。此模式编译以后的字节码文件中保留有行号等信息，配合日志使用，是个不错的方式。
	调试模式：一般在开始时使用。此模式编译以后的字节码文件中，包含有大量的类信息，类关联信息等。加载速度慢，占用内存多。
	安全模式：使用情况较少。此模式编译以后的字节码文件，只保留有类运行的必要信息。不便于查错和维护。
	命令：javac -help可以查看
	
	jsp页面的编写过程中同样需要注意缩进方式(一个tab键)，保证页面的可读性和可维护性。
	jsp文件命名规则：
		多单词之间使用连字符'-'连接
	
	jsp页面变量命名规则：
		表单输入框名称采用驼峰命名法，首字母消息．如: <input name="wnUsername"> <input name="oldPassword">...
		页面内使用变量，以下划线'_'开头．如：_oldPassword, _newPassword
	
	标签自定义属性命名规则：
		统一小写
		多个单词使用'-'连接
		
	配置文件命名规则：
		多单词之间使用连字符'-'连接
		配置变量的多单词之间使用下划线'_'连接
	
	系统统一使用slf4j日志。使用方式：
	private static final Logger logger = LoggerFactory.getLogger(XXX.class);
	
css样式的使用注意事项：
	页面布局：
		div的边框间距的使用规则：竖直方向，由下边层的margin-top决定；水平方向，有右边层的margin-left决定。
	id命名规则:
		采用驼峰命名法，首字母小写
		特殊情况，多单词之间使用连字符'-'连接
		
	class命名规则
		单词小写
		多个单词使用'-'连接
		
	
增、删、改、查 的缩写代码：a d m s

所有的翻页在做显示时，统一从1开始，而不是0。如果没有记录，则显示结果的描述为：第一页没有记录。
	


知识积累：
1、js边框
网页可见区域宽：document.body.clientWidth
网页可见区域高：document.body.clientHeight
网页可见区域宽：document.body.offsetWidth (包括边线的宽)
网页可见区域高：document.body.offsetHeight (包括边线的宽)
网页正文全文宽：document.body.scrollWidth
网页正文全文高：document.body.scrollHeight
网页被卷去的高：document.body.scrollTop
网页被卷去的左：document.body.scrollLeft
网页正文部分上：window.screenTop
网页正文部分左：window.screenLeft
屏幕分辨率的高：window.screen.height
屏幕分辨率的宽：window.screen.width
屏幕可用工作区高度：window.screen.availHeight
屏幕可用工作区宽度：window.screen.availWidth


HTML精确定位:scrollLeft,scrollWidth,clientWidth,offsetWidth
scrollHeight: 获取对象的滚动高度。
scrollLeft:设置或获取位于对象左边界和窗口中目前可见内容的最左端之间的距离
scrollTop:设置或获取位于对象最顶端和窗口中可见内容的最顶端之间的距离
scrollWidth:获取对象的滚动宽度
offsetHeight:获取对象相对于版面或由父坐标 offsetParent 属性指定的父坐标的高度
offsetLeft:获取对象相对于版面或由 offsetParent 属性指定的父坐标的计算左侧位置
offsetTop:获取对象相对于版面或由 offsetTop 属性指定的父坐标的计算顶端位置
event.clientX 相对文档的水平座标
event.clientY 相对文档的垂直座标
event.offsetX 相对容器的水平坐标
event.offsetY 相对容器的垂直坐标
document.documentElement.scrollTop 垂直方向滚动的值
event.clientX+document.documentElement.scrollTop 相对文档的水平座标+垂直方向滚动的量

IE，FireFox 差异如下：

IE6.0、FF1.06+：

clientWidth = width + padding

clientHeight = height + padding

offsetWidth = width + padding + border

offsetHeight = height + padding + border

IE5.0/5.5：
clientWidth = width - border

clientHeight = height - border

offsetWidth = width

offsetHeight = height
	

免费天气预报网页
http://m.weather.com.cn/m/pn7/weather.htm


stripes框架
网络地址
http://www.stripesframework.org/display/stripes/Quick+Start+Guide

下面是地址的计算方式：
Notice that we used the ActionBean's class name in the stripes:form tag. Nevertheless, it's good to know how Stripes goes from the class name to a URL. If you look at the generated code, you'll see action="/examples/quickstart/Calculator.action in the HTML. By default Stripes will examine ActionBeans and determine their URL based on their class and package names. To convert class names to URLs Stripes:

Removes any package names up to and including packages called 'web', 'www', 'stripes' and 'action'
Removes 'Action' and 'Bean' (or 'ActionBean') if it is the last part of the class name
Converts it to a path and appends '.action'


项目结构优化，使用分模块的方式规划，具体的: core(entity + dao + service), pcweb(电脑浏览器端), mobileweb(手机浏览器端), spider(爬取文章)
