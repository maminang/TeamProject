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
<link href="signin.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap.js"></script>
</head>
<body>
   <%-- navBar --%>
   <jsp:include page="navBar.jsp"/>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	
	
	
	<%-- navBar --%>
	<jsp:include page="navBar.jsp"/>
	
	<div class="container">
		<img src="cat.png" class="rounded mx-auto d-block">
		
		<form class="form-signin" action="insertMember.domain" method="post">
			<h2 class="form-signin-heading" align="center">New Account</h2>
			
				<label for="inputEmail" class="sr-only">ID</label> 
				<input class="form-control" placeholder="ID" type="text" style="ime-mode:inactive;" 
				 name="id" required autofocus>				
				
				<label for="inputPassword" class="sr-only">Password</label> 
					<input type="password" id="inputPassword" class="form-control" style="ime-mode:disabled position: relative; top: 8px;"
						placeholder="Password" name="pw" required autofocus>
				
				<label class="sr-only">name</label> 	
				<input class="form-control" placeholder="name" style="position: relative; top: 5px;"
					name="name" required autofocus>
			
			<label class="sr-only">nickName</label> 	
			<input
				class="form-control" placeholder="닉네임" style="position: relative; top: 13px;"
				name="nick" required autofocus>
				
			<label class="sr-only">birth</label> 				
			<input
				class="form-control" placeholder="생년월일(20190101 형식으로 8자리)" style="position: relative; top: 21px;"
				name="birth" required autofocus> 
			
			<label class="sr-only">email</label> 	
			<input
				class="form-control" placeholder="email(선택)" style="ime-mode:disabled; position: relative; top: 29px;" name="email" autofocus>
			
			<label class="sr-only">phone</label> 	
			<input
				class="form-control" placeholder="phone(선택)" type="number" style="position: relative; top: 37px;"
				name="phone" autofocus> 
							
			<button class="btn btn-lg btn-primary btn-block" type="submit" style="position: relative; top: 45px;">Sign
				in</button>
				 
		</form>
	  
	</div> 	
<!-- 	aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa -->
</body>
</html>