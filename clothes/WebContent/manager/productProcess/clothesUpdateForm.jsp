<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<script src="/clothes/js/jquery-1.11.0.min.js"></script>
<script src="/clothes/js/jquery.form.min.js"></script>
<script src="/clothes/manager/productProcess/clothesupdate.js"></script>

<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=/clothes/manager/managerMain.do" >
</c:if>

<div id="header">
  <button id="clothesMain">관리자 메인으로</button>
  <button id="clothesList">목록으로</button>
</div>
<form id="upForm1" action="/clothes/manager/clothesUpdatePro.do" 
          method="post" enctype="multipart/form-data">
<div id="clothesUpdateForm" class="box">
   <ul>
      <li><label for="clothes_kind">분류선택</label>
          <select id="clothes_kind" name="clothes_kind">
            <option value="100"
            <c:if test="${clothes_kind == 100}">selected</c:if>
            >상의</option>
            <option value="200"
            <c:if test="${clothes_kind == 200}">selected</c:if>
            >하의</option>
            <option value="300"
            <c:if test="${clothes_kind == 300}">selected</c:if>
            >신발</option>
          </select>
      <li><label for="clothes_title">이름</label>
          <input id="clothes_title" name="clothes_title" type="text" 
           size="50" maxlength="50" value="${clothes.clothes_title}">
          <input type="hidden" name="clothes_id" value="${clothes_id}">
      <li><label for="clothes_price">가격</label>
          <input id="clothes_price" name="clothes_price" type="text" 
           size="10" maxlength="9" value="${clothes.clothes_price}">원
      <li><label for="clothes_count">수량</label>
          <input id="clothes_count" name="clothes_count" type="text" 
           size="10" maxlength="5" value="${clothes.clothes_count}">개
      <li><label for="clothes_count">사이즈</label>
          <input id="clothes_size" name="clothes_size" type="text" 
           size="10" maxlength="5" value="${clothes.clothes_size}">
      <li><label for="clothes_image">옷 이미지</label>
          <input id="clothes_image" name="clothes_image" type="file">${clothes.clothes_image}  
      <li><label for="clothes_content">내용</label>
          <textarea id="clothes_content" name="clothes_content" 
                rows="13" cols="50">${clothes.clothes_content}</textarea>
      <li class="label2">
          <input type="submit" id="updateClothes" value="옷수정">
   </ul>
</div>         

</form>