<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�۳���</title>
</head>
<body>
	<form name="applyForm" method="POST">
		<table width="700" border="0" align="center">
			<tr>
	            <td>
	            	<font size="2" color="black">ī�װ� �̸�(���߿� ���� ����)</font>
				</td>
	        </tr>
	        <tr>
	            <td>
	            	<b><font size="5" color="black">����(���߿� ���� ����)</font></b>
	            </td>
	        </tr>
	        <tr>
	            <td>
	            	<font size="3" color="black">�г��� �Ǵ� �̸�(���߿� ���� ����)</font>
	            </td>
	        </tr>
	        <tr>
				<td>
					<hr>
				</td>
			</tr>
	        <tr>
	            <td>
	            	<textarea name="applyContent" cols="72" rows="20">����(���߿� ���� ����)</textarea> 
	            </td>
	        </tr>
	        <tr>
	            <td colspan="5">
	                <input type="button" name="updateApply" value="����" onclick="location.href='ApplyForm.jsp'">    
	                <input type="button" name="deleteApply" value="����" > 
	                <input type="button" name="goToApplyList" value="���" onclick="location.href='ApplyList.jsp'" > 
	                <input type="button" name="offerCollaboration" value="���� ��û" > 
	            </td>
	        </tr>
	    </table> 
	</form>
</body>
</html>