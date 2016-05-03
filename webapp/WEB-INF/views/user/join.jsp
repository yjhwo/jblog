<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
	$(function() {
		$("#join-form").submit(function() {
			// 1. 이름 유효성 체크(validation)
			if ($("#user_name").val() == "") {
				alert("이름은 필수 요소입니다.");
				$("#name").focus();
				return false;
			}
			// 2. ID 유효성 체크
			if ($("#user_id").val() == "") {
				alert("아이디는 필수 요소입니다.");
				$("#user_id").focus();
				return false;
			}
			// 						api
			if ($("#img-checkid").is(":visible") == false) { // 안 보이면
				alert("아이디 중복체크를 해야 합니다.");
				return false;
			}
			// 3.패스워드 유효성 체크
			if ($("#password").val() == "") {
				alert("패스워드는 필수 요소입니다.");
				$("#password").focus();
				return false;
			}

			// 4.약관 체크유무(과제) - jQuery checkbox, jQuery is checked
			if ($("input:checkbox[id='agree-prov']").is(":checked") == false) {
				alert("약관 체크를 해주십시오.");
				return false;
			}

			return true;
			//alert("여기가 보이면 폼을 submit하세요.");
			//return false;
		});
		$("#user_id").change(function() {
			$("#btn-checkid").show();
			$("#img-checkid").hide();
		});

		$("#btn-checkid").click(
						function() {
							var id = $("#user_id").val();
							if (id == "") {
								return;
							}
							console.log(id);

							$.ajax({
										url : "${pageContext.request.contextPath}/user/checkid?user_id="+ id, //요청URL
										type : "get", //통신 방식 get/post
										dataType : "json", //수신 데이터 타입
										data : "", //post방식인 경우 서버에 전달할 파라미터 데이터 
										//ex) a=checkemail&email=aaa@gmail.com
										//contentType:"application/json",	//보내는 데이터가 JSON형식인 경우
										//반드시 post방식인 경우
										//"{"a":"checkemail","email":"aaa@gmail.com"}"
										success : function(response) { //성공 시 callback
											if (response.result != "success") {
												return;
											}

											if (response.data == false) {
												alert("이미 존재하는 아이디입니다. 다른 아이디를 사용해 주세요.");
												$("#user_id").val("").focus();
												return;
											}
											//사용 가능한 이메일
											$("#btn-checkid").hide();
											$("#img-checkid").css("height","15px").show();
										},
										error : function(jqXHR, status, error) { //실패 시 callback
											console.error(jqXHR + ":" + status
													+ ":" + error);
										}
									});

						});
	});
</script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<ul class="menu">
			<li><a href="">로그인</a></li>
			<li><a href="">회원가입</a></li>
			<li><a href="">로그아웃</a></li>
			<li><a href="">내블로그</a></li>
		</ul>
		<form class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="user_name">이름</label>
			<input id="user_name" name="user_name" type="text" value="">
			
			<label class="block-label" for="user_id">아이디</label>
			<input id="user_id" name="user_id" type="text"> 
			
			<input id="btn-checkid" type="button" value="id 중복체크">
			<img id="img-checkid" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>
</body>
</html>
