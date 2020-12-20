
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="music" value="${musicArticle.music}" />
<c:set var="artist" value="${musicArticle.artist}" />
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/article/boardStyles.css">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<script src="script.js"></script>
<script>
window.articleRemove = function() {
	var result = confirm("정말 삭제하시겠습니까?");
	
	if(result){
		location.href='<c:url value='/article/articleDelete'><c:param name='musicId' value='${music.musicId}'/> </c:url>';
	}
	
	return false;
}

function likeCount(){
	var result = confirm("좋아요를 누르시겠습니까?");
	if(result) {
		location.href='<c:url value='/article/articleRead'><c:param name='musicId' value='${music.musicId}' /><c:param name='like' value='${musicArticle.likeCount}' /> </c:url>';
	}		
}

function isLogin() {
	var artist = '${artistId}';
	var writer = '${music.artistId}';
	
	if(artist != ''){
		document.getElementById("login").style.display = "none";
		document.getElementById("logout").style.display = "block";
		document.getElementById("btnNthWrite").style.display = "inline-block";
		document.getElementById("btnLike").style.display = "inline-block";
	}
	else {
		document.getElementById("logout").style.display = "none";
		document.getElementById("login").style.display = "block";
		document.getElementById("btnNthWrite").style.display = "none";
		document.getElementById("btnLike").style.display = "none";
	}
	
	if(artist != writer){
		document.getElementById("btn").style.display = 'none';
	}
	else{
		document.getElementById("btn").style.display = 'block';
	}
	
}
</script>
<style type="text/css">
#nthCreationList {
	padding: 10px 0;
	margin: auto;
	width: 80%;
	text-align: center;
}

#nthCreationList table {
	margin: auto;
	border-collapse: separate;
	border-spacing: 1px;
	text-align: center;
	line-height: 1.5;
	border-top: 1px solid #ccc;
	padding-top: 10px;
	align: center;
	width: 70%;
	border: 0;
	border-collapse: collapse;
}

#nthCreationList table th {
	border-bottom: 2px solid #999;
	padding: 10px;
}

#nthCreationList table .nth {
	width: 10%;
}

#nthCreationList table .username {
	width: 20%;
}

#nthCreationList table td {
	border-bottom: 1px solid #ccc;
	padding: 10px;
}

#nthCreationList table td a {
	color: black;
	text-decoration: none;
}

#nthCreationList table td a:hover {
	color: #47c9af;
	font-size: 15pt;
}

#prior td a:link {
	color: black;
	text-decoration: none;
}

#prior td a:visited {
	color: black;
	text-decoration: none;
}

#prior td a:hover {
	color: blue;
	text-decoration: none;
}

.page_wrap {
	text-align: center;
	font-size: 0;
}

.page_nation {
	display: inline-block;
}

.page_nation ul {
	list-style-type: none;
	display: inline-block;
}

.page_nation .none {
	display: none;
}

.page_nation a {
	display: block;
	margin: 0 3px;
	float: left;
	border: 1px solid #e6e6e6;
	width: 28px;
	height: 28px;
	line-height: 28px;
	text-align: center;
	background-color: #fff;
	font-size: 13px;
	color: #999999;
	text-decoration: none;
}

.page_nation a.selected {
	background-color: #42454c;
	color: #fff;
	border: 1px solid #42454c;
}

.page_nation ul li {
	float: left;
}
</style>
<title>Article Read</title>
</head>
<body onload='isLogin()'>
	<!-- 이후에 할일 : db에서 정보 가져오기 / 수정, 삭제는 작성자만 보게 하기 / 삭제 하기-->
	<div id='menu'>
		<ul>
			<li><a href='<c:url value='/home' />'>Home</a></li>
			<li class='active'><a
				href='<c:url value='/article/articleMain' />'>Article</a></li>
			<li><a href="<c:url value='/post/list' />">Find Artist</a></li>
			<li><a href='#'>My Page</a></li>
			<button id='logout'
				onclick="location.href='<c:url value='/artist/logout' />' ">Logout</button>
			<button id='login'
				onClick="location.href='<c:url value='/artist/login/form' />' ">Login</button>
		</ul>
	</div>

	<div id="boardRead">
		<table>
			<tr>
				<td height=20 align=center bgcolor=#cccccc><font color=white>
						글보기</font></td>
			</tr>
			<tr>
				<td bgcolor=white align=center>
					<table id="writeTable" width='80%'>
						<tr>
							<td><strong>제목 [${music.nth}차] </strong>&nbsp;
								${music.musicName}</td>
							<td align=right><strong>글쓴이</strong>&nbsp; <a
								href="<c:url value='/mypage'>
														<c:param name='artistId' value='${music.artistId}'/>
													 </c:url>">${artist.nickname}</a></td>
						</tr>

						<tr>
							<td colspan="2" style="word-break: break-word;">
								<p>
									<audio controls="controls" autoplay loop id="audio"
										style="height: 30px; width: 500px;">
										<source src="${music.musicPath}" type="audio/mp3">
										<source src="${music.musicPath}" type="audio/ogg">
										<source src="${music.musicPath}" type="audio/wav">
									</audio>
								</p>

								<p>${musicArticle.content}</p>
							</td>
						</tr>
						<c:if test="${music.originalMusicId ne 0}">
							<tr id="prior">
								<td colspan="2" style="font-size: 6pt;"><strong>원작</strong>&nbsp;
									<a
									href="<c:url value='/article/articleRead'>
						      <c:param name='musicId' value='${music.originalMusicId}'/>
						   </c:url>">${originalMusicName}</a>
									&nbsp;&nbsp;&nbsp; <strong>이전작</strong>&nbsp; <a
									href="<c:url value='/article/articleRead'>
						      <c:param name='musicId' value='${music.priorMusicId}'/>
						   </c:url>">${priorMusicName}</a></td>
							</tr>
						</c:if>
						<tr>
							<td colspan="2" align=right id="readRight"><strong>날짜</strong>&nbsp;
								${musicArticle.regDate}&nbsp; &nbsp;&nbsp;<strong>조회수</strong>&nbsp;
								${musicArticle.readCount}&nbsp; &nbsp;&nbsp;<strong>좋아요</strong>&nbsp;${musicArticle.likeCount}&nbsp;&nbsp;&nbsp;
								<button id='btnLike' onClick="likeCount()">좋아요</button>&nbsp;&nbsp;&nbsp;
								<button id='btnNthWrite'
									onClick="location.href='<c:url value='/article/articleNthWrite/form' > <c:param name='priorMusicId' value='${music.musicId}'/></c:url>'">2차
									창작</button>
								<button
									onclick="window.open('<c:url value='/article/articleHistory' > <c:param name='musicId' value='${music.musicId}'/></c:url>'
											,'History','scrollbars=yes width=650, height=700');return false">History</button>
							</td>
						</tr>
					</table> <br> <span id="btn">
						<button
							onClick="location.href='<c:url value='/article/articleModify/form' > <c:param name='musicId' value='${music.musicId}'/></c:url>'">수정</button>
						<button onClick="articleRemove()">삭제</button>
				</span>
				</td>
			</tr>
		</table>

		<br>
	</div>

	<c:if test="${not empty nthCreationList}">
		<div id='nthCreationList'>
			<table>
				<thead>
					<tr>
						<th class="nth">Nth</th>
						<th class="username">글쓴이</th>
						<th>제목</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="start" value="${ 5 * (page - 1)}" />
					<c:set var="end" value="${start + 4 }" />
					<c:forEach var="nthMusicList" items="${nthCreationList}"
						begin="${start}" end="${end}">
						<tr>
							<c:set var="nthMusic" value="${nthMusicList.music}" />
							<c:set var="nthArtist" value="${nthMusicList.artist}" />
							<td>${nthMusic.nth}</td>
							<td><a
								href="<c:url value='/mypage'>
											<c:param name='artistId' value='${nthMusic.artistId}'/>
										 </c:url>">${nthArtist.nickname}</a></td>
							<td><a
								href="<c:url value='/article/articleRead'>
						      <c:param name='musicId' value='${nthMusic.musicId}'/>
						   </c:url>">${nthMusic.musicName}</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="page_wrap">
			<div class="page_nation">
				<ul>
					<c:if test="${ curPageNum > 5 }">
						<li><a
							href="<c:url value='/article/articleRead' />?page=${ blockStartNum - 1 }&musicId=${music.musicId}">◀</a></li>
					</c:if>

					<c:forEach var="i" begin="${ blockStartNum }"
						end="${ blockLastNum }">
						<c:choose>
							<c:when test="${ i > lastPageNum }">
								<li>${ i }</li>
							</c:when>
							<c:when test="${ i == curPageNum }">
								<li class="selected">${ i }</li>
							</c:when>
							<c:otherwise>
								<li><a
									href="<c:url value='/article/articleRead' />?page=${ i }&musicId=${music.musicId}"
									&musicId=${music.musicId}>${ i }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${ lastPageNum > blockLastNum }">
						<li><a
							href="<c:url value='/article/articleRead' />page=${ blockLastNum + 1 }&musicId=${music.musicId}">▶</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</c:if>
</body>
</html>