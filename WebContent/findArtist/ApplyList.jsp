<!--  ������ DB�� ��� table�� �ۼ��ϰ�, ���߿� DB ����� �� �� forEach �������� ���̺� ���� �� ����¡ �ؾ��� �� -->
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� �Խ���</title>
	<link rel="stylesheet" href="../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../resources/css/ApplyList.css">
    <script src="../resources/js/bootstrap.js"></script>
</head>
<body>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-body">
                    <div class="table-responsive project-list">
                        <table class="table project-table table-centered table-nowrap">
                            <thead>
                            <tr>
								<td colspan="3">
									<b><font size="6">���� �Խ���</font></b>
								</td>
							</tr>
                                <tr>
                                    <th scope="col">��ȣ</th>
                                    <th scope="col">�з�</th>
                                    <th scope="col">����</th>
                                    <th scope="col">�ۼ���</th>
                                    <th scope="col">�ۼ���</th>
                                    <th scope="col">��ȸ</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<tr>
                                    <th scope="row">1</th>
                                    <td>����</td>
                                    <td>���� ���� ���մϴ�</td>
									<td>������</td>
									<td>2020.10.13</td>
									<td>13</td>
                                </tr>
                                
                                <tr>
                                    <th scope="row">2</th>
                                    <td>����</td>
                                    <td>Ű���� ���� ���� ���մϴ�</td>
									<td>������</td>
									<td>2020.10.15</td>
									<td>20</td>
                                </tr>
                                
                                <tr>
                                    <th scope="row">1</th>
                                    <td>����</td>
                                    <td>���� ���� ���մϴ�</td>
									<td>������</td>
									<td>2020.10.17</td>
									<td>27</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <!-- end project-list -->
            
                     <div class="pt-3">
                        <ul class="pagination">
				    		<li>
						      <a href="#" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </a>
						    </li>
						    <li><a href="#">1</a></li>
						    <li><a href="#">2</a></li>
						    <li><a href="#">3</a></li>
						    <li><a href="#">4</a></li>
						    <li><a href="#">5</a></li>
						    <li>
						      <a href="#" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </a>
							</li>
					  </ul>
                    </div>
                    <!-- end project-list -->
					
					<div class="write-find-artist">
						<form>
							<input type="button" name="writeApply" value="�ۼ�" onclick="location.href='ApplyForm.jsp'" class="btn btn-primary">
				        </form>
					</div>
					<!-- end write button -->

					<div class="col-sm-3">
        				<form class="navbar-form" role="search">
       						 <div class="input-group">
            					<input type="text" class="form-control" placeholder="Search" name="q">
				            	<div class="input-group-btn">
				                	<button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
				            	</div>
				        	</div>
				        </form>
				    </div>
				    <!-- end search -->

            	</div>
            </div>
        </div>
    </div>
    <!-- end row -->
</div>
</body>
</html>

