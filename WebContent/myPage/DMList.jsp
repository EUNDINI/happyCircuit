<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DM List</title>
<style>
	.align-center {
		margin: 0px auto;
	}
	.DM-list {
		width: 70%;
		min-width: 500px;
	}
	.artist-img {
		width: 60px;
		height: 60px;
		border-radius: 30px;
		float: left;
		margin: 10px;
	}
	table, tr, td {
		border: 1px solid #DDDDDD;
		border-collapse: collapse;
	}
	table {
		margin: 50px 0px;
	}
	td {
		padding: 5px;
	}
	.flex-container {
		display: flex;
		flex-direction: column;
		justify-content: space-evenly;
	}
	.flex-item {
		flex-grow: 1;
	}
	.hover-cursor {
		cursor: pointer;
	}
    @media (orientation: portrait) {
      	.DM-list {
        	width: 95%;
      	}
    }
</style>
</head>
<body>
	<div class="DM-list align-center">
		<table>
			<tr>
				<td onclick="" class="hover-cursor">
					<img src="../sample/holding_onto_gravity.jpg" class="artist-img">
					<div class="flex-container">
						<div class="flex-item"><span>artist</span></div>
						<div class="flex-item"><span>message 내용(마지막 메시지)</span></div>
					</div>
				</td>			
			</tr>
			<tr>
				<td onclick="" class="hover-cursor">
					<img src="../sample/holding_onto_gravity.jpg" class="artist-img">
					<div class="flex-container">
						<div class="flex-item"><span>artist</span></div>
						<div class="flex-item"><span>message 내용(마지막 메시지) 길게길게 여기엔 나중에 ...띄우는 거 메소드로 작업해서 넣자</span></div>
					</div>
				</td>			
			</tr>
		</table>
	</div>
</body>
</html>