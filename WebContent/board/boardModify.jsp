<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<title>Board Modify</title>
</head>
<body>
	<!-- 이후에 할일 : 확인 누르면 db에 저장-->
	<div id='menu'>
		<ul>
			<li><a href='home.jsp'>Home</a></li>
			<li class='active'><a href='#'>Board</a></li>
			<li><a href='#'>Find Artist</a></li>
			<li><a href='#'>My Page</a></li>
			<button>Login</button>
		</ul>
	</div>

	<div id="boardWrite">
		<form>
			<table style="padding-top: 10px" align=center width=80% border='0'>
				<tr>
					<td height=20 align=center bgcolor=#cccccc><font color=white>
							글수정</font></td>
				</tr>
				<tr>
					<td bgcolor=white align=center>
						<table id="writeTable" width='80%'>
							<tr>
								<th>작성자</th>
								<td>작성자 아이디</td>
							</tr>

							<tr>
								<th>장르</th>
								<td><input type="radio" name="genre" value="rock">ROCK <input
									type="radio" name="genre" value="R&B">R&B <input
									type="radio" name="genre" value="EDM">EDM <input
									type="radio" name="genre" value="pop" checked="checked">POP(댄스,
									K-pop etc) <input type="radio" name="genre" value="hiphop">HIP-HOP
									<input type="radio" name="genre" value="etc">ETC</td>
							</tr>

							<tr>
								<th>제목</th>
								<td><input type=text name=title size=60
									style='border: none; border-bottom: 1px solid #ccc;'></td>
							</tr>

							<tr>
								<th>내용</th>
								<td><textarea name=content cols=85 rows=15></textarea></td>
							</tr>

							<tr>
								<th>파일 업로드</th>
								<td><input type=file name=music size=60></td>
							</tr>
						</table>
						<br> 
						<span> 
						<input type="button" value="수정" /> <input
							type="button" value="취소" onclick="location.href='boardMain.jsp'" />
					</span>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>