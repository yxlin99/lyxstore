<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>匠心饰品店</title>
	<%-- 导入css --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
	<!-- 导入首页轮播图css和js脚本 -->
	<link type="text/css" href="${pageContext.request.contextPath }/client/css/autoplay.css" rel="stylesheet" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/client/js/autoplay.js"></script>
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
                        <img src="images/logo.png" alt="" />
                    </div>
	<%@include file="head.jsp"%>
	<%@include file="menu_search.jsp" %>
	</div>
</div>
	
	<div id="outerslider">
        	<div id="slidercontainer">
            	
                <div class="box_skitter box_skitter_large">
                    <ul>
                        <li>
                            <img src="images/homepic1.jpg" alt="" />
                            <div class="label_text">
                                <span>Hot-seller!</span>
                            </div>
                        </li>
                        <li>
                            <img src="images/homepic2.jpg" alt="" />
                            <div class="label_text">
                                <span>Beautiful earings!</span>
                            </div>
                        </li>
                        <li>
                            <img src="images/homepic3.jpg" alt="" />
                            <div class="label_text">
                                <span>Shining diamonds!</span>
                            </div>
                        </li>
                    </ul>
                </div>
                
            </div>
        </div>
	
	<%@ include file="foot.jsp" %>
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
