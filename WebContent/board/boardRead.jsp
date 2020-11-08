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
</style>
<title>Board Read</title>
</head>
<body>
	<!-- 이후에 할일 : db에서 정보 가져오기 / 수정, 삭제는 작성자만 보게 하기 / 삭제 하기-->
	<div id='menu'>
		<ul>
			<li><a href='home.jsp'>Home</a></li>
			<li class='active'><a href='#'>Board</a></li>
			<li><a href='#'>Find Artist</a></li>
			<li><a href='#'>My Page</a></li>
			<button>Login</button>
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
							<td><strong>제목 [nth] </strong>&nbsp;ㅁ누차ㅣ으므차이ㅡㅊ이mo</td>
							<td align=right><strong>글쓴이</strong>&nbsp;느ㅏㄴ미이ㅏ</td>
						</tr>

						<tr>
							<td colspan="2" style="word-break: break-word;">
								<p>
									<!-- 아마 나중에 jsp로 구현될 듯?  -->
									<audio controls="controls" autoplay loop id="audio"
										style="height: 30px; width: 500px;">
										<source src="../sample/IU.mp3" type="audio/mp3" />
									</audio>
								</p>

								<p>sbdljkncjkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkn sdknck dn
									nklmmmmmmmmmdsn jkcm,x'dcdnnjkm dcsnjknnnnnnnnnnnj amclksm
									dmslkccccccccccccc cdsnjk ;nasdk</p>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="font-size: 6pt;"><strong>원작</strong>&nbsp;ㅁ누차ㅣ으므차이ㅡㅊ이mo
								&nbsp;&nbsp;&nbsp; <strong>이전작</strong>&nbsp;ㅁ누차ㅣ으므차이ㅡㅊ이mo</td>
						</tr>

						<tr>
							<td colspan="2" align=right id="readRight"><strong>날짜</strong>&nbsp;
								2020.09.09&nbsp; &nbsp;&nbsp;<strong>조회수</strong>&nbsp; 3&nbsp;
								&nbsp;&nbsp;<strong>좋아요</strong>&nbsp; 3 &nbsp;&nbsp;&nbsp;
								<button>좋아요</button>&nbsp;&nbsp;&nbsp;
								<button>2차 창작</button></td>
						</tr>
					</table> <br> <span>
						<button value="수정">수정</button>
						<button value="삭제">삭제</button>
				</span>
				</td>
			</tr>
		</table>

		<br>
	</div>

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
				<tr>
					<td>2</td>
					<td>Lorem</td>
					<td><a href='#'>Ipsum</a></td>
				</tr>
				<tr>
					<td>7</td>
					<td>Lorem</td>
					<td><a href='#'>Ipsum</a></td>
				</tr>
				<tr>
					<td>2</td>
					<td>Lorem</td>
					<td><a href='#'>Ipsum</a></td>
				</tr>
				<tr>
					<td>2</td>
					<td>Lorem</td>
					<td><a href='#'>Ipsum</a></td>
				</tr>
				<tr>
					<td>4</td>
					<td>Lorem</td>
					<td><a href='#'>Ipsum</a></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>