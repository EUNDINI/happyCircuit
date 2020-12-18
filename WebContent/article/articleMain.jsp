<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.MusicArticle"%>
<%@page import="java.util.List "%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
function searchArticle(){
	if (form.search.value == "") {
		alert("검색어를 입력하십시오.");
		form.title.focus();
		return false;
	}
	
	form.submit();
}

function isLogin() {
	var artist = '${artistId}';
	if(artist != ''){
		document.getElementById("login").style.display = "none";
		document.getElementById("logout").style.display = "block";
		document.getElementById("btnWrite").style.display = "block";
	}
	else {
		document.getElementById("logout").style.display = "none";
		document.getElementById("login").style.display = "block";
		document.getElementById("btnWrite").style.display = "none";
	}
}
</script>
<style>
#board a {
	text-decoration: none;
	color: black;
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

#btnWrite {
	color: black;
	border: none;
	width: 80px;
	height: 40px;
}
</style>
<title>Article</title>
</head>
<body onload='isLogin()'>
	<div id='menu'>
		<ul>
			<li><a href='<c:url value='/home' />'>Home</a></li>
			<li class='active'><a
				href='<c:url value='/article/articleMain' />'>Article</a></li>

			<li><a href="<c:url value='/post/list' />">Find Artist</a></li>
			<li><a href="<c:url value='/mypage'>
						 	<c:param name='artistId' value='${artistId}'/>
						 </c:url>">My Page</a></li>

			<button id='logout'
				onclick="location.href='<c:url value='/artist/logout' />' ">Logout</button>
			<button id='login'
				onClick="location.href='<c:url value='/artist/login/form' />' ">Login</button>
		</ul>
	</div>
	<br><br>
	<div id="board">
		<table>
			<tr>
				<th width="80px">번호</th>
				<th>제목</th>
				<th width="100px">작성자</th>
				<th width="150px">날짜</th>
				<th width="80px">조회수</th>
				<th width="80px">좋아요</th>
			</tr>
			<c:set var="num" value="${total - 15 * (page - 1) + 1}" />
			<c:forEach var="musicArticle" items="${musicArticleList}">
				<tr>
					<c:set var="num" value="${num-1}" />
					<c:set var="music" value="${musicArticle.music}" />
					<c:set var="artist" value="${musicArticle.artist}"/>
					<td>${num}</td>
					<td><a
						href="<c:url value='/article/articleRead'>
						      <c:param name='musicId' value='${music.musicId}'/>
						   </c:url>">${music.musicName}</a></td>
					<td><a href="<c:url value='/mypage'>
									<c:param name='artistId' value='${music.artistId}'/>
								 </c:url>">${artist.nickname}</a></td>
					<td>${musicArticle.regDate}</td>
					<td>${musicArticle.readCount}</td>
					<td>${musicArticle.likeCount}</td>
				</tr>
			</c:forEach>
		</table>

		<div id="boardPage">
			<div class="page_wrap">
				<div class="page_nation">
					<ul>
						<c:if test="${ curPageNum > 5 && !empty search }">
							<li><a
								href="<c:url value='/article/articleSearch' />?page=${ blockStartNum - 1 }&search=${search}&condition=${condition}">◀</a></li>
						</c:if>

						<c:if test="${ curPageNum > 5 }">
							<li><a
								href="<c:url value='/article/articleMain' />?page=${ blockStartNum - 1 }">◀</a></li>
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
								<c:when test="${ !empty search}">
									<li><a
										href="<c:url value='/article/articleSearch' />?a=search&page=${ i }&search=${search}&condition=${condition}">${ i }</a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="<c:url value='/article/articleMain' />?page=${ i }">${ i }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:if test="${ lastPageNum > blockLastNum && !empty search }">
							<li><a
								href="<c:url value='/article/articleSearch' />?a=search&page=${ blockLastNum + 1 }&search=${search}&condition=${condition}">▶</a></li>
						</c:if>

						<c:if test="${ lastPageNum > blockLastNum }">
							<li><a
								href="<c:url value='/article/articleMain' />page=${ blockLastNum + 1 }">▶</a></li>
						</c:if>
					</ul>
				</div>

				<button id='btnWrite'
					onclick="location.href='<c:url value='/article/articleWrite/form ' />'">글작성</button>
			</div>
		</div>
		<br>
		<div id="boardSearch">
			<form name="form" action="<c:url value='/article/articleSearch' />">
				<select name="condition">
					<option value="musicName">제목</option>
					<option value="artistId">글쓴이</option>
				</select> <input type="text" size="20" name="search" placeholder="검색어를 입력하세요" />
				<input type="button" value="검색" onClick='searchArticle()' />
			</form>
		</div>

	</div>
</body>
</html>