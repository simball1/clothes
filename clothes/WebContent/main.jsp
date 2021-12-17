<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty loginUser}">
	<jsp:forward page='<c:url value="/member/login.do" />' />
</c:if>
<!DOCTYPE
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 전용 페이지</title>
<script type="text/javascript" src="<c:url value="/js/member.js" />"></script>
</head>
<body>
	<h2>회원 전용 페이지</h2>
	<form action="<c:url value="/member/logout.do"/>">
		<table>
			<tr>
				<td>안녕하세요. ${loginUser.name}(${loginUser.userid})님</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="로그아웃"> &nbsp;&nbsp;
				<c:url var="mUrl" value="/member/update.do?userid=${loginUser.userid}" />
				<input type="button" value="회원정보변경" onclick="location.href='${mUrl}'">
				<c:url var="bUrl" value="/board/list.do" />
				<input type="button" value="게시글 리스트" onclick="location.href='${bUrl}'">
				<c:url var="pUrl" value="/shop/clothesContent.do" />
				<input type="button" value="상품리스트" onclick="location.href='${pUrl}'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>