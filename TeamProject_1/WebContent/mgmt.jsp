<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="bootstrap.css" rel="stylesheet">
<link href="jumbotron.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap.js"></script>
</head>
<body>
	<jsp:include page="navBar.jsp" />
	총 회원수 : ${nbrMembers}
	<br> 총 게시물수 : ${nbrPosts}
	<br> ${month} 에 쓰여진 게시물수 : ${nbrPostsMonth}
	<br> 특정 달에 쓰여진 게시물수
	<br>
	<form action="mgmt.domain">
		<input type="month" name="month"><br> <input
			type="submit">
	</form>
</body>
</html>