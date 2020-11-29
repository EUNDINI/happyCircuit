<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�۾���</title>
<link rel="stylesheet" href="../resources/css/bootstrap.css">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="../resources/js/bootstrap.js"></script>
</head>
<body>
	<div class="container">
	    <form name="applyForm" method="POST" action="<c:url value='/findArtist/create' />" role="form" style="width:600px; margin: 0 auto; margin-top:40px;">
	        <b><font size="6" color="black" align="center">�۾���</font></b><hr>
	        
	        <div class="row">
	        	<div class="col-sm-4">
					<select name="postCategoryId" class="form-control" style="width:600px;height:32px;">
				      <option value="0" selected>ī�װ� ����</option>
					  <option value="1">���� ���� ����</option>
					  <option value="2">���� ���� ����</option>
					</select>
				</div>
			</div>
			
	        <br>
	        
	        <div class="required-field-block">
	            <input type="text" name="postTitle" placeholder="������ �Է����ּ���." class="form-control">
	            <div class="required-icon">
	                <div class="text"> <br></div>
	            </div>
	        </div>
	 
	        <div class="required-field-block">
	            <textarea  name="postContent" rows="15" class="form-control" placeholder="������ �Է����ּ���."></textarea>
	        </div>
	        
	        <br>
	        
	        <input type="file" name="postAttachment" />
	        
	        <br>
	        
	        <input type="submit" value="���" class="btn btn-primary">    
	    </form>
	</div>
</body>
</html>