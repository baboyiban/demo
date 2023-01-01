<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>title</title>
</head>
<body>
<%
	String msg = "Hello World";
	pageContext.setAttribute("msg",msg);
%>
${msg}
</body>
</html>