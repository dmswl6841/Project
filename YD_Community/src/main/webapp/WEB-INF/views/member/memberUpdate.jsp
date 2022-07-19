<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>

<body>

	<form action="memberUpdateSubmit.do" name="" method="post">
		<label for="id">회원 아이디</label>
		<input type="text" name="id" id="id" value="${member.memberId}" readonly><br>

		<label for="nickname">회원 닉네임</label>
		<input type="text" name="nickname" id="nickname" value="${member.memberNick}"><br>

		<label for="email">회원 이메일</label>
		<input type="text" name="email" id="email" value="${member.memberEmail}"><br>

		<label for="git">Git 주소</label>
		<input type="text" name="git" id="git" value="${member.memberGit}"><br>
		
		<input type="submit" value="수정">
	</form>

</body>

</html>