<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/css/test.css" rel="stylesheet">
</head>
<body>
	<h1>Index Page</h1>
	<h1>**${sessionScope.member.name}**</h1>
	
	<img alt="" src="/images/KakaoTalk_20220829_114230058_14.jpg">
	<a href="./qna/list">QNA</a>
	
	
	<c:if test="${empty sessionScope.member}">
	<a href="./member/login">로그인</a>
	<a href="./member/add">회원가입</a>	
	</c:if>
	
	<c:if test="${not empty sessionScope.member}">
	
	<a href="./member/logout">로그아웃</a>
	
	</c:if>
	
	
	
		
	<div>
		<img alt="" src="/file/qna/134dbc18-9179-4277-9459-1e08859db576_.jpg">
		<img alt="" src="/file/notice/yy.jpg">
		<a href="/fileDown/qna?fileNum=5">QnaDown</a>
		<a href="/fileDown/notice?fileNum=5">NoticeDown</a>
		
	</div>
	
	
</body>
</html>