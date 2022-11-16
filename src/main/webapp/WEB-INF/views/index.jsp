<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
	<h1>egap</h1>
	<a href="./qna/list">QNA</a>
	<h2><spring:message code="hi" var="h"></spring:message></h2>
	<h2><spring:message code="test" text="code (key)가 없을 때 출력하는 Message"></spring:message></h2>
	<!-- <button  id="btn">click</button> -->

<!-- 	<button  class="buttons">buttons1</button>
	<button  class="buttons">buttons2</button>
	<button  class="buttons">buttons3</button> -->

	<!-- <button id="test">test click</button> -->
	
	
	
	<!-- 로그인 후 -->
	<sec:authorize access="isAuthenticated()">
	<sec:authentication property="Principal" var="member"/>
		<h2><spring:message code="welcome" arguments="${member.name}"></spring:message></h2>
		<h2><spring:message code="welcome2"  arguments="${member.id},${member.name}" argumentSeparator=","></spring:message></h2>
		<a href="./member/myPage">myPage</a>

		<a href="#" id="logout">로그아웃</a>
		<form id="outForm" action="./member/logout" method="post">
			<button>로그아웃</button>
		</form>
		<a href="/member/delete">회원 탈퇴</a>
	</sec:authorize>

	<!-- 로그인 전 -->	
	<sec:authorize access="!isAuthenticated()">
		<a href="./member/login">로그인</a>
		<a href="/oauth2/authorization/kakao">KaKao Login</a>
		<a href="./member/add">회원가입</a>		
	</sec:authorize>

		
		<sec:authorize url="/admin">
			<a href="/admin">GO Admin</a>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('MANAGER', 'ADMIN')"><!-- 회원이 manager를 가지고 있거나 admin을 가지고있으면 해당 url이 보일 것ㄴ -->
			<a href="manager">GO Manager</a>
		</sec:authorize>
	
	<img alt="" src="/images/KakaoTalk_20220829_114230058_14.jpg">
	<!-- <img alt="" src="/file/qna/134dbc18-9179-4277-9459-1e08859db576_.jpg">
	<img alt="" src="/file/notice/yy.jpg"> 
	<div>
		<a href="/fileDown/qna?fileNum=5">QnaDown</a>
		<a href="/fileDown/notice?fileNum=5">NoticeDown</a>
	</div>  -->
	
	
	
	<script type="text/javascript">
		$("#logout").click(function(){
			$("#outForm").submit();
		})
		
		$("#kakao").click(function(){
			$.get("https://developers.kakao.com/logout", function(){
				location.reload();
			})
		})
	</script>
	

</body>
</html>