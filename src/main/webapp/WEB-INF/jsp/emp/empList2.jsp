<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>empList</title>
<style type="text/css">
body {
	width: 100vw;
	height: 100vh;
	margin: 0px;
	padding: 0px;
	text-align: center;
}

header {
	background: rgb(191, 63, 63);
	width: 80%;
	height: 10%;
	position: relative;
}

section {
	background: rgb(63, 63, 191);
	width: 80%;
	height: 80%;
	position: relative;
}

#section-1 {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	overflow: auto;
}

aside {
	float: right;
	position: relative;
	background: rgb(191, 191, 63);
	width: 20%;
	height: 80%;
}

#aside-1 {
	background: rgb(181, 181, 63);
}

#aside-2 {
	background: rgb(127, 127, 127);
}

footer {
	background: rgb(63, 191, 63);
	width: 100%;
	height: 10%;
}

h3 {
	margin: 0px;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}

input, input:focus {
	background: lightGrey;
	border: 1px solid black;
	outline: none;
}

input[type="number"]::-webkit-outer-spin-button, input[type="number"]::-webkit-inner-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}

button {
	background: lightGrey;
	border: 1px solid grey;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"
	integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	function search(e) {
		if (e.keyCode == 13) {
			var deptno = $("#deptno").val();
			var ename = $("#ename").val();
			if (deptno != "" && ename != "")
				location.href = "${path}/list?deptno=" + deptno + "&ename="
						+ ename;
			else if (deptno != "" && ename == "")
				location.href = "${path}/list?deptno=" + deptno;
			else if (deptno == "" && ename != "")
				location.href = "${path}/list?ename=" + ename;
			else
				location.href = "${path}/list";
		}
	}
</script>
</head>
<body>
	<%@include file="../login/status.jsp"%>
	<header>
		<h3>사원 목록</h3>
	</header>
	<aside>
		<div id="aside-1">
			<label>이름</label> <input id="ename" type="text" name="ename"
				onkeypress="search(event);"> <label>부서</label> <input id="deptno"
				type="number" name="deptno" onkeypress="search(event);">
		</div>
		<div id="aside-2" onclick="javascript:location.href='${path}/add';">추가</div>
	</aside>
	<section>
		<!-- -->
		<div id="section-1">
			<table>
				<c:forEach items="${listEmp}" var="item">
					<tr>
						<td>${item.ename}</td>
						<td><a href="${path}/detail?empno=${item.empno}">${item.ename}</a></td>
						<td>${item.deptno}</td>
						<td>${item.dname}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<!-- -->
	</section>
	<footer></footer>
</body>
</html>