<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
<script src="js/jquery-3.6.0.min.js"></script>
</head>


<body>
	<div align="center">
		<div><h1>공지사항 등록</h1></div>
		<div>
			<form id="frm" action="noticeBoardInsert.do" method="post" enctype="multipart/form-data">
				<div>
					<table border="1">
						<tr>
							<th width="150">게시판</th>
							<td width="200">
								<input readonly id="boardCategory" name="boardCategory" value="공지">
							</td>
							<th width="150">작성자</th>
							<td width="200">
								<input type="text" id="boardWriter" name="boardWriter">
							</td>
							<th width="150">임시 MemberNo</th>
							<td width="200">
								<input type="number" id="MemberNo" name="MemberNo">
							</td>
						</tr>
						<tr>
							<th>제목</th>
							<td colspan="3">
								<input type="text" size="73" id="boardTitle" name="boardTitle">
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td colspan="3">
								<textarea rows="6" cols="75" id="boardSubject" name="boardSubject"></textarea>
							</td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td colspan="3">
								<input type="file" id="file" name="file">
							</td>
							<td>
								<input type="hidden" type="text" id="boardHot" name="boardHot" value="">
								<input type="hidden" type="number" id="boardScrap" name="boardScrap" value="0">
								<input type="hidden" type="number" id="boardHit" name="boardHit" value="0">
								<input type="hidden" type="number" id="boardRecommend" name="boardRecommend" value="0">
							</td>
						</tr>
						
					</table>
				</div><br>
				<div>
					<input type="submit" value="저장">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="취소">
				</div>
			</form>
		</div>
	</div>
</body>
</html>