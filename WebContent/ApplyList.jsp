<!--  ������ DB�� ��� table�� �ۼ��ϰ�, ���߿� DB ����� �� �� forEach �������� ���̺� ���� �� ����¡ �ؾ��� �� -->
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� �Խ���</title>
</head>
<body>
	<form name="applyForm" method="POST">
		<table width="700" border="0" align="center">
			<tr>
				<td colspan="5">
					<b><font size="6" color="black">���� �Խ���</font></b>
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<hr>
				</td>
			</tr>
	        <tr>
	            <td><b>��ȣ</b></td>
	            <td><b>����</b></td>
	            <td><b>�ۼ���</b></td>
	            <td><b>�ۼ���</b></td>
	            <td><b>��ȸ</b></td>
	        </tr>
	        <tr>
	            <td>1</td>
	            <td>����1</td>
	            <td>�ۼ���1</td>
	            <td>�ۼ���1</td>
	            <td>��ȸ��1</td>
	        </tr>
	        <tr>
	            <td>2</td>
	            <td>����2</td>
	            <td>�ۼ���2</td>
	            <td>�ۼ���2</td>
	            <td>��ȸ��2</td>
	        </tr>
	        <tr>
				<td colspan="5">
					<hr>
				</td>
			</tr>
	        <tr>
				<td colspan="5">
					<input type="button" name="writeApply" value="�۾���" onclick="location.href='ApplyForm.jsp'">
				</td>
			</tr>
			<tr>
				<td colspan="5">
					����¡ �� �κ�
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<input type="text" name="searchApply" size="30" maxlength="40" placeholder="�˻�� �Է��� �ּ���">
					<input type="button" name="searchApplyButton" value="�˻�" >
				</td>
			</tr>
	    </table> 
	</form>
</body>
</html>