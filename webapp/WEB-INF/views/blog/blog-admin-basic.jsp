<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
	<!--Import Google Icon Font-->
	<link href="http://fonts.googleapis.com/icon?family=Material+Icons"	rel="stylesheet">
	<!--Import materialize.css-->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/materialize.min.css" media="screen,projection" />

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>JBlog</title>
	<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	  <!--Import jQuery before materialize.js-->
      <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/materialize.min.js"></script>

	<div id="container">
		<div id="header">
			<ul>
				<c:import url="/WEB-INF/views/include/blog_header.jsp"/>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li class="selected">기본설정</li>
					<li><a href="${pageContext.request.contextPath}/${sessionScope.authUser.user_id}/blog_category">카테고리</a></li>
					<li><a href="${pageContext.request.contextPath}/${sessionScope.authUser.user_id}/blog_write">글작성</a></li>
				</ul>
				<form method="post" action="upload" enctype="multipart/form-data">
					<c:if test='${not empty vo.user_id }'>
						<input type="hidden" name="user_id" value="${vo.user_id}"/>
					</c:if>
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td><input type="text" size="40" name="title"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">로고이미지</td>
			      			<c:choose>			
								<c:when test='${not empty vo.logo }'><br>
									<td><img src="${pageContext.request.contextPath}${vo.logo }" ></td>
								</c:when>
								<c:otherwise>
									<td><img src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg"></td>
								</c:otherwise>
							</c:choose>
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input type="file" name="file" ></td>      			
			      		</tr>           		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s">
			      				<button class="btn waves-effect waves-light green" type="submit" name="action">기본설정 변경
								   <i class="material-icons right">send</i>
								</button>
			      			</td>
			      			      			
			      		</tr>           		
			      	</table>
				</form>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>