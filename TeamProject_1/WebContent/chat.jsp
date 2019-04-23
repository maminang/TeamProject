<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Testing websockets</title>
<link href="bootstrap.css" rel="stylesheet">
<link href="jumbotron.css" rel="stylesheet">
<link href="signin.css" rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap.js"></script>
<style type="text/css">
.profileImg {
	width: 150px;
	height: 150px;
	object-fit: cover;
	border-radius: 50%;
}

#chat{
	margin: auto;

}
.chatBox {
	border-color : rgb(0, 123, 255);
	background-color: rgb(255, 255, 255);
}

</style>

</head>
<body>
	<%-- navBar --%>
	<jsp:include page="navBar.jsp" />
	<br><br>
	<div id ="chat">
	<c:choose>
	<c:when test="${not empty dto.pfa}">
	<img class="profileImg" src="upload/${dto.pfa}"> 
	</c:when>
	<c:when test="${empty dto.pfa}">
	<img src="cat.png" class="rounded mx-auto d-block" >
	</c:when>
	</c:choose>
	<br>
	<fieldset>
		
		<textarea id="messageWindow" rows="10" cols="50" readonly="readonly" class="chatBox"></textarea>
		<input class="form-control mr-sm-2 chatBox" id="inputMessage" type="text" onkeyup="enterkey()" autofocus>
	
		<div align="right">
			<button class="btn btn-outline-primary my-2 my-sm-0" type="submit" onclick="send()" >전송</button>
		<c:if test="${not empty login}">
			<input id="id" type="hidden" value="${login.id}">
		</c:if>
		<c:if test="${empty login}">
			<input id="id" type="hidden" value="익명">
		</c:if>
		</div>
	</fieldset>
		</div>
	<br>
<!-- 	<textarea rows="15" cols="100"> -->
<!-- 	내가 쓰는 채팅은 로그인상태에 상관없이 무조건 나: 로 나오고 -->
<!-- 	상대가 쓰는 채팅은 -->
<!-- 	상대가 로그인이 된 상태면 아이디: -->
<!-- 	상대가 로그인이 안 된 상태면 익명: -->
<!-- 	으로 나옴 -->
	
<!-- 	인터넷에 있는 소스 퍼와서 -->
<!-- 	1. 엔터누르면 전송하기 -->
<!-- 	2. 로그인상태에 따라 아이디 -->
<!-- 	3. 채팅이 길어지면 스크롤 자동으로 밑으로 내리기 -->
<!-- 	4. 채팅방들어온 사람수 표시하기 -->
<!-- 	를 추가로 넣었음 -->
<!-- 	</textarea> -->
</body>
<script type="text/javascript">
	var textarea = document.getElementById("messageWindow");
	var webSocket = new WebSocket(
			'ws://211.183.8.74:8089/TeamProject/broadcasting');
	var inputMessage = document.getElementById('inputMessage');
	var id = document.getElementById("id");
	webSocket.onerror = function(event) {
		onError(event)
	};
	webSocket.onopen = function(event) {
		onOpen(event)
	};
	webSocket.onmessage = function(event) {
		onMessage(event)
	};
	function onMessage(event) {
		textarea.value += event.data + "\n";
	}
	function onOpen(event) {
		textarea.value += "채팅에 연결되었습니다.\n";
		textarea.value += "채팅방에 있는 인원 수 : ";
	}
	function onError(event) {
		alert(event.data);
	}

	function send() {

          textarea.value += "나 : " + inputMessage.value + "\n";
		
		webSocket.send(id.value + " : " + inputMessage.value);
		inputMessage.value = "";
	}
           
	function enterkey() {
		if (window.event.keyCode == 13) {
			if (inputMessage.value != "") {
				send();
			}
		}
	}
	window.setInterval(function() {
		var elem = document.getElementById('messageWindow');
		elem.scrollTop = elem.scrollHeight;
	}, 0);
</script>

</html>
