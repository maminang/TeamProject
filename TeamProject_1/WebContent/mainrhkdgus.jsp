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

			<h1 class="display-3">��ȣ�� ù ������Ʈ^^</h1>



			<p>
				This is a template for a simple marketing or informational website.
				It includes a large callout called<br> a jumbotron and three
				supporting pieces of content. Use it as a starting point to create
				something more unique.
			</p>
			<p>
				<a class="btn btn-primary btn-lg" href="#" role="button">Learn
					more &raquo;</a>
			</p>
		</div>
	</div>

	<div class="container">
		<!-- Example row of columns -->
		<div class="row">
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
						<th><a href="mainSelectReadcnt.yadong">��ȸ��</a></th>
						<th><a href="mainSelectGood.yadong">���ƿ�</a></th>
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
				<p>
					<a class="btn btn-secondary" href="list.final" role="button">
						Board1 &raquo;</a>
				</p>
			</div>
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
						<th><a href="mainSelectReadcnt.yadong">��ȸ��</a></th>
						<th><a href="mainSelectGood.yadong">���ƿ�</a></th>
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
				<p>
					<a class="btn btn-secondary" href="list.final2" role="button">
						�Խ��� 2��(final2)) &raquo;</a>
				</p>
			</div>
			<div class="col-md-5">
				<h3>
					<a href="list.final3">�Խ���3</a>
				</h3>
				<table border="1">
					<tr>
						<th>num</th>
						<th>�ۼ���</th>
						<th>����</th>
						<th>�ۼ���</th>
						<th><a href="mainSelectReadcnt.yadong">��ȸ��</a></th>
						<th><a href="mainSelectGood.yadong">���ƿ�</a></th>
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
				<p>
					<a class="btn btn-secondary" href="list.final3" role="button">
						�Խ��� 3��(final3)) &raquo;</a>
				</p>
			</div>
		</div>
		
		<div class="col-md-5">
				<h3>
					<a href="list.final4">�Խ���4</a>
				</h3>
				<table border="1">
					<tr>
						<th>num</th>
						<th>�ۼ���</th>
						<th>����</th>
						<th>�ۼ���</th>
						<th><a href="mainSelectReadcnt.yadong">��ȸ��</a></th>
						<th><a href="mainSelectGood.yadong">���ƿ�</a></th>
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
				<p>
					<a class="btn btn-secondary" href="list.final4" role="button">
						�Խ��� 2��(final2)) &raquo;</a>
				</p>
			</div>

		<hr>

	</div>

	<footer class="container">
		<p>&copy; �ӱ���</p>
	</footer>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="bootstrap.js"></script>
</body>
</html>
