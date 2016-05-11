<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:choose>
	<c:when test='${empty authUser }'>
		<li><a href="${pageContext.request.contextPath}/user/loginform" class="waves-effect waves-light btn-large green">로그인</a></li>
		<li><a href="${pageContext.request.contextPath}/user/joinform" class="waves-effect waves-light btn-large green">회원가입</a></li>
	</c:when>
	<c:otherwise>
		<li><a href="${pageContext.request.contextPath}/user/logout" class="waves-effect waves-light btn-large green">로그아웃</a></li>
		<li><a href="${pageContext.request.contextPath}/${sessionScope.authUser.user_id}" class="waves-effect waves-light btn-large green">내블로그</a></li>
	</c:otherwise>
</c:choose>