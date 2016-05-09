<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:choose>
	<c:when test='${not empty vo.title }'>
		<br>
		<h1><a href="${pageContext.request.contextPath}/${vo.user_id}">${vo.title}'S BLOG</a></h1>
	</c:when>
	<c:otherwise>
		<h1>Spring 이야기</h1>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test='${empty authUser }'>
		<li><a
			href="${pageContext.request.contextPath}/user/loginform/ret">로그인</a></li>
		<!-- 블로그주소 페이지에서 로그인 한 경우 main으로 안가고 다시 해당 블로그로 가게 return... -->
	</c:when>
	<c:otherwise>
		<li><a href="${pageContext.request.contextPath}/user/logout?user_id=${sessionScope.authUser.user_id}">로그아웃</a></li>
		<li><a href="${pageContext.request.contextPath}/${sessionScope.authUser.user_id}/blog_admin">블로그 관리</a></li>
	</c:otherwise>
</c:choose>