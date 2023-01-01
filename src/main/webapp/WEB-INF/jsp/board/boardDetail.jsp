<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>title</title>
</head>
<body>
	<div>
		<label for="num">num</label> <input id="num" type="number" name="num"
			value="${board.num}" readonly>
	</div>
	<div>
		<label for="title">title</label> <input id="title" type="text"
			name="title" value="${board.title}" readonly>
	</div>
	<div>
		<label for="author">author</label> <input id="author" type="text"
			name="author" value="${board.author}" readonly>
	</div>
	<div>
		<label for="contents">contents</label>
		<textarea id="contents" name="contents" readonly="readonly">${board.contents}</textarea>
	</div>
	<div id="files">
		<table>
			<tr>
				<th>num</th>
				<th>fname</th>
				<th>fsize</th>
			</tr>
			<c:forEach items="${listAttach}" var="attach">
				<tr>
					<td>${attach.num}</td>
					<td><a href="/board/download?num=${attach.num}">${attach.fname}</a></td>
					<td>${attach.fsize}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>