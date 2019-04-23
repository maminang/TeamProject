<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link href="bootstrap.css" rel="stylesheet">
<link href="signin.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>

<%-- navBar --%>
	<jsp:include page="navBar.jsp"/>
	
	<div class="container">
		<img src="cat.png" class="rounded mx-auto d-block">
		<form class="form-signin" action="login.domain" method="post">
			<h2 class="form-signin-heading" align="center">Please sign in</h2>
			<label for="inputEmail" class="sr-only">Email address</label> 
			<input
				id="inputEmail" class="form-control"
				placeholder="Email address" name="id" required autofocus> 
			<label for="inputPassword" class="sr-only">Password</label> 
				<input type="password" id="inputPassword" class="form-control"
				placeholder="Password" name="pw" required >
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					Remember me
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
			<button class="btn btn-lg btn-primary btn-block" onclick="location.href='insertMemberUI.domain'" type="submit">New Account</button>
		</form>

	</div>
	<!-- /container -->

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="bootstrap.js"></script>
</body>
</html>
