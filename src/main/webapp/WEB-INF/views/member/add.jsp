<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script defer src="/js/memberAdd.js"></script>
<script defer src="/js/util.js"></script>
</head>
<body>
	
	<div class="container col-lg-6">
		<h1>Board Write Page</h1>
		<form:form modelAttribute="memberVO" method="post">
			<div class="mb-3">
				<label for="id" class="form-label">아이디를 입력해주세요</label>
				<form:input path="id" cssClass="form-control" id="id"/><!-- name과 똑같은 속성 -->
				<form:errors path="id" id="idr"></form:errors>
	<!-- 			<input type="text" class="form-control" id="id" aria-describedby="emailHelp" placeholder="id" name="id"> -->
				<div id="idr"></div>
			</div>
			<div class="mb-3">
				<label for="pw" class="form-label">비밀번호를 입력해주세요</label>
				<form:password path="pw" cssClass="form-control" id="pw"/>
				<!-- <input type="text" class="form-control" id="pw" aria-describedby="emailHelp" placeholder="pw" name="pw"> -->
				<form:errors path="pw" id="pwr"></form:errors>
				<div id="pwr"></div>
			</div>
			<div class="mb-3">
				<label for="pw2" class="form-label">비밀번호 다시 입력해주세요</label>
				<form:password path="pwCheck" cssClass="form-control" id="pw2"/>
				<!-- <input type="text" class="form-control" id="pw2" aria-describedby="emailHelp" placeholder="pw2"> -->
				<form:errors path="pwCheck" id="pwr2"></form:errors>
				 <div id="pwr2"></div>
			</div>
			<div class="mb-3">
				<label for="name" class="form-label">이름을 입력해주세요</label>
				<form:input path="name" cssClass="form-control" id="name"/>
				<!-- input type="text" class="form-control" id="name" aria-describedby="emailHelp" placeholder="name" name="name"> -->
				<form:errors path="name" id="namer"></form:errors>
				<div id="namer">
					${name}
					<!-- el특성은 없으면 출력을 하지 않는다 -->
				</div>
			</div>
			<div class="mb-3">
				<label for="email" class="form-label">이메일을 입력해주세요</label>
				<form:input path="email" cssClass="form-control" id="email"/>
				<!-- <input type="text" class="form-control" id="email" aria-describedby="emailHelp" placeholder="email" name="email"> -->
				<form:errors path="email" id="emailr"></form:errors>
				<div id="emailr"></div>
			</div>
			<div class="mb-3">
				<label for="age" class="form-label">나이를 입력해주세요</label>
				<form:input path="age" cssClass="form-control" id="age"/>
				<!-- <input type="text" class="form-control" id="email" aria-describedby="emailHelp" placeholder="email" name="email"> -->
				<form:errors path="age" id="age"></form:errors>
				<div id="ager"></div>
			</div>
			<div class="mb-3">
				<label for="birth" class="form-label">나이를 입력해주세요</label>
				<form:input path="birth" cssClass="form-control" id="birth"/>
				<!-- <input type="text" class="form-control" id="email" aria-describedby="emailHelp" placeholder="email" name="email"> -->
				<form:errors path="birth" id="birth"></form:errors>
				<div id="birthr"></div>
			</div>
				<button class="btn btn-primary" type="submit" id="joinButton">가입하기</button>
		</form:form>
	</div>
		<!--                                            약관 test                                                        -->
		<div>
			<div>
				ALL <input type="checkbox" id="all">
			</div>
			<div>
				동의1 <input type="checkbox" class="check" name="" id="">
				<div>
					약관 1
				</div>
			</div>
			<div>
				동의2 <input type="checkbox" class="check" name="" id="">
				<div>
					약관 2
				</div>
			</div>
			<div>
				동의3 <input type="checkbox" class="check" name="" id="">
				<div>
					약관 3
				</div>
			</div>
		</div>


	<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
</body>
</html>	