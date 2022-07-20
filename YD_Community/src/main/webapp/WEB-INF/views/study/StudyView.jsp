<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디게시글 보기</title>
<script src="js/jquery-3.6.0.min.js"></script>
</head>

<body>
	<div align="center">
		<div>
			<h1>스터디게시글 보기</h1>
		</div>

		<form name="writeFrm">
			<div>
				<table border="1" id="studyDel">
					<tbody>
						<c:choose>
							<c:when test="${not empty vo}">
							<input type="hidden" name="studyNo" value="${vo.studyNo}">
								<tr>
									<td>${vo.studyTitle }</td>
									<td>${vo.studySystem }</td>
									<td>${vo.studyPeriod }</td>
									<td>${vo.studyLanguage }</td>
								</tr>
								<tr>
									<td colspan="2">${vo.studyDate }</td>
									<td colspan="2">${vo.studyWriter }</td>
								</tr>

								<tr>
									<td colspan="4">${vo.studySubject }</td>
								</tr>
								<tr>
									<td colspan="2">${vo.studySystem }</td>
									<td colspan="2">${vo.studyAttech }</td>
								</tr>
								<c:if test="${vo.memberNo eq memberNo}">
									<tr>
										<td colspan="4">
											<button type="button" onclick="studyUpdate(${vo.studyNo })">수정</button>&nbsp;&nbsp;
											<button type="button" onclick="studyDelete(${vo.studyNo })">삭제</button>
										</td>
									</tr>
								</c:if>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="5" align="center">게시글이 존재하지 않습니다</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</form>

		<a href="StudyList.do" class="btn">목록</a><br>

	</div>


	<script type="text/javascript">
	// 삭제
		function studyDelete(id) {
			
			const xhr = new XMLHttpRequest();
			const url = "StudyDelete.do?studyNo=" + id;
			xhr.onload = function() {
				if (xhr.status >= 200 && xhr.status < 300) {
					if (xhr.response == 1) {
						alert("데이터가 삭제되었습니다.");
						location.href = "StudyList.do";
					} else {
						alert("데이터가 삭제되지 않았습니다.");
					}
				};					
			} 
			xhr.open('GET', url);
			xhr.send();
		}
		
	// 수정
		function studyUpdate(id) {
			var result = confirm("게시글을 수정하시겠습니까?");
			if (result) {
				var form = document.writeFrm;
				form.method = "post";
				form.action = "StudyUpdateForm.do";
				form.submit();
			}
		}
	</script>

</body>
</html>