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
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	  <!--Import jQuery before materialize.js-->
      <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/materialize.min.js"></script>

	<div class="center-content">
		<h1 class="logo" onClick="location.href='${pageContext.request.contextPath}/main';">JBlog</h1>
		<ul class="menu">
			<c:import url="/WEB-INF/views/include/header.jsp"/>
		</ul>
		
		<form class="login-form" name="loginform" method="post" action="${pageContext.request.contextPath}/user/login?ret=${ret}">
      		<label>아이디</label> <input type="text" name="user_id">
      		<label>패스워드</label> <input type="password" name="password" value="">					
      		<!-- <input type="submit" value="로그인"> -->
      		<button class="btn waves-effect waves-light  green" type="submit" name="action">로그인
			   <i class="material-icons right">send</i>
			</button>
      		
		</form>
	</div>
</body>
</html>
