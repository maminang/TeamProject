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


<!-- 		����Ʈ������Ʈ�� ����Ʈ�� ����Ʈ�� ����Ʈ�� ����Ʈ�� ����Ʈ�� ����Ʈ�� ����Ʈ��   -->
		
	<div class="jumbotron">
		<div class="container">

			<h1 class="display-5">Title : ${dto.title}</h1>
			<img class="profileImg" alt="�����ʻ����� �����ϴ�" src="upload/${mdto.pfa}">
			�ۼ��� :${dto.writer} �ۼ��� : ${dto.writeDate}
			��ȸ�� :${dto.readCnt } ${youtube }
			<br><br>
			<h2>${dto.content}</h2>
			<p>
				<%-- ���ƿ� �Ⱦ�� : ���߿� ajax ó�� --%>
				
			<form action="good.final2">
				<button class="btn btn-outline-primary my-2 my-sm-0" 
				style="position: absolute; right: 100px;" type="submit" >
						���ƿ�${dto.good}
					<c:if test="${idGood}">��</c:if>
					<c:if test="${!idGood}">��</c:if>
				</button>
				<input class="form-control mr-sm-2" type="hidden"
					value="${dto.num }" name="num">
			</form>
			<form action="bad.final2" style="position: absolute; right: 15px;">
				<button class="btn btn-outline-primary my-2 my-sm-0" type="submit">�Ⱦ��${dto.bad}</button>
				<input class="form-control mr-sm-2" type="hidden"
					value="${dto.num }" name="num">

			</form>
			
			<c:if test="${login.grade==0 || dto.writer==login.id}">
				<form action="updateUI.final2" style="position: absolute; right: 52px; top: 340px;">
					<input type="hidden" value="${dto.num}" name="num">
					<button class="btn btn-sm btn-primary" type="submit">����</button>
				</form>
			</c:if>

			<c:if test="${login.grade==0 || dto.writer==login.id}">
				<form action="delete.final2" style="position: absolute; right: 15px; top: 340px; float: right;">
					<input type="hidden" value="${dto.num}" name="num">
					<button class="btn btn-sm btn-primary" type="submit">����</button>

				</form>
			</c:if>
		</div>
	</div>

<!-- 		����Ʈ������Ʈ�� ����Ʈ�� ����Ʈ�� ����Ʈ�� ����Ʈ�� ����Ʈ�� ����Ʈ�� ����Ʈ��   -->

	<%-- �̹��� --%>

	<c:if test="${not empty dto.up_adress}">
		<img alt="${dto.up_adress}" src="upload/${dto.up_adress}">
		<br>
	</c:if>
	
	<br>

	<%--��Ʃ�� --%>
	<c:if test="${not empty dto.youtube}">
		<iframe width="420" height="345"
			src="https://www.youtube.com/embed/${youtube}"> </iframe>
	</c:if>

	<%-- �̹��� ���� �ٿ�ε� --%>
	<c:if test="${not empty dto.up_adress}">
		<br>
		÷������ 
		<a href="download.final2?num=${dto.num}">${dto.up_adress}</a>
		<br>
	</c:if>

<!-- 	 ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� 	 -->
	
	<table class="table table-hover">
			<tbody>
				<c:forEach items="${commentList}" var="comment">
					<tr>
						<td>
								����ۼ��� :${comment.writer}<br>
      							���� : ${comment.content}
      						<div align="right">
							<c:if test="${login.grade==0 || comment.writer==login.id}">
								<form action="deleteComment.final2">
									<input type="hidden" value="${dto.num}" name="num">
									<input type="hidden" value="${comment.cnum}" name="cnum">
									<button id="deleteBtn" class="btn btn-sm btn-primary" type="submit">����</button>
								</form>
							</c:if>
      						</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
	
<!-- 	 ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� ���ǥ�� 	 -->
	
	
	<%--����ۼ� --%>
	<c:if test="${not empty login}">
		<form action="writeComment.final2">
			<textarea name="content" rows="6" cols="75"
				placeholder="����� ���⿡ �����ּ���" required></textarea>
			<br> <input name="writer" type="hidden" value="${login.id}">
			<button class="btn btn-outline-primary my-2 my-sm-0" type="submit" >����ۼ�</button>
			<input class="form-control mr-sm-2" type="hidden" value="${dto.num }"
				name="num">
		</form>
	</c:if>
	<br>
	<%-- ������ư --%>
	<form action="list.final2" style="width: -10; float: left;">
		<input type="hidden" value="${curPage}" name=curPage>
		<button class="btn btn-sm btn-primary" type="submit">�������</button>
	</form>
	<form action="writeReplyUI.final2">
		<input type="hidden" value="${dto.num}" name="num">
		<button class="btn btn-sm btn-primary" type="submit">��۴ޱ�</button>
	</form>
	<br>


</body>
</html>