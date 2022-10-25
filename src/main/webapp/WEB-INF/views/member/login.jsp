<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
</head>
<body>
	
	<div class="container col-lg-6">
		<h1>Board Write Page</h1>
		<form action="login" method="post">
			<div class="mb-3">
				<label for="id" class="form-label">아이디를 입력해주세요</label>
				<input type="text" class="form-control" id="id" aria-describedby="emailHelp" placeholder="id" name="id">
			</div>
			<div class="mb-3">
				<label for="pw" class="form-label">비밀번호를 입력해주세요</label>
				<input type="text" class="form-control" id="pw" aria-describedby="emailHelp" placeholder="pw" name="pw">
			</div>
				<button class="btn btn-primary" type="submit">로그인하기</button>
		</form>
	</div>


	<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
</body>
</html>	