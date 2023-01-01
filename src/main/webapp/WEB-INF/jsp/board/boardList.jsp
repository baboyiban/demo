<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>title</title>
<style type="text/css">
/* premitive */
body {
	width: 100vw;
	height: 100vh;
	text-align: center;
	margin: 0px;
	padding: 0px;
}

h1, h2, h3, h4, h5, h6 {
	margin: 0px;
}

th {
	background: rgb(32, 127, 223);
}

td {
	background: rgb(63, 191, 223);
	width: 5%;
	line-height: 100%;
}

tr:hover > td {
	background: rgb(191, 191, 223);
}
tr:active > td {
	background: rgb(191, 223, 223);
}

/* custom */
header {
	background: rgb(191, 127, 127);
	position: relative;
	width: 80%;
	height: 6%;
}

#header-1 {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}

#menu {
	background: rgb(127, 191, 127);
	position: relative;
	width: 80%;
	height: 4%;
}

#menu-1 {
	background: rgb(127, 159, 127);
	position: absolute;
	width: 20%;
	height: 80%;
	top: 50%;
	transform: translateY(-50%);
	margin: 0%;
}

#loginStatus {
	background: rgb(127, 127, 127);
	width: 20%;
	height: 10%;
	float: right;
	position: relative;
}

#loginStatus-1 {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}

main {
	background: rgb(223, 223, 127);
	width: 100%;
	height: 80%;
	position: relative;
}

#main-1 {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}

footer {
	background: rgb(127, 127, 127);
	width: 100%;
	height: 10%;
	position: relative;
}
</style>
</head>
<body>
	<div id="loginStatus">
		<div id="loginStatus-1">
			<%@include file="../login/status.jsp"%>
		</div>
	</div>
	<header>
		<div id="header-1">
			<h3>게시판 목록</h3>
		</div>
		<div id="header-2"></div>
	</header>
	<div id="menu">
		<div id="menu-1" onclick="location.href='/board/add';">
			<div>추가</div>
		</div>
	</div>
	<main>
		<div id="main-1">
			<c:if test="${not empty list}">
				<table>
					<tr>
						<th>num</th>
						<th>title</th>
						<th>author</th>
					</tr>
					<c:forEach items="${list}" var="item">
						<tr onclick="location.href='/board/detail?num=${item.NUM}';">
							<td>${item.NUM}</td>
							<td>${item.TITLE}</td>
							<td>${item.AUTHOR}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</main>
	<footer> </footer>
</body>
</html>