<!--  지금은 DB가 없어서 table로 작성하고, 나중에 DB 만들면 그 때 forEach 구문으로 테이블 생성 및 페이징 해야할 듯 -->
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>구인 게시판</title>
</head>
<body>
	<form name="applyForm" method="POST">
		<table width="700" border="0" align="center">
			<tr>
				<td colspan="5">
					<b><font size="6" color="black">구인 게시판</font></b>
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<hr>
				</td>
			</tr>
	        <tr>
	            <td><b>번호</b></td>
	            <td><b>제목</b></td>
	            <td><b>작성자</b></td>
	            <td><b>작성일</b></td>
	            <td><b>조회</b></td>
	        </tr>
	        <tr>
	            <td>1</td>
	            <td>제목1</td>
	            <td>작성자1</td>
	            <td>작성일1</td>
	            <td>조회수1</td>
	        </tr>
	        <tr>
	            <td>2</td>
	            <td>제목2</td>
	            <td>작성자2</td>
	            <td>작성일2</td>
	            <td>조회수2</td>
	        </tr>
	        <tr>
				<td colspan="5">
					<hr>
				</td>
			</tr>
	        <tr>
				<td colspan="5">
					<input type="button" name="writeApply" value="글쓰기" onclick="location.href='ApplyForm.jsp'">
				</td>
			</tr>
			<tr>
				<td colspan="5">
					페이징 들어갈 부분
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<input type="text" name="searchApply" size="30" maxlength="40" placeholder="검색어를 입력해 주세요">
					<input type="button" name="searchApplyButton" value="검색" >
				</td>
			</tr>
	    </table> 
	</form>
</body>
</html>