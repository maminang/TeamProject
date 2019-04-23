<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Bootstrap Example</title>

<link href="bootstrap.css" rel="stylesheet">
<link href="jumbotron.css" rel="stylesheet">

</head>
<body>
	
	<%-- navBar --%>
	<jsp:include page="navBar.jsp"/>
	
	<h1>tbl_yadong</h1>
	<table border="1">
		<tr>
			<th>num</th>
			<th>�ۼ���</th>
			<th>����</th>
			<th>�ۼ���</th>
			<th>��ȸ��</th>
			<th>repRoot</th>
			<th>repStep</th>
			<th>repIndent</th>
		</tr>
		<tr>
			<th>${dto.num }</th>
			<th>${dto.writer }</th>
			<th>${dto.title }</th>
			<th>${dto.writeDate }</th>
			<th>${dto.readcnt }</th>
			<th>${dto.repRoot }</th>
			<th>${dto.repStep }</th>
			<th>${dto.repIndent }</th>
		</tr>

	</table>
	<br>
	<table border="1">
		<tr>
			<th>����</th>
		</tr>
		<tr>
			<th>${dto.content }</th>
		</tr>
	</table>
	<br>
	<a href="good.yadong?num=${dto.num }">���ƿ�</a>${dto.good }
	
	
	<table border="1">
		<tr>
			<th>repStep</th>
			<th>�г���</th>
			<th>���</th>
			<th></th>
			<th>�ۼ���</th>
		</tr>
		<c:forEach var="rdto" items="${rdto }">
			<tr>
				<th><c:choose>
						<c:when test="${rdto.repIndent eq 0}">${rdto.repStep }</c:when>
						<c:when test="${rdto.repIndent ne 0}">��${rdto.writer }</c:when>
					</c:choose></th>
				<th>${rdto.writer }</th>
				<th>${rdto.content }</th>
				<th><c:if test="${rdto.repIndent <= 0 }">
						<form action="reReplyInsert.yadong" method="post">
							<input name="num" value=${dto.num } type="hidden"> 
							<input name="repStep" value=${rdto.repStep } type="hidden"> 
							<input name="content" required="required" type="text" placeholder="����� �Է����ּ���"> 
							<input type="submit" value="��۾���">
						</form>
					</c:if></th>
				<th>${rdto.writeDate }</th>

				<th>
					<form action="replyUpdate.yadong" method="post">
						<input name="num" value="${dto.num }" type="hidden">			
						<input name="repLineUp" value=${rdto.repLineUp } type="hidden"> 
						<input name="content" required="required" type="text" placeholder="������ ����� �Է����ּ���"> 
						<input type="submit" value="����">
					</form>
				</th>
				<th><a href="#">����</a></th>
			</tr>

		</c:forEach>
		<%
			request.getAttribute("rdto");
		%>
	</table>
	
	<form action="replyInsert.yadong" method="post">
		<input name="num" value=${dto.num } type="hidden"> 
		<input name="content" required="required" type="text" placeholder="����� �ۼ����ּ���"> 
		<input type="submit" value="��۾���">
	</form>
	<a href="select.yadong">���</a>
	<a href="updateYadongUI.yadong?num=${dto.num }">����</a>
	<a href="delete.yadong?num=${dto.num }">����</a>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="bootstrap.js"></script>

</body>
</html>










