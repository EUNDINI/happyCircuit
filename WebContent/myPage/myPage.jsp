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
	.profile-introduction {
		padding: 10px;
		width: 60%;
		min-width: 500px;
		margin: 50px auto;
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
	.hover-cursor {
		cursor: pointer;
	}
	@media (orientation: portrait) {
      	
    }
</style>
</head>
<body>
	<a href="" class="DM">DM</a>
	
	<div class="align-center profile-image">
		<img src="../sample/holding_onto_gravity.jpg" class="profile-img">
	</div>
	
	<div class="align-center profile-introduction">
		<span>소개입니다. 구역 구분을 위해 색을 넣어둔 거고 나중엔 뺄 겁니다~ 아니면 연한 회색이나</span>
	</div>
	
	<div class="align-center like-list">
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
		%>
	</div>
</body>
</html>