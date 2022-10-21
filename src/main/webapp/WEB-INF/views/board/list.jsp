<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>List Page</h2>
	
	<table class="table table-striped">
	<thead>
		<tr>
		<th>num</th><th>title</th><th>writer</th><th>contents</th>
		</tr>
	</thead>
	
	<tbody>
	<c:forEach items="${list}" var="list">
		<tr>
			<td>${list.num}</td>
			<%-- <td>${list.title}</td> --%>
			<td><a href="/qna/detail?num=${list.num}">${list.title}</a></td>
			<td>${list.writer}</td>
			<td>${list.contents}</td>
		</tr>
		</c:forEach>
		
	</tbody>
	</table>
		<div>
			<a href="./add" class="btn btn-danger">WRITE</a>
		</div>

</body>
</html>