<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>게시글쓰기</title>

</head>
<style>
select {
	width: 500px;
	height: 50px;
	border-radius: 5px;
}

option {
	font-family: 'Courier New', Courier, monospace;
	font-size: 20px;
}

input {
	width: 500px;
	height: 50px;
	border-radius: 5px;
}

span {
	margin-right: 8px;
	width: 40px;
	height: 40px;
	display: inline-block;
	text-align: center;
	border-radius: 50%;
	background: #ffcd00;
	font-size: 30px;
	font-weight: 700;
	line-height: 19px;
	color: #fff;
	margin-top: 5px;
}

h1 {
	display: block;
	font-size: 1.5em;
	margin-block-start: 0.83em;
	margin-block-end: 0.83em;
	margin-inline-start: 0px;
	margin-inline-end: 0px;
	font-weight: bold;
}
</style>
<body>
	
	<!-- ---------게시판글쓰기양식-------- -->
	<form id="main">
		<div align="left">
			<div>

				<h1>
					<span>1</span>프로젝트 기본 정보를 입력해주세요.
				</h1>
				<hr style="margin-bottom: 15px;">

	
	<div align="center">
		<div>
			<h1>게시글 등록</h1>
		</div>
		<div>
			<form id="frm" action="StudyInsert.do" method="post"
				enctype="multipart/form-data">
				<div>
					<table border="1">
						<tr>
							<th width="150">작성자</th>
							<td width="200"><input type="text" id="studyWriter"
								name="studyWriter"></td>
							<th width="150">제목</th>
							<td width="200"><input type="text" id="studyWriter"
								name="studyWriter"></td>
						</tr>
						
						<tr>
							<th>내용</th>
							<td colspan="3">
							<textarea rows="75" cols="160" size="73" id="studySubject" name="studySubject"></textarea></td>
						
						</tr>

						<tr>
							<th width="150">작성일자</th>
							<td width="200"><input type="date" id="studyWriter"
								name="studyWriter"></td>
							<th width="150">사용언어</th>
							<td width="200"><input type="text" id="studyWriter"
								name="studyWriter"></td>
						</tr>
						
						<tr>
							<th width="150">스터디개월수</th>
							<td width="200"><input type="text" id="studyWriter"
								name="studyWriter"></td>
							<th width="150">스터디방식</th>
							<td width="200"><input type="text" id="studyWriter"
								name="studyWriter"></td>
						</tr>
						
						
						<tr>
							<th width="150">스터디멤버수</th>
							<td width="200"><input type="text" id="studyWriter"
								name="studyWriter"></td>
						</tr>
						
						<tr>
							<th>첨부파일</th>
							<td colspan="3"><input type="file" id="file" name="file">
							</td>
						</tr>
					</table>
				</div>
				<br>
				<div>

					<input type="submit" value="저장"> &nbsp;&nbsp;&nbsp; <input
						type="reset" value="취소">
				</div>
			</form>
		</div>

	</div>
</body>
</html>