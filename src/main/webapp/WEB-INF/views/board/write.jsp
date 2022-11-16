<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
<script defer src="/js/write.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
</head>
<body>
	
	<div class="container col-lg-6">
		<h1>Board Write Page</h1>
		<form:form modelAttribute="qnaVO" action="add"  method="post" enctype="multipart/form-data">
		<sec:csrfInput/>
			<div class="mb-3">
				<label for="writer" class="form-label">Writer</label>
				<form:input path="writer" cssClass="form-control" id="writer"/>
				<form:errors path="writer" id="writerr"></form:errors>
				<div id="writerr"></div>
			</div>
			<div class="mb-3">
				<label for="title" class="form-label">Title</label>
				<form:input path="title" cssClass="form-control" id="title"/>
				<form:errors path="title" id="titler"></form:errors>
				<div id="titler"></div>
			</div>
			<div class="mb-3">
				<label for="contents" class="form-label">Contents</label>
				<form:input path="contents" cssClass="form-control" id="contents"/>
				<form:errors path="contents" id="contentsr"></form:errors>
				<div id="contentsr"></div>
			</div>
			
			<div class="mb-3" id="files">
		
			</div>
			<div class="mb-3" >
				<button type="button" id="fileAdd">FileAdd</button>
			</div>
			
				<input type="hidden" name="hit" value="0">
				<input type="hidden" name="ref" value="0">
				<input type="hidden" name="depth" value="0">
				<input type="hidden" name="step" value="0">

				<button class="btn btn-primary" type="submit">등록하기</button>
		</form:form>
	</div>


	<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
	<script type="text/javascript">
 $('#contents').summernote({
        tabsize: 4,
        height: 250,
      callbacks:{
         onImageUpload:function(file){
            console.log("ImageUpload");
            console.log("file", file);
			uploadFile(file);
            $('#contents').summernote('pasteHTML', imgNode);
         }
      }
      });
	  function deleteFile(file){
		console.log("src == >> ", file.attr("src"));
		$.post("./summerFileDelete", {fileName: file.attr("src")}, function(result){
			console.log("result =>", result)
		})
	  }

     //ajax upload 함수
     function uploadFile(file){
      console.log("file", file);
      //<form>
      const formData = new FormData();
      //<input type="file"
      formData.append("file", file[0])

      $.ajax({
         type:"POST",
         url:"summerFile",
         data:
            formData
         ,
         //header
         cache:false,
         processData:false,
		 contentType:false,
         enctype:"multipart/form-data",
         success:function(img){
            console.log("Image => ",img);
			img = '<img src="'+img+'">
			$('#contents').summernote('pasteHTML', img);
         },
         error:function(){
            console.log("img upload failed");
         }

      });
     }

		
	</script>	
</body>
</html>	