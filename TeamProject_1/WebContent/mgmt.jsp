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
	�� ȸ���� : ${nbrMembers}
	<br> �� �Խù��� : ${nbrPosts}
	<br> ${month} �� ������ �Խù��� : ${nbrPostsMonth}
	<br> Ư�� �޿� ������ �Խù���
	<br>
	<form action="mgmt.domain">
		<input type="month" name="month"><br> <input
			type="submit">
	</form>
</body>
</html>