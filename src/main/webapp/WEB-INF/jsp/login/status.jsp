<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script type="text/javascript">
	function logout() {
		$.ajax({
			url : "/login/logout",
			method : "post",
			data : "",
			cache : false,
			dataType : "json",
			success : function(res) {
				alert(res.result);
				if (res.result == "로그아웃 성공")
					location.href = "/login";
			},
			error : function(xhs, status, err) {
				alert(err);
			}
		});
	}
</script>
<div>${id}로 로그인중...</div>
<button type="button" onclick="javascript:logout();">로그아웃</button>