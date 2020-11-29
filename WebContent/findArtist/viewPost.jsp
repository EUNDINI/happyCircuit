<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Post post = (Post)request.getAttribute("post");
	//String path = this.getServletContext().getRealPath("post");
	//System.out.println("1: " + path);
	//String uploadPath = this.getServletContext().getRealPath("/");
	//System.out.println("2: " + uploadPath);
	
	//String a = request.getServletContext().getRealPath("/");
	//System.out.println("3: " + a);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글내용</title>

<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="../resources/js/bootstrap.js"></script>
<script>
function userRemove() {
	return confirm("정말 삭제하시겠습니까?");		
}
</script>
</head>
<body>
<% request.setCharacterEncoding("UTF-8"); %>
	<div class="container">
	    <form name="viewPost" method="POST" role="form" style="width:600px; margin: 0 auto; margin-top:40px;" >
            <font size="2">${post.postCategoryName}</font><br>
			<b><font size="4">${post.postTitle}</font></b>
			<font size="2" style="float:right;">${post.postDate}</font><br>
			<font size="3">${post.nickname}</font>
			<font size="2" style="float:right;">조회수 ${post.postView}</font>
			<br>
			<hr>
	        
	        <br>

	        <div class="required-field-block">

	        	<c:set var="postAttachment" value="${post.postAttachment}" />	        	
	        	<c:if test="${postAttachment ne '첨부파일없음'}"> 
				   <c:set var="postAttachmentRoute" value="/resources/findArtist/${post.postAttachment}" />
				   <img src="<c:url value='${postAttachmentRoute}' />" width="600" /> 
				   <br><br>
				</c:if>
								
	            <textarea  name="postContent" rows="15" class="form-control" >${post.postContent}</textarea>
	        </div>

	        <br>
	        
	        <a href="<c:url value='/findArtist/update'>
	     		   <c:param name='postId' value='<%=Integer.toString(post.getPostId())%>'/>
			 	 </c:url>"><input type="button" name="updatePost" value="수정" class="btn btn-primary"></a>
	        
			 <a href="<c:url value='/findArtist/delete/post'> <c:param name='postId' value='<%=Integer.toString(post.getPostId())%>'/></c:url>"
			 	onclick="return userRemove();">
	        	<input type="button" name="deletePost" value="삭제" class="btn btn-danger"> </a>
 
            <a href="<c:url value='/findArtist/list' />">
	        	<input type="button" name="goToPostList" value="목록" class="btn btn-light"> </a> 
         
	         <a href="<c:url value='/findArtist/collaborate'>
	     		   <c:param name='postId' value='<%=Integer.toString(post.getPostId())%>'/>
			 	 </c:url>"><input type="button" name="offerCollaboration" value="협업 신청" class="btn btn-success" style="float:right;" ></a>
	                 
	    </form>
	</div>
</body>
</html>