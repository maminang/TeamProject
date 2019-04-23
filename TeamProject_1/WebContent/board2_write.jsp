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
<style type="text/css">
.profileImg {
	width: 150px;
	height: 150px;
	object-fit: cover;
	border-radius: 50%;
}

table.type04 {
    border-collapse: separate;
    border-spacing: 1px;
    text-align: left;
    line-height: 1.5;
    border-top: 1px solid #ccc;
  margin : 20px 10px;
}
table.type04 th {
    width: 150px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}

table.type04 td {
    width: 100px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    margin: auto;
}
</style>
</head>
<body>
	<%-- navBar --%>
	<jsp:include page="navBar.jsp"/>
	<div align="center">
	<br>
	<Br>
	<h1>�۾���</h1>
	<br>
	<Br>
	<form action="write.final2" method="post" enctype="multipart/form-data">
		<table class="type04" >
		<tr>
				<td >�ۼ���</td>
				<td><input type="text" readonly="readonly" name="writer" value="${login.id}"></td>
			</tr>
			<tr>
				<td >����</td>
				<td><input style="width:300px" name="title" placeholder="����" required ></td>
			</tr>
			
			<tr>
				<td>����</td>
				<td><textarea rows="10" cols="80" name="content"
						placeholder="���⿡ �����ּ���" required></textarea><br> </td>
			</tr>
				<tr align="left">
				<td></td>
				<td>	<input type="file" name="file"> </td></tr>
				
			<tr align="left">
			<td></td>
			<td>
			<input style="width:400px" name="youtube" type="text" placeholder="��Ʃ�� '�����ϱ�'�� ������ ���� �ּҸ� �Է����ּ���">
			</td></tr>
			
		</table>
		<button class="btn btn-outline-primary my-2 my-sm-0" type="submit">����</button>
		<button class="btn btn-outline-primary my-2 my-sm-0" type="reset">�ʱ�ȭ</button>
		<br> 
	</form>
	<form action="list.final2?curPage=${curPage}">
	<button style="margin-top: 10px" class="btn btn-sm btn-primary" type="submit">�ڷΰ���</button>
	</form>
	</div>
</body>
</html>