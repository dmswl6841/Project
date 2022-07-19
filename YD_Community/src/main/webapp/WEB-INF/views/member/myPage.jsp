<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>

	<div align="center">
		<div>
			<h1>${member.memberNick }</h1>
		</div>
		<div>
			<input type="button" id="modify"
				onclick="location.href='memberUpdate.do?id=${member.memberId}'" value="내 정보 수정"><input
				type="button" id="joinout" onclick="joinoutCheck()" value="탈퇴">
		</div>
	</div>

	<script>
		function joinoutCheck() {
			let q = confirm("정말 탈퇴하시겠습니까? 이 행위는 되돌릴 수 없습니다.");
			if (q) {
				location.href="memberJoinout.do?id=${member.memberId}";
			} 
		}
	</script>
</body>

</html>