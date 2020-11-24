<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR"%>
<!doctype html>
<html lang="kr">
<head>
<meta charset="UTF-8">
<title>Login & Register</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/artist/style.css" />
</head>
<body>

	<section class="container">
		<article class="half">
			<h1>Nth Create</h1>
			<div class="tabs">
				<span class="tab signin active"><a href="#signin">Sign in</a></span>
				<span class="tab signup"><a href="#signup">Sign up</a></span>
			</div>
			<div class="content">
				<div class="signin-cont cont">
					<form action="<c:url value='/artist/login' />" method="post" >
						<input type="text" name="artistId" id="artistId" class="inpt"
							required="required" placeholder="Your ID"> <label
							for="artistId">Your ID</label> <input type="password"
							name="password" id="password" class="inpt" required="required"
							placeholder="Your password"> <label for="password">Your
							Password</label>
						<div class="submit-wrap">
							<input type="submit" value="Sign in" class="submit">
						</div>
					</form>
				</div>
				
				<div class="signup-cont cont">
					<form action="<c:url value='/artist/register' />" method="post" enctype="multipart/form-data">

						<input type="text" name="userId" id="userID" class="inpt"
							required="required" placeholder="Your ID"> <label
							for="userID">Your ID</label> <input type="password"
							name="password" id="password" class="inpt" required="required"
							placeholder="Your password"> <label for="password">Your
							Password</label> <input type="text" name="nickname" id="nickname"
							class="inpt" required="required" placeholder="Your NickName">
						<label for="nickname">Your NickName</label> <input type="text"
							name="profile" id="profile" class="inpt" required="required"
							placeholder="Your Profile"> 
							<label for="profile">Your
							Profile</label> 
							
							<input type="file" name="image" id="image"
							class="inpt" required="required" placeholder="Your Image">
						<label for="image">Your Image</label>
						
						<div class="submit-wrap">
							<input type="submit" value="Sign up" class="submit">
						</div>
					</form>
				</div>
			</div>
		</article>
		<div class="half bg"></div>
	</section>



	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript">
		$('.tabs .tab').click(function() {
			if ($(this).hasClass('signin')) {
				$('.tabs .tab').removeClass('active');
				$(this).addClass('active');
				$('.cont').hide();
				$('.signin-cont').show();
			}
			if ($(this).hasClass('signup')) {
				$('.tabs .tab').removeClass('active');
				$(this).addClass('active');
				$('.cont').hide();
				$('.signup-cont').show();
			}
		});

		$('.container .bg').mousemove(
				function(e) {
					var amountMovedX = (e.pageX * -1 / 30);
					var amountMovedY = (e.pageY * -1 / 9);
					$(this).css('background-position',
							amountMovedX + 'px ' + amountMovedY + 'px');
				});
	</script>
</body>
</html>
