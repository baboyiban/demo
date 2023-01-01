<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>login</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"
	integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	function loginUser() {
		$.ajax({
			url : "/login",
			method : "post",
			data : $("#loginForm").serialize(),
			cache : false,
			dataType : "json",
			success : function(res) {
				alert(res.result);
				if (res.result=="로그인 성공")
					location.href = "/emp/list";
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
		<form id="loginForm">
			<div>
				<label for="id">아이디</label> <input type="text" name="id">
			</div>
			<div>
				<label for="pwd">암호</label> <input type="password" name="pwd">
			</div>
			<button type="button" onclick="javascript:loginUser();">로그인</button>
		</form>
	</main>
</body>
</html>