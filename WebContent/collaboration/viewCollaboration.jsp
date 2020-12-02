<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="../resources/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/article/boardStyles.css">
<script>
	alert("협업 신청 DM을 성공적으로 보냈습니다.");
</script>
</head>
<body>
<% request.setCharacterEncoding("UTF-8"); %>
	
	<div id='menu'>
		<ul>
			<li><a href='<c:url value='/home' />'>Home</a></li>
			<li><a href="<c:url value='/article/articleMain' />">Article</a></li>
			<li class='active'><a href="<c:url value='/post/list' />">Find Artist</a></li>
			<li><a href="<c:url value='/mypage'>
				<c:param name='artistId' value='${artistId}'/>
				</c:url>">My Page</a></li>
		</ul>
	</div>
	<br> <br>
	
	<div class="container">
	    <form name="applyForm" method="POST" role="form" style="width:600px; margin: 0 auto; margin-top:40px;">
	        <b><font size="6" color="black" align="center">협업 신청</font></b><hr>
	        
	        <div class="required-field-block">
	            <input type="text" name="collaborationTitle" class="form-control" value="${collaboration.collaborationTitle}">
	            <div class="required-icon">
	                <div class="text"> <br></div>
	            </div>
	        </div>
	       
	        <div class="required-field-block">
	            <textarea  name="collaborationContent" rows="15" class="form-control">${collaboration.collaborationContent}</textarea>
	        </div>
	        <br> 
	        
	        <a href="<c:url value='/post/list' />">
	        	<input type="button" name="goToPostList" value="목록" class="btn btn-light"> </a> 
	        <br>
	    </form>
	</div>
</body>
</html>