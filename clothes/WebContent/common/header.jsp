<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/header.css"/>
<script src="/clothes/member/login.js"></script>

<title>Insert title here</title>
</head>
<body>
	<!-- 헤더 영역-->
		<header>
		<c:if test="${empty sessionScope.id}">
		
		  <div class="links">
			<a href="http://localhost:8080/clothes/manager/managerLoginForm.do" class="link_text">MGLOGIN</a>
			<a href="http://localhost:8080/clothes/loginForm.do" class="link_text">LOGIN</a>
			<a href="http://localhost:8080/clothes/registerForm.do" class="link_text">JOIN</a>
			<a href="http://localhost:8080/clothes/buyList.do" class="link_text">ORDER</a>
		  </div>
		  <a href="/clothes/index.do"><img src="images/a.png" class="img_logo"/></a>
		  </c:if>
		  <c:if test="${!empty sessionScope.id}">
		  		  <div class="links">
		  
		  	<button id="uLogout">LOGOUT</button>
		  	<a href="http://localhost:8080/clothes/cartList.do" class="link_text">CART</a>
		  	<a href="http://localhost:8080/clothes/buyList.do" class="link_text">ORDER</a>  
		  			  </div>
		  	
		  		  <a href="/clothes/index.do"><img src="images/a.png" class="img_logo"/></a>
		  
		  </c:if>
		  
		  
		  <nav>
			<div class="nav_items">    <!-- div class="cata" class="box2"> -->
			  <ul>
				<li><a href="/clothes/list.do?clothes_kind=100">TOPS</a></li>
				<li><a href="/clothes/list.do?clothes_kind=200">BOTTOMS</a></li>
				<li><a href="/clothes/list.do?clothes_kind=300">SHOES</a></li>
			 	<li><a href="/clothes/list.do?clothes_kind=all">전체보기</a></li>
			 </ul>
			  </div>
		  </nav>
		</header>
</body>
</html>