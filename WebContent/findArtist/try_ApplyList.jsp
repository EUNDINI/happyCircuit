<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
   <!-- 
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../resources/css/try.css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    --> 
    <!-- 
        <link rel="stylesheet" href="../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../resources/css/try.css">
    <script src="../resources/js/bootstrap.js"></script>
     -->

	<link rel="stylesheet" href="../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../resources/css/try.css">
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
									<b><font size="6">구인 게시판</font></b>
								</td>
							</tr>
                                <tr>
                                    <th scope="col">번호</th>
                                    <th scope="col">분류</th>
                                    <th scope="col">제목</th>
                                    <th scope="col">작성자</th>
                                    <th scope="col">작성일</th>
                                    <th scope="col">조회</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<tr>
                                    <th scope="row">1</th>
                                    <td>보컬</td>
                                    <td>객원 보컬 구합니다</td>
									<td>김은진</td>
									<td>2020.10.13</td>
									<td>13</td>
                                </tr>
                                
                                <tr>
                                    <th scope="row">2</th>
                                    <td>세션</td>
                                    <td>키보드 세션 보컬 구합니다</td>
									<td>이지우</td>
									<td>2020.10.15</td>
									<td>20</td>
                                </tr>
                                
                                <tr>
                                    <th scope="row">1</th>
                                    <td>보컬</td>
                                    <td>객원 보컬 구합니다</td>
									<td>문현정</td>
									<td>2020.10.17</td>
									<td>27</td>
                                </tr>

<!--                               
                                <tr>
                                    <th scope="row">1</th>
                                    <td>New admin Design</td>
                                    <td>02/5/2019</td>
                                    <td>
                                        <span class="text-success font-12"><i class="mdi mdi-checkbox-blank-circle mr-1"></i> Completed</span>
                                    </td>
                                    <td>
                                        <div class="team">
                                            <a href="javascript: void(0);" class="team-member" data-toggle="tooltip" data-placement="top" title="" data-original-title="Roger Drake">
                                                <img src="https://bootdey.com/img/Content/avatar/avatar6.png" class="rounded-circle avatar-xs" alt="" />
                                            </a>

                                            <a href="javascript: void(0);" class="team-member" data-toggle="tooltip" data-placement="top" title="" data-original-title="Reggie James">
                                                <img src="https://bootdey.com/img/Content/avatar/avatar7.png" class="rounded-circle avatar-xs" alt="" />
                                            </a>

                                            <a href="javascript: void(0);" class="team-member" data-toggle="tooltip" data-placement="top" title="" data-original-title="Gerald Mayberry">
                                                <img src="https://bootdey.com/img/Content/avatar/avatar8.png" class="rounded-circle avatar-xs" alt="" />
                                            </a>
                                        </div>
                                    </td>
                                    <td>
                                        <p class="mb-0">Progress<span class="float-right">100%</span></p>

                                        <div class="progress mt-2" style="height: 5px;">
                                            <div class="progress-bar bg-success" role="progressbar" style="width: 100%;" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                    </td>

                                    <td>
                                        <div class="action">
                                            <a href="#" class="text-success mr-4" data-toggle="tooltip" data-placement="top" title="" data-original-title="Edit"> <i class="fa fa-pencil h5 m-0"></i></a>
                                            <a href="#" class="text-danger" data-toggle="tooltip" data-placement="top" title="" data-original-title="Close"> <i class="fa fa-remove h5 m-0"></i></a>
                                        </div>
                                    </td>
                                </tr>
-->
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
						<button class="btn btn-primary">작성</button>
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