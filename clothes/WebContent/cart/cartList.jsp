<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<script src="/clothes/js/jquery-1.11.0.min.js"></script>
<script src="/clothes/cart/cartList.js"></script>
<link rel="stylesheet" href="css/cart.css"/>
<link rel="stylesheet" href="css/shoppingbasket.css"/>


<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh" content="0;url=/clothes/index.do">
</c:if>

<div id="cata" class="box2">
	<ul>
		<li><a href="/clothes/list.do?clothes_kind=100">상의</a>
		<li><a href="/clothes/list.do?clothes_kind=200">하의</a>
		<li><a href="/clothes/list.do?clothes_kind=300">신발</a>
		<li><a href="/clothes/list.do?clothes_kind=all">전체</a>
	</ul>
</div>
<div id="goShop">
	<button id="conShopping">쇼핑계속</button>
	<button id="shopMain">메인으로</button>
</div>
<div id="cartList">
	<c:if test="${count > 0 }">
 		<table>
 			<tr>
 				<td width="300">상품명</td>
 				<td width="300">판매가격</td>
 				<td width="300">수량</td>
 				<td width="300">사이즈</td>
 				<td width="300">금액</td>
 			</tr>
 			<c:set var="total" value="0"/>
 			<c:forEach var="cart" items="${cartLists}">
 				<tr>
 					<td width="300">
	 					<img src="/clothes/clothesImage/${cart.getClothes_image()}"
						class="cartimage">${cart.getClothes_title()} </td>
 					<td width="100">
          				<fmt:formatNumber value="${cart.getBuy_price()}" type="number" pattern="#,##0"/>원</td>
 	        		<td width="150">
 	          			<input type="text" name="buy_count" size="5" value="${cart.getBuy_count()}">
 	         	 		<button id="updateSu" name="${cart.getCart_id()},${cart.getBuy_count()}" 
 		      	 		onclick="editSu(this)">수정</button>
 					</td>
 					<td width="100">
 						${cart.getClothes_size() }
 					</td>
 	       			<td align="center"  width="150">
 	         			<c:set var="amount" value="${cart.getBuy_count()*cart.getBuy_price()}"/>
 	         			<c:set var="total" value="${total+amount}"/>
 	         			<fmt:formatNumber value="${amount}" type="number" pattern="#,##0"/>원
 	         			<button id="deleteList" name="${cart.getCart_id()}" 
	       				onclick="delList(this)">삭제</button>
       				</td>
 				</tr>
  			</c:forEach>
    		<tr>
     			<td colspan="4" align="right" class="b">총 금액 :
       			<fmt:formatNumber value="${total}" type="number" pattern="#,##0"/>원</td>
    		</tr>
    		<tr  height="10">
      			<td colspan="5" align="center">
         		<div id="cinfo">
         			<table>
         				<tr>
           					<td>
           						<form id="cartForm" method="post" action="/clothes/buyForm.do">
            						<input type="hidden" name="buyer" value="${sessionScope.id}">
            						<input type="submit" value="구매하기">
            					</form>
            				</td>
           					<td>
           						<form id="cartClearForm" method="post" action="/clothes/deleteCart.do">
            						<input type="hidden" name="list" value="all">
            						<input type="hidden" name="buyer" value="${sessionScope.id}">
            						<input type="submit" value="장바구니비우기">
            					</form>
            				</td>
						</tr>
         			</table>
         		</div>
      			</td>
    		</tr>
 		</table>
	</c:if>
</div> 