<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<div>
			<h1>로 그 인</h1>
			<div>
				<form id="frm" action="memberLogin.do" method="post">
					<table border="1">
						<tr>
							<th width="150">아 이 디</th>
							<td width="200"><input type="text" id="memberId"
								name="memberId" required="required"
								placeholder="Enter Your ID..."></td>
						</tr>
						<tr>
							<th width="150">패스워드</th>
							<td width="200"><input type="password" id="memberPw"
								name="memberPw" required="required"
								placeholder="Enter Your Password..."></td>
						</tr>
					</table>
					<br>
					<div>
						<input type="submit" value="로그인">&nbsp;&nbsp;&nbsp; <input
							type="reset" value="취 소">&nbsp;&nbsp;&nbsp; <input
							type="button" value="홈" onclick="location.href='main.do'">&nbsp;&nbsp;&nbsp;
						<input type="button" value="아이디 찾기" onclick="location.href=''">
					</div>
				</form>
			</div>
		</div>
	</div>

	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<a href="javascript:KakaoLogin();"> <img
		style="height: 60px; width: auto;"
		src="https://nosearch.com/static/webp/images/icon_kakao_talk_min.webp" /></a>

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