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
			<table border="1">
				<tbody>
					<c:choose>
						<c:when test="${not empty studyviewlist}">
							<c:forEach items="${studyviewlist }" var="b">
								<tr>
								<td id="studyNo" style="display:none">${b.studyNo }</td>
									<td>${b.studyTitle }</td>
									<td>${b.studySystem }</td>
									<td>${b.studyPeriod }</td>
									<td>${b.studyLanguage }</td>
								</tr>
								<tr>
									<td>${b.studyDate }</td>
									<td>${b.studyWriter }</td>
								</tr>

								<tr>
									<td colspan="5">${b.studySubject }</td>
								</tr>
								<tr>
									<td colspan="3">${b.studySystem }</td>
									<td>${b.studyAttech }</td>
								</tr>

								<input type="button" value="수정" onclick="doAction(0)">
								<input type="button" value="삭제" onclick="studyDelete(this)"
									name="studyNo">

							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="8" align="center">게시글이 존재하지 않습니다</td>
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
		function studyDelete(obj) {
			let boardId=$("#studyNo").text();			
			console.log(boardId);

			 const xhr = new XMLHttpRequest();
			const url = "StudyDelete.do?studyNo=" + boardId;
			console.log(url)
			xhr.onload = function() {

				if (xhr.status >= 200 && xhr.status < 300) {
					if (xhr.response == 1) {
						console.log("12");
					} else {
						errorCallback(new Error(xhr.stautsText));
					}
				}
				;

				xhr.open('GET', url);
				console.log(xhr.open('GET', url));
				xhr.send();
			} 

		}
	</script>

</body>
</html>