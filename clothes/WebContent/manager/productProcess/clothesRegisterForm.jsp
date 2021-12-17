<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<script src="/clothes/js/jquery-1.11.0.min.js"></script>
<script src="/clothes/js/jquery.form.min.js"></script>
<script src="/clothes/manager/productProcess/clothesregist.js"></script>

<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh"
		content="0;url=/clothes/manager/managerMain.do">
</c:if>

<div id="listHeader">
	<button id="clothesMain">관리자 메인으로</button>
	<button id="clothesList">목록으로</button>
</div>
<form id="upForm1" action="/clothes/manager/clothesRegisterPro.do" method="post" enctype="multipart/form-data">
	<div id="clothesReigstForm" class="box">
		<ul>
			<li><label for="clothes_kind">분류선택</label> <select
				id="clothes_kind" name="clothes_kind">
					<option value="100">상의</option>
					<option value="200">하의</option>
					<option value="300">신발</option>
			</select>
			<li><label for="clothes_title">이름</label> <input
				id="clothes_title" name="clothes_title" type="text" size="50"
				placeholder="이름" maxlength="50">
			<li><label for="clothes_price">가격</label> <input
				id="clothes_price" name="clothes_price" type="text" size="10"
				placeholder="가격" maxlength="9">원
			<li><label for="clothes_count">수량</label> <input
				id="clotehs_count" name="clothes_count" type="text" size="10"
				placeholder="수량" maxlength="5">
			<li><label for="clothes_size">사이즈</label> 
				<select id="clothes_size" name="clothes_size">
					<option value="S">S</option>
					<option value="M">M</option>
					<option value="L">L</option>
				</select>
			<li><label for="clothes_image">옷 이미지</label> <input
				id="clothes_image" name="clothes_image" type="file">
			<li><label for="clothes_content">옷 설명</label> <textarea
				id="clothes_content" name="clothes_content" rows="13" cols="50"></textarea>
			<li class="label2">
				<input type="submit" id="registClothes" value="옷 등록">
		</ul>
	</div>
</form>