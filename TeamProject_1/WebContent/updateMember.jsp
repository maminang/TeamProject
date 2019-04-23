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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap.js"></script>
</head>
<body>
<%-- navBar --%>
	<jsp:include page="navBar.jsp"/>
	<div class="container" align="center">
	<h1>
	PROFILE UPDATE
	</h1>
	<br><br>
	<form class="form-signin" action="updateMember.domain" method="post" enctype="multipart/form-data">
	<label class="sr-only">${dto.nick}</label> 	
		<input
				value="${dto.nick}" class="form-control" placeholder="nickname" style="position: relative; top: 0px;"
				name="nick" required="required"  autofocus>
				
	<label class="sr-only">${dto.email}</label> 	
		<input
				value="${dto.email}" class="form-control"required="required"  placeholder="email(선택)" style="ime-mode:disabled; position: relative; top: 15px;" name="email" autofocus>
			
	<label class="sr-only">${dto.phone}</label> 	
		<input
				value="${dto.phone}" class="form-control" placeholder="phone(선택)" type="number" style="position: relative; top: 30px;"
				name="phone" required="required" autofocus> 
				
	<label class="sr-only">PROFILE</label> 	
		<input
				class="form-control" type="file" placeholder="file(선택)" style="ime-mode:disabled; position: relative; top: 45px;" name="file" autofocus>
	
		<input type="hidden" value="${dto.id}" name="id"><br><br><br>
		<button class="btn btn-outline-primary my-2 my-sm-0" type="submit">저장</button>
		
	</form>
	</div> 
</body>
</html>