<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>게시글쓰기</title>
<style>
.form{
width: auto;
height: auto;
}
</style>

</head>

<body>

	
	<!-- ---------게시판글쓰기양식-------- -->
		
	<div align="center">
		<div>
			<h1>게시글 등록</h1>
		</div>
		<div class ="form">
			<form id="frm" action="StudyInsert.do" method="post"
				enctype="multipart/form-data">
				<div>
					<table border="1">
						<tr>
							<th width="150">작성자</th>
							<td width="800"><input type="text" id="studyWriter"
								name="studyWriter"></td>
							<th width="150">제목</th>
							<td width="200"><input type="text" id="studyTitle"
								name="studyTitle"></td>
						</tr>
						
						<tr>
							<th>내용</th>
							<td colspan="5">
							<textarea rows="30" cols="30" size="30" id="studySubject" name="studySubject"></textarea></td>
						</tr>

						<tr>
							<th width="150">사용언어</th>
							<td width="200"><input type="text" id="studyLanguage"
								name="studyLanguage"></td>
						</tr>
						
						<tr>
							<th width="150">스터디개월수</th>
							<td width="200"><input type="text" id="studyPeriod"
								name="studyPeriod"></td>
							<th width="150">스터디방식</th>
							<td width="200"><input type="text" id="studySystem"
								name="studySystem"></td>
								
						</tr>
						
						<tr>
							<th width="150">스터디멤버수</th>
							<td width="200"><input type="text" id="studyMember"
								name="studyMember"></td>
						</tr>
						
						<tr>
							<th width="150">멤버넘버</th>
							<td width="200"><input type="text" id="memberNo"
								name="memberNo"></td>
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
					<input type="submit" value="저장"> &nbsp;&nbsp;&nbsp; 
					<input type="reset" value="취소">
				</div>
				
			</form>
	</div>
	</div>
</body>
</html>