<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<link rel="stylesheet" href="../resources/css/bootstrap.css">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="../resources/js/bootstrap.js"></script>
<script>
function postUpdate() {
	if (updatePostForm.postCategoryId.value == "0") {
		alert("카테고리를 선택해주세요.");
		updatePostForm.postCategoryId.focus();
		return false;
	} 
	if (updatePostForm.postTitle.value == "") {
		alert("제목을 입력해주세요.");
		updatePostForm.postTitle.focus();
		return false;
	}	
	if (updatePostForm.postContent.value == "") {
		alert("내용을 입력해주세요.");
		updatePostForm.postContent.focus();
		return false;
	}
	updatePostForm.submit();
}
</script>
</head>
<body>
<% request.setCharacterEncoding("UTF-8"); %>

	<div class="container">
	    <form name="updatePostForm" method="POST" action="<c:url value='/findArtist/update' />" role="form" style="width:600px; margin: 0 auto; margin-top:40px;">
	        <b><font size="6" color="black" align="center">글쓰기</font></b><hr>
	        
	        <div class="row">
	        	<div class="col-sm-4">
		        	<select name="postCategoryId" class="form-control" style="width:600px;height:32px;">
		        		<c:choose>
							<c:when test="${post.postCategoryId eq 0}">
								<option value="0" selected>카테고리 선택</option>
						  		<option value="1">객원 보컬 구인</option>
						  		<option value="2">객원 세션 구인</option>
							</c:when>
							<c:when test="${post.postCategoryId eq 1}">
								<option value="0">카테고리 선택</option>
						  		<option value="1" selected>객원 보컬 구인</option>
						  		<option value="2">객원 세션 구인</option>
							</c:when>
							<c:when test="${post.postCategoryId eq 2}"> 
								<option value="0">카테고리 선택</option>
						  		<option value="1">객원 보컬 구인</option>
						  		<option value="2" selected>객원 세션 구인</option>
							</c:when>
						</c:choose>
					</select>
				</div>
			</div>
			
	        <br>
	        
	        <div class="required-field-block">
	            <input type="text" name="postTitle" value="${post.postTitle}" class="form-control">
	            <div class="required-icon">
	                <div class="text"> <br></div>
	            </div>
	        </div>
	 
	        <div class="required-field-block">
	            <textarea  name="postContent" rows="15" class="form-control">${post.postContent}</textarea>
	        </div>
	        
	        <br>
	        
	        <input type="file" name="postAttachment" />
	        
	        <br>
	        
	        <input type="text" name="postId" value="${post.postId}" style="display:none">
	        
	        <input type="button" value="수정" onClick="postUpdate()" class="btn btn-primary">
	         <br>    
	    </form>
	</div>
</body>
</html>