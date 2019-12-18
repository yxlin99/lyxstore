<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
/**
 * my_click和my_blur均是用于前台页面搜索框的函数
 */
//鼠标点击搜索框时执行
function my_click(obj, myid){
	//点击时，如果取得的值和搜索框默认value值相同，则将搜索框清空
	if (document.getElementById(myid).value == document.getElementById(myid).defaultValue){
	  document.getElementById(myid).value = '';
	  obj.style.color='#000';
	}
}
//鼠标不聚焦在搜索框时执行
function my_blur(obj, myid){
	//鼠标失焦时，如果搜索框没有输入值，则用搜索框的默认value值填充
	if (document.getElementById(myid).value == ''){
	 document.getElementById(myid).value = document.getElementById(myid).defaultValue;
	 obj.style.color='#999';
 }
}

/**
 * 点击搜索按钮执行的函数
 */
function search(){
	document.getElementById("searchform").submit();
}
</script>
<div id="divmenu">
<section id="navigation">
                    <nav>
                        <ul id="topnav" class="sf-menu">
                            <li><a href="${pageContext.request.contextPath}/client/index.jsp" class="current">Home</a></li>
                            <li><a href="${pageContext.request.contextPath}/showProductByPage?category=earings">Earings</a></li>
                            <li><a href="${pageContext.request.contextPath}/showProductByPage?category=rings">Rings</a></li>
                            <li><a href="${pageContext.request.contextPath}/showProductByPage?category=necklace" >Necklaces</a></li>
                            <li><a href="${pageContext.request.contextPath}/showProductByPage?category=bracelet">Bracelets</a></li>
                            <li><a href="${pageContext.request.contextPath}/showProductByPage?category=hairband">Hairclip</a></li>
                        </ul><!-- topnav -->
                    </nav><!-- nav -->
</section>
</div>