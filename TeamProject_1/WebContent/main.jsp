<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Main</title>
<link href="bootstrap.css" rel="stylesheet">
<link href="jumbotron.css" rel="stylesheet">


</head>
<body>
	<%-- navBar --%>
	<jsp:include page="navBar.jsp"/>
	

	<!-- -----------------------------------------------------------����------------------------------------------------------- -->
	<!-- -----------------------------------------------------------����------------------------------------------------------- -->
	<!-- -----------------------------------------------------------����------------------------------------------------------- -->

	<div class="jumbotron">
		<div class="container">

			<h2 class="display-3">��ȣ�� ù ������Ʈ^^</h2>
		</div>
	</div>

<!-- 			�Խ���  �Խ���  �Խ���  �Խ���  �Խ���  �Խ���  �Խ���  �Խ���  �Խ���  �Խ���  �Խ���   -->
	<div class="container">
		<div class="row">
<!-- 		�Խ���1  �Խ���1  �Խ���1  �Խ���1  �Խ���1  �Խ���1  �Խ���1  �Խ���1  �Խ���1  �Խ���1   -->
			<div class="col-md-5">
				<h3>
					<a href="list.final">�Խ���1</a>
				</h3>
				<table border="1">
					<tr>
						<th>num</th>
						<th>�ۼ���</th>
						<th>����</th>
						<th>�ۼ���</th>
						<th>��ȸ��</th>
						<th>���ƿ�</th>
					</tr>
					<c:forEach var="dto" items="${board1List}">
						<tr>
							<th>${dto.num }</th>
							<th>${dto.writer }</th>
							<th><a href="read.final?num=${dto.num }">${dto.title}</a></th>
							<th>${dto.writeDate }</th>
							<th>${dto.readCnt }</th>
							<th>${dto.good}</th>
						<tr>
					</c:forEach>
				</table>
			</div>
			
<!-- 			�Խ���2  �Խ���2  �Խ���2  �Խ���2  �Խ���2  �Խ���2  �Խ���2  �Խ���2  �Խ���2  �Խ���2     -->
			<div class="col-md-5">
				<h3>
					<a href="list.final2">�Խ���2</a>
				</h3>
				<table border="1">
					<tr>
						<th>num</th>
						<th>�ۼ���</th>
						<th>����</th>
						<th>�ۼ���</th>
						<th>��ȸ��</th>
						<th>���ƿ�</th>
					</tr>
					<c:forEach var="dto" items="${board2List}">
						<tr>
							<th>${dto.num }</th>
							<th>${dto.writer }</th>
							<th><a href="read.final2?num=${dto.num }">${dto.title}</a></th>
							<th>${dto.writeDate }</th>
							<th>${dto.readCnt }</th>
							<th>${dto.good}</th>
						<tr>
					</c:forEach>
				</table>
			</div>
			
<!-- 			�Խ���3  �Խ���3  �Խ���3  �Խ���3  �Խ���3  �Խ���3  �Խ���3  �Խ���3  �Խ���3  �Խ���3   -->
			<div class="col-md-5">
				<h3>
					<br>
					<a href="list.final3">�Խ���3</a>
				</h3>
				<table border="1">
					<tr>
						<th>num</th>
						<th>�ۼ���</th>
						<th>����</th>
						<th>�ۼ���</th>
						<th>��ȸ��</th>
						<th>���ƿ�</th>
					</tr>
					<c:forEach var="dto" items="${board3List}">
						<tr>
							<th>${dto.num }</th>
							<th>${dto.writer }</th>
							<th><a href="read.final3?num=${dto.num }">${dto.title}</a></th>
							<th>${dto.writeDate }</th>
							<th>${dto.readCnt }</th>
							<th>${dto.good}</th>
						<tr>
					</c:forEach>
				</table>
			</div>
			
<!-- 			�Խ���4  �Խ���4  �Խ���4  �Խ���4  �Խ���4  �Խ���4  �Խ���4  �Խ���4  �Խ���4  �Խ���4   -->
			<div class="col-md-5">
				<h3>
					<br>
					<a href="list.final4">�Խ���4</a>
				</h3>
				<table border="1">
					<tr>
						<th>num</th>
						<th>�ۼ���</th>
						<th>����</th>
						<th>�ۼ���</th>
						<th>��ȸ��</th>
						<th>���ƿ�</th>
					</tr>
					<c:forEach var="dto" items="${board4List}">
						<tr>
							<th>${dto.num }</th>
							<th>${dto.writer }</th>
							<th><a href="read.final4?num=${dto.num }">${dto.title}</a></th>
							<th>${dto.writeDate }</th>
							<th>${dto.readCnt }</th>
							<th>${dto.good}</th>
						<tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<hr>
	</div>
<!-- 					�Խ���  �Խ���  �Խ���  �Խ���  �Խ���  �Խ���  �Խ���  �Խ���  �Խ���  �Խ���  �Խ���   -->
	<footer class="container">
		<p>&copy; �ӱ���</p>
	</footer>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="bootstrap.js"></script>
</body>
</html>
