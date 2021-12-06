<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<script src="/clothes/js/jquery-1.11.0.min.js"></script>
<script src="/clothes/manager/productProcess/clotheslist.js"></script>

<c:if test="${empty sessionScope.id }">
	<meta http-equiv="Refresh" content="0;url=/clothes/manager/managerMain.do" >
</c:if>

<div id="listHeader">
	<p>등록된 상품 목록(전체 상품:${count})
	<button id="regist">책 등록</button>
	<button id="bookMain">관리자 메인으로</button>
</div>
<div id="clothess">
	<c:if test="${count == 0}">
		<ul>
			<li>등록된 상품이 없습니다.
		</ul>
	</c:if>
	<c:if test="${count > 0}">
		<table>
			<tr class="title">
			  <td align="center"  width="30">번호</td> 
		      <td align="center"  width="30">옷분류</td> 
		      <td align="center"  width="100">이름</td>
		      <td align="center"  width="50">가격</td> 
		      <td align="center"  width="50">수량</td> 
		      <td align="center"  width="50">사이즈</td> 
		      <td align="center"  width="50">옷 이미지</td>
		      <td align="center"  width="70">등록일</td>
		      <td align="center"  width="50">수정</td>
		      <td align="center"  width="50">삭제</td> 
			</tr>
		
			<c:set var="number" value="${0}"/>
			<c:forEach var="clothes" items="${clothesList}">
			<tr>
				<td align="center" width="50">
					<c:set var="number" value="${number+1}"/>
	  				<c:out value="${number}"/>
				</td>
				<td width="30">${clothes.getClothes_kind()}</td>
				<td width="100" align="left">${clothes.getClothes_title()}</td>
      			<td width="50" align="right">${clothes.getClothes_price()}</td> 
      			<td width="50" align="right">
		      		<c:if test="${clothes.getClothes_count() == 0}">
		         		<font color="red">일시품절</font>
		      		</c:if>
		      		<c:if test="${clothes.getClothes_count() > 0}">
		         		${clothes.getClothes_count()}
		      		</c:if>
      			</td> 
      			<td width="50">${clothes.getClothes_size()}</td>
		      	<td width="50">${clothes.getClothes_image()}</td>
		      	<td width="50"><fmt:formatDate pattern="yyyy-MM-dd" value="${clothes.getReg_date()}"/></td>
		      	<td width="50">
		      	<button id="edit" name="${clothes.getClothes_id()},${clothes.getClothes_kind()}" onclick="edit(this)">수정</button></td>
			  	<td width="50">
			  	<button id="delete" name="${clothes.getClothes_id()},${clothes.getClothes_kind()}" onclick="del(this)">삭제</button></td>
			</tr>
			</c:forEach>		 
		</table>
	</c:if>
</div>