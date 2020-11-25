<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- onclick에 주소 추가해야 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
<style>
	* {
		text-align: center;
	}
	.align-center {
		margin: 0px auto;
	}
	.profile-image {
		margin-top: 50px;
		width: 200px;
		height: 200px;
		border-radius: 100px;
		background-color: #C3C3C3;
	}
	.profile-img {
		width: 100%;
		height: auto;
		border-radius: 100px;
	}
	.profile-nickname {
		padding: 10px;
		margin-top: 20px;
	}
	.profile-introduction {
		padding: 10px;
		width: 60%;
		min-width: 500px;
		margin-top: 20px;
		background-color: #EEEEEE;
	}
	.like-list {
		padding: 10px;
		width: 80%;
	}
	.DM {
		padding-right: 10px;
	}
	.music-container {
		display: flex;
		justify-content: space-around;
		margin: 20px 0px;
		min-width: 800px;
	}
	.music {
		flex-grow: 1;
		margin: 12px;
	}
	.music-img {
		max-width: 100%;
		height: auto;
	}
	.music-title {
		
	}
	.music-artist {
		font-size: 0.8em;
		color: #AAAAAA !important;
	}
	.update, .delete {
    	margin-top: 20px;
    	margin-bottom: 50px; 
	}
	.btn-update, .btn-delete, .DM, .btn-DM {
      	background-color: #AAAAAA;
      	color: white;
      	padding: 10px;
      	border-radius: 15px;
      	border: 0px;
      	width: 50px;
      	border: 1px solid #AAAAAA;
    	text-decoration: none;
    	font-size: 0.9em;
    }
    .btn-update:hover, .btn-update:hover, .DM:hover, .btn-DM:hover {
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
      	
    }
</style>
</head>
<body>
	<a href="<c:url value='/DM/list' />" class="DM">DM</a> <!-- 내 DM 목록 -->
	
	<div class="align-center profile-image">
		<img src="${artist.image}" class="profile-img">
	</div>
	
	<div class="aling-cneter profile-nickname">
		<span>${artist.nickname}</span>
	</div>
	
	<div class="align-center profile-introduction">
		<span>${artist.profile}</span>
	</div>
	<div class="update">
		<a href="<c:url value='/mypage/update' />" class="btn-update">수정</a>
		<a href="<c:url value='/DM/create' />" class="btn-DM">DM보내기</a>
	</div>
	
	<div class="align-center like-list">
		<c:forEach var="music" items="${musicList} varStatus="status">
			<c:if test="${status.index % 5 == 0}">
				<div class="music-container">
			</c:if>
			<div class="music">
				<img src="../sample/holding_onto_gravity.jpg" class="music-img hover-effect" onclick="location.href=''">
				<div class="music-title">
					<span onclick="location.href=''" class="hover-cursor">title</span>
				</div>
				<div class="music-artist">
					<span onclick="location.href=''" class="hover-cursor">artist</span>
				</div>
			</div>
			<c:if test="${status.index + 1 % 5 == 0}">
				<div class="music-container">
			</c:if>
		</c:forEach> 
		<!-- 
		<% 
		for (int i = 0; i < 15; i++) {
			if (i % 5 == 0) {
		%>
				<div class="music-container">
			<%
			}
			%>
			<div class="music">
				<img src="../sample/holding_onto_gravity.jpg" class="music-img hover-effect" onclick="location.href=''">
				<div class="music-title">
					<span onclick="location.href=''" class="hover-cursor">title</span>
				</div>
				<div class="music-artist">
					<span onclick="location.href=''" class="hover-cursor">artist</span>
				</div>
			</div>
		<%
			if ((i + 1) % 5 == 0) {
		%>
				</div>
		<%
			}
		}
		%>-->
	</div>
	<div class="delete">
		<a href="<c:url value='/artist/delete' />" class="btn-delete">탈퇴</a>
	</div>
</body>
</html>