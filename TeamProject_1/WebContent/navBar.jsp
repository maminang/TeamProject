<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- 	============================================================================================ -->
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	<a class="navbar-brand" href="#">Navbar</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarsExampleDefault"
		aria-controls="navbarsExampleDefault" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="index.jsp">Home<span
					class="sr-only">(current)</span></a></li>
			<li class="nav-item"><a class="nav-link" href="chat.domain">Chat</a></li>

			<li class="nav-item">
				<c:if test="${not empty login}">
					<a class="nav-link" href="memberInfo.domain">MemberInfo</a>
				</c:if>
				<c:if test="${empty login}">
					<a class="nav-link disabled" href="login.jsp">MemberInfo</a>
				</c:if>
			</li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="dropdown01"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
				<div class="dropdown-menu" aria-labelledby="dropdown01">
					<a class="dropdown-item" href="list.final">�Խ���1</a> <a
						class="dropdown-item" href="list.final2">�Խ���2</a> <a class="dropdown-item"
						href="list.final3">�Խ���3</a> <a class="dropdown-item" href="list.final4">�Խ���4</a>
						<a class="dropdown-item" href="uploaded.domain">���ε�Խ���</a>
						<c:if test="${login.grade == 9}"><a class="dropdown-item" href="mgmt.domain">�濵��������</a></c:if>
				</div></li>
		</ul>

		<c:choose>
			<c:when test="${empty login}">
				<a class="nav-link" href="login.jsp">Login</a>
			</c:when>
			<c:when test="${not empty login}">
				<a class="nav-link" href="logout.domain">Logout</a>
			</c:when>
		</c:choose>

		<form class="form-inline my-2 my-lg-0" action="searchUI.domain"
			method="post">
<!-- 			<select name="searchOption"> -->
<!-- 				<option value="title">����</option> -->
<!-- 				<option value="content">����</option> -->
<!-- 				<option value="writer">�ۼ���</option> -->
<!-- 			</select>  -->
			<input class="form-control mr-sm-2" type="text" placeholder="�˻�"
				aria-label="Search" name="keyword">
			<button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Search</button>
		</form>
	</div>
</nav>
<!-- 	============================================================================================ -->