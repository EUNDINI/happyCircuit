<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>My Page</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/article/boardStyles.css">
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
<div id='menu'>
		<ul>
			<li><a href='<c:url value='/home' />'>Home</a></li>
			<li><a href="<c:url value='/article/articleMain' />">Article</a></li>
			<li><a href="<c:url value='/post/list' />">Find Artist</a></li>
			<li class='active'><a href="#">My Page</a></li>
		</ul>
	</div>
	<br> <br>
	
	<div class="align-center main">
		
		<!-- 현재 이미지 -->
		<div class="align-center profile-image">
			<img src="${pageContext.request.contextPath}/sample/${artist.image}" class="profile-img">
		</div>
		
		<div class="aling-cneter profile-nickname">
			<span>${artist.nickname}</span>
		</div>
		
		<form name="form" method="POST" action="<c:url value='/mypage/update' />" enctype="multipart/form-data">
			<input type="file" class="form-item" name="image">
			<br>
			<input type="text" name="profile" class="form-item introduction" value="${artist.profile}">
			<div class="wrap-btns">
				<input type="submit" value="수정" class="btn-update hover-cursor">
				<a href="<c:url value='/mypage'>
							<c:param name='artistId' value='${artist.artistId}' />
						</c:url>" class="btn-update">취소</a>
			</div>
		</form>
	</div>
</body>
</html>