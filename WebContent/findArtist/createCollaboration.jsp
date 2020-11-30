<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<script>
function collaborationCreate() {
	if (createCollaborationForm.collaborationTitle.value == "") {
		alert("제목을 입력해주세요.");
		form.collaborationTitle.focus();
		return false;
	} 
	if (createCollaborationForm.collaborationContent.value == "") {
		alert("내용을 입력해주세요.");
		form.collaborationContent.focus();
		return false;
	}	
	createCollaborationForm.submit();
}
</script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="../resources/js/bootstrap.js"></script>
</head>
<body>
<% request.setCharacterEncoding("UTF-8"); %>

	<div class="container">
	    <form name="createCollaborationForm" method="POST" action="<c:url value='/findArtist/create/collaboration' />" role="form" style="width:600px; margin: 0 auto; margin-top:40px;">
	        <b><font size="6" color="black" align="center">협업 신청</font></b><hr>
	        
	        <div class="required-field-block">
	            <input type="text" name="collaborationTitle" class="form-control" placeholder="제목을 입력해주세요." value="">
	            <div class="required-icon">
	                <div class="text"> <br></div>
	            </div>
	        </div>
	       
	        <div class="required-field-block">
	            <textarea  name="collaborationContent" rows="15" class="form-control" placeholder="내용을 입력해주세요."></textarea>
	        </div>
	        <br>
	        
	        <input type="text" name="postId" value="${post.postId}" style="display:none">
	         
	        <input type="button" value="신청" onClick="collaborationCreate()" class="btn btn-primary">  
	        <br>
	    </form>
	</div>
</body>
</html>