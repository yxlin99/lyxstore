<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>匠心饰品店</title>
	<%--导入css --%>
	<link rel="stylesheet" href="client/css/main.css" type="text/css" />
	<link rel="stylesheet" type="text/css" href="styles/style.css" />
    <link rel="stylesheet" type="text/css" href="styles/skitter.styles.css" media="all" />

	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600,700,300' rel='stylesheet' type='text/css' />
    <link href='http://fonts.googleapis.com/css?family=Oswald:700,400' rel='stylesheet' type='text/css' />
    
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
								<h1>全部商品</h1>&nbsp;&nbsp;&nbsp;&nbsp;共${bean.totalCount}种商品
								<hr />
								<div style="margin-top:20px; margin-bottom:5px">
									<img src="client/images/productlist.gif" width="100%" height="38" />
								</div>

								<table cellspacing="0" class="booklist">
									<tr>
										<c:forEach items="${bean.ps}" var="p" varStatus="vs">
											<td>
												<div class="divbookpic">
													<p>
														<a href="${pageContext.request.contextPath}/findProductById?id=${p.id}"><img
															src="${pageContext.request.contextPath}${p.imgurl}"
															width="115" height="129" border="0" /> </a>
													</p>
												</div>
												<div class="divlisttitle">
													<a href="${pageContext.request.contextPath}/findProductById?id=${p.id}">书名： ${p.name}<br />售价：￥${p.price} </a>
												</div>
											</td>
											<%-- <c:if test="${vs.count%4==0}">
											</c:if> --%>
										</c:forEach>
									</tr>
								</table>
								<!-- <table cellspacing="0" class="booklist">
									<tr>
									</tr>
								</table> -->

								<div class="pagination">
									<ul>
										<c:if test="${bean.currentPage!=1}">
											<li class="nextPage">
												<a href="${pageContext.request.contextPath}/MenuSearchSerlvet?currentPage=${bean.currentPage-1}&textfield=${bean.searchfield}">&lt;&lt;上一页</a>
											</li>
										</c:if>
										<c:if test="${bean.currentPage==1}">
											<li class="disablepage">&lt;&lt;上一页</li>
										</c:if>
										<c:forEach begin="1" end="${bean.totalPage}" var="pageNum">

											<c:if test="${pageNum==bean.currentPage}">
												<li class="currentpage">${pageNum }</li>
											</c:if>
											<c:if test="${pageNum!=bean.currentPage}">
												<li><a href="${pageContext.request.contextPath}/MenuSearchSerlvet?currentPage=${pageNum}&textfield=${bean.searchfield}">${pageNum}</a>
												</li>
											</c:if>

										</c:forEach>

										<c:if test="${bean.currentPage==bean.totalPage||bean.totalPage==0}">
											<li class="disablepage">下一页 &gt;&gt;</li>
										</c:if>

										<c:if test="${bean.currentPage!=bean.totalPage&&bean.totalPage!=0}">
											<li class="nextpage">
												<a href="${pageContext.request.contextPath}/MenuSearchSerlvet?currentPage=${bean.currentPage+1}&textfield=${bean.searchfield}">下一页&gt;&gt;</a>
											</li>
										</c:if>
									</ul>
								</div></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>
