<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�۾���</title>

</head>
<body>
	<form name="applyForm" method="POST" action="ApplyList.jsp">
		<table width="700" border="0" align="center">
			<tr>
				<td>
					<b><font size="6" color="black" align="center">�۾���</font></b>
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
				      <option value="none" selected>ī�װ� ����</option>
					  <option value="vocal">���� ���� ����</option>
					  <option value="session">���� ���� ����</option>
					</select>
				</td>
	        </tr>
	        <tr>
	            <td>
	            	<input type="text" name="applyTitle" size="70" maxlength="100" placeholder="������ �Է��� �ּ���.">
	            </td>
	        </tr>
	        <tr>
	            <td>
	            	<textarea name="applyContent" cols="72" rows="20" placeholder="������ �Է��� �ּ���."></textarea> 
	            </td>
	        </tr>
	        <tr>
	            <td>
	                <input type="file" name="applyAttachment" />
	            </td>    
	        </tr>
	        <tr>
	            <td colspan="5">
	                <input type="submit" value="���" >    
	            </td>
	        </tr>
	    </table> 
	</form>
</body>
</html>