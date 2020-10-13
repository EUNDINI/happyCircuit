<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/bootstrap.css">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="../resources/js/bootstrap.js"></script>
</head>
<body>
	<div class="container">
		<!-- 디엠쪽으로 action 보내게 수정해야 함  -->
	    <form name="applyForm" method="POST" action="listPost.jsp" role="form" style="width:600px; margin: 0 auto; margin-top:40px;">
	        <b><font size="6" color="black" align="center">협업 신청</font></b><hr>
	        
	        <div class="required-field-block">
	            <input type="text" name="applyTitle" placeholder="받아오는 제목" class="form-control" readonly>
	            <div class="required-icon">
	                <div class="text"> <br></div>
	            </div>
	        </div>
	        
	        <div class="required-field-block">
	            <input type="text" name="nickname" placeholder="받아오는 닉네임" class="form-control" readonly>
	            <div class="required-icon">
	                <div class="text"> <br></div>
	            </div>
	        </div>
	 
	        <div class="required-field-block">
	            <textarea  name="applyContent" rows="15" class="form-control" placeholder="내용을 입력해주세요."></textarea>
	        </div>
	        <br>
	        
	        <input type="submit" value="신청" class="btn btn-primary">    
	    </form>
	</div>
</body>
</html>