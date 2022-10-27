<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
<script defer src="/js/write.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">

</head>
<body>
<form action="update" method="post" enctype="multipart/form-data">

<input type="hidden" name=num value="${QnaVO.num}">
			<div class="mb-3">
				<label for="writer" class="form-label">Writer</label>
				<input type="text" class="form-control" id="writer" value="${QnaVO.writer}" name="writer">
			</div>
			<div class="mb-3">
				<label for="title" class="form-label">Title</label>
				<input type="text" class="form-control" id="title" value="${QnaVO.title}" name="title">
			</div>
			<div class="mb-3">
				<label for="contents" class="form-label">Contents</label>
				<textarea class="form-control" id="contents" value="${QnaVO.contents}" name="contents"></textarea>
			</div>			
				<c:forEach items="${QnaVO.qnaFileVOs}" var="file">
					<div class="mb-3" id="files">
						<span>${file.oriName}</span>
						<button type="button" class="deleteFile" data-file-num="${file.fileNum}">X</button>	
					</div>
				</c:forEach>
			
			<div class="mb-3" >
				<button type="button" id="fileAdd">FileAdd</button>
			</div>
				<button class="btn btn-primary" type="submit">등록하기</button>
		</form>
</body>
</html>