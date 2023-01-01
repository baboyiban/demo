<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>empEdit</title>
<style type="text/css">
body {
	background: grey;
}

main {
	width: fit-content;
	margin: auto;
}

td {
	display: block;
}

.btnDiv {
	display: inline-block;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"
	integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	function edit() {
		$.ajax({
			url : "${path}/edit",
			method : "post",
			dataType : "json",
			data : $("#editForm").serialize(),
			cache : false,
			success : function(res) {
				alert(res.result);
				if (res.result == "수정 성공")
					location.href = "${path}/list";
			},
			error : function(xhs, status, err) {
				alert(err);
			}
		});
	}
</script>
</head>
<body>
	<main>
		<h3>사원 정보 수정</h3>
		<form id="editForm">
			<table>
				<c:if test="${not empty emp}">
					<tr>
						<td><label>${emp.empno}</label><input type="hidden"
							name="empno" value="${emp.empno}"></td>
						<td><input type="text" name="ename" value="${emp.ename}"></td>
						<td><input type="number" name="deptno" value="${emp.deptno}"></td>
						<td><input type="number" name="sal" value="${emp.sal}"></td>
						<td><label>${emp.hiredate}</label></td>
					</tr>
				</c:if>
			</table>
			<div class="btnDiv">
				<c:if test="${not empty emp}">
					<button type="button"
						onclick="javascript:location.href='${path}/detail?empno=${emp.empno}';">취소</button>
				</c:if>
			</div>
			<div class="btnDiv">
				<button type="button" onclick="javascript:edit();">수정</button>
			</div>
		</form>
	</main>
</body>
</html>