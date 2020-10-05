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
<title>HOME</title>
</head>
<body>
	<!-- 각각 연동 / 좋아요 차트 가져오기 -->
	<div id='menu'>
		<ul>
			<li class='active'><a href='#'>Home</a></li>
			<li><a href='boardMain.jsp'>Board</a></li>
			<li><a href='#'>Find Artist</a></li>
			<li><a href='#'>My Page</a></li>
			<button>Login</button>
		</ul>
	</div>
	<br>
	<div id='home' align="center">
		<input id="tab1" type="radio" name="tabs" checked> <label
			for="tab1">전체 인기차트</label> <input id="tab2" type="radio" name="tabs">
		<label for="tab2">ROCK 인기차트</label> <input id="tab3" type="radio"
			name="tabs"> <label for="tab3">R&B 인기차트</label> <input
			id="tab4" type="radio" name="tabs"> <label for="tab4">POP
			인기차트</label> <input id="tab5" type="radio" name="tabs"> <label
			for="tab5">EMD 인기차트</label> <input id="tab6" type="radio" name="tabs">
		<label for="tab6">HIP-HOP 인기차트</label> <input id="tab7" type="radio"
			name="tabs"> <label for="tab7">기타장르 인기차트</label>

		<section id="content1" align="center">
			<div class="chart">
				<ol>
					<li><a href="#">&nbsp;&nbsp;제목&nbsp;&nbsp;작성자&nbsp;&nbsp;좋아요수
					</a></li>
				</ol>
			</div>
		</section>

		<section id="content2">
			<div class="chart">
				<ol>
					<li><a href="#">&nbsp;&nbsp;제목&nbsp;&nbsp;작성자&nbsp;&nbsp;좋아요수
					</a></li>
					<li><a href="#">&nbsp;&nbsp;제목&nbsp;&nbsp;작성자&nbsp;&nbsp;좋아요수
					</a></li>
				</ol>
			</div>
		</section>

		<section id="content3">
			<div class="chart">
				<ol>
					<li><a href="#">&nbsp;&nbsp;제목&nbsp;&nbsp;작성자&nbsp;&nbsp;좋아요수
					</a></li>
					<li><a href="#">&nbsp;&nbsp;제목&nbsp;&nbsp;작성자&nbsp;&nbsp;좋아요수
					</a></li>
					<li><a href="#">&nbsp;&nbsp;제목&nbsp;&nbsp;작성자&nbsp;&nbsp;좋아요수
					</a></li>
				</ol>
			</div>
		</section>

		<section id="content4">
			<div class="chart">
				<ol>
					<li><a href="#">&nbsp;&nbsp;제목&nbsp;&nbsp;작성자&nbsp;&nbsp;좋아요수
					</a></li>
					<li><a href="#">&nbsp;&nbsp;제목&nbsp;&nbsp;작성자&nbsp;&nbsp;좋아요수
					</a></li>
				</ol>
			</div>
		</section>

		<section id="content5">
			<div class="chart">
				<ol>
					<li><a href="#">&nbsp;&nbsp;제목&nbsp;&nbsp;작성자&nbsp;&nbsp;좋아요수
					</a></li>
				</ol>
			</div>
		</section>

		<section id="content6">
			<div class="chart">
				<ol>
					<li><a href="#">&nbsp;&nbsp;제목&nbsp;&nbsp;작성자&nbsp;&nbsp;좋아요수
					</a></li>
					<li><a href="#">&nbsp;&nbsp;제목&nbsp;&nbsp;작성자&nbsp;&nbsp;좋아요수
					</a></li>
				</ol>
			</div>
		</section>

		<section id="content7">
			<div class="chart">
				<ol>
					<li><a href="#">&nbsp;&nbsp;제목&nbsp;&nbsp;작성자&nbsp;&nbsp;좋아요수
					</a></li>
					<li><a href="#">&nbsp;&nbsp;제목&nbsp;&nbsp;작성자&nbsp;&nbsp;좋아요수
					</a></li>
					<li><a href="#">&nbsp;&nbsp;제목&nbsp;&nbsp;작성자&nbsp;&nbsp;좋아요수
					</a></li>
				</ol>
			</div>
		</section>
	</div>
</body>
</html>