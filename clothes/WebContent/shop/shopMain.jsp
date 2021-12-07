<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>

<script src="<c:url value='/js/jquery-1.11.0.min.js'/>"></script>

<div id="cata" class="box2">
  <ul>
    <li><a href="/clothes/list.do?clothes_kind=100">상의</a>
    <li><a href="/clothes/list.do?clothes_kind=200">하의</a>
    <li><a href="/clothes/list.do?clothes_kind=300">신발</a>
    <li><a href="/clothes/list.do?clothes_kind=all">전체</a>
  </ul>
</div>

<div id="shop" class="box2">
  <c:forEach var="clothesList" items="${clothesLists}">
    <c:set var="clothes_kind" value="${clothesList[0].getClothes_kind()}"/>
    <c:if test="${clothes_kind=='100'}">
      <c:set var="clothes_kindName" value="상의"/>
    </c:if>
    <c:if test="${clothes_kind=='200'}">
      <c:set var="clothes_kindName" value="하의"/>
    </c:if>
    <c:if test="${clothes_kind=='300'}">
      <c:set var="clothes_kindName" value="신발"/>
    </c:if>
    <p class="b">[${clothes_kindName}] 분류의 신규 옷 목록:<a href="/clothes/list.do?clothes_kind=${clothes_kind}">더보기</a></p>
    <c:forEach var="clothes" items="${clothesList}">
     <table class="vhcenter">
      <tr height="30"> 
        <td rowspan="4"  width="100">
          <a href="/clothes/clothesContent.do?clothes_id=${clothes.getClothes_id()}&clothes_kind=${clothes.getClothes_kind()}">
             <img src="/clothes/clothesImage/${clothes.getClothes_image()}" class="listimage"></a></td>
        <td width="350" class="vhcenter">
          <a href="/clothes/clothesContent.do?clothes_id=${clothes.getClothes_id()}&clothes_kind=${clothes.getClothes_kind()}" class="b">
              ${clothes.getClothes_title()}</a></td>
        <td rowspan="4" width="100">
          <c:if test="${clothes.getClothes_count()==0}">
            일시품절
          </c:if>
          <c:if test="${clothes.getClothes_count()!=0}">
            구매가능
          </c:if>
       </td>      
       </tr>
       <tr height="30">
        <td width="350"><c:set var="price" value="${clothes.getClothes_price()}"/>
        </td></tr> 
     </table>
    </c:forEach>
    <br>
  </c:forEach>
</div>