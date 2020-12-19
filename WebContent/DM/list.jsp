<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		width: 100%;
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
		height: 70px;
		margin: auto 10px;
	}
	.flex-item {
		flex-grow: 1;
	}
	.hover-cursor {
		cursor: pointer;
	}
	.update {
    	margin-top: 20px;
    	margin-bottom: 50px; 
    	align: center;
	}
	.btn-update {
      	background-color: #BBBBBB;
      	color: white;
      	padding: 10px;
      	border-radius: 15px;
      	border: 0px;
      	width: 50px;
      	border: 1px solid #BBBBBB;
    	text-decoration: none;
    	font-size: 0.9em;
    }
    .btn-update:hover {
        text-decoration: none;
        background-color: white;
        color: #BBBBBB;
        transition-property: background-color, color;
        transition-duration: 0.2s;
        transition-timing-function: ease-in-out;
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
			<c:forEach var="dm" items="${dmList}" varStatus="status">
				<tr>
					<td onclick="location.href=`<c:url value='/DM/room'>
											   		<c:param name='dmId' value='${dm.dmId}'/>
											 	</c:url>`" class="hover-cursor">
						<img src="../sample/${lastMsgList[status.index].artist.image}" class="artist-img">
						<div class="flex-container">
							<div class="flex-item"><span>${lastMsgList[status.index].artist.nickname}</span></div>
							<div class="flex-item"><span>${lastMsgList[status.index].message}</span></div>
						</div>
					</td>	
				</tr>
			</c:forEach>
		</table>
		<div class="align-center">
			<a href="<c:url value='/mypage'>
					 	<c:param name='artistId' value='${artistId}'/>
					 </c:url>" class="btn-update">My Page</a>
		</div>
	</div>
</body>
</html>