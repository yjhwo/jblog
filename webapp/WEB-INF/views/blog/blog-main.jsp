<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<ul>
				<c:import url="/WEB-INF/views/include/blog_header.jsp"/>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${title }</h4>
					<p>${content }</p>
				</div>
				<ul class="blog-list">				
					<c:forEach items="${postList }" var="vo" varStatus ="status">
						<li><a href="${pageContext.request.contextPath}/${user_id }?category_id=${vo.category_id}&post_id=${vo.post_id}">${vo.title }</a> <span>${vo.reg_date }</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<c:choose>			
					<c:when test='${not empty vo.logo }'><br>
						<img src="${pageContext.request.contextPath}${vo.logo }" >
					</c:when>
					<c:otherwise>
						<img src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${list }" var="vo" varStatus ="status">
					<li><a href="${pageContext.request.contextPath}/${user_id }?category_id=${vo.category_id}">${vo.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>${vo.user_id }</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>