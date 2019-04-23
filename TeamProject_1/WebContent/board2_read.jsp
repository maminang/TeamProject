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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap.js"></script>
<style type="text/css">
.profileImg {
	width: 50px;
	height: 50px;
	object-fit: cover;
	border-radius: 50%;
}
/* #deleteBtn{ */
/* 	color: red; */
/* 	position: relative; top: 700px; */
/* } */
</style>
</head>
<body>
	<!-- 	============================================================================================ -->
	<jsp:include page="navBar.jsp" />
	<!-- 	============================================================================================ -->


<!-- 		점보트론점보트론 점보트론 점보트론 점보트론 점보트론 점보트론 점보트론 점보트론   -->
		
	<div class="jumbotron">
		<div class="container">

			<h1 class="display-5">Title : ${dto.title}</h1>
			<img class="profileImg" alt="프로필사진이 없습니다" src="upload/${mdto.pfa}">
			작성자 :${dto.writer} 작성일 : ${dto.writeDate}
			조회수 :${dto.readCnt } ${youtube }
			<br><br>
			<h2>${dto.content}</h2>
			<p>
				<%-- 좋아요 싫어요 : 나중에 ajax 처리 --%>
				
			<form action="good.final2">
				<button class="btn btn-outline-primary my-2 my-sm-0" 
				style="position: absolute; right: 100px;" type="submit" >
						좋아요${dto.good}
					<c:if test="${idGood}">♥</c:if>
					<c:if test="${!idGood}">♡</c:if>
				</button>
				<input class="form-control mr-sm-2" type="hidden"
					value="${dto.num }" name="num">
			</form>
			<form action="bad.final2" style="position: absolute; right: 15px;">
				<button class="btn btn-outline-primary my-2 my-sm-0" type="submit">싫어요${dto.bad}</button>
				<input class="form-control mr-sm-2" type="hidden"
					value="${dto.num }" name="num">

			</form>
			
			<c:if test="${login.grade==0 || dto.writer==login.id}">
				<form action="updateUI.final2" style="position: absolute; right: 52px; top: 340px;">
					<input type="hidden" value="${dto.num}" name="num">
					<button class="btn btn-sm btn-primary" type="submit">수정</button>
				</form>
			</c:if>

			<c:if test="${login.grade==0 || dto.writer==login.id}">
				<form action="delete.final2" style="position: absolute; right: 15px; top: 340px; float: right;">
					<input type="hidden" value="${dto.num}" name="num">
					<button class="btn btn-sm btn-primary" type="submit">삭제</button>

				</form>
			</c:if>
		</div>
	</div>

<!-- 		점보트론점보트론 점보트론 점보트론 점보트론 점보트론 점보트론 점보트론 점보트론   -->

	<%-- 이미지 --%>

	<c:if test="${not empty dto.up_adress}">
		<img alt="${dto.up_adress}" src="upload/${dto.up_adress}">
		<br>
	</c:if>
	
	<br>

	<%--유튜브 --%>
	<c:if test="${not empty dto.youtube}">
		<iframe width="420" height="345"
			src="https://www.youtube.com/embed/${youtube}"> </iframe>
	</c:if>

	<%-- 이미지 파일 다운로드 --%>
	<c:if test="${not empty dto.up_adress}">
		<br>
		첨부파일 
		<a href="download.final2?num=${dto.num}">${dto.up_adress}</a>
		<br>
	</c:if>

<!-- 	 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 	 -->
	
	<table class="table table-hover">
			<tbody>
				<c:forEach items="${commentList}" var="comment">
					<tr>
						<td>
								댓글작성자 :${comment.writer}<br>
      							내용 : ${comment.content}
      						<div align="right">
							<c:if test="${login.grade==0 || comment.writer==login.id}">
								<form action="deleteComment.final2">
									<input type="hidden" value="${dto.num}" name="num">
									<input type="hidden" value="${comment.cnum}" name="cnum">
									<button id="deleteBtn" class="btn btn-sm btn-primary" type="submit">삭제</button>
								</form>
							</c:if>
      						</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
	
<!-- 	 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 댓글표시 	 -->
	
	
	<%--댓글작성 --%>
	<c:if test="${not empty login}">
		<form action="writeComment.final2">
			<textarea name="content" rows="6" cols="75"
				placeholder="댓글을 여기에 적어주세요" required></textarea>
			<br> <input name="writer" type="hidden" value="${login.id}">
			<button class="btn btn-outline-primary my-2 my-sm-0" type="submit" >댓글작성</button>
			<input class="form-control mr-sm-2" type="hidden" value="${dto.num }"
				name="num">
		</form>
	</c:if>
	<br>
	<%-- 각종버튼 --%>
	<form action="list.final2" style="width: -10; float: left;">
		<input type="hidden" value="${curPage}" name=curPage>
		<button class="btn btn-sm btn-primary" type="submit">목록으로</button>
	</form>
	<form action="writeReplyUI.final2">
		<input type="hidden" value="${dto.num}" name="num">
		<button class="btn btn-sm btn-primary" type="submit">답글달기</button>
	</form>
	<br>


</body>
</html>