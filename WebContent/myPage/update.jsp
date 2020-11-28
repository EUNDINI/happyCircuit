<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>My Page</title>
<style type="text/css">
	.align-center {
		margin: 0px auto;
	}
	.main {
		width: 70%;
		text-align: center;
	}
	img {
		width: 200px;
		height: 200px;
		border-radius: 100px;
		margin: 20px;
	}
	.form-item {
		margin: 7px;
	}
	.introduction {
		width: 80%;
      	padding: 10px;
      	border-radius: 15px;
		border: 1px solid #AAAAAA;
	}
    textarea:focus, input:not(#submit):focus, .uneditable-input:focus {
      	border-color: #AAAAAA;
      	box-shadow: 0 1px 1px #AAAAAA inset, 0 0 8px #AAAAAA;
      	outline: 0 none;
    } 
    .wrap-btns {
    	margin: 30px auto;
    }
	.btn-update {
      	background-color: #AAAAAA;
      	color: white;
      	padding: 10px;
      	border-radius: 15px;
      	border: 0px;
      	width: 50px;
      	border: 1px solid #AAAAAA;
    	text-decoration: none;
    	font-size: 0.9em;
    	margin: 10px;
    }
    .btn-update:hover {
        text-decoration: none;
        background-color: white;
        color: #AAAAAA;
        transition-property: background-color, color;
        transition-duration: 0.2s;
        transition-timing-function: ease-in-out;
    }
	.hover-cursor {
		cursor: pointer;
	}
    @media (orientation: portrait) {
      	.main {
        	width: 95%;
      	}
    }
</style>
<script>
	function artistModify() {
		form.submit();
	}
</script>
</head>
<body>
	<div class="align-center main">
		<img src="../sample/holding_onto_gravity.jpg"><!-- ���� �̹��� -->
		<div class="aling-cneter profile-nickname">
			<span>${artist.nickname}</span>
		</div>
		
		<form name="form" method="POST" action="<c:url value='/mypage/update' />" enctype="multipart/form-data">
			<input type="file" class="form-item" name="image">
			<br>
			<input type="text" class="form-item introduction" value="${artist.profile}" name="profile">
			<div class="wrap-btns">
				<input type="button" value="����" onClick="artistModify()" class="btn-update hover-cursor">
				<!-- <a href="" class="btn-update" onClick="artistModify()">����</a> -->
				<a href="<c:url value='/mypage'>
							<c:param name='artistId' value='${artist.artistId}' />
						</c:url>" class="btn-update">���</a>
			</div>
		</form>
	</div>
</body>
</html>