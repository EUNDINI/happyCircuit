<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추천하는 음악</title>
<style>
	* {
		text-align: center;
	}
	.align-center {
		margin: 0px auto;
	}
	.recommend-music-title {
		margin: 50px;
	}
	.music-list {
		padding: 10px;
		width: 80%;
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
		max-width: 190px;
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
	<div class="recommend-music-title">추천하는 음악</div>
	
	<div class="align-center music-list">
		<c:forEach var="music" items="${musicList}" varStatus="status">
			<c:if test="${status.index % 5 == 0}">
				<div class="music-container">
			</c:if>
			<div class="music">
				<!-- 이미지 경로 수정 필요 -->
				<img src="../sample/holding_onto_gravity.jpg" 
					class="music-img hover-effect hover-cursor" 
					onclick="location.href='<c:url value='/article/articleRead'>
											    <c:param name='musicId' value='${music.musicId}'/>
											</c:url>'">
				<div class="music-title">
					<span onclick="location.href='<c:url value='/article/articleRead'>
												     <c:param name='musicId' value='${music.musicId}'/>
												  </c:url>'" class="hover-cursor">${music.musicName}</span>
				</div>
				<div class="music-artist">
					<span onclick="location.href='<c:url value='/article/articleRead'>
												     <c:param name='musicId' value='${music.musicId}'/>
												  </c:url>'" class="hover-cursor">${artistList[status.index].nickname}</span>
				</div>
			</div>
			<c:if test="${(status.index + 1) % 5 == 0}">
				</div>
			</c:if>
		</c:forEach> 
	</div>
</body>
</html>