<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>匠心饰品店</title>
	<%--导入css --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/main.css" type="text/css" />
	<link rel="stylesheet" type="text/css" href="styles/style.css" />
    <link rel="stylesheet" type="text/css" href="styles/skitter.styles.css" media="all" />

	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600,700,300' rel='stylesheet' type='text/css' />
    <link href='http://fonts.googleapis.com/css?family=Oswald:700,400' rel='stylesheet' type='text/css' />
	<script type="text/javascript">
	function categoryChange(category)
	{
		switch (category) {
		case "earings":
			return "耳环";
			break;
		case "rings":
			return "戒指";
			break;
		case "necklace":
			return "项链";
			break;
		case "bracelet":
			return "手链";
			break;
		case "hairband":
			return "发夹";
			break;
		default:
			break;
		}
		
	}
	</script>
</head>
<body>
<div id="bodychild">
<div id="outercontainer">
        <div id="outerheader">
                    <div id="logo">
                        <img src="/client/images/logo.png" alt="" />
                    </div>
	<%@include file="head.jsp"%>
	<%@include file="menu_search.jsp" %>
	</div>
</div>
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td>
					<table cellspacing="0" class="listcontent">
						<tr>
							<td>
								<h1>商品目录</h1>
								<hr />
								&nbsp;&nbsp;&nbsp;&nbsp;共${bean.totalCount}种商品
								<hr />
								<table cellspacing="0" class="booklist">
									<tr>
										<c:forEach items="${bean.ps}" var="p" varStatus="vs">
											<td>
												<div class="divbookpic">
													<p>
														<a href="${pageContext.request.contextPath}/findProductById?id=${p.id}">
															<img src="${pageContext.request.contextPath}${p.imgurl}" width="115" height="129" border="0" /> 
														</a>
													</p>
												</div>
												<div class="divlisttitle">
													<a href="${pageContext.request.contextPath}/findProductById?id=${p.id}">书名： ${p.name}<br />售价：￥${p.price} </a>
												</div>
											</td>
										</c:forEach>
									</tr>
								</table>
								<div class="pagination">
									<ul>
										<c:if test="${bean.currentPage!=1}">
											<li class="disablepage_p">
												<a class="disablepage_a" href="${pageContext.request.contextPath}/showProductByPage?currentPage=${bean.currentPage-1}&category=${bean.category}"></a>
											</li>
										</c:if>
										<c:if test="${bean.currentPage==1}">
											<li class="disablepage_p2"></li>
										</c:if>
										<c:forEach begin="1" end="${bean.totalPage}" var="pageNum">
											<c:if test="${pageNum==bean.currentPage}">
												<li class="currentpage">${pageNum }</li>
											</c:if>
											<c:if test="${pageNum!=bean.currentPage}">
												<li><a href="${pageContext.request.contextPath}/showProductByPage?currentPage=${pageNum}&category=${bean.category}">${pageNum}</a>
												</li>
											</c:if>
										</c:forEach>

										<c:if test="${bean.currentPage==bean.totalPage||bean.totalPage==0}">
											<li class="disablepage_n2"></li>
										</c:if>
										<c:if test="${bean.currentPage!=bean.totalPage&&bean.totalPage!=0}">
											<li class="disablepage_n">
												<a class="disablepage_a" href="${pageContext.request.contextPath}/showProductByPage?currentPage=${bean.currentPage+1}&category=${bean.category}"></a>
											</li>
										</c:if>
									</ul>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>

</div>


	<!-- Javascript
================================================== -->
<script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>
<!-- jQuery Superfish -->
<script type="text/javascript" src="js/hoverIntent.js"></script> 
<script type="text/javascript" src="js/superfish.js"></script> 
<script type="text/javascript" src="js/supersubs.js"></script>

<!-- Custom Script -->
<script type="text/javascript" src="js/custom.js"></script>

<!-- Slider -->
<script type="text/javascript" src="js/jquery.animate-colors-min.js"></script>
<script type="text/javascript" src="js/jquery.skitter.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript">
jQuery(document).ready(function(){

	//=================================== SLIDESHOW ===================================//
	jQuery(".box_skitter_large").skitter({
		animation: "random",
		interval: 3000,
		numbers: false, 
		numbers_align: "right", 
		hideTools: false,
		controls: false,
		focus: false,
		focus_position: true,
		width_label:'1000px', 
		enable_navigation_keys: true,   
		progressbar: false
	});

});
</script>
</body>
</html>
