<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>

<style>

.input-group {
	position: relative;
	width: 100%;
	margin: 1rem 0;
}

.input-group i {
	position: absolute;
	top: 50%;
	left: 1rem;
	transform: translateY(-50%);
	font-size: 1.4rem;
	color: var(- -gray-2);
}

.input-group input {
	width: 100%;
	
	font-size: 1rem;
	background-color: var(- -gray);
	border-radius: .5rem;
	border: 0.125rem solid var(- -white);
	outline: none;
}

.input-group input:focus {
	border: 0.125rem solid var(- -primary-color);
}

</style>
</head>
<body>
	<div align="center">
		<div>
			<form id="frm" action="memberJoin.do" onsubmit="return formCheck()"
				method="post">
				<div>
					<table >
						<!-- placeholder, required -->

						<tr>
							<th>아이디</th>
							<td>
							<div class="input-group">
							<input type="text" id="memberId"
								name="memberId" size="20" placeholder="Enter Id.."> &nbsp;
								 &nbsp; <input type="hidden"
								id="checkId" value="No"> &nbsp;
								<button type="button" id="btn" onclick="idCheck()">중복체크</button>
								</div>
							</td>
						</tr>
						
						<tr>
							<th>패스워드</th>
							<td>
							<div class="input-group">
							<input type="password" id="memberPassword"
								name="memberPassword" size="20" placeholder="Enter Password.."></div></td>
						</tr>
						<tr>
							<th>패스워드확인</th>
							<td><div class="input-group"><input type="password" id="checkPw"
								name="checkPw" size="20" placeholder="Confirm Your Password.."></div></td>
						</tr>

						<tr>
							<th>닉네임</th>
							<td><div class="input-group"><input type="text" id="memberNickname"
								name="memberNickname" size="20" placeholder="Enter Nickname.."> </div><input type="hidden"
								id="checkNickname" value="No">
								<button type="button"  onclick="nicknameCheck()">중복체크</button></td>
						</tr>

						<tr>
							<th>이메일</th>
							<td><div class="input-group"><input type="text" id="memberEmail"
								name="memberEmail" size="20" placeholder="Enter Email.."></div></td>
						</tr>
						<tr>
							<th>git 주소</th>
							<td><div class="input-group"><input type="text" id="memberGit"
								name="memberGit" size="20" placeholder="Enter Git Address.."></div></td>
						</tr>
					</table>
				</div>
				<br>
				<div class="g-recaptcha"
					data-sitekey="6LdZtPggAAAAAA6ZEiPG56C-XmfBK7EJhpmMnaSm"></div>
				<br> <br>
				<div>
					<input type="submit" class="btn btn-success" value="회원가입"> <input type="reset"
					class="btn btn-success"	value="취소"> <input type="button" class="btn btn-success" value="홈으로 이동"
						onclick="location.href='main.do'">
				</div>
				<br> <br>
			</form>
		</div>
		<div>
			<a href="javascript:KakaoJoin();"> <img
				style="height: 60px; width: auto;"
				src="https://bogam.kr/wp-content/uploads/2021/12/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA-2021-12-20-%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE-2.57.09.png" /></a>
		</div>
	</div>
	<script type="text/javascript">
		window.Kakao.init("2a2d3b75fcf2829f4ecf48caa95d3805");

		function KakaoJoin() {

			Kakao.Auth.login({
				 scope: 'profile_nickname, account_email',
				success: function (response) {
					Kakao.API.request({ // 사용자 정보 가져오기 
						url: '/v2/user/me',
						success: (response) => {
							console.log("response ", response);
							let id = response.id + "kId";
							let pw = response.id + "kPw";
							let nickname = response.properties.nickname;
							let email = response.kakao_account.email;
							

							// 아이디 중복 검사
							fetch("ajaxMemberIdCheck.do?id=" + id)
								.then(result => result.text())
								.then(result => {
									if (result == "able") {

										$.ajax({
											type: "post",
											url: 'KakaoJoin.do',
											data: {
												"memberId": id,
												"memberPw": pw,
												"memberNickname": nickname,
												"memberEmail": email,
											},
											dataType: "text",
											success: function (result) {
												console.log("ajax result " + result);
												if (result == "ok") {
													alert('회원가입이 정상적으로 처리되었습니다');
													location.href='main.do';
												}
											},
											error: function (request, status, error) {
												alert("code: " + request.status +
													"\n" + "message: " + request
													.responseText + "\n" +
													"error: " + error);
											}
										});

									} else {
										// 로그인 처리
										let xhtp = new XMLHttpRequest();
										xhtp.open("get", "KakaoLogin.do?id=" + id + "&pw=" + pw);
										xhtp.send();
										xhtp.onload = function () {
											if (this.readyState == 4 && this.status == 200) {
												console.log("res: " + this.responseText);
												successAjaxKakao(this.responseText);
											} else {
												failAjax();
											}
										}
									}

								})
								.catch(failAjax);
						}
					});
				},
				fail: function (error) {
					alert(error);
				}
			});
		}


		function successAjaxKakao(str) {
			if (str == "ok") {
				alert('이미 가입된 계정입니다');
				location.href = 'main.do';
			} else {
				alert('로그인 실패!');
			}
		}

		/* 사이트키 6LdZtPggAAAAAA6ZEiPG56C-XmfBK7EJhpmMnaSm */
		/* 비밀키 6LdZtPggAAAAALQKH7Nj442q8eKh_ozTbMGZQGBJ */

		function formCheck() {
			// input에 널 값 안 들어가도록..
			// id 의 경우 중복 확인을 하고 넘어갈 수 있도록..
			// pw 의 경우 두 번 확인할 수 있도록..

			if (frm.memberId.value == "") {
				alert("아이디를 입력해 주세요.")
				frm.memberId.focus();
				return false;
			}

			if (frm.checkId.value == "No") {
				alert("아이디 중복 확인을 해 주세요.")
				return false;
			}

			if (frm.memberPassword.value == "") {
				alert("패스워드를 입력해 주세요.")
				frm.memberPassword.focus();
				return false;
			}

			if (frm.checkPw.value == "") {
				alert("패스워드를 한 번 더 입력해 주세요.")
				frm.checkPw.focus();
				return false;
			}

			if (frm.memberPassword.value != frm.checkPw.value) {
				alert("패스워드가 일치하지 않습니다.")
				frm.memberPassword.value = ""
				frm.checkPw.value = ""
				frm.memberPassword.focus();
				return false;
			}

			if (frm.memberNickname.value == "") {
				alert("닉네임을 입력해 주세요.")
				frm.memberNickname.focus();
				return false;
			}
			if (frm.checkNickname.value == "No") {
				alert("닉네임 중복 확인을 해 주세요.")

				return false;
			}
			if (frm.memberEmail.value == "") {
				alert("이메일을 입력해 주세요.")
				frm.memberEmail.focus();
				return false;
			}
			// 이메일 정규식
			let regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			if (regExp.test(frm.memberEmail.value) == false) {
				alert("이메일 형식 오류")
				frm.memberEmail.focus();
				return false
			}
			if (grecaptcha.getResponse() == "") {
				alert("로봇이 아닙니다 확인");
				return false;
			}
			return true;
		}

		function idCheck() {
			let id = frm.memberId.value;
			if (id == "") {
				alert("아이디를 입력해 주세요.")
				frm.memberId.focus();
			} else {

				fetch("ajaxMemberIdCheck.do?id=" + id)
					.then(result => result.text())
					.then(result=> {
					console.log("result: " + result);
					successAjaxId(result);					
					})
					.catch(error => console.log(error));

				/* 	const xhtp = new XMLHttpRequest();
					xhtp.open("get", "ajaxMemberIdCheck.do?id=" + id);
					xhtp.send();
					xhtp.onload = function() {
						if (this.readyState == 4 && this.status == 200) {
							successAjax(this.responseText);
						} else {
							failAjax();
						}
					} */
			}

		}

		function nicknameCheck() {
			let nickname = frm.memberNickname.value;
			if (nickname == "") {
				alert("닉네임을 입력해 주세요.")
				frm.memberNickname.focus();
			} else {
				fetch("ajaxMemberNicknameCheck.do?nickname=" + nickname)
					.then(result => result.text())
					.then(successAjaxNickname)
					.catch(failAjax);

			}
		}

		function successAjaxNickname(str) {
			if (str == "able") {
				alert('사용 가능한 닉네임입니다.');
				frm.checkNickname.value = "Yes";
			} else {
				alert("사용할 수 없는 닉네임입니다.");
				frm.memberNickname.value = "";
				frm.memberNickname.focus();
			}
		}

		function successAjaxId(str) {
			if (str == "able") {
				alert('사용 가능한 아이디 입니다.');
				frm.checkId.value = "Yes";
				frm.btn.disabled = true;
				frm.memberPassword.focus(); // reset하면 disabled가 회복이 안 됨
			} else {
				alert("사용할 수 없는 아이디입니다.");
				frm.memberId.value = "";
				frm.memberId.focus();
			}
		}

		function failAjax() {
			alert('현재 네트워크 통신 장애로 처리할 수 없습니다. 잠시 후 다시 시도해 보세요.')
		}
	</script>
</body>
</html>