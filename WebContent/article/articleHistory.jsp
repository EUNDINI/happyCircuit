<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>History</title>
<script type="text/javascript">
	function closeThis(url) {
		opener.document.location.href = url;
		self.close();
	}
</script>

<style type="text/css">
#priorList {
	padding: 10px 0;
	margin: auto;
	width: 90%;
	text-align: center;
}

#priorList table {
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

#priorList table th {
	border-bottom: 2px solid #999;
	padding: 10px;
}

#priorList table .nth {
	width: 10%;
}

#priorList table .username {
	width: 20%;
}

#priorList table td {
	border-bottom: 1px solid #ccc;
	padding: 10px;
}

#priorList table td a {
	color: black;
	text-decoration: none;
}

#priorList table td a:hover {
	color: #47c9af;
	font-size: 15pt;
}
</style>
</head>
<body>
	<c:if test="${empty priorList}">
		<h1 style="text-align: center;">History가 존재하지 않습니다.</h1>
	</c:if>
	<c:if test="${not empty priorList}">
		<div id='priorList'>
			<table>
				<thead>
					<tr>
						<th class="nth">Nth</th>
						<th class="username">글쓴이</th>
						<th>제목</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="priorMusicList" items="${priorList}">
						<tr>
							<c:set var="priorMusic" value="${priorMusicList.music}" />
							<c:set var="artist" value="${priorMusicList.artist}" />
							<td>${priorMusic.nth}</td>
							<td>${artist.nickname}</td>
							<td><a  href="#" onclick="closeThis('<c:url value='/article/articleRead'><c:param name='musicId' value='${priorMusic.musicId}' /></c:url>');return false;">${priorMusic.musicName}</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>
</body>
</html>