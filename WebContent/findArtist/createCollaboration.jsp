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
		<!-- �������� action ������ �����ؾ� ��  -->
	    <form name="applyForm" method="POST" action="listPost.jsp" role="form" style="width:600px; margin: 0 auto; margin-top:40px;">
	        <b><font size="6" color="black" align="center">���� ��û</font></b><hr>
	        
	        <div class="required-field-block">
	            <input type="text" name="applyTitle" placeholder="�޾ƿ��� ����" class="form-control" readonly>
	            <div class="required-icon">
	                <div class="text"> <br></div>
	            </div>
	        </div>
	        
	        <div class="required-field-block">
	            <input type="text" name="nickname" placeholder="�޾ƿ��� �г���" class="form-control" readonly>
	            <div class="required-icon">
	                <div class="text"> <br></div>
	            </div>
	        </div>
	 
	        <div class="required-field-block">
	            <textarea  name="applyContent" rows="15" class="form-control" placeholder="������ �Է����ּ���."></textarea>
	        </div>
	        <br>
	        
	        <input type="submit" value="��û" class="btn btn-primary">    
	    </form>
	</div>
</body>
</html>