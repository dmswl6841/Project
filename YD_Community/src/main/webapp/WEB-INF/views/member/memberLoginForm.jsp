<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Login</title>

<!-- Custom fonts for this template-->
<link href="./resoursce/vendor/fontawesome-free/css/all.min.css1"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="./resoursce/css/sb-admin-2.min.css1" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">Welcome !</h1>
									</div>
									<form id="frm" action="memberLogin.do" method="post">
										<div class="form-group">
											<input type="id" class="form-control form-control-user"
												id="memberId" name="memberId" aria-describedby="emailHelp"
												placeholder="Id">
										</div>
										<div class="form-group">
											<input type="password" id="memberPw"
												name="memberPw" class="form-control form-control-user"
												id="exampleInputPassword" placeholder="Password">
										</div>
										<div class="form-group">
											<div class="custom-control custom-checkbox small">
												<input type="checkbox" class="custom-control-input"
													id="customCheck"> <label
													class="custom-control-label" for="customCheck">Remember
													Me</label>
											</div>
										</div>


										<input type="submit"
											class="btn btn-primary btn-user btn-block" value="로그인"> &nbsp; &nbsp; &nbsp;
										<a href="javascript:KakaoLogin();"> <img
											style="height: 60px; width: auto;"
											src="https://nosearch.com/static/webp/images/icon_kakao_talk_min.webp" /></a>


									</form>
									<hr>

									<div class="text-center">
										<a class="small" onclick="location.href='main.do'">HOME</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>

	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="./resoursce/vendor/jquery/jquery.min.js1"></script>
	<script src="./resoursce/vendor/bootstrap/js/bootstrap.bundle.min.js1"></script>

	<!-- Core plugin JavaScript-->
	<script src="./resoursce/vendor/jquery-easing/jquery.easing.min.js1"></script>

	<!-- Custom scripts for all pages-->
	<script src="./resoursce/js/sb-admin-2.min.js1"></script>



	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>

	<script>
		window.Kakao.init("2a2d3b75fcf2829f4ecf48caa95d3805");

		function KakaoLogin() {
			window.Kakao.Auth.login({
				scope: 'profile_nickname, account_email',
				success: function (response) {
					window.Kakao.API.request({
						url: '/v2/user/me',
						success: res => {
							console.log("response ", res);
							let id = res.id + "kId";
							let pw = res.id + "kPw";
							if (res.kakao_account != null) {
								//xhtp.open("get", "ajaxMemberIdCheck.do?id=" + id); // 호출할 주소와 방식 설정				
								let xhtp = new XMLHttpRequest();
								xhtp.open("get", "KakaoLogin.do?id=" + id + "&pw=" + pw);
								//xhtp.open("get", "KakaoLogin.do?id=" + kakao_account.email + "&pw=" + kakao_account.email);
								xhtp.send();
								xhtp.onload = function () {
									if (this.readyState == 4 && this.status == 200) {
										console.log("res: " + this.responseText);
										successAjax(this.responseText);
									} else {
										failAjax();
									}
								}

								//console.log(kakao_account);
								//alert('로그인 성공!');
								//document.querySelector('#memberId').value = kakao_account.email
								//document.querySelector('#memberPW').value = kakao_account.email
								//location.href = 'memberLogin.do';

								//this.setState({isLogin:true})


								//console.log('1: ', document.querySelector('#memberId').value);
								//console.log('2: ', document.querySelector('#memberPW').value);

								//location.href='memberLogin.do';
							}
						}
					})
				},

			})
		}

		function successAjax(str) {
			if (str == "ok") {
				alert('로그인 성공!');
				location.href = 'main.do';
			} else {
				alert('로그인 실패!');
			}
		}

		function failAjax() {
			alert('네트워크 통신 장애로 처리할 수 없습니다. 잠시 후 다시 시도해 주세요.');
		}
	</script>
</body>
</html>