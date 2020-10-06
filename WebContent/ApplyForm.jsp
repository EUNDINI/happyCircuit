<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글쓰기</title>

</head>
<body>
	<form name="applyForm" method="POST" action="ApplyList.jsp">
		<table width="700" border="0" align="center">
			<tr>
				<td>
					<b><font size="6" color="black" align="center">글쓰기</font></b>
				</td>
			</tr>
			<tr>
				<td>
					<hr>
				</td>
			</tr>
			<tr>
	            <td>
	            	<select name="category">
				      <option value="none" selected>카테고리 선택</option>
					  <option value="vocal">객원 보컬 구인</option>
					  <option value="session">객원 세션 구인</option>
					</select>
				</td>
	        </tr>
	        <tr>
	            <td>
	            	<input type="text" name="applyTitle" size="70" maxlength="100" placeholder="제목을 입력해 주세요.">
	            </td>
	        </tr>
	        <tr>
	            <td>
	            	<textarea name="applyContent" cols="72" rows="20" placeholder="내용을 입력해 주세요."></textarea> 
	            </td>
	        </tr>
	        <tr>
	            <td>
	                <input type="file" name="applyAttachment" />
	            </td>    
	        </tr>
	        <tr>
	            <td colspan="5">
	                <input type="submit" value="등록" >    
	            </td>
	        </tr>
	    </table> 
	</form>
</body>
</html>