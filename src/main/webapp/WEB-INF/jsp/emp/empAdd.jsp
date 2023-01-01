<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>empAdd</title>
<style type="text/css">
body {
	background: grey;
}

main {
	width: fit-content;
	margin: auto;
}

.btnDiv {
	display: inline-block;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"
	integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	function add() {
		if (requiredValue()) {
			$.ajax({
				url : "${path}/add",
				method : "post",
				data : $("#addForm").serialize(),
				dataType : "json",
				cache : false,
				success : function(res) {
					alert(res.result);
					if (res.result == "추가 성공")
						location.href = "${path}/list";
				},
				error : function(xhs, status, err) {
					alert(err);
				}
			});
		}
	}
	function requiredValue() {
		if ($("#ename").val() == "" || $("#deptno").val() == ""
				|| $("#sal").val() == "") {
			alert("정보를 입력해주세요");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<main>
		<h3>사원 추가</h3>
		<form id="addForm">
			<!-- 
사번, 이름, 부서, 급여, 입사일
 		-->
			<div>
				empno<label>${empno}</label>
			</div>
			<div>
				ename<input id="ename" type="text" name="ename">
			</div>
			<div>
				deptno<input id="deptno" type="number" name="deptno">
			</div>
			<div>
				sal<input id="sal" type="number" name="sal">
			</div>
			<div>
				hiredate<label><%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%></label>
			</div>
		</form>
		<div class="btnDiv">
			<button type="button"
				onclick="javascript:location.href='${path}/list';">목록</button>
		</div>
		<div class="btnDiv">
			<button type="button" onclick="javascript:add();">확인</button>
		</div>
	</main>
</body>
</html>