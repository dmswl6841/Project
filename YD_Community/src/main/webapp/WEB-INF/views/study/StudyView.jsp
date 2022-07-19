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


		<div>
			<table border="1" id="studyDel">
				<tbody>
					<c:choose>
						<c:when test="${not empty vo}">
							<tr>
								<td>${vo.studyTitle }</td>
								<td>${vo.studySystem }</td>
								<td>${vo.studyPeriod }</td>
								<td>${vo.studyLanguage }</td>
							</tr>
							<tr>
								<td colspan="2">${vo.studyDate }</td>
								<td colspan="2"> ${vo.studyWriter }</td>
							</tr>

							<tr>
								<td colspan="4">${vo.studySubject }</td>
							</tr>
							<tr>
								<td colspan="2">${vo.studySystem }</td>
								<td colspan="2">${vo.studyAttech }</td>
							</tr>
							<tr>
								<td colspan="4">
									<button type="button">수정</button>&nbsp;&nbsp;
									<button type="button" onclick="studyDelete(${vo.studyNo })">삭제</button>
								</td>	
							</tr>						
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="5" align="center">게시글이 존재하지 않습니다</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<a href="StudyList.do" class="btn">목록</a>
		</div>
		<br>

	</div>


	<script type="text/javascript">
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
	</script>

</body>
</html>