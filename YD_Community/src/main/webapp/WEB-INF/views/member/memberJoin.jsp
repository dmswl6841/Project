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
			<h1>${message }</h1>
		</div>
		<div>
			<input type="button" value="홈" onclick="location.href='main.do'">
			<input type="button" value="로그인"
				onclick="location.href='memberLoginForm.do'">
		</div>
	</div>
</body>
</html>
