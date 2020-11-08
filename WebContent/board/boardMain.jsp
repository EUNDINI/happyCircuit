<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="boardStyles.css">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<script src="script.js"></script>
<title>Board</title>
</head>
<body>
	<!-- 이후에 할일 : 페이지 처리 / DB연결해서 글리스트 가져오기  -->
	<div id='menu'>
		<ul>
			<li><a href='home.jsp'>Home</a></li>
			<li class='active'><a href='#'>Board</a></li>
			<li><a href='#'>Find Artist</a></li>
			<li><a href='#'>My Page</a></li>
			<button>Login</button>
		</ul>
	</div>

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
		</table>
		
		<div id="boardPage">
			<button
				style="color: black; border: none; width: 80px; height: 40px;"
				onclick="location.href='boardWrite.jsp'">글작성</button>
		</div>
		<br>
		<div id="boardSearch">
			<form>
				<select name="opt">
					<option value="0">제목</option>
					<option value="1">내용</option>
					<option value="2">제목+내용</option>
					<option value="3">글쓴이</option>
				</select> 
				<input type="text" size="20" name="condition" /> <input type="submit" value="검색" />
			</form>
		</div>
		
	</div>
</body>
</html>