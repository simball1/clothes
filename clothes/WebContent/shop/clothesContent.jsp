<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>

<script src="/clothes/js/jquery-1.11.0.min.js"></script>
<script src="/clothes/shop/clothescontent.js"></script>

<div id="cata" class="box2">
  <ul>
    <li><a href="/clothes/list.do?clothes_kind=100">상의</a>
    <li><a href="/clothes/list.do?clothes_kind=200">하의</a>
    <li><a href="/clothes/list.do?clothes_kind=300">신발</a>
    <li><a href="/clothes/list.do?clothes_kind=all">전체</a>
  </ul>
</div>

<div id="showClothes">
  <table class="vhcenter"> 
   <tr height="30"> 
     <td rowspan="6"  width="150">
        <img src="/clothes/clothesImage/${clothes.getClothes_image()}" class="contentimage"></td> 
     <td width="500">
       <b>${clothes.getClothes_title()}</b></td>
   </tr> 
   <tr><td width="500"><c:set var="price" value="${clothes.getClothes_price()}"/>
   
   <tr><td width="500">

     <c:if test="${!empty sessionScope.id}">
       <c:if test="${clothes.getClothes_count()==0}">
         <p>일시품절
       </c:if>
       <c:if test="${clothes.getClothes_count()>=1}">
         수량 : <input type="text" size="5" id="buy_count" value="1"> 개
       </c:if>
       <input type="hidden" id="clothes_id" value="${clothes_id}">
       <input type="hidden" id="clothes_image" value="${clothes.getClothes_image()}">
       <input type="hidden" id="clothes_title" value="${clothes.getClothes_title()}">
       <input type="hidden" id="clothes_price" value="${clothes_price}">
       <input type="hidden" id="clothes_kind" value="${clothes_kind}">
       <input type="hidden" id="buyer" value="${sessionScope.id}">              
       <button id="insertCart">장바구니에 담기</button>
     </c:if>
     <c:if test="${empty sessionScope.id}">
       <c:if test="${clothes.getClothes_count()==0}">
         <p>일시품절
       </c:if>
       <p>제품을 구매하시려면 로그인 하세요.
     </c:if>
     <button id="list">목록으로</button>
     <button id="shopMain">메인으로</button>
   </td>
  </tr>         
  <tr class="ch">
    <td colspan="2" class="hleft">${clothes.getClothes_content()}</td>
  </tr> 
</table>
</div>

<div id="showQna">
<p class="b">상품QnA 
<c:if test="${!empty sessionScope.id}">
   <button id="writeQna">상품QnA쓰기</button>
</c:if>
<c:if test="${empty sessionScope.id}">
   <p>상품QnA를 쓰실려면 로그인 하세요.</p>
</c:if>
</p>
<c:if test="${count == 0}">
   <ul>
      <li>등록된 상품QnA가 없습니다.
   </ul>
</c:if>
<c:if test="${count > 0}">
  <c:forEach var="qna" items="${qnaLists}">
    <ul>
      <li>
        <c:set var="writer" value="${qna.getQna_writer()}"/>
        ${fn:substring(writer, 0, 4)}****
        <small class="date">(${qna.getReg_date()})</small>
      <li>${qna.getQna_content()}
      <li>
        <c:if test="${sessionScope.id==writer}">
          <button id="edit" name="${qna.getQna_id()},${clothes_kind}" 
	       onclick="edit(this)">수정</button>
	      <button id="delete" name="${qna.getQna_id()},${clothes_id},${clothes_kind}" 
	       onclick="del(this)">삭제</button>
        </c:if>   
    </ul>
  </c:forEach>
</c:if>
</div>
