<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DM Detail</title>
<style>
	.align-center {
		margin: 0px auto;
	}
	.dm {
		background-color: #F3F3F3;
		width: 40%;
		padding: 5px;
		padding-top: 15px;
		border-radius: 15px;
		margin-top: 50px;
		margin-bottom: 50px;
	}
	.sender-left {
      	margin: 7px 10px 5px 10px;
	}
	.message {
      	display: inline-block;
      	position: relative;
      	max-width: 70%;
      	padding: 10px;
      	padding-top: 8px;
      	background: #DDDDDD;
      	border-radius: 15px;
      	word-wrap: break-word;
      	margin: 0px 20px;
	}
    .message-left:after {
      	border-top:15px solid  #DDDDDD;
      	border-left: 15px solid transparent;
      	border-right: 0px solid transparent;
      	border-bottom: 0px solid transparent;
      	content: "";
      	position: absolute;
      	top: 10px;
      	left: -14px;
    }
    .message-right:after {
      	border-top:15px solid  #DDDDDD;
      	border-left: 0px solid transparent;
      	border-right: 15px solid transparent;
      	border-bottom: 0px solid transparent;
      	content: "";
      position: absolute;
      	top :10px;
      	right: -14px;
    }
    .message-right {
    	text-align: right;
    	margin-top: 10px;
    	margin-left: 0px;
    }
    .message-left {
    	margin-bottom: 10px;
    	margin-right: 0px;
    }
    .time {
    	display: inline-block;
    	font-size: 0.8em;
    }
    .create-message {
      	width: 96%;
      	margin: 10px;
      	margin-top: 20px;
    }
    .enter-message {
      	padding: 10px;
      	border-radius: 15px;
      	border: 1px solid #AAAAAA;
      	width: calc(100% - 80px);
    }
    textarea:focus, input:not(#submit):focus, .uneditable-input:focus {
      	border-color: #AAAAAA;
      	box-shadow: 0 1px 1px #AAAAAA inset, 0 0 8px #AAAAAA;
      	outline: 0 none;
    } 
    .btn-submit, .btn-delete {
      	background-color: #AAAAAA;
      	color: white;
      	padding: 10px;
      	border-radius: 15px;
      	border: 0px;
      	width: 50px;
      	border: 1px solid #AAAAAA;
    }
    .btn-submit:hover, .btn-delete:hover{
        text-decoration: none;
        background-color: white;
        color: #AAAAAA;
        transition-property: background-color, color;
        transition-duration: 0.2s;
        transition-timing-function: ease-in-out;
    }
    .btn-delete {
    	text-decoration: none;
    	font-size: 0.9em;
    }
	.hover-cursor {
		cursor: pointer;
	}
    @media (orientation: portrait) {
      	.dm {
        	width: 95%;
      	}
    }
</style>
</head>
<body>
	<div class="dm align-center">
		<div align="right">
			<div class="time time-right">
				12:30
			</div>
			<div class="message message-right">
				안녕하세요			
			</div>
		</div>
		<div>
			<div class="sender-left">
				<span>artist2</span>
			</div>
			<div class="message message-left">
				예 안녕하세요
			</div>
			<div class="time time-left">
				12:30
			</div>
		</div>
		<div align="right">
			<div class="time time-right">
				12:30
			</div>
			<div class="message message-right">
				안녕하세요테스트좀하겠습니다안녕하세요테스트좀하겠습니다안녕하세요테스트좀하겠습니다안녕하세요테스트좀하겠습니다안녕하세요테스트좀하겠습니다		
			</div>
		</div>
		<div align="right">
			<div class="time time-right">
				12:30
			</div>
			<div class="message message-right">
				안녕하세요테스트좀하겠습니다안녕하세요테스트좀하겠습니다안녕하세요테스트좀하겠습니다안녕하세요테스트좀하겠습니다안녕하세요테스트좀하겠습니다
			</div>
		</div>
		<div>
			<div class="sender-left">
				<span>artist2</span>
			</div>
			<div class="message message-left">
				예 안녕하세요
			</div>
			<div class="time time-left">
				12:30
			</div>
		</div>
		<div class="create-message">
			<form>
				<input type="text" class="enter-message">
				<input type="submit" class="btn-submit hover-cursor" value="전송">
			</form>
		</div>
	</div>
	<div align="center">
		<a href="" class="btn-delete">방 삭제</a>
	</div>
</body>
</html>