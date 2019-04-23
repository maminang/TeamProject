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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap.js"></script>
</head>
<body>
<%-- navBar --%>
	<jsp:include page="navBar.jsp"/>
	
<div class="container" align="center">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="article">
				<tr>
					<td>${article.num}</td>
					<td><c:forEach begin="1" end="${article.repIndent}">└</c:forEach>
						<a href="read.${article.tblName}?num=${article.num}&curPage=${curPage}">
							${article.title}</a> ${article.commentCnt }</td>
					<td>${article.writer}</td>
					<td>${article.writeDate}</td>
					<td>${article.readCnt}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
	
			<!-- 	페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징  -->
			<%--이전--%>
	<nav aria-label="Page navigation example" style="align-content: center">
		<ul class="pagination justify-content-center">
			<li class="page-item">
				<c:choose>
					<c:when test="${curPage > 1}">
						<a class="page-link"
							href="uploaded.domain?curPage=${beginPageNum-1 > 0?beginPageNum-1:1}">Prev</a>
					</c:when>
					<c:otherwise>
						<a class="page-link" href="#">Previous</a>
				</c:otherwise>
				</c:choose>
			</li>
			<%--이전--%>


			<%--페이지--%>
			<c:forEach begin="${beginPageNum}" end="${stopPageNum}"
				varStatus="status">&nbsp;
						<li class="page-item">
						<c:choose>
							<c:when test="${status.current == curPage}">
								<li class="page-item active">
									<a class="page-link">${status.current}<span class="sr-only">(current)</span></a>
								</li>
							</c:when>
						<c:otherwise>
							<a class="page-link"
								href="uploaded.domain?curPage=${status.current}">${status.current}</a>
						</c:otherwise>
					</c:choose>
					</li>
			</c:forEach>
			<%--페이지--%>

			<%--다음--%>
			<li class="page-item">
				<c:choose>
					<c:when test="${curPage < totalPage}">
						<a class="page-link"
							href="uploaded.domain?curPage=${stopPageNum+1 < totalPage?stopPageNum+1:totalPage}">Next</a>
					</c:when>
					<c:otherwise>
						<a class="page-link" href="#">Next</a>
					</c:otherwise>
				</c:choose>
			</li>
			<%--다음--%>
		</ul>
	</nav>
<!-- 	페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징  -->
	
	

</body>
</html>