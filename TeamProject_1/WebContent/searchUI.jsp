<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>
<title>Insert title here</title>
<link href="bootstrap.css" rel="stylesheet">
<link href="jumbotron.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap.js"></script>

<style>
	.search { display: table; margin-left: auto; margin-right: auto; } 
</style>


</head>
<body>
	<%-- navBar --%>
	<jsp:include page="navBar.jsp" />
	<br>
	<br>
	<br>
	<div class="container" align="center">
	<div align="left">
	<select name="searchOption2" onchange="haha(this)">
		<option ${searchOption2=='writeDate'?'selected':''} value="writeDate">ÃÖ½Å¼ø</option>
		<option ${searchOption2=='good'?'selected':''} value="good">ÃßÃµ¼ø</option>
		<option ${searchOption2=='readCnt'?'selected':''} value="readcnt">Á¶È¸¼ø</option>
	</select>
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>¹øÈ£</th>
				<th>Á¦¸ñ</th>
				<th>ÀÛ¼ºÀÚ</th>
				<th>ÀÛ¼ºÀÏ</th>
				<th>Á¶È¸¼ö</th>
				<th>ÃßÃµ¼ö</th>
				<th>list.tblName</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${list}">
				<tr>
					<td>${list.num}</td>
					<td><a href="read.${list.tblName }?num=${list.num}">${list.title}</a></td>
					<td>${list.writer}</td>
					<td>${list.writeDate}</td>
					<td>${list.readCnt}</td>
					<td>${list.good}</td>
					<td>${list.tblName }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	

	<!-- 	ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡  -->
			<%--ÀÌÀü--%>
	<nav aria-label="Page navigation example" style="align-content: center">
		<ul class="pagination justify-content-center">
			<li class="page-item">
				<c:choose>
					<c:when test="${curPage > 1}">
						<a class="page-link"
							href="searchUI.domain?curPage=${beginPageNum-1 > 0?beginPageNum-1:1}&keyword=${keyword}&searchOption=${searchOption}&searchOption2=${searchOption2}">Previous</a>
					</c:when>
					<c:otherwise>
						<a class="page-link" href="#">Previous</a>
					</c:otherwise>
				</c:choose>
			</li>
			<%--ÀÌÀü--%>

			<%--ÆäÀÌÁö--%>
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
								href="searchUI.domain?curPage=${status.current}&keyword=${keyword}&searchOption=${searchOption}&searchOption2=${searchOption2}">${status.current}</a>
						</c:otherwise>
					</c:choose>
					</li>
			</c:forEach>
			<%--ÆäÀÌÁö--%>

			<%--´ÙÀ½--%>
			<li class="page-item">
				<c:choose>
					<c:when test="${curPage < totalPage}">
						<a class="page-link"
							href="searchUI.domain?curPage=${stopPageNum+1 < totalPage?stopPageNum+1:totalPage}&keyword=${keyword}&searchOption=${searchOption}&searchOption2=${searchOption2}">Next</a>
					</c:when>
					<c:otherwise>
						<a class="page-link" href="#">Next</a>
					</c:otherwise>
				</c:choose>
			</li>
			<%--´ÙÀ½--%>
		</ul>
	</nav>
<!-- 	ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡ ÆäÀÌÂ¡  -->
	<div class="search">
	<form class="form-inline my-2 my-lg-0" action="searchUI.domain"  method="post">
		<select name="searchOption">
			<option value="title">Á¦¸ñ</option>
			<option value="content">³»¿ë</option>
			<option value="writer">ÀÛ¼ºÀÚ</option>
		</select> 
		<br>
		<input class="form-control mr-sm-1" type="text"
				placeholder="Search" aria-label="Search" name="keyword">
			<button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Search</button>
		</form>
	</div>
	<br>
	
	<div>
		<button class="btn btn-sm btn-primary" onclick="location.href='searchUI.domain?curPage=1&keyword=${keyword}&searchOption=${searchOption}&searchOption2=${searchOption2}'" type="submit">Ã¹ÆäÀÌÁö·Î</button>
	</div>
		
	<script type="text/javascript">
		function haha(select) {
			var val = select.value;

			location.href = "searchUI.domain?searchOption=${searchOption}&keyword=${keyword}&searchOption2="
					+ val;

		}
		
	</script>
	</div>
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>