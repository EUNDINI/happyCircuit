<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Collaboration collaboration = (Collaboration)request.getAttribute("collaboration");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/bootstrap.css">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="../resources/js/bootstrap.js"></script>
</head>
<body>
<% request.setCharacterEncoding("UTF-8"); %>

	<div class="container">
	    <form name="applyForm" method="POST" action="listPost.jsp" role="form" style="width:600px; margin: 0 auto; margin-top:40px;">
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
	        
	        <a href="<c:url value='/findArtist/list' />">
	        	<input type="button" name="goToPostList" value="목록" class="btn btn-light"> </a> 
	          
	    </form>
	</div>
</body>
</html>