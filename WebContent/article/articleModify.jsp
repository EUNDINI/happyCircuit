<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String artistId = (String) session.getAttribute("artistId");

//if (artistId == null || artistId.equals("")) {
//	response.sendRedirect("articleMain.jsp");
//}
%>
<c:set var="music" value="${musicArticle.music}" />
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
<script type="text/javascript">
function articleModify() {
	if (form.title.value == "") {
		alert("제목(노래)을 입력하십시오.");
		form.title.focus();
		return false;
	}

	if (form.content.value == "") {
		alert("설명을 입력하십시오.");
		form.content.focus();
		return false;
	}
	form.submit();
}

function setting() {
	var artist = '${artistId}';
	if(artist != ''){
		document.getElementById("login").style.display = "none";
		document.getElementById("logout").style.display = "block";
	}
	else {
		document.getElementById("logout").style.display = "none";
		document.getElementById("login").style.display = "block";
	}
	
	var st = '${music.genre}';
	$('input:radio[name=genre]:input[value=' + st + ']').attr("checked", true);
	
}

isLogin();

</script>
<title>Article Modify</title>
</head>
<body onload="setting()">
	<!-- 이후에 할일 : 확인 누르면 db에 저장-->
	<div id='menu'>
		<ul>
			<li><a href='<c:url value='/home' />'>Home</a></li>
			<li class='active'><a
				href='<c:url value='/article/articleMain' />'>Article</a></li>
			li><a href="<c:url value='/findArtist/list' />">Find Artist</a></li>
			<li><a href="<c:url value='/mypage'>
						 	<c:param name='artistId' value='${artistId}'/>
						 </c:url>">My Page</a></li>
			<button id='logout' onclick="location.href='<c:url value='/artist/logout' />' ">Logout</button>
			<button id='login'
				onClick="location.href='<c:url value='/artist/login/form' />' ">Login</button>
		</ul>
	</div>
<br><br>
	<div id="boardWrite">
		<form name="form" method="post" enctype="multipart/form-data"
			action="<c:url value='/article/articleModify'><c:param name='musicId' value='${music.musicId}' /> </c:url>">
			<table style="padding-top: 10px" align=center width=80% border='0'>
				<tr>
					<td height=20 align=center bgcolor=#cccccc><font color=white>
							글수정</font></td>
				</tr>
				<tr>
					<td bgcolor=white align=center>
						<table id="writeTable" width='80%'>
							<tr>
								<th width="200px">작성자</th>
								<td>${music.artistId}</td>
							</tr>

							<tr>
								<th>장르</th>
								<td><input type="radio" name="genre" value="rock">ROCK
									<input type="radio" name="genre" value="rNb">R&B <input
									type="radio" name="genre" value="edm">EDM <input
									type="radio" name="genre" value="pop">POP(댄스, K-pop
									etc) <input type="radio" name="genre" value="hiphop">HIP-HOP
									<input type="radio" name="genre" value="etc">ETC</td>
							</tr>

							<tr>
								<th>제목</th>
								<td><input type=text name=title size=60
									style='border: none; border-bottom: 1px solid #ccc;'
									value="${music.musicName}"></td>
							</tr>

							<tr>
								<th>내용</th>
								<td><textarea name=content cols=70 rows=15>${musicArticle.content}</textarea></td>
							</tr>

							<tr>
								<th>파일 업로드</th>
								<td><input type=file name=music size=60></td>
							</tr>
						</table> <br> <input type="hidden" name="musicId "
						value="${music.musicId}" /> <span> <input type="button"
							value="수정" onClick="articleModify()" /> <input type="button"
							value="취소"
							onClick="location.href='<c:url value='/article/articleRead'><c:param name='musicId' value='${music.musicId}' /> </c:url>'" />
					</span>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>