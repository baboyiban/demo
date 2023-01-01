<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>empDetail</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"
	integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	function delEmp() {
		console.log($("#detailForm").serialize());
		$.ajax({
			url:"${path}/delete",
			method:"post",
			data:$("#detailForm").serialize(),
			dataType:"json",
			cache:false,
			success:function(res) {
				alert(res.result);
				location.href="${path}/list";
			},
			error:function(xhs,status,err) {
				alert(err);
			}
		});
	}
	function isDeptno() {
		$.ajax({
			url:"${path}/deptno",
			method:"post",
			data:"",
			cache:false,
			dataType:"json",
			success:function(res) {
				if (res.result) {
					
				}
			},
			error:function(xhs,status,err){
				alert(err);
			}
		});
	}
</script>
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
</head>
<body>
	<main>
		<h3>사원 정보</h3>
		<c:if test="${not empty emp}">
			<form id="detailForm">
				<table>
					<tr>
						<td><label>${emp.empno}</label><input type="hidden"
							name="empno" value="${emp.empno}"></td>
						<td><label>${emp.ename}</label><input type="hidden"
							name="ename" value="${emp.ename}"></td>
						<td><label>${emp.deptno}</label><input type="hidden"
							name="deptno" value="${emp.deptno}"></td>
						<td><label>${emp.sal}</label><input type="hidden" name="sal"
							value="${emp.sal}"></td>
						<td><label>${emp.hiredate}</label><input type="hidden"
							name="hiredate" value="${emp.hiredate}"></td>
					</tr>
				</table>
			</form>
			<div class="btnDiv">
				<button type="button"
					onclick="javascript:location.href='${path}/list';">목록</button>
			</div>
			<div class="btnDiv">
				<button type="button"
					onclick="javascript:location.href='${path}/edit?empno=${emp.empno}';">수정</button>
			</div>
			<div class="btnDiv">
				<button type="button" onclick="javascript:delEmp();">삭제</button>
			</div>
		</c:if>
	</main>
</body>
</html>