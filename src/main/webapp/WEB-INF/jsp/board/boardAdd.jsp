<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>title</title>
<style type="text/css">
body {
	width: 100vw;
	height: 100vh;
}

h1, h2, h3, h4, h5, h6 {
	margin: 0px;
}

div>label {
	display: block;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"
	integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	function saveBoard() {
		$.ajax({
			url : "/board/add",
			method : "post",
			data : $("#boardForm").serialize(),
			dataType : "json",
			cache : false,
			success : function(res) {
				alert(res.result);
			},
			error : function(xhs, status, err) {
				alert(err);
			}
		});
	}
</script>
</head>
<body>
	<header>
		<h3>게시판 추가</h3>
	</header>
	<form id="boardForm" action="/board/add" method="post" enctype="multipart/form-data">
		<div>
			<label for="title">title</label> <input id="title" type="text"
				name="title">
		</div>
		<div>
			<label for="author">author</label><input id="author" type="text"
				name="author" value="${id}" readonly>
		</div>
		<div>
			<label for="contents">contents</label>
			<textarea id="contents" name="contents"></textarea>
		</div>
		<div>
			<input type="file" name="files" multiple="multiple">
		</div>
		<div>
			<button type="button" onclick="submit();">확인</button>
		</div>
	</form>
</body>
</html>