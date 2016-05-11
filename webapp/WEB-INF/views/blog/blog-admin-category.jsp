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
<script type="text/javascript">
var categoryNo = 1;

var fetchList = function(){
	$.ajax({
		url:"${pageContext.request.contextPath}/category/getList",
		type:"post",
		dataType:"json",		
		data:"blog_id=${vo.blog_id}",
		success:function(response){
			if(response.result != "success"){
				return;
			}
			
			//HTML 생성한 후 UL에 append
			$.each(response.data, function(index, vo){
				$("#category-list").append(renderHtml(vo));
				categoryNo++;
			});
		
		},
		error:function(xhr, status, error){	/* xhr: xml http request*/
			console.error(status+":"+error);
		}
	});	
}

var renderHtml = function(vo){
	var Html ="<tr id='tr-"+vo.category_id+"'>"+
				"<th>"+categoryNo +"</th>"+
			  	"<th>"+vo.name+"</th>"+
			  	"<th>"+vo.post_count+"</th>"+
			  	"<th>"+vo.description+vo.category_id+"</th>"+
			  	"<th><img src='${pageContext.request.contextPath}/assets/images/delete.jpg' onclick='clickDel("+vo.category_id+")' style='cursor:pointer'></th>"+  
				"</tr>";
	
	
	return Html;
}

var clickDel = function(category_id){

		$.ajax({
			url:"${pageContext.request.contextPath}/category/del",
			type:"post",
			dataType:"json",		
			data:"category_id="+category_id,
			success:function(response){
				if(response.result != "success"){
					alert("카테고리 삭제에 실패하였습니다.");
					return;
				}	
				
				//categoryNo = 1;					// 변수 다시 초기화
				$("#tr-"+category_id).empty();		// 값 삭제
				
			},
			error:function(xhr, status, error){	/* xhr: xml http request*/
				console.error(status+":"+error);
			}
		});	
}

$(function() {
	$("#btn_addCategory").click(
					function() {
						var name = $("#name").val();
						var desc = $("#desc").val();
						//alert("${vo.blog_id}");
						if (name == "" ) {	// desc는 없어도 괜찮음
							return;
						}
						
						$.ajax({
							url : "${pageContext.request.contextPath}/category/add", //요청URL
							type : "post", 
							dataType : "json", 
							data : "name="+name+"&desc="+desc+"&blog_id=${vo.blog_id}", 
							success : function(response) { 							//성공 시 callback
								if (response.result != "success") {
									alert("카테고리 추가에 실패하였습니다.");
									return;
								}
								$("#name").val("");
								$("#desc").val("");
								
								$("#category-list").append(renderHtml(response.data));
								categoryNo++;
							},
							error : function(jqXHR, status, error) { //실패 시 callback
								console.error(jqXHR + ":" + status
										+ ":" + error);
							}
						});
						
					});
	// 최초 데이터 가져오기
	fetchList();
});


</script>
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
					<li><a href="${pageContext.request.contextPath}/${sessionScope.authUser.user_id}/blog_admin">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath}/${sessionScope.authUser.user_id}/blog_write">글작성</a></li>
				</ul>
				
			     <table id="category-list" class="admin-cat">
			      	<tr>
			      		<th>번호</th>
			      		<th>카테고리명</th>
			      		<th>포스트 수</th>
			      		<th>설명</th>
			      		<th>삭제</th>      			
			      	</tr>
				</table>
     		 	
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" id="name" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" id="desc" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td>
			      			<button class="btn waves-effect waves-light green" type="submit" name="action" id="btn_addCategory">카테고리 추가
							 	<i class="material-icons right">send</i>
							</button>
		      			</td>
		      		</tr>      		      		
		      	</table> 
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