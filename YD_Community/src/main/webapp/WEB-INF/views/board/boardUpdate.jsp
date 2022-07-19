<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<script src="js/jquery-3.6.0.min.js"></script>
</head>


<body>
	<div align="center">
		<div>
			<h1>게시글 수정</h1>
		</div>
		<div>

			<form id="frm" action="boardUpdate.do" method="post" enctype="multipart/form-data">
				<div>
					<table border="1">
						<tbody>
							<c:choose>
								<c:when test="${not empty vo}">
									<input type="hidden" name="boardNo" value="${vo.boardNo}">
									<tr>
										<th width="150">게시판</th>
										<td width="200"><input readonly id="boardCategory"
											name="boardCategory" value="${vo.boardCategory }"></td>
										<th width="150">작성자</th>
										<td width="200"><input readonly type="text"
											id="boardWriter" name="boardWriter"
											value="${vo.boardWriter }"></td>
										<th width="150">임시 MemberNo</th>
										<td width="200"><input type="number" id="MemberNo"
											name="MemberNo" value="${vo.memberNo }"></td>
									</tr>
									<tr>
										<th>제목</th>
										<td colspan="3"><input type="text" size="73"
											id="boardTitle" name="boardTitle" value="${vo.boardTitle }">
										</td>
									</tr>
									<tr>
										<th>내용</th>
										<td colspan="3"><textarea rows="6" cols="75"
												id="boardSubject" name="boardSubject">${vo.boardSubject }</textarea>
										</td>
									</tr>
									<tr>
										<th>첨부파일</th>
										<td colspan="3"><input type="file" id="file" name="file"></td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="8" align="center">게시글이 존재하지 않습니다</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
				<br>
				<div>
					<input type="submit" value="수정">&nbsp;&nbsp;&nbsp; <input
						type="reset" value="취소">
				</div>
			</form>


		</div>
	</div>
</body>
</html>