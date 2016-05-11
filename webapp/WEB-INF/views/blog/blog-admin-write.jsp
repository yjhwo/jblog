<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
			<div id="content" class="full-screen" >
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath}/${sessionScope.authUser.user_id}/blog_admin">기본설정</a></li>
					<li><a href="${pageContext.request.contextPath}/${sessionScope.authUser.user_id}/blog_category">카테고리</a></li>
					<li class="selected">글작성</li>
				</ul>
				<form action="write" method="post">
					<c:if test='${not empty vo.user_id }'>
						<input type="hidden" name="user_id" value="${vo.user_id}"/>
					</c:if>
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
			      				<input type="text" size="60" name="title" value="">
			      				
				      			<select name="category_id" >
									<c:forEach items="${list }" var="vo" varStatus ="status">
										<option value="${vo.category_id }">${vo.name }</option>
									</c:forEach>
				      			</select>
				      			
				      		</td>
			      		</tr>
			      		<spring:hasBindErrors name="postVO">
								<c:if test="${errors.hasFieldErrors('title') }">
									<c:set var="errorName" value="${errors.getFieldError( 'title' ).codes[0] }" />
									<br>
									<strong style="color: red"> 
									<spring:message code="${errorName }" text="${errors.getFieldError( 'title' ).defaultMessage }" />
									</strong>
								</c:if>
						</spring:hasBindErrors>
			      		
			      		<tr>
			      			<td class="t">내용</td>
			      			<td><textarea name="content"></textarea></td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s">
				      			<button class="btn waves-effect waves-light green" type="submit" name="action">포스트하기
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
	  <script type="text/javascript">
      
      $(document).ready(function() {
        $('select').material_select();
      });
            
      </script>
</body>
</html>