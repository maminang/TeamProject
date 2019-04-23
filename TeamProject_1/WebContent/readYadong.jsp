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
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
			<th>조회수</th>
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
			<th>내용</th>
		</tr>
		<tr>
			<th>${dto.content }</th>
		</tr>
	</table>
	<br>
	<a href="good.yadong?num=${dto.num }">좋아요</a>${dto.good }
	
	
	<table border="1">
		<tr>
			<th>repStep</th>
			<th>닉네임</th>
			<th>댓글</th>
			<th></th>
			<th>작성일</th>
		</tr>
		<c:forEach var="rdto" items="${rdto }">
			<tr>
				<th><c:choose>
						<c:when test="${rdto.repIndent eq 0}">${rdto.repStep }</c:when>
						<c:when test="${rdto.repIndent ne 0}">ㄴ${rdto.writer }</c:when>
					</c:choose></th>
				<th>${rdto.writer }</th>
				<th>${rdto.content }</th>
				<th><c:if test="${rdto.repIndent <= 0 }">
						<form action="reReplyInsert.yadong" method="post">
							<input name="num" value=${dto.num } type="hidden"> 
							<input name="repStep" value=${rdto.repStep } type="hidden"> 
							<input name="content" required="required" type="text" placeholder="댓글을 입력해주세요"> 
							<input type="submit" value="댓글쓰기">
						</form>
					</c:if></th>
				<th>${rdto.writeDate }</th>

				<th>
					<form action="replyUpdate.yadong" method="post">
						<input name="num" value="${dto.num }" type="hidden">			
						<input name="repLineUp" value=${rdto.repLineUp } type="hidden"> 
						<input name="content" required="required" type="text" placeholder="수정할 댓글을 입력해주세요"> 
						<input type="submit" value="수정">
					</form>
				</th>
				<th><a href="#">삭제</a></th>
			</tr>

		</c:forEach>
		<%
			request.getAttribute("rdto");
		%>
	</table>
	
	<form action="replyInsert.yadong" method="post">
		<input name="num" value=${dto.num } type="hidden"> 
		<input name="content" required="required" type="text" placeholder="댓글을 작성해주세요"> 
		<input type="submit" value="댓글쓰기">
	</form>
	<a href="select.yadong">목록</a>
	<a href="updateYadongUI.yadong?num=${dto.num }">수정</a>
	<a href="delete.yadong?num=${dto.num }">삭제</a>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="bootstrap.js"></script>

</body>
</html>










