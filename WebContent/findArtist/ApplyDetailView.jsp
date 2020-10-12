<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글내용</title>
<link rel="stylesheet" href="../resources/css/bootstrap.css">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="../resources/js/bootstrap.js"></script>
</head>
<body>
	<div class="container">
	    <form name="applyForm" method="POST" action="ApplyList.jsp" role="form" style="width:600px; margin: 0 auto; margin-top:40px;">
            <font size="2">객원 보컬 구인</font><br>
			<b><font size="4">객원 보컬 구합니다</font></b>
			<font size="2" style="float:right;">2020.10.13</font><br>
			<font size="3">김은진</font>
			<font size="2" style="float:right;">조회수 13</font>
			<br>
			<hr>
	        
	        <br>

	        <div class="required-field-block">
	            <textarea  name="applyContent" rows="15" class="form-control" placeholder="글 내용이 될 예정"></textarea>
	        </div>

	        <br>
	        
	        <input type="button" name="updateApply" value="수정" onclick="location.href='ApplyForm.jsp'" class="btn btn-primary">    
            <input type="button" name="deleteApply" value="삭제" class="btn btn-danger"> 
            <input type="button" name="goToApplyList" value="목록" onclick="location.href='ApplyList.jsp'" class="btn btn-light"> 
            <input type="button" name="offerCollaboration" value="협업 신청" class="btn btn-success" style="float:right;"> 
	                 
	    </form>
	</div>
</body>
</html>