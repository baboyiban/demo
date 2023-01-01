<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>gugu</title>
</head>
<body>
	구구단 5단
	<c:if test="${not empty list}">
		<c:forEach var="item" items="${list}">
			<div>${item}</div>
		</c:forEach>
	</c:if>
</body>
</html>