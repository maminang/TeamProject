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
<!--
@import url("style.css");
-->
</style>
</head>
<body>
	<%-- navBar --%>
	<jsp:include page="navBar.jsp" />
	<br>
	<br>
	<br>
	
	<div class="container" align="center">
	<form class="form-inline my-2 my-lg-0" action="searchUI.domain"
			method="post">
			<select name="searchOption">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="writer">작성자</option>
			</select> 
			<input class="form-control mr-sm-2" type="text"
				placeholder="Search" aria-label="Search" name="keyword">
			<button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Search</button>
		</form>
	<div align="right">
	<c:if test="${not empty login}">
		<form action="writeUI.final?curPage=${to.curPage}">
			<button class="btn btn-sm btn-primary " type="submit">글쓰기</button>
			<input type="hidden" value="${to.curPage}" name="curPage">
		</form>
		</c:if> 
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">작성일</th>
					<th scope="col">조회수</th>
					<th scope="col">좋아요</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="article">
					<tr>
						<td>${article.num}</td>
						<td><c:forEach begin="1" end="${article.repIndent}">└</c:forEach>
							<a href="read.final?num=${article.num}&curPage=${to.curPage}">
								${article.title}</a> [${article.commentCnt }]</td>
						<td>${article.writer}</td>
						<td>${article.writeDate}</td>
						<td>${article.readCnt}</td>
						<td>${article.good }</td>
					
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
		
		<%-- <c:when test="${not empty login}">
            <form action="#">
               <input type="submit">
            </form>
         </c:when> --%>
         <br>
	
	
		<!-- 	페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징 페이징  -->
			<%--이전--%>
	<nav aria-label="Page navigation example" style="align-content: center">
		<ul class="pagination justify-content-center">
			<li class="page-item">
				<c:choose>
					<c:when test="${to.curPage > 1}">
						<a class="page-link"
							href="list.final?curPage=${to.beginPageNum-1 > 0?to.beginPageNum-1:1}">Previous</a>
					</c:when>
					<c:otherwise>
						<a class="page-link" href="#">Previous</a>
					</c:otherwise>
				</c:choose>
			</li>
			<%--이전--%>

			<%--페이지--%>
			<c:forEach begin="${to.beginPageNum}" end="${to.stopPageNum}"
				varStatus="status">&nbsp;
						<li class="page-item">
						<c:choose>
							<c:when test="${status.current == to.curPage}">
								<li class="page-item active">
									<a class="page-link">${status.current}<span class="sr-only">(current)</span></a>
								</li>
							</c:when>
						<c:otherwise>
							<a class="page-link"
								href="list.final?curPage=${status.current}">${status.current}</a>
						</c:otherwise>
					</c:choose>
					</li>
			</c:forEach>
			<%--페이지--%>

			<%--다음--%>
			<li class="page-item">
				<c:choose>
					<c:when test="${to.curPage < to.totalPage}">
						<a class="page-link"
							href="list.final?curPage=${to.stopPageNum+1 < to.totalPage?to.stopPageNum+1:to.totalPage}">Next</a>
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
	
	
		
		<form action="list.final?curPage=1">
		<button class="btn btn-sm btn-primary  " type="submit">첫페이지로</button>
		</form>
	</div>
</body>
</html>