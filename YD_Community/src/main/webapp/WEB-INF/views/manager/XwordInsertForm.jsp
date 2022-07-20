<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>금지어 추가</title>
</head>
<body>
	<div><h3>금지어 추가</h3></div>
	<form id="frm" action="XwordInsert.do" method="post">
		<div>
			<table border="1">
				<tr>금지어 입력</tr>
				<input type="text" id="Xword" name="Xword">
				<input type="submit" value="저장">
			</table>
		</div>
	</form>
</body>
</html>