<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<style>
	.popover {
		display: none;
	}
</style>
</head>
<body>
	<section class="container lg-8">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Num</th>
					<th>Writer</th>
					<th>Title</th>
					<th>regDate</th>
					<th>Hit</th>
					<th>images</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${QnaVO.num}</td>
					<td>${QnaVO.writer}</td>
					<td>${QnaVO.title}</td>
					<td>${QnaVO.regDate}</td>
					<td>${QnaVO.hit}</td>
					<c:forEach items="${QnaVO.qnaFileVOs}" var="i">
						<td>
							<img src="/file/${i.fileName}" style="width: 150px; height: 150px;" >
						</td>
					</c:forEach>
				</tr>
			</tbody>
		</table>
		<textarea class="form-control" id="contents">${QnaVO.contents}</textarea>
	<%-- 	<c:forEach items="${QnaVO.qnaFileVOs}" var="file">
			<img src="">	
		</c:forEach> --%>
	</section>
	

</body>
</html>