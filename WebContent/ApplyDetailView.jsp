<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글내용</title>
</head>
<body>
	<form name="applyForm" method="POST">
		<table width="700" border="0" align="center">
			<tr>
	            <td>
	            	<font size="2" color="black">카테고리 이름(나중에 수정 예정)</font>
				</td>
	        </tr>
	        <tr>
	            <td>
	            	<b><font size="5" color="black">제목(나중에 수정 예정)</font></b>
	            </td>
	        </tr>
	        <tr>
	            <td>
	            	<font size="3" color="black">닉네임 또는 이름(나중에 수정 예정)</font>
	            </td>
	        </tr>
	        <tr>
				<td>
					<hr>
				</td>
			</tr>
	        <tr>
	            <td>
	            	<textarea name="applyContent" cols="72" rows="20">내용(나중에 수정 예정)</textarea> 
	            </td>
	        </tr>
	        <tr>
	            <td colspan="5">
	                <input type="button" name="updateApply" value="수정" onclick="location.href='ApplyForm.jsp'">    
	                <input type="button" name="deleteApply" value="삭제" > 
	                <input type="button" name="goToApplyList" value="목록" onclick="location.href='ApplyList.jsp'" > 
	                <input type="button" name="offerCollaboration" value="협업 신청" > 
	            </td>
	        </tr>
	    </table> 
	</form>
</body>
</html>