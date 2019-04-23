<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="bootstrap.css" rel="stylesheet">
<link href="jumbotron.css" rel="stylesheet">
<link href="signin.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap.js"></script>
<style type="text/css">
.profileImg {
	width: 150px;
	height: 150px;
	object-fit: cover;
	border-radius: 50%;
}
.memberInfo {
 	border-color : rgb(0, 123, 255);
	background-color: rgb(245, 245, 245);  
}

</style>
</head>
<body>
	<%-- navBar --%>
	<jsp:include page="navBar.jsp" />
	<div class="container" align="center">

		<form class="form-signin" action="insertMember.domain" method="post">
			<c:choose>
				<c:when test="${not empty dto.pfa}">
					<h1 class="form-signin-heading" align="center">Member INFO</h1>
					<img class="profileImg" src="upload/${dto.pfa}" >

				</c:when>
				<c:when test="${empty dto.pfa}">
					<img src="cat.png" class="rounded mx-auto d-block">
					<br>

					<h5 class="form-signin-heading" align="center">사진이 없습니다</h5>
				</c:when>
			</c:choose>
			<br> <br> 
			
<%-- 			<label for="inputID" class="sr-only">${dto.id}</label> --%>
<%-- 			<input value="${dto.id}" class="form-control" placeholder="id" --%>
<!-- 				type="text" style="ime-mode: inactive;" name="id" -->
<!-- 				readonly="readonly" autofocus> -->
				
			<label for="inputID" class="sr-only">${dto.id}</label>	 
			<div class="form-control memberInfo">
				${dto.id}
			</div>
				
<%-- 			<label class="sr-only">${dto.name}</label> --%>
<%-- 			<input value="${dto.name}" class="form-control" placeholder="name" --%>
<!-- 				style="position: relative; top: 5px;" name="name" -->
<!-- 				readonly="readonly" autofocus> -->
				
			<label class="sr-only">${dto.name}</label>
			<div class="form-control memberInfo" style="position: relative; top: 5px;">
				${dto.name}
			</div>
				 
<%-- 			<label class="sr-only">${dto.nick}</label> --%>
<%-- 			<input value="${dto.nick}" class="form-control" --%>
<!-- 				placeholder="nickname" style="position: relative; top: 13px;" -->
<!-- 				name="nick" readonly="readonly" autofocus> -->
				 
			<label class="sr-only">${dto.nick}</label>				 
			<div class="form-control memberInfo" style="position: relative; top: 13px;">
				${dto.nick}
			</div>
			
<%-- 			<label class="sr-only">${dto.birth}</label>  --%>
<%-- 			<input value="${dto.birth}" --%>
<!-- 				class="form-control" placeholder="생년월일(20190101 형식으로 8자리)" -->
<%-- 				style="position: relative; top: 21px;" name="${dto.birth}" --%>
<!-- 				readonly="readonly" autofocus>  -->
				
			<label class="sr-only">${dto.birth}</label> 
			<div class="form-control memberInfo" style="position: relative; top: 21px;">
				${dto.birth}
			</div>
				
<%-- 			<label class="sr-only">${dto.email}</label> --%>
<%-- 			<input value="${dto.email}" class="form-control" readonly="readonly" --%>
<!-- 				placeholder="email(선택)" -->
<!-- 				style="ime-mode: disabled; position: relative; top: 29px;" -->
<!-- 				name="email" autofocus> -->
			
			<label class="sr-only">${dto.email}</label>
			<div class="form-control memberInfo" style="position: relative; top: 29px;" >
				<c:choose>
					<c:when test="${not empty dto.phone}">
						${dto.email}
					</c:when>
					<c:otherwise>
						email(선택)
					</c:otherwise>
				</c:choose>
			</div>
				 
				
<%-- 			<label class="sr-only">${dto.phone}</label> --%>
<%-- 			<input value="${dto.phone}" class="form-control" --%>
<!-- 				placeholder="phone(선택)" type="number" -->
<!-- 				style="position: relative; top: 37px;" name="phone" -->
<!-- 				readonly="readonly" autofocus> -->

			<label class="sr-only">${dto.phone}</label>
			<div class="form-control memberInfo" style="position: relative; top: 37px;" >
				<c:choose>
					<c:when test="${not empty dto.phone}">
						${dto.phone}
					</c:when>
					<c:otherwise>
						phone(선택)
					</c:otherwise>
				</c:choose>
			</div>
				
		</form>
		<br> <br>
		<form action="updateMemberUI.domain">
			<button class="btn btn-outline-primary my-2 my-sm-0" type="submit">정보수정</button>
			<input type="hidden" value="${dto.id}" name="id">
		</form>
	</div>
</body>
</html>





