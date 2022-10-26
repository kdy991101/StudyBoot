<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/css/test.css" rel="stylesheet">
<script defer src="/js/test.js"></script>
<!-- defer병렬처리하는 것 -->
<c:import url="./temp/boot.jsp"></c:import>
</head>
<body>
	<h1>Index Page</h1>
	<c:if test="${not empty sessionScope.member}">
	<h1>**${sessionScope.member.name}님 환영합니다**</h1>
	</c:if>
	<img alt="" src="/images/KakaoTalk_20220829_114230058_14.jpg">
	<!-- empty = 비어있는` -->
	<c:if test="${empty sessionScope.member}">
		<a href="./member/login">로그인</a>
		<a href="./member/add">회원가입</a>	
	</c:if>
		<img alt="" src="/file/qna/134dbc18-9179-4277-9459-1e08859db576_.jpg">
		<img alt="" src="/file/notice/yy.jpg">
	
	<c:if test="${not empty sessionScope.member}">
	<a href="./qna/list">QNA</a>
		<a href="./member/logout">로그아웃</a>
	<div>
		<a href="/fileDown/qna?fileNum=5">QnaDown</a>
		<a href="/fileDown/notice?fileNum=5">NoticeDown</a>
	</div>
	</c:if>
	
	<%-- <div>
		<c:choose>
			<c:when test="${not empty member}">
				<a href="./member/logout">로그아웃</a>
			</c:when>
			<c:otherwise>
				<a href="./member/login">로그인</a>
				<a href="./member/add">회원가입</a>
			</c:otherwise>
		</c:choose>
	</div --%>>

	<button  id="btn">click</button>

	<button  class="buttons">buttons1</button>
	<button  class="buttons">buttons2</button>
	<button  class="buttons">buttons3</button>

	<button id="test">test click</button>
	
	
	
	
	

</body>
</html>