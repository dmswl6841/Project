<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div align="center">
		<div>
			<h1>게시글 수정</h1>
		</div>
		<div>

			<form id="frm" action="StudyUpdate.do" method="post"
				enctype="multipart/form-data">
				<div>
					<table border="1">
						<tbody>
							<c:choose>
								<c:when test="${not empty vo}">
									<input type="hidden" name="studyNo" value="${vo.studyNo}">
									<tr>
										<td><input type="text" size="73" id="studyTitle"
											name="studyTitle" value="${vo.studyTitle }"></td>
										<td><input type="text" size="73" id="studySystem"
											name="studySystem" value="${vo.studySystem }"></td>
										<td><input type="text" size="73" id="studyPeriod"
											name="studyPeriod" value="${vo.studyPeriod }"></td>
										<td><input type="text" size="73" id="studyLang"
											name="studyLang" value="${vo.studyLanguage }"></td>
									</tr>
									<tr>
										<td colspan="2">${vo.studyDate }</td>
										<td colspan="2">${vo.studyWriter }</td>
									</tr>

									<tr>
										<td colspan="4"><input type="text" size="73"
											id="studySubject" name="studySubject"
											value="${vo.studySubject }"></td>
									</tr>
									<tr>
										<td colspan="2"><input type="text" size="73"
											id="studySystem" name="studySystem"
											value="${vo.studySystem }"></td>
										<td colspan="2"><input type="text" size="73"
											id="studyAttech" name="studyAttech"
											value="${vo.studyAttech }"></td>
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